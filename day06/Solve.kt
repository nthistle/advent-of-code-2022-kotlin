import java.io.File

fun main() {
    val input = File("input.txt").readText().trim()
    println(4 + input.windowed(4).indexOfFirst( { it.toSet().size == it.length } ))
    println(14 + input.windowed(14).indexOfFirst( { it.toSet().size == it.length } ))
}
