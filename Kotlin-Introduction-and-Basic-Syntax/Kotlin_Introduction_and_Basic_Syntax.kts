import kotlin.math.pow

// Задача 1
val a = 5
val b = 6.25
val c = 4.5
val d = 5.5
val h = 3.825

perimTrap(a,b,c,d)
areaTrap(a,b,h)

fun perimTrap(a: Int, b: Double, c: Double, d: Double): Double {
    return a + b + c + d
}

fun areaTrap(a: Int, b: Double, h: Double): Double {
    return (a + b) * h / 2
}

//______________________________________________________________

// Задача 2

val r: Double = 12.755
val pi: Double = 3.141592653589793

circumference(r)
areaCircle(r)

fun circumference(r: Double): Double {
    return 2 * pi * r
}

fun areaCircle(r: Double): Double {
    return pi * r.pow(2)
}