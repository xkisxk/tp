# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

---

## Main Menu
### Deleting a deck: `delete`
Deletes the deck indicated by the index or the name.

Format: `delete <index/name of deck>`

Example of Usage:

`delete 1`

`delete test`

Expected outcome:

### Testing flashcards within a deck: test
Format: `test`

Enter test mode. The program will ask the user to input the index for the deck that is to be tested.
The word to be tested will be displayed in the console. 
Typing the exact character-for-character definition for the word will result in a correct answer. 
Otherwise, the response will be marked as incorrect. Regardless of whether the answer is correct 
or incorrect, the console will then display the next word to be tested. When all cards in the deck 
have been tested, the percentage of correct answers will be displayed in the console, as well as the 
cards which received incorrect responses.

Example of Usage:

`test`

Expected outcome:


### Review flashcards: review
Format: `review`
Enter review mode, which is the same as test mode except that the cards tested will be the cards 
that the user got wrong on more than 50% of the tests.

Example of usage:

`review`

Expected outcome:

---
## Deck Menu
###Deleting a flashcard: delete
Format: `delete <word/phrase/index>`
Deletes the <index>th flashcard or the flashcard which front matches <word/phrase> if it 
exists in the current deck of flashcards.

Remark:
* If there are cards with identical front description in the deck, the first instance of the card 
matching that <word/phrase> will be deleted.

Example of usage:

`delete 1`

`delete card`

`delete sequence diagrams`

Expected outcome:


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
