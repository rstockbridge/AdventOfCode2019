# Advent Of Code 2019

Project for Kotlin tests and solutions for the [2019 puzzles](http://adventofcode.com/2019).

To add daily solutions:

- Copy your daily inputs into the `dayXX/src/main/resources/input.txt` files.
- Write your daily solutions in the `main` functions in the files named `dayXX/src/main/kotlin/Main.kt`.
- (Optional) Write your daily tests by creating new test classes in the `dayXX/src/test/kotlin/` directories.

# Instructions

## Running Solutions

All days:

    ./gradlew run

Specific day, e.g. 14:

    ./gradlew day14:run

## Running Tests

All days:

    ./gradlew test

Specific day, e.g. 14:

    ./gradlew day14:test
