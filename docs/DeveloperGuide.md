# Developer Guide
![](assets/logo.png)

# Content
1. [Introduction](#introduction)<br/>
2. [Acknowledgements](#acknowledgements)<br/>
3. [Design](#design)<br/>
3.1. [Model Component](#model-component)<br/>
3.2. [UI Component](#ui-component)<br/>
3.3. [Logic Component](#logic-component)<br/>
3.4. [Storage Component](#storage-component)<br/>
4. [Implementation](#implementation)<br/>
4.1. [Edit](#edit)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.1.1 [EditDeckCommand](#editdeckcommand)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.1.2 [EditCardCommand](#editcardcommand)<br/>
4.2. [Move](#move)<br/>
4.3. [Find](#find)<br/>
4.4. [Test Feature](#test-feature)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.4.1 [Test Setup](#test-setup)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.4.2 [Testing Process](#testing-process)<br/>
4.5. [Storage](#storage)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.5.1. [Writing to File](#writing-to-file)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;4.5.2. [Reading from File](#reading-from-file)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.2.1. [ReadCardsFromFile](#readcardsfromfile)<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.5.2.2. [ReadTestFromFile](#readtestfromfile)<br/>
5. [Product Scope](#product-scope)<br/>
5.1 [Target User Profile](#target-user-profile)<br/>
5.2 [Value Proposition](#value-proposition)<br/>
6. [User Stories](#user-stories)<br/>
7. [Non-Functional Requirements](#non-functional-requirements)<br/>
8. [GLossary](#glossary)<br/>
9. [Instructions for Manual Mesting](#instructions-for-manual-testing)

# [1. Introduction](#content)

CardLI is a Command Line Interface (CLI) desktop app designed to help students manage their flashcards. CardLI can help
students keep track of all their flashcards. It also allows students to test their knowledge. All of this in one
single platform.

## [2. Acknowledgements](#content)

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

* [__AB3:__](https://se-education.org/addressbook-level3/) For reference regarding the user guide and developer guide
* __Jansi:__ For its ANSI escape sequence library, currently used in flashcard testing

## [3. Design](#content)
CardLi has one main component, ```Main```, consisting of one class `CardLi`. It is responsible for:

<li> At app launch: initialises the components in the correct sequence, and connects them up with each other</li>

![](assets/overallArchitectureDiagram.png)

The rest of the App consists of the following components:
* ```Model```: Holds the data of the App in memory.
* ```UI```: The UI of the App
* ```Logic```: The command executor
* ```Storage```: Reads and writes data from and to an external file(s)

Each component is explained in the sections below.

### [3.1 Model Component](#content)

![](assets/modelArchitectureDiagram2.png)

The `Model` component consists of two packages: 

The `flashcard` package that contains:
<li> DeckManager</li>
<li> Deck </li>
<li> FlashCard</li>
<br>

and the `testing` package that contains:
<li> Answer</li>
<li> TestManager</li>
<li> TestHistory</li>
<li> TestManager</li>
<li> Countdown</li>

A `Deck` contains a private arrayList of `FlashCards`, and `DeckManager` contains a private arrayList of `Decks`.

An `AnswerList` contains a private arrayList of `Answers`, which is created by `TestManager` when the commands for the test functions are executed (`ReviewCommand`, `TestCommand`). 

The `Countdown` class creates a timer that displays the time left for a question during a Test or Review.

### [3.2. UI Component](#content)
The UI component consists of two classes, ```CardLiUi``` and ```TestUi```. It outputs greeting, exit and help messages to the user on command.

### [3.3. Logic Component](#content)

![](assets/logicArchitectureDiagram.png)

> ℹ️  `XYZCommand` is a placeholder name for subclasses of the abstract `Command` class (eg. `AddCardsCommand`, `DeleteDeckCommand`, `TestCommand` etc)

Here is a partial architecture diagram of the `Logic` component. It executes user commands via Command classes when appropriate with the appropriate arguments as given by the Parser component.

CardLi’s user commands operate on a 2-tier structure: a Systemwide level and a Deck level. The Systemwide level commands execute commands related to the management of decks, while the Deck level commands execute commands related to flashcards in a specific deck. The specific implementations are elaborated on in the *Implementation* section.

How the `Logic` component works:
1. When `Main` is called upon to execute a command, `Inner Parser` or `Outer Parser` parses the user input to get the command word. 
2. According to the command word a subclass of the `Command` object (eg. `AddCardsCommand`, `DeleteDeckCommand`) which is executed by Main/
3. The command communicates with the `Model` when it is executed (eg. to add a flashcard).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned to the `UI` from `Logic` to process.

![](assets/parserArchitectureDiagram.png)

Above is the architecture diagram containing the parser classes in `Logic` that are used for parsing a user command.

How the parsing works:

`Inner Parser` or `Outer Parser` creates a `XYZCommand` which in turn creates its corresponding `XYZCommandParser` (eg. when `InnerParser` creates a `EditCardCommand`, `EditCardCommand` creates a `EditCardParser`.)</li>

All `XYZCommandParser` classes implement the `CommandArgumentParser` interface.
### [3.4. Storage Component](#content)
The `Storage` component:
* Saves all the decks
* Saves all the flashcards
* Remembers which deck each flashcard belongs to
* Saves the results of each test

All app data is saved as JSON files.

## [4. Implementation](#content)

> ℹ️  Note: In the sequence diagrams below, the lifeline for objects should end at the destroy marker (X) but due
> to a limitation of PLANTUML, the lifeline reaches the end of the diagram.

### [4.1. Edit](#content)

This subsection provides details on the implementation of the commands that enable the editing of the `Deck` object.

The user can only edit the `name` attribute of the `Deck` object, which represents the name of the deck.

#### [4.1.1. `EditDeckCommand`](#content)


Given below is the sequence diagram for `edit` (Deck):

![](assets/editDeckCommandSeqDiagram.png)

The `EditDeckCommand` allows the changing of the name of the `Deck`.

By entering the edit command in the `OuterParser` class, an `EditDeckCommand` object is created and its constructor is
called. This object is returned to `CardLi` class, which then calls the `execute()` method
of the `EditDeckCommand` object.

The `execute()` method in the `EditDeckCommand` class self-invokes the `prepareEditDeckCommand()` method, which helps
the handling of edge cases as well as format the method arguments. In turn, `prepareEditDeckCommand()` self-invokes the
`prepareDeckIndex` method, which handles the formatting of the deck index specified by the user. `prepareDeckIndex()` 
returns `deck`, of string type, which represents the deck to be edited. `prepareEditDeckCommand()` will then return
a string array, `preparedArguments`, which represents the arguments for the next method call.

The `execute()` method will then call the `editDeck()` method of the `DeckManager` class, which in turn calls the 
`setName()` method of the `Deck` class. Once `editDeck()` is completed, a message of string type is returned to the
`execute()` method. The message is stored in a `CommandResult` class, which is then returned to `CardLi`.

`CardLi` then calls upon the `printResult()` method of the `CardLiUI` class to print the message to the user.

#### [4.1.2. `EditCardCommand`](#content)

![](assets/editCardCommandSeqDiagram.png)

The `EditCardCommand` allows the changing of the content of the `FlashCard`. The user can decide to change either the `front`
or `back` attributes of the `FlashCard`object, which represents the front and back side of the card.

By entering the edit command in the `InnerParser` class, an `EditCardCommand` object is created and its constructor is
called. This object is returned to `CardLi` class, which then calls the `execute()` method
of the `EditCardCommand` object.

The `execute()` method in the `EditCardCommand` class self-invokes the `prepareEditCardCommand()` method, which helps
the handling of edge cases as well as format the method arguments. In turn, `prepareEditClardCommand()` self-invokes the
`prepareCardIndex` method, which handles the formatting of the card index specified by the user. `prepareCardIndex()`
returns `card`, of string type, which represents the card to be edited. `prepareEditCardCommand()` will then return
a string array, `preparedArguments`, which represents the arguments for the next method call.

The `execute()` method will then call the `editCard()` method of the `Deck` class, which in turn calls the
`setFront()` or `setBack()` method of the `FlashCard` class. Once `editCard()` is completed, a message of string type is returned to the
`execute()` method. The message is stored in a `CommandResult` class, which is then returned to `CardLi`.

`CardLi` then calls upon the `printResult()` method of the `CardLiUI` class to print the message to the user.

### [4.2. Move](#content)

![](assets/moveCardCommandSeqDiagram.png)

This subsection provides details on the implementation of the `moveCardCommand`. This command
enables moving of a card in a deck the user is currently in to another deck.

By entering the move command in the `InnerParser` class, an `MoveCardCommand` object is created and its constructor is
called. This object is returned to `CardLi` class, which then calls the `execute()` method
of the `MoveCardCommand` object.

The `execute()` method in the `MoveCardCommand` class self-invokes the `prepareMoveCardCommand()` method, which helps
the handling of edge cases as well as format the method arguments. In turn, `prepareMoveCardCommand()` self-invokes the
`prepareCardIndex` method, which handles the formatting of the card index specified by the user. After this, `prepareMoveCardCommand()`
self invokes the `prepareDeckIndex` method, which handles the formatting of the deck index specified by the user.`prepareCardIndex()`
returns `card`, of string type, which represents the card to be edited. `prepareDeckIndex()`
returns `deck`, of string type, which represents the deck to be edited.`prepareMoveCommand()` will then return
a string array, `preparedArguments`, which represents the arguments for the next method call.

The `execute()` method will then call the `moveCard()` method of the `DeckManager` class, which in turn calls the
`getCard()` method of the `Deck` class to get a copy of the card to be moved. `DeckManager` then calls the `addFlashCard()`
method of the `Deck` class to add the card to the deck specified by the user. Next, `DeckManager` calls the `deleteFlashCard()`
method of the `Deck` class to delete the card from the deck it was from. Once `moveCard()` is completed, a message of string type is returned to the
`execute()` method. The message is stored in a `CommandResult` class, which is then returned to `CardLi`.

`CardLi` then calls upon the `printResult()` method of the `CardLiUI` class to print the message to the user.

### [4.3. Find](#content)

![](assets/findFlashcardDiagram.png)

Given above is the sequence diagram of the `find` function. This feature allows users of CardLI to find a `FlashCard` by providing a search term to the input following the command term `find`. By invoking this function the user can view specific `FlashCards` matching the search term from the main menu, instead of entering each **Deck** and manually looking through the list of **FlashCard** for the desired ones.

Currently, `find` is implemented on a Systemwide level. After the `CardLiUi` handles the user input, `OuterParser` formats the user input and creates a `FindCardsCommand` object which is returned. `Duke` calls the `execute()` method of the `FindCardsCommand` object. The `FindCardsParser` extracts the search term(s) and passes them to `CommandResult` which invokes the  `findCards()` method of `DeckManager` that repeatedly calls the `returnMatchingFlashCards()` method that iterates once for each instance of a `Deck`.

`returnMatchingFlashCards()` is implemented by creating a stream that consists of all the `FlashCards` from one deck, and filters them based on whether they contain the search term given. Finally all the `FlashCards` that contain the search term are collected in an arrayList and their console outputs are returned in string format for `CardLiUi` to display to the user.

### [4.4. Test Feature](#content)

![class diagram](assets/testClassDiagram.png)

Currently, test feature is implemented on a systemwide level and is handled by `TestManager`.
`TestManager` will call on `TestUi` and `TestParser` to handle the inputs and outputs with the user
and the parsing respectively during the test.
#### [4.4.1. Test Setup](#content)

![sequence diagram](assets/TestSequenceDiagram.png)

To enter into test mode, the user needs to enter `test` in main menu in which the program will
prompt the user to input a number corresponding to the index of the deck that they want to 
be tested on or "all" to test all decks.

![sequence diagram](assets/getTestDeckSequenceDiagram.png)

In both cases, `TestManager` will create an `AnswerList` using a `Deck` that it creates or gets from
`DeckManager` depending on the condition which is shown by the sequence diagram above. 
If the user decides to test all decks, the program will compile all `FlashCard` into a `Deck`. If the user
decides to test a single deck, the program will get that deck instance from `DeckManager`.
The `AnswerList` is where the user's response to the test is stored, and it is made up 
of `Answer` as shown in the class diagram above. The `AnswerList` is also tagged with the test deck.

![sequence diagram](assets/prepareTestDeckSeqDiagram.png)

After constructing the `AnswerList`, the preparation begins. The `Deck` that is attached to
the `AnswerList` gets duplicated, then shuffled. Afterwards, the `AnswerList` will be populated with
"NIL" `Answers`.

#### [4.4.2. Testing Process](#content)

![sequence diagram](assets/testInProgressSeqDiagram.png)

This is where the actual test starts. The test will keep looping until every card in the `Deck` to test
is answered. And there is another loop within that loops until the `currentQuestion`, which is an `int` 
representing the question number, goes out of bounds. Inside the inner loop, `testCard` is called to
test an individual card. The resulting `nextQuestionFlag` decides whether to proceed to the next question
(if it equals to '0') or go back to a previous question (if it equals to '1'). If this results in 
`currentQuestion` going out of bounds and if every question is not answered, currentQuestion will get
reset to either the lowest or highest question number that is not answered.

![sequence diagram](assets/testCardSeqDiagram.png)

The question is printed for the user to answer. The user's answer is then parsed and checked if 
it is `/Next` or `/Back`. If it is neither, the user's answer is added into `AnswerList`. If it is
`/Next`, nextQuestionFlag is set to 0 and if it is `/Back`, nextQuestionFlag is set to 1.

![sequence diagram](assets/markTestSequenceDiagram.png)

After all cards have been tested, the marking process begins as shown by the sequence diagram above. The
program will print the question, followed by the actual answer, followed by the user's answer.
For every correct answer, the user's score increments and `TestUi` will print a correct answer message.
After marking all the questions, the user's results will be printed and saved in `TestHistory`.
This concludes the entire `startTest()` process.

The `startReview()` process is similar to `startTest()`, except the `getTestDeck()` function has an additional
condition of the `FlashCard` getting strictly less than 50% of the total number of tests. 

<details>
<summary>Remark</summary>

>`TestCommand` and `ReviewCommand` is executed quite differently compared to the other features.
> The other commands returns the resulting string of the execution but test and review only returns the end
> test/review message.
> This is because, unlike the others, test and review needs to constantly interact with the user,
> which means that the feature needs to print out a response message after the user's input. Not only
> that, it needs to also update the timer live, which makes storing the entire process as a string
> when `Command.execute()` is called not really feasible.
</details>
 
### [4.5. Storage](#content)

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

#### [4.5.1. Writing to File](#content)

`writeToFile(ArrayList<T> arrayList, String type)`

This method invokes the save function by writing the user's data to the specified text files. It takes in two arguments,
namely an `ArrayList` of a generic type `<T>` as well as a `boolean` value, `saveCards`, that indicates whether the method
is saving cards or tests to their respective text files.

![](assets/writeToFileSequenceDiagram.png)

For the saving of the user's decks of flashcards, the method call will expect an `ArrayList` of `Deck` objects along 
with a `saveCards` argument of true. 
The `toString()` methods within the `Deck`and `Flashcard` classes have been overridden as per the specified format
of saving the decks of flashcards to the text file. 
For a `Flashcard` instance, the `toString()` method outputs a formatted string: `<front> | <back> | <userScore> 
| <totalScore>`. For a `Deck` instance, the `toString()` method also outputs a formatted string containing information 
about the deck name, the number of flashcards within the deck, on top of information on each of the flashcards contained
within the deck. An example of the format of the `Cards_CardLI.txt` where the decks of flashcards are saved is shown 
in the screenshot below.

![](assets/Cards_CardLI.txt%20Example.png)

For the saving of the user's test history, the method call will expect an `ArrayList` of `AnswerList` objects along
with a `saveCards` argument of false.
The `toString()` methods within the `AnswerList`and `Answer` classes have been overridden as per the specified format
of saving the test history to the text file.
For a `Answer` instance, the `toString()` method outputs a formatted string: `<answer> | <questionIndex>`. 
For a `AnswerList` instance, the `toString()` method also outputs a formatted string containing information
about the test deck and the user's test score, on top of information on each of the user's answers for the test. 
An example of the format of the `Tests_CardLI.txt` where the decks of flashcards are saved is shown
in the screenshot below.

![](assets/Tests_CardLI.txt%20Example.png)

#### [4.5.2. Reading from File](#content)

`readCardsFromFile()` and `readTestsFromFile()`

These two methods are executed every time the CardLI application is opened.
The methods use an instance of the `Scanner` class to parse through the text files line by line. 
As per the saving format explained in the `writeToFile()` method above, the 
`readCardsFromFile()`/`readTestsFromFile()` methods essentially reverse engineer the process to save the user's 
data into the application before any commands are given from the user. The individual methods
will be explained in more detail in the following paragraphs.

##### [4.5.2.1 `readCardsFromFile`](#content)

![](assets/readCardsFromFileSequenceDiagram.png)

The `readCardsFromFile()` method reads from the `Cards_CardLI.txt` file.
A generic instance of this method will be explained using the screenshot of the respective file given 
above.
Once the method is invoked, a new instance of an `ArrayList`  of `Deck` instances, called `decks`, 
is created to store the `Deck` instances that are parsed from the text file.
Line 1 is parsed as the number of `Deck` instances that are expected within the text file.
Then, Lines 2-5 represent the first `Deck` instance while lines 6-8 represent the second `Deck` instance.
Since the decks are saved in a pre-determined format within the text files, the `parseDeck(Scanner s)` 
method has been abstracted to parse decks from the text file and convert them to `Deck` instances
to be returned from the method. 
For each of the `Deck` instances, the first line is parsed as the name of the deck.
The second line is then parsed as the number of flashcards to expect within the deck.
The remaining lines of each `Deck` instance is parsed as a flashcard each by splitting the parsed line based on the
regular expression, `" | "`. 
All the parsed data is converted to the relevant data types and passed as arguments to form a `Deck` 
instance.
Lastly, all the individually parsed `Deck` instances are added to the `decks` variable and returned from
the method. 

##### [4.5.2.2 `readTestFromFile`](#content)

![](assets/readTestsFromFileSequenceDiagram.png)

The `readTestsFromFile()` method reads from the `Tests_CardLI.txt` file.
A generic instance of this method will be explained using the screenshot of the respective file given
above.
Once the method is invoked, a new instance of an `ArrayList` of `AnswerList` instances, called 
`testHistory`, is created to store the `AnswerList` instances that are parsed from the text file.
Line 1 is parsed as the number of `AnswerList` instances that are expected within the text file.
Lines 2-9 represent the 1 `AnswerList` instance that is expected within this text file.
Since the answer lists are saved in a pre-determined format within the text files, the 
`parseAnswerList(Scanner s)` method has been abstracted to parse answer lists from the text file and 
convert them to `AnswerList` instances to be returned from the method. 
For each of the `AnswerList` instances, the `parseDeck` method explained in the previous section
is first called to parse a `Deck` instance from the text file, representing the deck of flashcards
for which the answers correspond to. This is seen in lines 2-5 in the screenshot provided.
The next line is parsed as the number of answers that are expected for the answer list.
Subsequently, 1 line is read for each answer that is expected, where the parsed line is split based on the
regular expression, `" | "`.
The last line of each `AnswerList` instance is parsed as the score obtained by the user for the answer list.
All the parsed data is converted to the relevant data types and passed as arguments to form a `AnswerList` instance.
Lastly, all the individually parsed `AnswerList` instances are added to the `testHistory` variable and
returned from the method. 

## [5. Product scope](#content)

### [5.1. Target user profile](#content)

* Pre-University/University/Polytechnic students
* Reasonably comfortable using CLI apps
* Types fast
* Prefers to store their information online rather than physically
* Has a lot of flashcards

### [5.2. Value proposition](#content)

CardLI provides a:
<li> User-friendly </li>
<li> Storage efficient</li>
<li> Internet connection independent flashcard experience.

## [6. User Stories](#content)

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

## [7. Non-Functional Requirements](#content)

<li> The app should be usable by a user who is reasonably comfortable using Command Line Interface.</li>
<li> The app should be able to handle at least 25 flashcard decks of at least 25 cards each.</li>
<li> The app should work on a computer that has Java 11 or above installed.</li>
<li> The app should store data in a format that is readable by humans, and easy for machines to parse and generate.</li>


## [8. Glossary](#content)

* *glossary item* - Definition

## [9. Instructions for Manual Testing](#content)

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
