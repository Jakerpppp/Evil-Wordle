# Evil Wordle Scala Project

Welcome to the Evil Wordle Scala Project! 

## Overview

This Scala project implements the Evil Wordle game, a variant of the classic word-guessing game Wordle. In this version, the core functionality of Wordle is implemented using immutability and functional programming principles. Additionally, a twist is added by determining the most "evil" word from a set of words after the user has made their first guess.

## Features

- **Immutable Design:** The project emphasizes immutability, ensuring that data structures and states are handled in a purely functional manner.

- **Functional Programming:** Leveraging functional programming principles in Scala to create a concise and expressive codebase.

- **Evil Word Determination:** After the user makes their first guess, the program determines the most "evil" word from a set of words to challenge the player further.

## Getting Started

To run the Evil Wordle Scala Project, follow these steps:

```bash
# Clone the repository
git clone https://github.com/jakerpppp/Evil-Wordle.git

# Navigate to the project folder
cd evil-wordle-scala

# Compile the Scala code
sbt compile

# Run the application
sbt run
```
Make sure you have SBT installed on your machine.

## Gameplay

The user starts by guessing a word, and the program provides feedback on correct letters and their positions.
After the first guess, the program calculates and reveals the most "evil" word from the set of words. The definition of an "evil" word can be based on various criteria, such as difficulty or likelihood of confusion.
The user continues to make guesses until they correctly identify the secret word or exhaust their attempts.
Motivation

The Evil Wordle Scala Project was created as an exploration of functional programming in Scala and the application of immutable design principles. It serves as a fun and challenging variation of the classic Wordle game, adding an extra layer of complexity.

Lessons Learned

- **Immutability in Scala:** Understanding and implementing immutability in Scala to create robust and functional code.

- **Functional Programming Concepts:** Applying functional programming concepts, such as pure functions and immutability, to enhance the maintainability of the code.

- **Wordle Algorithm Design:** Developing algorithms to determine the most "evil" word based on specific criteria.

## Future Enhancements

While the project currently implements the Evil Wordle game, there's always room for improvement and expansion. Future enhancements may include additional game variations, improved evil word criteria, and an enhanced user interface.

Feel free to play the Evil Wordle game and enjoy the challenge!