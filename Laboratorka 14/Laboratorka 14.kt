import java.util.Scanner

//Сумма цифр числа рекурсией вверх
fun sumDigitsUp(digit:Int):Int =  if (digit != 0) digit%10 + sumDigitsUp(digit/10) else 0
//Сумма цифр числа рекурсией вниз
tailrec fun sumDigitsDown(digit:Int, sum:Int):Int =  if (digit != 0) sumDigitsDown(digit/10, sum+digit%10) else sum
