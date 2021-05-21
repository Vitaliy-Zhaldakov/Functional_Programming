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
