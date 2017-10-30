@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import java.lang.Math.*
import lesson1.task1.discriminant
import lesson3.task1.digitNumber

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var notAbs = 0.0
    for (element in v)
        notAbs += element * element
    return sqrt(notAbs)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var middleEl = 0.0
    if (list.isEmpty()) return 0.0
    for (i in 0 until list.size)
        middleEl += list[i]
    return middleEl / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    val middleEl = mean(list)
    for (i in 0 until list.size)
        list[i] -= middleEl
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    if (a.isEmpty()) return 0.0
    else {
        var VecSum = 0.0
        for (i in 0 until a.size)
            VecSum += a[i] * b[i]
        return VecSum
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var thing = 0.0
    return when (p.isEmpty()) {                                           //выглядит глупо, но я решил попробовать что-то новое
        true -> thing
        false -> {
            for (i in 0 until p.size)
                thing += p[i] * pow(x, i.toDouble())
            thing
        }
    }
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (!list.isEmpty()) {
        for (i in list.size - 1 downTo 1)
            for (j in 0 until i)
                list[i] += list[j]
    }
    return list
}


/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var newN = n
    var i = 2
    val digList = mutableListOf<Int>()
    while (newN != 1) {
        if (newN % i.toDouble() == 0.0) {
            digList.add(i)
            newN /= i
        } else i++
    }
    return digList
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var newN = n
    val baseNum = mutableListOf<Int>()
    do {
        baseNum.add(0, newN % base)
        newN /= base
    } while (newN != 0)
    return baseNum
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base)
    var stringNum = ""
    for (i in 0 until list.size) {
        if (list[i] > 9)
            stringNum += (list[i] + 87).toChar().toString()
        else stringNum += list[i]
    }
    return stringNum
}


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var j = 0
    var finNum = 0
    for (i in digits.size - 1 downTo 0) {
        finNum += digits[i] * pow(base.toDouble(), j.toDouble()).toInt()
        j++
    }
    return finNum
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var finNum = 0
    var j = 0
    for (i in str.length - 1 downTo 0) {
        if ((str[i] - 48).toInt() > 9)
            finNum += (str[i].toInt() - 87) * pow(base.toDouble(), j.toDouble()).toInt()
        else finNum += (str[i] - 48).toInt() * pow(base.toDouble(), j.toDouble()).toInt()
        j++
    }
    return finNum
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var newN = n
    var romNum = ""
    val rom = listOf<String>("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val arab = listOf<String>("1", "4", "5", "9", "10", "40", "50", "90", "100", "400", "500", "900", "1000")
    var i = 12
    while (newN != 0) {
        while (arab[i].toInt() <= newN) {
            newN -= arab[i].toInt()
            romNum += rom[i]
        }
        i--
    }
    return romNum
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val units = listOf<String>("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    var numSize = digitNumber(n)
    var strNum = ""
    while (numSize > 0) {
        when (numSize) {
            1 -> if (n / pow(10.0, numSize - 1.0).toInt() % 10 != 0)
                strNum += units[n / pow(10.0, numSize - 1.0).toInt() % 10 - 1] + " "
            2 -> {
                strNum += dozens(strNum, n, numSize, units)
                if (n / pow(10.0, numSize - 1.0).toInt() % 10 == 1) numSize--
            }
            3 -> strNum += hundreds(strNum, n, numSize, units)
            4 -> strNum += thousands(strNum, n, numSize, units)
            5 -> strNum += dozens(strNum, n, numSize, units)
            6 -> strNum += hundreds(strNum, n, numSize, units)
        }
        numSize--
    }
    return strNum.trim()
}

fun thousands(strNum: String, n: Int, numSize: Int, units: List<String>): String {
    val number = n / pow(10.0, numSize - 1.0).toInt() % 10
    return if (n / pow(10.0, numSize.toDouble()).toInt() % 10 != 1)
        when (number) {
            0 -> "тысяч "
            1 -> "одна тысяча "
            2 -> "две тысячи "
            3 -> "три тысячи "
            4 -> "четыре тысячи "
            else -> units[number - 1] + " тысяч "
        }
    else "тысяч "
}

fun hundreds(strNum: String, n: Int, numSize: Int, units: List<String>): String {
    val number = n / pow(10.0, numSize - 1.0).toInt() % 10
    return when (number) {
        0 -> ""
        1 -> "сто "
        2 -> "двести "
        in 3..4 -> units[number - 1] + "ста "
        else -> units[number - 1] + "сот "
    }
}

fun dozens(strNum: String, n: Int, numSize: Int, units: List<String>): String {
    val number = n / pow(10.0, numSize - 1.0).toInt() % 10
    when (number) {
        0 -> return ""
        1 -> return if (n / pow(10.0, numSize - 2.0).toInt() % 10 != 0) {
            val nextNum = n / pow(10.0, numSize - 2.0).toInt() % 10
            when (nextNum) {
                1 -> "одиннадцать "
                2 -> "двенадцать "
                3 -> "тринадцать "
                4 -> "четырнадцать "
                else -> units[nextNum - 1].substring(0, units[nextNum - 1].length - 1) + "надцать "
            }
        } else {
            "десять "
        }
        in 2..3 -> return units[number - 1] + "дцать "
        4 -> return "сорок "
        9 -> return "девяносто "
        else -> return units[number - 1] + "десят "
    }
}