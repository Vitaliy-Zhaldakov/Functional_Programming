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
