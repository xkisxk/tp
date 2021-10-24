# User Guide

## Introduction

CardLI is a desktop app to help you create, organising, and review flashcards via a Command Line 
Interface. As students ourselves, we find that studying through the use of flashcards can greatly
increase the efficacy of our study sessions as they break down large chapters and concepts into 
easy-to-understand segments. Hence, CardLI was designed in order to help students study better 
through the use of flashcards, while also reducing the paper waste that goes into writing our physical
flashcards. The interfacing within the application has been design to be interactive and functional,
while the commands were designed to be intuitive to use. 

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `Duke` from [here](http://link.to/duke).
3. Take note of the full filepath of directory in which ``Duke.jar`` is saved.
4. Open the command prompt by searching ``cmd`` in the search bar.
5. Navigate to the file directory containing ``Duke.jar`` using the command ``cd <filepath>``.
6. Start up CardLI using the command ``java -jar Duke.jar``.

The above steps are for users who are running CardLI on a Windows device. If you are using an Apple
and Linux device, you will have to open the command prompt equivalent on your operating system in step 4. 
Subsequently, you will also have to use the equivalent command on your operating system for 
navigation to a file directory in step 5. The remaining steps are the same across all operating systems.

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

### Exiting the app: ``bye``
Exits the CardLI application within the Command Line Interface. 
Using this command will also save the current decks of flashcards into a text file named ``CardLI.txt`` 
stored within the same directory as ``Duke.jar``.

Format: ``bye``

## FAQs

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

**Q**: Can I directly edit the ``CardLI.txt`` file to add, edit or delete decks and flashcards?

**A**: If you are familiar with the format of how the decks and flashcards are saved within the text
file, you are free to do so. However, if errors are generated upon the next start up of the CardLI 
application, you are advised to revert all changes to the text file and make the necessary changes
from within the application. We will only recommend this method if you are a veteran user and have a
good grasp of the application.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
