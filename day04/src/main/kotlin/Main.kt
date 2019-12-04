fun main() {
    println("Part I: the solution is ${solvePartI()}.")
    println("Part II: the solution is ${solvePartII()}.")
}

fun solvePartI(): Int {
    var result = 0

    for (password in Range.lowerBound..Range.upperBound) {
        if (isValidPartI(password)) {
            result++
        }
    }

    return result
}

fun solvePartII(): Int {
    var result = 0

    for (password in Range.lowerBound..Range.upperBound) {
        if (isValidPartII(password)) {
            result++
        }
    }

    return result
}

fun isValidPartI(password: Int): Boolean {
    return hasDouble(password.toString()) && neverDecreases(password.toString())
}

fun isValidPartII(password: Int): Boolean {
    return hasDoubleOnly(password.toString()) && neverDecreases(password.toString())
}

private fun hasDouble(password: String): Boolean {
    for (i in 0 until password.length - 1) {
        if (password[i].toInt() == password[i + 1].toInt()) {
            return true
        }
    }

    return false;
}

private fun hasDoubleOnly(password: String): Boolean {
    // double is at beginning of password
    if (password[0].toInt() == password[1].toInt() && password[1].toInt() != password[2].toInt()) {
        return true
    }

    // double is at end of password
    if (password[password.length - 3].toInt() != password[password.length - 2].toInt() && password[password.length - 2].toInt() == password[password.length - 1].toInt()) {
        return true
    }

    // double is in middle of password
    for (i in 0 until password.length - 3) {
        if (password[i].toInt() != password[i + 1].toInt() && password[i + 1].toInt() == password[i + 2].toInt() && password[i + 2].toInt() != password[i + 3].toInt()
        ) {
            return true
        }
    }

    return false
}

private fun neverDecreases(password: String): Boolean {
    for (i in 1 until password.length) {
        if (password[i - 1].toInt() > password[i].toInt()) {
            return false
        }
    }

    return true;
}

object Range {
    const val lowerBound = 130254
    const val upperBound = 678275
}

