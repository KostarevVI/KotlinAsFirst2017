@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.*


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var counter = 0
    var seed = abs(n)
    do {
        seed /= 10
        counter++
    } while (seed > 0)
    return counter
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var firstF = 1
    var secondF = 0
    for (i in 2..n) {
        val c = firstF
        firstF += secondF
        secondF = c
    }
    return firstF
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var number1 = m
    var number2 = n
    while (number1 != number2) {
        if (number1 > number2) number1 -= number2
        else number2 -= number1
    }
    return m * n / number1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var minD = 2
    while (n % minD != 0) minD++
    return minD
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxD = n - 1
    while (n % maxD != 0) maxD--
    return maxD
}

/**
 * Простая
 *
 * Оределить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.п
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val maxVal = max(m, n)
    for (i in 2..maxVal) {
        if (m % i == 0 && n % i == 0) {
            return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    when {
        sqrt(m.toDouble()) % 1.0 == 0.0 -> return true
        sqrt(n.toDouble()) % 1.0 == 0.0 -> return true
        floor(sqrt(m.toDouble())) != floor(sqrt(n.toDouble())) -> return true
        else -> return false
    }
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var i = -1
    var j = 3.0
    val newX = x % (2 * PI)
    var memberAll = newX
    do {
        val member = i * pow(newX, j) / factorial(j.toInt())
        i *= -1
        j += 2.0
        memberAll += member
    } while (abs(member) >= eps)
    return memberAll
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var i = -1
    var j = 2.0
    val newX = x % (2 * PI)
    var memberAll = 1.0
    do {
        val member = i * pow(newX, j) / factorial(j.toInt())
        i *= -1
        j += 2.0
        memberAll += member
    } while (abs(member) >= eps)
    return memberAll
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */

fun revert(n: Int): Int {
    var newNum = 0
    for (i in 1..digitNumber(n)) {
        val singleNum = n / pow(10.0, i - 1.0).toInt() % 10
        newNum += singleNum * pow(10.0, digitNumber(n) - i.toDouble()).toInt()
    }
    return newNum
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    for (i in 1 until digitNumber(n)) {
        if (n / pow(10.0, i - 1.0).toInt() % 10 != n / pow(10.0, i.toDouble()).toInt() % 10)
            return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var numDigits = 1
    var sqrNum = 1
    while (numDigits < n) {
        sqrNum++
        numDigits += digitNumber(sqrNum * sqrNum)
    }
    return (sqrNum * sqrNum) / pow(10.0, numDigits - n.toDouble()).toInt() % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var numDigits = 0
    var i = 1
    var fibNum: Int
    do {
        fibNum = fib(i)
        numDigits += digitNumber(fibNum)
        i++
    } while (numDigits < n)
    return fibNum / pow(10.0, numDigits - n.toDouble()).toInt() % 10
}


