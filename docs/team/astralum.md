# Jin Minyue - Project Portfolio Page

## Project: CardLI
CardLI is a desktop Command Line Interface (CLI) application that allows users to create, organise, test and
review their flashcards. It is written in java and has about 4kLoC.

Below are my contributions to the project.

### Summary of Contributions

#### Features Implemented
* **View flashcards**
  * What it does: Prints all the flashcards in the current deck to the standard output. Each flashcard is formatted
    such that the front and back are printed side by side, with centred-justification and line-wrapping.
  * Justification: This feature is essential so that users can know which cards they have in their decks.
  * Highlights: It required the creation of several helper methods to split the lines of the front and back, pad
    the one with fewer lines with empty lines, pad the last line with whitespaces to center it, and finally join each
    line of front and back to be displayed on the same line.
* **Countdown timer**
  * What it does: Prints a countdown timer to the standard output, starting from the given time. When used while
    testing, ends the test prematurely if the user hasn't finished it, and will reject the answer for the current
    question the user is at when the timer runs out.
  * Justification: It simulates the time-limited testing conditions users undergo when preparing for tests.
  * Highlights: It was difficult and complicated to implement. It relies on the relative position of the user's cursor
    to clear the current timer line and print the new timer line with the updated time. Since it is updated and prints in
    the background asynchronously, it might not always appear in the correct location. A workaround had to be implemented
    to allow it to start printing after a certain amount of time has elapsed so that the other synchronous printing tasks
    will be completed first.
  * Credits: Jansi, a library that enables ANSI escape sequences to be used

#### Code Contributed
[tP Code Dashboard](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=astralum&tabRepo=AY2122S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

#### Enhancements Implemented
* Implemented timer feature in the testing mode, including ending the test when the time is up

#### Others
* Refactored code to use `Command` classes that upon calling `execute()` would return a `CommandResult` to be printed.
  Each type of command had its own `XYZCommand` that inherited from `Command` and implemented an `execute()` that was
  specific to it. [#69](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/69)
  * Justification: The code was clunky and the storage-related methods had to be implemented in the same
    class as the Logic or even Model components. Abstracting out the `Command` classes helped make the code simpler,
    more understandable and easier to use.
  * Highlights: It was difficult and extremely time-consuming as changing attributes to be non-static meant many
    methods that depended on static methods were broken. In addition, many existing methods that handled logic
    had to be moved to the created `Command` classes, and many existing methods that handled parsing input had to be moved
    to the new `Parser` classes created. The original `Parser` class was also split into two so that one, the `InnerParser`,
    handled commands occurring in the main menu and the other, the `OuterParser`, handled commands occurring in the deck menu.

#### Contributions to the UG

* Added the features, adding a deck and viewing a deck: [#85](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/85)
* Added explanation of timer during a test: [#208](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/208)

#### Contributions to the DG

* Added acknowledgements: [#82](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/82), [#189](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/189)
* Wrote the original Design: [#71](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/71)
* Added implementation details of `Countdown`: [#194](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/194)
  * Added sequence diagram of how `Countdown` works
* Added explanation of how the test interacts with the timer: [#194](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/194)
* Added instructions for manual testing of the features test and review: [#208](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/208)

#### Contributions to team-based tasks
* Added Javadoc to many classes: [#194](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/194), etc.
* Added Jansi to build.gradle: [#93](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/93)
* Tested the programme on Linux

#### Reviewing/mentoring contributions
* Helped teammates better use the "Tell, Don't Ask" principle

#### Contributions beyond the project team
* Tested and [reported bugs](https://github.com/astralum/ped/issues) in the software of another team
* [Reviewed and commented](https://github.com/nus-cs2113-AY2122S1/tp/pull/34#pullrequestreview-792610303) on the Developer Guide of another team
