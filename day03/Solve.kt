import java.io.File

fun toPriority(c: Char) =
    when {
        c in 'a'..'z' -> 1 + (c - 'a')
        c in 'A'..'Z' -> 27 + (c - 'A')
        else -> 0
    }

fun main() {
    val input = File("input.txt").readLines()
    println(
        input
            .map {
                val (left, right) = it.chunked(it.length / 2).map { it.toSet() }
                toPriority(left.intersect(right).first())
            }
            .sum()
    )

    println(
        input
            .chunked(3)
            .map {
                val (elf1, elf2, elf3) = it.map { it.toSet() }
                toPriority(elf1.intersect(elf2).intersect(elf3).first())
            }
            .sum()
    )
}
