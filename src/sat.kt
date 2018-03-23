import kotlin.math.pow

class Vector(var x: Double, var y: Double)

/**
 * @param poly1, poly2 The two polygons described as arrays of points as Vectors
 *  Note: The points list must go in sequence around the polygon
 * @param maxDist The maximum distance between any two points of any two polygons that can be touching
 *  If this null then the optimization check that uses it will be skipped
 */
fun hasCollided(poly1: Array<Vector>, poly2: Array<Vector>, maxDist: Double?=null): Boolean {
    // Do an optimization check using the maxDist
    if (maxDist != null) {
        if ((poly1[1].x - poly2[0].x).pow(2) + (poly1[1].y - poly2[0].y).pow(2) <= maxDist.pow(2)) {
            // Collision is possible so run SAT on the polys
            return runSAT(poly1, poly2)
        } else {
            return false
        }
    } else {
        // No maxDist so run SAT on the polys
        return runSAT(poly1, poly2)
    }
}

fun runSAT(poly1: Array<Vector>, poly2: Array<Vector>): Boolean {
    // Implements the actual SAT algorithm
    val edges = polyToEdges(poly1) + polyToEdges(poly2)
    val axes = Array(edges.size, { index -> orthogonal(edges[index])})

    for (axis in axes) {
        if (!overlap(project(poly1, axis), project(poly2, axis))) {
            // The polys don't overlap on this axis so they can't be touching
            return false
        }
    }

    // The polys overlap on all axes so they must be touching
    return true
}

/**
 * Returns a vector going from point1 to point2
 */
fun edgeVector(point1: Vector, point2: Vector): Vector {
    return Vector(point2.x - point1.x, point2.y - point1.y)
}

/**
 * Returns an array of the edges of the poly as vectors
 */
fun polyToEdges(poly: Array<Vector>): Array<Vector> {
    return Array(poly.size, { index -> edgeVector(poly[index], poly[(index + 1) % poly.size]) })
}

/**
 * Returns a new vector which is orthogonal to the given vector
 */
fun orthogonal(vector: Vector): Vector {
    return Vector(vector.y, -vector.x)
}

/**
 * Returns the dot (or scalar) product of the two vectors
 */
fun dotProduct(vector1: Vector, vector2: Vector): Double {
    return vector1.x * vector2.x + vector1.y * vector2.y;
}

/**
 * Returns a vector showing how much of the poly lies along the axis
 */
fun project(poly: Array<Vector>, axis: Vector): Vector {
    val dots = Array(poly.size, { index -> dotProduct(poly[index], axis) })
    return Vector(dots.min()!!, dots.max()!!)
}

/**
 * Returns a boolean indicating if the two projections overlap
 */
fun overlap(projection1: Vector, projection2: Vector): Boolean {
    return projection1.x <= projection2.y && projection2.x <= projection1.y
}