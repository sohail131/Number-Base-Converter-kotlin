package converter

import java.util.stream.IntStream
import kotlin.math.pow

// Do not delete this line

fun main() {
    var firstLevel = ""

    while (true) {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        firstLevel = readln()

        if (firstLevel == "/exit") {
            break
        } else {
            secondLevelLoop(firstLevel)
        }
    }
}

fun secondLevelLoop(firstLevel: String) {
    if (firstLevel != "/back") {
        val (source, target) = firstLevel.split(" ").map{it.toInt()}
        var number = ""

        while (true) {
            println("Enter number in base $source to convert to base $target (To go back type /back)")
            number = readln()

            if (number == "/back") {
                break
            } else {
                numberConverter(source, target, number)
            }
        }
    }
}

fun numberConverter(source: Int, target: Int, number: String) {
    if (number.contains(".")) {
        val data = number.split(".")
        var result = data[0].toBigInteger(source).toString(target).plus(".")
        var fraction = IntStream.range(0, data[1].length).mapToDouble { i -> data[1].substring(i, i + 1).toInt(source) / source.toDouble().pow(i + 1.0) }.sum()

        for (i in 0..4) {
            fraction *= target
            result += fraction.toInt().toString(target)
            fraction -= fraction.toInt()
        }

        println("Conversion result: $result")
    } else {
        println("Conversion result: ${number.toBigInteger(source).toString(target)}")
    }
}
