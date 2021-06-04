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

//Task 4.5 Дан целочисленный массив и натуральный индекс.
//Необходимо определить является ли элемент по указанному индексу глобальным минимумом
fun checkMin(array: Array<Int>, index:Int):Boolean = minElem(array) == array[index]

//Task 4.6 Дан целочисленный массив. Необходимо осуществить циклический
//сдвиг элементов массива влево на три позиции
fun cyclicShiftLeft(array: Array<Int>): List<Int> {
    val ar1 = array.drop(3)
    return  ar1 + array.take(3)
}

//Task 4.17
fun exchangeMinMax(array: Array<Int>):Array<Int?>
    {
        val newArray:Array<Int?> = Array(array.size){ null }
        newArray[array.indexOf(minElem(array))] = maxElem(array)
        newArray[array.indexOf(maxElem(array))] = minElem(array)
        fillNull(array, newArray, 0)
        return newArray
    }
//Заполняет нулевые элементы значениями из другого массива
tailrec fun fillNull(array:Array<Int>, newArray:Array<Int?>, counter:Int):Array<Int?> = if(counter == array.size) newArray
    else if(newArray[counter] == null) { newArray[counter] = array[counter]
                                        fillNull(array,newArray, counter + 1) }
    else fillNull(array,newArray,counter + 1)

//Task 4.19 Дан целочисленный массив. Необходимо осуществить циклический
//сдвиг элементов массива вправо на одну позицию
fun cyclicShiftRight(array: Array<Int>):List<Int>
{
    val ar1 = array.dropLast(1)
    return array.takeLast(1) + ar1
}

//Task 4.26 Дан целочисленный массив. Необходимо найти количество
//элементов между первым и последним минимальным
fun numBetweenMin (array: Array<Int>) = numBetweenMin (array,array.indexOf(minElem(array)), array.indexOf(maxElem(array)),0, 0)
tailrec fun numBetweenMin (array: Array<Int>, first: Int, second: Int, acum:Int, counter: Int):Int =
    if(counter == array.size - 1) acum else if (counter > first && counter < second) numBetweenMin(array, first, second, acum + 1, counter + 1)
        else numBetweenMin(array, first, second, acum, counter + 1)

//Task 4.29 Дан целочисленный массив и интервал a..b. Необходимо проверить 
//наличие максимального элемента массива в этом интервале
fun checkMaxInRange(max:Int, a:Int, b:Int):Boolean = if(a == b) false else if(a == max) true else checkMaxInRange(max,a + 1, b)
