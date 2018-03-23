import kotlin.math.pow

class Vector(val x: Double, val y: Double)

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

}

