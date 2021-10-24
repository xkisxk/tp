# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`

System-wide Mode:

|Action|Format|
|-------|------|
|add deck|`add <name of deck>`|
|view decks|`view`|
|edit deck|`edit <index of deck> /n name`|
|delete deck|`delete <index/name of deck>`|
|enter deck|`enter <index of deck>`|
|test|`test`|
|view overall statistics for flashcards|`viewfc`|
|view test statistics|`viewtest <index of test>` prints the result of the test indicated by the index.<br>`viewtest all` prints out the results for all tests.|
|review|`review`|
|find flashcard|`find <word/phrase>`|
|exiting program|`exit`|

Deck Mode:

|Action|Format|
|------|------|
|add flashcard|`add /f <word> /b <definition>`|
|deleting a flashcard|`delete <word/index>`|
|editing a flashcard|`edit <index> /s <front or back> /i input`|
|viewing flashcards|`view`|
|exiting deck mode|`exit`|




