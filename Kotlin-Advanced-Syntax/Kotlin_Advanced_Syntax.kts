fun initialFunction(
    a: Int,
    functionOne: (b: Int) -> String,
    functionTwo: (c: Int) -> String,
): String{
    return if(a%3==0) functionOne(a) // ако дадено число се дели на 3 се вкарва в първата функция
    else functionTwo(a) // в противен случай се вкарва във втората
}

val fN = fun(b: Int): String {
    val isInside = arrayOf(2,3,4,5,6,12,13,14,15)
    val forYes = isInside.filter { it == b } // горния масив се филтрира само със стойностите от първата функция
    return if (forYes.toString() == "[]") "NO" // ако числото липсва
    else "YES" // ако числото е част и от функцията && от горния масив
}

for (element in 0..20){ // тестват се числата от 0 до 20
    println(initialFunction(element, fN) { c -> // първата функция се вкарва като отделна променлива, а втората - като ламбда
        val isInside = arrayOf(0,2,4,6,10,11,12,13) // task array
        val forYes = isInside.filter { it == c } // горния масив се филтрира само със стойностите от втората функция
        return@initialFunction if (forYes.toString() == "[]") "Skipped (part of functionOne or not part of task array)"
        else findDivisors(c) // ако числото е част и от функцията и от горния масив се стартира функция за намиране на делители
    })
}

fun findDivisors(n: Int): String {
    val devisorsArray: MutableList<Int> = ArrayList() // създаване на масив, в който да се вкарват
    print("Devisors of $n: ")
    // намиране на всеки делител
    for (i in 1..n) {
        // взима се числото n и се дели с всяко число от 1 до себе си и при точно делене се слага в масива
        if (n % i == 0) devisorsArray.add(i)
    }
    // принтиране на всеки делител от масива
    devisorsArray.forEach {
        print("$it ")
    }
    // врътка за избегване на kotlin.Unit в принтирането
    return ""
}
