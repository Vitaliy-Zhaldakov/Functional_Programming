import java.util.Scanner

//Сумма цифр числа рекурсией вверх
fun sumDigitsUp(digit:Int):Int = if (digit != 0) digit%10 + sumDigitsUp(digit/10) else 0
//Сумма цифр числа хвостовой рекурсией
tailrec fun sumDigitsDown(digit:Int, sum:Int):Int = if (digit != 0) sumDigitsDown(digit/10, sum+digit%10) else sum
//Минимальная цифра числа рекурсией вверх
fun minDigitUp(digit:Int, curMin:Int):Int = if (digit != 0) if (digit%10 < curMin) minDigitUp(digit/10,digit%10)
                                                          else minDigitUp(digit/10, curMin) else curMin
//Минимальная цифра числа хвостовой рекурсией
tailrec fun minDigitDown(digit:Int, curMin:Int):Int = if (digit != 0) if (digit%10 < curMin) minDigitDown(digit/10,digit%10)
                                                                else minDigitDown(digit/10, curMin) else curMin
//Максимальная цифра числа рекурсией вверх
fun maxDigitUp(digit:Int, curMax:Int):Int = if (digit != 0) if (digit%10 > curMax) maxDigitUp(digit/10,digit%10)
                                                          else maxDigitUp(digit/10, curMax) else curMax
//Максимальная цифра числа хвостовой рекурсией
tailrec fun maxDigitDown(digit:Int, curMax:Int):Int = if (digit != 0) if (digit%10 > curMax) maxDigitDown(digit/10,digit%10)
                                                        else maxDigitDown(digit/10, curMax) else curMax
//Произведение цифр числа рекурсией вверх
fun mulDigitsUp(digit:Int):Int = if (digit != 0) digit%10 * mulDigitsUp(digit/10) else 1
//Произведение цифр числа хвостовой рекурсией
tailrec fun mulDigitsDown(digit:Int, mul:Int):Int = if (digit != 0) mulDigitsDown(digit/10, digit%10*mul) else mul

//Функция обхода числа, выполняющая заданные операции над числом
fun numberTraversal(digit: Int, init: Int = 0, operation:(Int,Int) -> Int) = operation(digit,init)
//Функция обхода числа с условием, выполняющая заданные операции над числом
fun numberTraversalWithCondition(digit: Int, init: Int, operation: (Int, Int) -> Int, check: (Int) -> Boolean) =
    if (check(digit)) operation(digit,init) else 0

//Проверка числа на чётность
fun even(digit: Int):Boolean = digit % 2 == 0
//Проверка на чётность суммы цифр
fun evenSumDigits(digit: Int):Boolean = sumDigitsDown(digit,0) % 2 == 0
//Проверка минимальной цифры на нечётность
fun oddMinDigit(digit: Int):Boolean = minDigitDown(digit,digit%10) % 2 != 0

fun main()
{
    val scanner = Scanner(System.`in`)
        print("Введите число: ")
        val digit = scanner.nextInt()
        println(numberTraversal(digit,0) { digit, init -> sumDigitsDown(digit, init) })
        println(numberTraversal(digit,1) { digit, init -> mulDigitsDown(digit, init) })
        println(numberTraversal(digit,digit%10) { digit, init -> minDigitDown(digit, init) })
        println(numberTraversal(digit,digit%10) { digit, init -> maxDigitDown(digit, init) })
        
        //Если число чётное, найти сумму его цифр
        println(numberTraversalWithCondition(digit,0, { digit, init -> sumDigitsDown(digit, init) },{digit -> even(digit)}))
        //Если сумма цифр числа чётная, найти произведение цифр числа
        println(numberTraversalWithCondition(digit,1, { digit, init -> mulDigitsDown(digit, init) },{digit -> evenSumDigits(digit)}))
        //Если минимальная цифра нечётная, найти максимальную цифру
        println(numberTraversalWithCondition(digit, digit%10, {digit, init -> maxDigitDown(digit, init) },{digit -> oddMinDigit(digit)}))
}
