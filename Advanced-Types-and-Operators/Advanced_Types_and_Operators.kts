var evenCounter = 0
var oddCounter = 0
var primeCounter = 0
val primeSet: MutableList<Int> = ArrayList()

for (element in 2..100) {
    if (element % 2 == 0) evenCounter++
    else oddCounter++

    //алгоритъм за намиране дали дадено число е просто
    var i = 2
    var isPrime = true
    while (i <= element / 2) {
        if (element % i == 0) {
            isPrime = false
            break
        }
        ++i
    }
    if (isPrime) {
        primeCounter++
        primeSet.add(element)
    }
}
println("\nЧетни: $evenCounter, Нечетни: $oddCounter, Прости: $primeCounter")
primeSet