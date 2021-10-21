# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

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

CardLI is a Command Line Interface (CLI) flashcard application designed for students who can type quickly.

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
