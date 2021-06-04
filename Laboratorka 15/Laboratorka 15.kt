import java.io.File
import java.util.Scanner
import kotlin.math.abs

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

//Task 4.41 Дан целочисленный массив. Найти среднее арифметическое модулей его элементов
fun averageAbs(array: Array<Int>):Int = sumOfAbs(array) / array.size
//Сумма модулей элементов массива
fun sumOfAbs(array:Array<Int>):Int = arrayOp(array,{ elem:Int, sum:Int -> abs(elem) + abs(sum) }, 0, 0)

//Task 4.47 Для введенного списка положительных чисел построить список всех
//положительных делителей элементов списка без повторений
tailrec fun positiveDels(list: List<Int>, listDel: MutableList<Int>, counter: Int):List<Int> = if(counter != list.size)
    positiveDels(list, listOfDel(list[counter],1, listDel), counter + 1) else listDel.distinct()

//Формирование списка делителей числа
fun listOfDel(num:Int,del:Int, list: MutableList<Int>):MutableList<Int> = if(del == num) list.plus(del).toMutableList()
    else if(num % del == 0) listOfDel(num, del + 1, list.plus(del).toMutableList())
            else listOfDel(num, del + 1, list)
            
//Task 4.53 Для введенного списка построить новый с элементами, большими, чем среднее арифметическое списка,
//но меньшими, чем его максимальное значение
tailrec fun task53(inputList:List<Int>, outputList:List<Int>, counter:Int):List<Int> = if(counter == inputList.size) outputList
    else if(inputList[counter] > averageList(inputList) && inputList[counter] < inputList.maxOrNull()!!)
        task53(inputList, outputList + inputList[counter], counter + 1)
            else task53(inputList, outputList, counter + 1)

//Среднее арифметическое списка
fun averageList(list: List<Int>):Int = list.sum() / list.size            

//Task 5
fun listOp(): List<Int> {
    print("Введите размер списка:  ")
    val size = readLine()!!.toInt()
    val list = listOf<Int>()
    return listInput(list, 0, size)
}
//Ввод элементов списка с клавиатуры
tailrec fun listInput(list : List<Int>, counter : Int, size : Int) : List<Int> =
    if (counter == size) list else listInput(list.plus(readLine()!!.toInt()), counter + 1, size)

//Функция перебора элементов списка
tailrec fun listOp(a: Iterator<Int>, f: (Int, Int) -> Int, result: Int): Int =
    if (!a.hasNext()) result else
        listOp(a, f, f(a.next(),result))

//Task 7
fun listInputFile(input : Map<Int, Int>) : List<Int?> {
    val list:List<Int> = listOf()
    return listInputFile(list, 0, input)
}
//Заполнение массива элементами из файла
fun listInputFile(list: List<Int?>, counter : Int, input : Map<Int, Int>) : List<Int?> = if (counter == list.size) list
    else listInputFile(list + input[counter], counter + 1,  input)

//Организация чтения из файла
//Одна строка - одно число, возвращает мэп индексированный
fun listInputFile(fileName:String) : List<Int?> {
    val input = File(fileName).readLines()
        .withIndex() //Возвращает ленивую итерацию, которая обертывает каждый элемент исходного массива в IndexedValue, содержащий индекс этого элемента и сам элемент.
        .map { indexedValue -> indexedValue.index to indexedValue.value.toInt() }  // Создаёт карту
        .toMap() //Возвращает карту
    return listInputFile(input)
}

//Функция выбора источника считывания (Клавиатура или файл)
fun listSelectInput() : List<Int?> {
    println(
        "Откуда считывать массив?\n" +
                "1. Клавиатура\n" +
                "2. Файл"
    )
    val type = readLine()!!.toInt()
    if (type == 2) {
        println("Введите имя файла: ")
        val name = readLine().toString()
        return listInputFile("${name}.txt")
    }
    else
        return listOp()
}

//Task 8.5
fun listCheckMin(list: List<Int>, index:Int):Boolean = list.minOrNull() == list[index]

//Task 8.6
fun listCyclicShiftLeft(list: List<Int>): List<Int> {
    val ar1 = list.drop(3)
    return  ar1 + list.take(3)
}

//Task 8.17
tailrec fun listExchangeMinMax(list: List<Int>, result: List<Int>, counter: Int):List<Int> = when {
    counter == list.size -> result
    list[counter] == list.minOrNull() -> listExchangeMinMax(list, result + list.maxOrNull()!!, counter + 1)
    list[counter] == list.maxOrNull() -> listExchangeMinMax(list, result + list.minOrNull()!!, counter + 1)
    else -> listExchangeMinMax(list, result + list[counter], counter + 1)
}

//Task 8.19
fun listCyclicShiftRight(list: List<Int>):List<Int>
{
    val ar1 = list.dropLast(1)
    return list.takeLast(1) + ar1
}

//Task 8.26
fun listNumBetweenMin (list: List<Int>) = listNumBetweenMin (list,list.indexOf(list.minOrNull()), list.indexOf(list.maxOrNull()),0, 0)
tailrec fun listNumBetweenMin (list: List<Int>, first: Int, second: Int, acum:Int, counter: Int):Int =
    if(counter == list.size - 1) acum else if (counter > first && counter < second) listNumBetweenMin(list, first, second, acum + 1, counter + 1)
    else listNumBetweenMin(list, first, second, acum, counter + 1)

//Task 8.29
fun listCheckMaxInRange(list: List<Int>, a:Int, b:Int):Boolean = if(a == b) false else if(a == list.maxOrNull()) true else listCheckMaxInRange(list,a + 1, b)

//Task 8.41
fun listAverageAbs(list: List<Int>):Int = listSumOfAbs(list) / list.size
//Сумма модулей элементов массива
fun listSumOfAbs(list: List<Int>):Int = listOp(list.iterator(),{ elem:Int, sum:Int -> abs(elem) + abs(sum) }, 0)
