import java.util.Scanner

//Функция создания массива
fun arrayOp():Array<Int>
{
    val scanner = Scanner(System.`in`)
    print("Введите количество элементов массива: ")
    val num = scanner.nextInt()
    val array: Array<Int> = Array(num) { 0 }
    return arrayInput(0,array)
}
//Ввод элементов массива с клавиатуры
tailrec fun arrayInput(init:Int, array: Array<Int>): Array<Int> =
    if(init == array.size) array else
    {
        array[init] = readLine()!!.toInt()
        arrayInput(init + 1, array)
    }

//Функция перебора элементов массива
tailrec fun arrayOp(array:Iterator<Int>, f: (Int, Int) -> Int, acum:Int):Int = if(!array.hasNext()) acum
else {arrayOp(array, f, f(array.next(), acum))}

//Функция перебора элементов массива
tailrec fun arrayOp(array: Array<Int>, function:(Int, Int) -> Int, acum:Int, counter:Int):Int =
    if (array.size == counter) acum else arrayOp(array,function,function(array[counter], acum), counter + 1)

//Сумма элементов массива
fun sumOfElem(array:Array<Int>):Int = arrayOp(array,{ elem:Int, sum:Int -> elem + sum }, 0, 0)
//Произведение элементов массива
fun multiOfElem(array:Array<Int>):Int = arrayOp(array,{ elem:Int, mul:Int -> elem * mul}, 1, 0)

//Поиск элемента массива, удовлетворяющего условию
tailrec fun findElem(array:Array<Int>, function: (Int, Int) -> Boolean, acum:Int, counter:Int):Int =
    if(array.size == counter) acum else findElem(array,function, if(function(array[counter], acum)) array[counter] else acum, counter + 1)

//Минимальный элемент массива
fun minElem(array: Array<Int>) = findElem(array, {elem:Int, acum:Int -> elem < acum}, array[0],0)
//Максимальный элемент массива
fun maxElem(array:Array<Int>) = findElem(array, {elem:Int, acum:Int -> elem > acum}, array[0],0)
