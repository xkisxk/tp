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



###Find
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>

<body>

![](assets/Find.png)
</body>
<body>
    <p dir="ltr" style="line-height:1.38;margin-top:0pt;margin-bottom:0pt;"><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">This feature allows users of CardLI to find a&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">FlashCard</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;by providing a search term to the input following the command term `find`. By invoking this function the user can view specific&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">FlashCards</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;matching the search term from the main menu, instead of entering each deck and manually looking through the list of&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">FlashCard</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;for the desired ones.</span></p>
    <p></p>
    <p dir="ltr" style="line-height:1.38;margin-top:0pt;margin-bottom:0pt;"><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">Currently, &ldquo;Find&rdquo; is implemented on a Systemwide level. After the&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">Ui&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">handles the user input,&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">Parser&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">formats the user input and passes the search term(s) to&nbsp;</span><span style="font-size:11pt;font-family:Arial;font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">Find</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;which repeatedly calls the&nbsp;</span><span style="font-size:11pt;font-family:'Courier New';font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">filter()</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;method that iterates once for each instance of a deck.</span></p>
    <p></p>
    <p><span style="font-size:11pt;font-family:'Courier New';font-weight:700;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">filter()</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;is implemented by creating a stream that consists of all the flashcards in one deck, and filters them based on whether they contain the search term given. Finally all the flashcards that contain the search term are collected in an arrayList to be displayed to the user along with their&nbsp;</span><span style="font-size:11pt;font-family:'Courier New';font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">deckIndex</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;and</span><span style="font-size:11pt;font-family:'Courier New';font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">&nbsp;cardIndex</span><span style="font-size:11pt;font-family:Arial;font-weight:400;font-style:normal;font-variant:normal;text-decoration:none;vertical-align:baseline;white-space:pre;white-space:pre-wrap;">.</span></p>
</body>




###Test Feature
![class diagram](../docs/assets/testClassDiagram.png)

Currently, test feature is implemented on a systemwide level and is handled by `TestManager`.
`TestManager` will call on `TestUi` and `TestParser` to handle the inputs and outputs with the user
and the parsing respectively during the test.

At the start of the test, the user will choose to test themselves with a single deck or all decks at once.
This is dependent on the integer the user inputs.

In both cases, `TestManager` will create an `AnswerList` using a `Deck` that it creates or gets from
`DeckManager` depending on the condition which is shown by the sequence diagram below. The `AnswerList`
is where the user's response to the test is stored, and it is made up of `Answer` as shown in the class
diagram above.

![sequence diagram](../docs/assets/getTestDeckSequenceDiagram.png)

After initializing the `AnswerList`, the testing begins. The `Deck` gets shuffled, then
the user will answer the question one at a time. This process is repeated for the entire `Deck` that
is being tested which is shown below by the sequence diagram.

![sequence diagram](../docs/assets/testAllCardsShuffledSequenceDiagram.png)

After all cards have been tested, the marking process begins as shown by the sequence diagram below.
For every correct answer, the user's score increments and `TestUi` will print a correct answer message.
After marking all the questions, the user's results will be printed and saved in `TestHistory`.

![sequence diagram](../docs/assets/markTestSequenceDiagram.png)

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
