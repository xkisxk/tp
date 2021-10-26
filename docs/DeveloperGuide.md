# Developer Guide

#Introduction

CardLI is a Command Line Interface (CLI) desktop app designed to help students manage their flashcards. CardLI can help
students keep track of all their flashcards. It also does tests for students to test their knowledge. All of this in one
single platform.

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design
CardLi has one main component, ```Duke```, which is the entry point to the App.

The rest of the App consists of the following components:
* ```Parser```: Interfaces with the user by reading and interpreting input
* ```UI```: The UI of the App
* ```Logic```: The command executor
* ```Storage```: Reads and writes data from and to an external file(s)

Each component is explained in the sections below.


### Parser Component
The Parser component consists of two classes, ```Parser``` and ```TestParser```. Parser deals with input relating to adding, deleting, editing and viewing flashcards and decks, while ```TestParser``` deals with input relating to testing.

How the Parser component works:
* Identifies the command input by the user
* Parses command arguments
* Executes commands using the Logic component
* Handles exceptions relating to invalid arguments

### UI Component
The UI component consists of two classes, ```CardLiUi``` and ```TestUi```. It outputs greeting, exit and help messages to the user on command.

### Logic Component
The Logic component consists of the classes ```DeckManager```, ```Deck```, ```Flashcard```, ```TestManager```, ```AnswerList``` and ```Answer```. It executes user commands by calling on methods in its classes when appropriate with the appropriate arguments as given by the Parser component.

CardLi’s user commands operate on a 2-tier structure: a Systemwide level and a Deck level. The Systemwide level commands execute commands related to the management of decks, while the Deck level commands execute commands related to flashcards in a specific deck. The specific implementations are elaborated on in the *Implementation* section.

### Storage Component
The Storage component:
* Saves all the decks
* Saves all the flashcards
* Remembers which deck each flashcard belongs to
* Saves the results of each test

## Implementation
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

### Storage

This feature allows users of CardLI to save data on their current decks of flashcards as well as
the tests that they have completed thus far. This will also allow users to re-access the data
when they re-enter the application. This way, they will not have to keep re-adding the same flashcards,
while also being able to review tests that they had previously done on the application. 

This feature is implemented by saving the user’s data into two separate text files, which will be saved into
a new `data` directory created upon first start up of the application if it does not yet exist. 
This new directory will be created within the current directory from which the `CardLI.jar` file is run in the CLI. 
The file paths of the two text files are hard coded as `Cards_CardLI.txt` and `Tests_CardLItxt`, which will save data
on the user's decks of flashcards and test history respectively. 
When the user inputs the command `bye`, the application execute the save functions. When the 
user restarts or re-enters the application, the application will parse the text files and convert them into
the relevant data. The format of how the data is saved into the text files are specified 
during the development process in order to reduce the risk of bugs arising when the text files are being parsed, which
will be explained in the following paragraphs.

A `Storage` class was implemented to contain all the methods to execute the save and parse functions to and from the 
relevant text files. An instance of this class is created upon first start up the application to handle all the 
method calls. The respective methods will be explained in more detail in the following paragraphs.

`writeToFile(ArrayList<T> arrayList, String type)`

This method invoke the save function by writing the user's data to the specified text files. It takes in two arguments,
namely an `ArrayList` of a generic type `<T>` as well as a `String` denoting the `type` of data being saved.

For the saving of the user's decks of flashcards, the method call will expect an `ArrayList` of `Deck` objects along 
with a `type` argument of "cards". 
The `toString()` methods within the `Deck`and `Flashcard` classes have been overridden as per the specified format
of saving the decks of flashcards to the text file. 
For a `Flashcard` instance, the `toString()` method outputs a formatted string: `<front> | <back> | <userScore> 
| <totalScore>`. For a `Deck` instance, the `toString()` method also outputs a formatted string containing information 
about the deck name, the number of flashcards within the deck, on top of information on each of the flashcards contained
within the deck. An example of the format of the `Cards_CardLI.txt` where the decks of flashcards are saved is shown 
in the screenshot below.

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>

<body>

![](assets/CardLI.txt%20SS.PNG)
</body>

For the saving of the user's test history, the method call will expect an `ArrayList` of `AnswerList` objects along
with a `type` argument of "tests".
The `toString()` methods within the `AnswerList`and `Answer` classes have been overridden as per the specified format
of saving the test history to the text file.
For a `Answer` instance, the `toString()` method outputs a formatted string: `<answer> | <questionIndex>`. 
For a `AnswerList` instance, the `toString()` method also outputs a formatted string containing information
about the test deck and the user's test score, on top of information on each of the user's answers for the test. 
An example of the format of the `Tests_CardLI.txt` where the decks of flashcards are saved is shown
in the screenshot below.

//TODO insert ss of Tests.CardLI.txt

`readCardsFromFile()` and `readTestsFromFile()`

These two methods are executed every time the CardLI application is opened.
The methods use an instance of the `Scanner` class to parse through the text files. 
As per the saving format explained in the `writeToFile()` method above, the 
`readCardsFromFile()`/`readTestsFromFile()` methods essentially reverse engineer the process to save the user's 
data into the application before any commands are given from the user. 



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
