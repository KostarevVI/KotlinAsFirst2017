@file:Suppress("UNUSED_PARAMETER")

package lesson5.task1

import java.lang.Math.*

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    val month = listOf("января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val parts = str.split(" ")
    return try {
        if (parts.size == 3 && parts[1] in month)
            String.format("%02d.%02d.%d", parts[0].toInt(), month.indexOf(parts[1]) + 1, parts[2].toInt())
        else ""
    } catch (e: NumberFormatException) {
        ""
    }
}

/***
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val month = listOf("января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val parts = digital.split(".")
    return try {
        if (parts.size == 3 && parts[1].toInt() > 0) {
            String.format("%d %s %s", parts[0].toInt(), month[parts[1].toInt() - 1], parts[2])
        } else ""
    } catch (e: NumberFormatException) {
        ""
    }
}

/***
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String = when {
    "[^0-9\\-()+\\s]".toRegex().find(phone) != null -> ""
    "^+".toRegex().find(phone) != null -> phone.filter { it !in listOf('-', ' ', '(', ')', '\n') }
    else -> ""
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var bestJump = -1
    val allJumps = jumps.split(" ")
    try {
        for (i in 0 until allJumps.size)
            if (allJumps[i].trim().isNotEmpty())
                if (allJumps[i] !in listOf("-", "%") && allJumps[i].toInt() >= 0 && allJumps[i].toInt() > bestJump)
                    bestJump = allJumps[i].toInt()
    } catch (e: NumberFormatException) {
        return -1
    }
    return bestJump
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var bestJump = -1
    val allJumps = jumps.split(" ")
    try {
        for (i in 0 until allJumps.size - 1 step 2)
            if ("+" in allJumps[i + 1] && allJumps[i].toInt() >= 0 && allJumps[i].toInt() > bestJump)
                bestJump = allJumps[i].toInt()
    } catch (e: NumberFormatException) {
        return -1
    }
    return bestJump
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val strExp = expression.split(" ")
    var number: Int
    try {
        number = strExp[0].toInt()
        for (i in 1 until strExp.size - 1 step 2) {
            when (strExp[i]) {
                "+" -> number += strExp[i + 1].toInt()
                "-" -> number -= strExp[i + 1].toInt()
            }
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
    return number
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {

    val newStr = str.toLowerCase().split(" ")
    for (i in 0 until newStr.size - 1) {
        if (newStr[i] == newStr[i + 1]) {
            var j = 0
            for (k in 0 until i)
                j += newStr[k].length + 1
            return j
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    val listDesc = description.split("; ")
    var maxValue = -1.0
    var word = ""
    for (i in 0 until listDesc.size) {
        val listSplit = listDesc[i].split(" ")
        if (listSplit.size > 1 && listSplit[1].toDouble() > maxValue) {
            maxValue = listSplit[1].toDouble()
            word = listSplit[0]
        }
    }
    return word
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val rom = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val arab = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    var i = 0
    var num: Int
    var result = 0
    if (roman.isNotEmpty()) {
        while (i <= roman.length - 1) {
            if (roman[i].toString() in rom) {
                when (roman[i]) {
                    'I' -> if (i < roman.length - 1) {
                        when (roman[i + 1]) {
                            'V' -> {
                                num = arab[rom.indexOf(roman[i].toString()) + 1]
                                i++
                            }
                            'X' -> {
                                num = arab[rom.indexOf(roman[i].toString()) + 3]
                                i++
                            }
                            else -> num = arab[rom.indexOf(roman[i].toString())]
                        }
                    } else num = arab[rom.indexOf(roman[i].toString())]
                    'X' -> if (i < roman.length - 1) {
                        when (roman[i + 1]) {
                            'L' -> {
                                num = arab[rom.indexOf(roman[i].toString()) + 1]
                                i++
                            }
                            'C' -> {
                                num = arab[rom.indexOf(roman[i].toString()) + 3]
                                i++
                            }
                            else -> num = arab[rom.indexOf(roman[i].toString())]
                        }
                    } else num = arab[rom.indexOf(roman[i].toString())]
                    'C' -> if (i < roman.length - 1) {
                        when (roman[i + 1]) {
                            'D' -> {
                                num = arab[rom.indexOf(roman[i].toString()) + 1]
                                i++
                            }
                            'M' -> {
                                num = arab[rom.indexOf(roman[i].toString()) + 3]
                                i++
                            }
                            else -> num = arab[rom.indexOf(roman[i].toString())]
                        }
                    } else num = arab[rom.indexOf(roman[i].toString())]
                    else -> num = arab[rom.indexOf(roman[i].toString())]
                }
            } else {
                return -1
            }
            i++
            result += num
        }
    } else return -1
    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var steps = 0
    var pos = floor(cells / 2.0).toInt()
    val cellsList = MutableList(cells) { 0 }
    val brPosList = mutableListOf<Int>()
    var br = 0
    var i = 0
    try {
        //проверка на непарные скобки
        while (i < commands.length) {
            when (commands[i]) {
                '[' -> br++
                ']' -> br--
            }
            if (br < 0)
                throw IllegalArgumentException()
            i++
        }
        if (br != 0)
            throw IllegalStateException()
        //основной цикл
        i = 0
        while (i < commands.length && steps < limit) {
            when (commands[i]) {
                '+' -> {
                    cellsList[pos]++
                    i++
                }
                '-' -> {
                    cellsList[pos]--
                    i++
                }
                '>' -> {
                    pos++
                    if (pos < cells)
                        i++
                    else
                        throw IllegalStateException()
                }
                '<' -> {
                    pos--
                    if (pos >= 0)
                        i++
                    else
                        throw IllegalStateException()
                }
                ' ' -> {
                    i++
                }
                '[' -> if (cellsList[pos] == 0) {
                    var brCount = 1
                    while (brCount > 0) {
                        i++
                        when (commands[i]) {
                            '[' -> brCount++
                            ']' -> brCount--
                        }
                        steps++
                    }
                    steps--
                    i++
                } else {
                    i++
                    brPosList.add(i)
                }
                ']' -> if (cellsList[pos] != 0) {
                    i = brPosList.last()
                } else {
                    i++
                    brPosList.removeAt(brPosList.size - 1)
                }
            }
            steps++
        }
    } catch (e: Exception) {
        when (e) {
            is IllegalArgumentException, is IllegalStateException ->
                throw IllegalArgumentException()
        }
    }
    return cellsList
}
