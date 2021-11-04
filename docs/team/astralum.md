# Jin Minyue  - Project Portfolio Page

## Project: CardLI
CardLI is a desktop Command Line Interface (CLI) application that allows users to create, organise, test and 
review their flashcards. It is written in java and has about 4kLoC.

Below are my contributions to the project.

### Summary of Contributions

#### Features Implemented
* **View flashcards**
  * What it does: Prints all the flashcards in the current deck to the standard output. Each flashcard is formatted
such that the front and back are printed side by side, with centered-justification and line-wrapping.
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
* Implemented timer feature in the testing mode

#### Contributions to the UG

#### Contributions to the DG

#### Contributions to team-based tasks