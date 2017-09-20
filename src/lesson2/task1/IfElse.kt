@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*
/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var word: String
    word = age.toString();
        if (age % 10 in 5..9 || age % 10 == 0 || age % 100 in 11..19)
            word += " лет"
    else
        if (age % 10 == 1)
            word += " год"
        else word += " года"
    return word
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double
        {
            var halfTime: Double = 0.0
            var s1 = t1*v1
            var s2 = t2*v2
            var s3 = t3*v3

            var halfS = (s1+s2+s3)/2
            if (halfS<=s1)
                halfTime = halfS/v1
            else if (halfS>s1 && halfS<=s2+s1)
                halfTime = t1 + ((halfS -s1)/v2)
            else if (halfS>s2+s1)
                halfTime = t1 + t2 + ((halfS - s1 - s2)/v3)
            return halfTime
        }

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int
{
    var who:Int = 0
    if (rookX1 == kingX || rookY1 == kingY) who = 1
    if (rookX2 == kingX || rookY2 == kingY)
        if (who == 1) who = 3
            else who = 2
    return who
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int):Int
{
    var who:Int = 0
    if (rookX == kingX || rookY == kingY) who = 1
    if (abs(kingX - bishopX) == abs(kingY - bishopY))
        if (who == 1) who = 3
        else who = 2
    return who
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int
{
    var answer:Int = -1
    var max:Double = 0.0
    if (a>=b && a>=c)
        max = a
    else if (b>=a && b>=c)
        max = b
    else if (c>=a && c>=b)
        max = c

    if (a+b>c && a+c>b && b+c>a)
        if (max*max<a*a+b*b+c*c-max*max)
            answer = 0
        else if (max*max==a*a+b*b+c*c-max*max)
            answer = 1
        else if (max*max>a*a+b*b+c*c-max*max)
            answer = 2
    return answer
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int):Int
{
    var answer:Int = -1
    if (abs(c-b)<abs(d-a) && b<c)
            answer = -1
    else if (abs(c-b)>abs(d-a) && d<a)
            answer = -1
    else if (b==c || a==d) answer=0
    else if (c>a && d<b) answer = d-c
    else if (a>c && b<d) answer = b-a
    else if (c>a && b<d) answer = b-c
    else if (a>c && d<b) answer = d-a
    return answer
}
