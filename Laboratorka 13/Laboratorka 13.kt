fun main()
{
    println("Hello world")
}

fun main(args: Array<String>)
{
    val s = readLine()
    println("Hello, $s")
}

fun main(args: Array<String>)
{
    println("Какой язык у вас любимый?")
    val language = readLine()
    when (language)
    {
        "Kotlin" -> println("Ясно, подлиза")
        "Prolog" -> println("Ясно, подлиза")
        else -> println("Ничоси :О")
    }
}

import java.util.Scanner

//Сумма цифр числа
fun sumDigits(digit:Int):Int =  if (digit != 0) digit%10 + sumDigits(digit/10) else 0
//Минимальная цифра числа
fun minDigit(digit:Int, curMin:Int):Int = if (digit != 0) if (digit%10 < curMin) minDigit(digit/10,digit%10)
                                                          else minDigit(digit/10, curMin) else curMin
//Максимальная цифра числа
fun maxDigit(digit:Int, curMax:Int):Int = if (digit != 0) if (digit%10 > curMax) maxDigit(digit/10,digit%10)
                                                          else maxDigit(digit/10, curMax) else curMax
//Произведение цифр числа
fun multDigits(digit:Int):Int =  if (digit != 0) digit%10 * multDigits(digit/10) else 1

fun main()
{
    val scanner = Scanner(System.`in`)
    print("Введите число: ")
    val digit = scanner.nextInt()

    print("Сумма цифр числа: ")
    println(sumDigits(digit))

    print("Минимальная цифра числа: ")
    println(minDigit(digit,digit%10))

    print("Максимальная цифра числа: ")
    println(maxDigit(digit,digit%10))

    print("Произведение цифр числа: ")
    println(multDigits(digit))
}
