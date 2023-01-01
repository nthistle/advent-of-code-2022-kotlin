import java.io.File

enum class Action(val n: Int) {
    ROCK(0),
    PAPER(1),
    SCISSORS(2);

    companion object {
        val map = Action.values().associateBy { it.n }
    }
}

enum class GameResult(val s: String) {
    LOSE("X"),
    TIE("Y"),
    WIN("Z");

    companion object {
        val map = GameResult.values().associateBy { it.s }
    }
}

fun actionFromResult(theirAction: Action, result: GameResult): Action =
    Action.map[
            (theirAction.n +
                when (result) {
                    GameResult.LOSE -> 2
                    GameResult.TIE -> 0
                    GameResult.WIN -> 1
                }) % 3]!!

fun actionScore(action: Action): Int = action.n + 1

fun roundScore(ourAction: Action, theirAction: Action): Int =
    when ((ourAction.n - theirAction.n + 3) % 3) {
        0 -> 3
        1 -> 6
        2 -> 0
        else -> 0
    }

val part1Map =
    mapOf(
        "A" to Action.ROCK,
        "B" to Action.PAPER,
        "C" to Action.SCISSORS,
        "X" to Action.ROCK,
        "Y" to Action.PAPER,
        "Z" to Action.SCISSORS
    )

fun main() {
    val input = File("input.txt").readLines().map({ it.split(" ") })
    println(
        input
            .map {
                val (theirAction, ourAction) = it.map { part1Map[it]!! }
                actionScore(ourAction) + roundScore(ourAction, theirAction)
            }
            .sum()
    )

    println(
        input
            .map {
                val theirAction = part1Map[it[0]]!!
                val ourResult = GameResult.map[it[1]]!!
                val ourAction = actionFromResult(theirAction, ourResult)
                actionScore(ourAction) + roundScore(ourAction, theirAction)
            }
            .sum()
    )
}
