import java.io.File
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

//Task 3. Ввод из файла
fun arrayInputFile(input : Map<Int, Int>) : Array<Int> {
    val array:Array<Int> = Array(input.size){0}
    return arrayInputFile(array, 0, input.size, input)
}
//Заполнение массива элементами из файла
fun arrayInputFile(array : Array<Int>, counter : Int, size : Int, input : Map<Int, Int>) : Array<Int> =
    if (counter == size) array else {
        array[counter] = input[counter]!!
        arrayInputFile(array, counter + 1, size, input)
    }

//Организация чтения из файла
//Одна строка - одно число, возвращает мэп индексированный
fun inputFile(fileName:String) : Array<Int> {
    val input = File(fileName).readLines()
        .withIndex() //Возвращает ленивую итерацию, которая обертывает каждый элемент исходного массива в IndexedValue, содержащий индекс этого элемента и сам элемент.
        .map { indexedValue -> indexedValue.index to indexedValue.value.toInt() }  // Создаёт карту
        .toMap() //Возвращает карту
    return arrayInputFile(input)
}

//Функция выбора источника считывания (Клавиатура или файл)
fun selectInput() : Array<Int> {
    println(
        "Откуда считывать массив?\n" +
                "1. Клавиатура\n" +
                "2. Файл"
    )
    val type = readLine()!!.toInt()
    if (type == 2) {
        println("Введите имя файла: ")
        val name = readLine().toString()
        return inputFile("${name}.txt")
    }
    else
        return arrayOp()
}
