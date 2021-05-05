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

//Количество делителей числа, не делящихся на 3
fun numOfDel(digit: Int, del:Int):Int = if(digit != del) if (digit % del == 0 && del % 3 != 0) 1 + numOfDel(digit, del + 1)
                                                         else numOfDel(digit, del + 1) else 1

//Минимальная нечетная цифра числа
fun minOddDigit(digit:Int, curMin:Int):Int = if (digit != 0) if (curMin == 0)
        if(digit%10 % 2 != 0) minOddDigit(digit/10, digit%10) else minOddDigit(digit/10,curMin)
        else if(digit%10 < curMin && digit%10 % 2 !=0) minOddDigit(digit/10, digit%10)
             else minOddDigit(digit/10,curMin)
                  else curMin

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

    print("Количество делителей числа, не делящихся на 3: ")
    println(numOfDel(digit, 1))

    print("Минимальная нечетная цифра числа: ")
    println(minOddDigit(digit,0))
}
