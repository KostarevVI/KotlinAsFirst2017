@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.*

import kotlin.coroutines.experimental.suspendCoroutine

/*fun lenght(n:Int):Int{
    var i = 0
    var newN = n
    while(newN>0){
        newN/=10
        i++
    }
    return i
}*/

fun simpleNum(n: Int, m: Int): Int {
    var a = n
    var b = m
    var c: Int
    while (b != 0) {
        c = a % b
        a = b
        b = c
    }
    return abs(a)
}

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
    var c: Int
    for (i in 2..n) {
        c = firstF
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
    var number1: Int = m
    var number2: Int = n
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
    when (simpleNum(n, m)) {
        1 -> return true
        else -> return false
    }
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {

    for (i in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt()) {
        if (i * i in m..n) {
            return true
        }
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var newNumber = ""
    for (i in n.toString().length - 1 downTo 0) {
        newNumber += n.toString()[i]
    }
    return newNumber.toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = TODO()
/*{
    var firstP = ""
    var secondP = ""
    var initLine = n.toString()
    var halfLen: Int
    if (initLine.length % 2 == 0)
        halfLen = initLine.length / 2
    else halfLen = (initLine.length - 1) / 2
    for (i in 0 until halfLen - 1) {
        firstP += initLine[i]
    }
    for (i in initLine.length - 1 downTo initLine.length - halfLen) {
        secondP += initLine[i]
    }
    if (firstP == secondP) return true
    else return false
}*/
//^
//|
/*org.opentest4j.AssertionFailedError:
at org.junit.jupiter.api.AssertionUtils.fail(AssertionUtils.java:48)
at org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:54)
at org.junit.jupiter.api.AssertTrue.assertTrue(AssertTrue.java:33)
at org.junit.jupiter.api.Assertions.assertTrue(Assertions.java:110)
at lesson3.task1.Tests.isPalindrome(Tests.kt:185)
at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
at java.lang.reflect.Method.invoke(Method.java:498)
at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:389)
at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:115)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:167)
at org.junit.jupiter.engine.execution.ThrowableCollector.execute(ThrowableCollector.java:40)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:163)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:110)
at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:57)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$execute$3(HierarchicalTestExecutor.java:83)
at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:77)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$null$2(HierarchicalTestExecutor.java:92)
at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
at java.util.Iterator.forEachRemaining(Iterator.java:116)
at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$execute$3(HierarchicalTestExecutor.java:92)
at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:77)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$null$2(HierarchicalTestExecutor.java:92)
at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
at java.util.Iterator.forEachRemaining(Iterator.java:116)
at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.lambda$execute$3(HierarchicalTestExecutor.java:92)
at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:77)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:51)
at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:43)
at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:170)
at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:154)
at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:90)
at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:62)
at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)*/
//Я понимаю, что вы не экстраненс, но что это значит?

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
