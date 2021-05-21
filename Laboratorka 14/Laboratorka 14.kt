import java.util.Scanner

//Сумма цифр числа рекурсией вверх
fun sumDigitsUp(digit:Int):Int =  if (digit != 0) digit%10 + sumDigitsUp(digit/10) else 0
