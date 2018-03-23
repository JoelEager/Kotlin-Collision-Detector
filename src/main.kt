fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage:\nkotlin-collision-detector <iterations>")
        return
    }

    val iterations = args[0].toInt()
    val tank = arrayOf(Vector(195.0, 95.0), Vector(205.0, 95.0), Vector(205.0, 105.0), Vector(195.0, 105.0))
    var shell = arrayOf(Vector(99.5, 99.5), Vector(100.5, 99.5), Vector(100.5, 100.5), Vector(99.5, 100.5))

    println("Result: ${hasCollided(tank, shell)}")
}