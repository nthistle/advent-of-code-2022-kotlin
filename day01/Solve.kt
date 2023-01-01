import java.io.File

fun main() {
    val input =
        File("input.txt").readText().trim().split("\n\n").map { it.lines().map { it.toInt() } }
    input
        .map { it.sum() }
        .let {
            println(it.max())
            println(it.sorted().takeLast(3).sum())
        }
}
