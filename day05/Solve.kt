import java.io.File

data class Operation(val count: Int, val src: Int, val dst: Int)

fun nums(s: String): Sequence<Int> = Regex("-?\\d+").findAll(s).map { it.value.toInt() }

fun main() {
    val (initialCrates, rawOperations) =
        File("input.txt").readText().trim().split("\n\n").map { it.lines() }
    val crateConfig =
        initialCrates
            .dropLast(1)
            .map { it.chunked(4).map { it[1] } }
            .let { crates ->
                val n = crates.last().size
                (0..(n - 1)).map { i -> crates.map { it[i] }.filter { it != ' ' }.reversed() }
            }
    val operations =
        rawOperations.map {
            val (count, src, dst) = nums(it).toList()
            Operation(count, src - 1, dst - 1)
        }

    println(
        crateConfig
            .map { it.toMutableList() }
            .let { crates ->
                operations.forEach { operation ->
                    repeat(operation.count) {
                        crates[operation.dst].add(crates[operation.src].removeLast())
                    }
                }
                crates.map { it.last() }.joinToString("")
            }
    )

    println(
        crateConfig
            .map { it.toMutableList() }
            .let { crates ->
                operations.forEach { operation ->
                    crates[operation.dst].addAll(crates[operation.src].takeLast(operation.count))
                    repeat(operation.count) { crates[operation.src].removeLast() }
                }
                crates.map { it.last() }.joinToString("")
            }
    )
}
