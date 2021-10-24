# User Guide

## Introduction

CardLI is a desktop app to help you create, organising, and review flashcards via a Command Line Interface.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 
The current version of CardLI has two menus: main and deck. In the main menu, you are working with
all the decks, so commands only apply to the decks and not the individual flashcards. Enter the deck
menu to work with the flashcards.

### Content
1. [Main Menu](#main-menu)
2. [Deck Menu](#deck-menu)

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


## Main Menu
### Editing a deck: edit
Edits the name of the deck indicated by the index.

Format: `edit <index of deck> /n <name>`

Example of Usage:

`edit 2 /n mathematics`

Expected outcome:

---
## Deck Menu
### Editing a flashcard: edit
Edits the front or the back of the flashcard of index <index> to the user input given.

Format: `edit <index> /s <front or back> /i <input>`

Example of usage:

`edit 3 /s front /i 1 + 1`

`edit 3 /s back /i 2`

Expected outcome:

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

### Main Menu
* Edit `edit <index of deck> /n <name>`
### Deck Menu
* Edit `edit <index> /s <front or back> /i <input>`