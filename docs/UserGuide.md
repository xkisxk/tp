# User Guide


![](assets/logo.png)
## Introduction


CardLI is a desktop app that helps you create, organise, and review flashcards via a Command Line 
Interface. As students ourselves, we find that studying through the use of flashcards can greatly
increase the efficacy of our study sessions as they break down large chapters and concepts into 
easy-to-understand segments. Hence, CardLI was designed in order to help students study better 
through the use of flashcards, while also reducing the paper waste that goes into writing physical
flashcards. The interfacing within the application is designed to be interactive and functional,
while the commands are designed to be intuitive to use. 


## How To Use

This guide is meant to help you to get familiarised with all the features of CardLI. The `Quick Start`
section will tell you how to download and get CardLI set up. The `Features` section provides an
in depth description of all available features, which you can refer to if you to know the exact definitions. 
There is also a `Command Summary` section that will give you a brief overview of the format of all
commands and an example in table format to make it easier for you to navigate.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `CardLI.jar` from [here](http://link.to/duke).
3. Take note of the full filepath of directory in which `CardLI.jar` is saved.
4. Open the command prompt by searching `cmd` in the search bar.
5. Navigate to the file directory containing `CardLI.jar` using the command `cd <filepath>`.
6. Start up CardLI using the command``java -jar CardLI.jar`.

The above steps are for users who are running CardLI on a Windows device. If you are using an Apple
and Linux device, you will have to open the command prompt equivalent on your operating system in step 4. 
Subsequently, you will also have to use the equivalent command on your operating system for 
navigation to a file directory in step 5. The remaining steps are the same across all operating systems.

## Features 
The current version of CardLI has two menus: main and deck. In the main menu, you are working with
all the decks, so commands only apply to the decks and not the individual flashcards. Enter the deck
menu to work with the flashcards.


### Content
1. [Main Menu](#1-main-menu)<br/>
1.1 [Add Deck](#11-adding-a-deck-add)<br/>
1.2 [Edit Deck](#12-editing-a-deck-edit)<br/>
1.3 [Delete Deck](#13-deleting-a-deck-delete)<br/>
1.4 [Enter Deck](#14-entering-a-deck-entercontent)<br/>
1.5 [Test](#15-test-flashcards-testcontent)<br/>
1.6 [Review](#16-review-flashcards-reviewcontent)<br/>
1.7 [View Decks](#17-viewing-all-decks-viewcontent)<br/>
1.8 [View Flashcard Stats](#18-view-flashcard-statistics-viewfccontent)<br/>
1.9 [View Test Stats](#19-view-test-statistics-viewtestcontent)<br/>
1.10 [Find Flashcard](#110-finding-a-flashcard-findcontent)<br/>
1.11 [Help](#111-listing-all-commands-helpcontent)<br/>
1.12 [Exit App](#112-exiting-the-app-byecontent)<br/>
2. [Deck Menu](#2-deck-menucontent)<br/>
2.1 [Add Flashcard](#21-adding-a-flashcard-addcontent)<br/>
2.2 [Edit Flashcard](#22-editing-a-flashcard-editcontent)<br/>
2.3 [Delete Flashcard](#23-deleting-a-flashcard-deletecontent)<br/>
2.4 [Move Flashcard](#24-moving-a-flashcard-movecontent)<br/>
2.5 [View Flashcard](#25-viewing-flashcards-viewcontent)<br/>
2.6 [Help](#26-listing-all-commands-in-deck-mode-helpcontent)<br/>
2.7 [Exit Deck Mode](#27-exiting-deck-mode-exitcontent)<br/>
3. [FAQ](#3-faqscontent)<br/>
4. [Command Summary](#4-command-summarycontent)<br/>
4.1 [Main Menu](#41-main-menucontent)<br/>
4.2 [Deck Menu](#42-deck-menucontent)<br/>

---
## [1. Main Menu](#content)
>💾 Your decks are saved after each command.

### [1.1 Adding a deck: `add`](#content)
Creates and adds a new deck with the given name, if it does not already exist.

Format: `add <name of deck>`

Example of Usage:

`add English vocab`

Expected outcome:

![](assets/ug/adddeck.png)

### [1.2 Editing a deck: `edit`](#content)
Edits the name of the deck indicated by the index.
`/d` denotes the index of the deck to be edited and `/n` denotes the new name
of the deck.

This command is for those who want to change the name of the deck to a more
suitable name.

Format: `edit /d <index of deck> /n <name>`

Example of Usage:

`edit /d 1 /n mathematics`

Expected outcome:

![](assets/ug/editdeck.png)
### [1.3 Deleting a deck: `delete`](#content)
Deletes the deck indicated by the index or the name.

Format: `delete <index of deck>`

Example of Usage:

`delete 1`

Expected outcome:

![](assets/ug/deleteDeck.png)

### [1.4 Entering a deck: `enter`](#content)
Enters the deck with the given index.

Format:
`enter <index of deck>`

Expected outcome: 

![](assets/ug/enter.png)
### [1.5 Test flashcards: `test`](#content)

Enter test mode. The program will ask you to input the index for the deck that is to be tested.
The word to be tested will be displayed in the console. 
Typing the exact character-for-character definition for the word will result in a correct answer. 
Otherwise, the response will be marked as incorrect. Regardless of whether the answer is correct 
or incorrect, the console will then display the next word to be tested. When all cards in the deck 
have been tested, the percentage of correct answers will be displayed in the console, as well as the 
cards which received incorrect responses.

If you do not know the answer to the current question being tested, and you want to skip to another
question, you can do so by typing `/NEXT` or `/BACK` when prompted with the test question. 

`/NEXT` will skip to the next question while `/BACK` will go back to the previous question tested.
> ❗ Test data is not saved until a test is complete. If the program crashes mid-test, the data for that test will not be saved.

Format: `test`

Expected outcome:

![](assets/ug/test.png)

### [1.6 Review flashcards: `review`](#content)
Enters review mode, which is the same as test mode except that the cards tested will be the cards
that the user got wrong on more than 50% of the tests.

Format: `review`

Expected outcome:

![](assets/ug/review.png)

### [1.7 Viewing all decks: `view`](#content)
Displays the names of all decks.

Format: `view`

Expected outcome:

![](assets/ug/viewdeck.png)

### [1.8 View flashcard statistics: `viewfc`](#content)
Prints out all flashcards that have been added up to this point, including the cumulative score of
all tests done for each of the flashcards.

Format: `viewfc`

Expected outcome:

![](assets/ug/viewfc.png)
### [1.9 View test statistics: `viewtest`](#content)
Prints the results for a particular test index or for all tests, depending on argument that 
follows the `viewtest` command.

Format: `viewtest <index of test>` or `viewtest all`

Expected outcome:

![](assets/ug/viewtestIndex.png)
![](assets/ug/viewtestAll.png)

### [1.10 Finding a flashcard: `find`](#content)
Flashcards with descriptions matching the search terms are displayed on the screen.

Format: `find <search terms>`

Expected outcome:

![](assets/ug/find.png)

### [1.11 Listing all commands: `help`](#content)
Lists all commands within the main menu.

Format: `help`

Expected outcome:

![](assets/ug/help1.png)

![](assets/ug/help2.png)
### [1.12 Exiting the app: `bye`](#content)
Exits the CardLI application within the Command Line Interface.

Format: `bye`

Expected outcome:

![](assets/ug/bye.png)
---
## [2. Deck Menu](#content)
> 💾 Your flashcards are saved after each command.

### [2.1 Adding a flashcard `add`](#content)
Adds a flashcard with the given front and back to the current deck.

Format: `add /f <word/phrase on front of flashcard> /b <word/phrase on back of flashcard>`

Example of usage:
` add /f glycerol /b C3H8O3`

Expected outcome:


![](assets/ug/addflash.png)

### [2.2 Editing a flashcard: `edit`](#content)
Edits the front or the back of your chosen flashcard to your given input.
`/c` denotes the card index, `/s` denotes the side of the flashcard to edit and
`/i` denotes the content you want to change the flashcard to.

This command is for those who have made a mistake in the front or back of the card
and wish to fix it without having to resort to deleting and adding the card.

Format: `edit /c <index of card> /s <front or back> /i <input>`

Example of usage:

`edit /c 1 /s front /i 1 + 1`

Expected outcome:

![](assets/ug/editcard.png)

### [2.3 Deleting a flashcard: `delete`](#content)
Deletes the flashcard indicated by the index or the flashcard which front matches <word/phrase> if it 
exists in the current deck of flashcards.

Format: `delete <index of card>`

Example of usage:

`delete 1`

Expected outcome:

![](assets/ug/deleteindexflash.png)

### [2.4 Moving a flashcard: `move`](#content)
Moves a flashcard from the deck you are currently in to a new deck of your choice.
`/c` denotes the card index or the front phrase of the card and `/d` denotes the 
deck index or the name of the deck you want to move the card to.

This command is for those who have accidentally added a flashcard to a wrong deck
and now want to transfer the card to another deck without going through the hassle
of deleting the card and adding the card in another deck.

Format: `move /c <index of card> /d <index of deck>`

Example of usage: 

`move /c 1 /d 2`

Expected outcome:

* Deck 2 before moving
![](assets/ug/move2.png)

* Deck 1 before and after moving; Deck 2 after moving
![](assets/ug/move4.png)

### [2.5 Viewing flashcards: `view`](#content)
Displays all the flashcards in the current deck.

Format: `view`

Expected outcome:

![](assets/ug/viewcard.png)

### [2.6 Listing all commands in deck mode: `help`](#content)
Lists all commands within Deck Mode.

Format: `help`

Expected outcome:

![](assets/ug/helpdeck.png)
### [2.7 Exiting deck mode: `exit`](#content)
Exits deck mode and returns to the main menu.

Format: ``exit``

Expected outcome:

![](assets/ug/exit.png)
## [3. FAQs](#content)

**Q**: How do I transfer my data to another computer? 

**A**: In order to transfer the data on your decks of flashcards to another computer, simply transfer
the `CardLI.txt` file to your other computer, and download the latest version of CardLI there. 
Ensure that both the `CardLI.txt` and `CardLI.jar` files are in the **same directory** before running
the CardLI application in the command prompt as instructed under the Quick Start section. 

**Q**: Can I directly edit the `CardLI.txt` file to add, edit or delete decks and flashcards?

**A**: If you are familiar with the format of how the decks and flashcards are saved within the text
file, you are free to do so. However, if errors are generated upon the next start up of the CardLI 
application, you are advised to revert all changes to the text file and make the necessary changes
from within the application. We will only recommend this method if you are a veteran user and have a
good grasp of the application.

## [4. Command Summary](#content)

### [4.1 Main Menu:](#content)

|Action|Format|
|-------|------|
|add deck|`add <name of deck>`|
|edit deck|`edit /d <index of deck> /n name`|
|delete deck|`delete <index>`|
|enter deck|`enter <index of deck>`|
|test|`test`|
|review|`review`|
|view decks|`view`|
|view overall statistics for flashcards|`viewfc`|
|view test statistics|`viewtest <index of test>` prints the result of the test indicated by the index.<br>`viewtest all` prints the results for all tests.|
|find flashcard|`find <word/phrase>`|
|lists all commands in main menu|`help`|
|exiting program|`bye`|

### [4.2 Deck Menu:](#content)

|Action|Format|
|------|------|
|add flashcard|`add /f <word> /b <definition>`|
|editing a flashcard|`edit /c <index of card> /s <front or back> /i input`|
|deleting a flashcard|`delete <index of card>`|
|moving a flashcard| `move /c <index of card> /d <index of deck>`|
|viewing flashcards|`view`|
|lists all commands in deck mode|`help`|
|exiting deck mode|`exit`|

