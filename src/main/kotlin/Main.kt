import java.text.DecimalFormat
//2
class Person(val name: String, val surname: String, val age: Int){
    override fun toString(): String {
        return "Гражданина звать $surname $name', отроду $age лет"
    }
}

fun Person.mau() {
    val e = when {
        this.age < 18 -> " уже"
        this.age > 50 -> " все еще"
        else -> ""
    }
    println("${this.name} в свои ${this.age} лет$e говорит Мау")
}

fun List<Person>.sortByAge(): List<Person> {
    return this.sortedByDescending { it.age }
}

fun List<Person>.sortAlphaByNameWithSurname(): List<Person> {
    return this.sortedWith(compareBy({ it.name }, { it.surname }))
}

//3
inline fun <T> measureExecutionTime(block: () -> T): Long {
    val startTime = System.currentTimeMillis()
    block()
    val endTime = System.currentTimeMillis()
    return endTime - startTime
}

tailrec fun factorial(n: Long, acc: Long = 1): Long {
    return if (n == 1L) {
        acc
    } else {
        factorial(n - 1, acc * n)
    }
}

fun main() {
    //1
    fun reSum() = (0..99).toList().filter { it % 2 == 0 }.slice(10..20).sumOf { it + 1 }
    println("Задание1: Сумма ${reSum()}\n")

    //2
    println("Задание2:")
    val ages = (5..90)
    val persons = listOf(
        Person("Иван","Иванов", ages.random()),
        Person("Иван","Петров", ages.random()),
        Person("Сергей","Сергеев", ages.random()),
        Person("Николай","Николаев", ages.random())
    )

    println("\nСортировка по возрасту в порядке убывания:")
    persons.sortByAge().forEach { println(it) }

    println("\nСортировка в алфавитном порядке по имени (учитывая фамилию):")
    persons.sortAlphaByNameWithSurname().forEach { println(it) }

    //3
    val executionTime = measureExecutionTime {
        println("\nЗадание3: Результат фушкцим с вычеслением времени исполнения:")
        println(DecimalFormat("0.###E0").format((1..factorial(10)).toList().map { it * it }.filter { (it % 2).toInt() == 0 }.sum()))
    }

    println("\nВремя выполнения функции: $executionTime мс")

    println("\nШутка и игра с when:")
    persons.forEach { it.mau() }
}