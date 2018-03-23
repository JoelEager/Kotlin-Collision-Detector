fun main(args: Array<String>) {
    val iterations = args.getOrNull(0)?.toIntOrNull()

    if (iterations == null) {
        println("Usage:\njava <kotlin args> MainKt <iterations>")
        return
    }

    println("Benchmarking sat.hasCollided() using a shell and tank...")
    println("Using $iterations iterations\n")

    println("Time with maxDist: ${runTest(iterations, 15.56)}")
    println("Time without:      ${runTest(iterations)}")
}

fun runTest(iterations: Int, maxDist: Double?=null): Double {
    val startTime = System.currentTimeMillis()

    for (count in 0..iterations) {
        val tank = arrayOf(Vector(195.0, 95.0), Vector(205.0, 95.0), Vector(205.0, 105.0), Vector(195.0, 105.0))
        val shell = arrayOf(Vector(99.5, 99.5), Vector(100.5, 99.5), Vector(100.5, 100.5), Vector(99.5, 100.5))

        while (!hasCollided(tank, shell, maxDist)) {
            // Move the shell
            for (vector in shell) {
                vector.x += 1.0;
            }
        }
    }

    return (System.currentTimeMillis() - startTime) / 1000.0
}