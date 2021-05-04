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

fun main()
{
    val scanner = Scanner(System.`in`)
    print("Введите число: ")
    val digit = scanner.nextInt()
    println(sumDigits(digit))
}
