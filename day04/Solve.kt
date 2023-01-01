import java.io.File

fun main() {
    val input =
        File("input.txt").readLines().map { it.split(",").map { it.split("-").map { it.toInt() } } }

    println(
        input.count {
            val (s0, e0) = it[0]
            val (s1, e1) = it[1]
            ((s1 <= s0) && (e0 <= e1)) || ((s0 <= s1) && (e1 <= e0))
        }
    )

    println(
        input.count {
            val (s0, e0) = it[0]
            val (s1, e1) = it[1]
            (s1 <= e0) && (s0 <= e1)
        }
    )
}
