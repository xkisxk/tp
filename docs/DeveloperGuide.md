# Developer Guide

#Introduction

CardLI is a Command Line Interface (CLI) desktop app designed to help students manage their flashcards. CardLI can help
students keep track of all their flashcards. It also does tests for students to test their knowledge. All of this in one
single platform.

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

### Editing a Deck

This subsection provides details on the implementation of the commands that enable the editing of the `Deck` object.

The user can only edit the `name` attribute of the `Deck` object, which represents the name of the deck.

### `EditDeckCommand`
The 'EditDeckCommand' allows the changing of the name of the `Deck`.

Given below is the sequence diagram for `EditDeckCommand`:
![](assets/editDeckCommandSeqDiagram.png)

### Editing a FlashCard

The 'EditCardCommand' allows the changing of the content of the `FlashCard`. The user can decide to change either the `front`
or `back` attributes of the `FlashCard`object, which represents the front and back side of the card.

Given below is the sequence diagram for `EditCardCommand`:
![](assets/editDeckCommandSeqDiagram.png)



## Product scope

### Target user profile

* Pre-University/University/Polytechnic students
* Reasonably comfortable using CLI apps
* Types fast
* Prefers to store their information online rather than physically
* Has a lot of flashcards


### Value proposition

CardLI provides a:
<li> User-friendly </li>
<li> Storage efficient</li>
<li> Internet connection independent</li>
flashcard experience.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
| |user| add flashcards|
| |user| delete flashcards|
| |user| view my flashcards|know what cards I currenly have in the deck|
| |user| test myself with my flashcards|know if I have memorised the flashcards correctly|
| |student preparing for their exam|see a list of the flashcards I got wrong|know which concepts I do not know|
|v2.0|user|shuffle my flashcards|test myself with a different order of flashcards each time|
| |student in a hurry|edit my flashcards|change the front or back of a flashcard without deleting and adding it again
| |student who studies multiple subjects|organise my flashcards into different decks|keep flashcards with related topics in the same set|
| |student preparing for their exam|see a timer when in test mode|keep track of how much time I have spent on each question
| |student who wants to test themself|to see the percentage of correct answers for each flashcard|which cards I am less proficient in
| |student in a hurry|test myself with the flashcards I have answered wrongly for more than half the time|get more proficient at the concepts I am not familiar with
| |student with many flashcards and decks|find a flashcard by searching for a term matching it|find the flashcard without looking through all my decks
| |student with little time|save my flashcards|I do not have to add my flashcards to the app every time I use it

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
