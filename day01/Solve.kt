import java.io.File

fun main() {
    val input =
        File("input.txt").readText().trim().split("\n\n").map { it.lines().map { it.toInt() } }
    val totalPerElf = input.map { it.sum() }
    println(totalPerElf.max())
    println(totalPerElf.sorted().takeLast(3).sum())
}
