# Joanne Wong - Project Portfolio Page

## Overview

CardLI is a Command Line Interface app that helps students manage their flashcards. Students can organise their
flashcards and test their knowledge of them with CardLI.

### Summary of Contributions

#### Code Contributed

My RepoSense Link can be
found [here](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=jwweiyin&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=JWweiyin&tabRepo=AY2122S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

#### Enhancements Implemented

1. Adding and deleting of flashcards (Pull requests [#7](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/7)
   , [#19](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/19))
These functions add and delete flashcards from the deck.
Initially, we allowed the user to delete flashcards by providing the **index of card** or the **content on the front of
the flashcard**. However, this led to unexpected outcomes when users added cards which front only contained a positive
integer. Hence we discarded the delete by content method as there was insufficient time to design an elegant
implementation to handle such cases.

2. Adding of decks (Pull requests [#49](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/49))
This function allows multiple decks to be managed by the program.

3. Inner Parser (Pull requests [#60](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/60)
   , [#61](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/61))
The inner parser handles all commands in deck mode, separate from the parser handling the commands in the main menu.
Initially, both deck mode and main menu commands were handled within one parser, but doing so required more commands and
flags (*add* was used to add flashcards to a deck, *adddeck* was used to add a deck). To make the usage of the app more
intuitive, the deck mode commands were extracted and placed in their own parser.

4. Flashcard search (Pull request [#81](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/81))
This function searches for and displays flashcards matching the search term input by the user. It allows the user to
find certain flashcards without entering each deck and manually looking through all the flashcards. The function is not
case-sensitive.

5. JUnit Testing
Wrote tests for JUnit for add, edit and delete flashcard functions.

6. Bug fixes for v2.1 (Pull requests [#164](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/164)
   , [#172](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/172) )

#### Contributions to the UG

Added screenshots to show sample output of the commands. Wrote `find`, `help` and `bye` sections. Wrote command summary.
Edited descriptions and format of commands for
consistency. ([#179](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/179))

#### Contributions to the DG

Wrote and constructed architecture diagrams for overall architecture, model component, logic component. Wrote design
details and constructed sequence diagram under `find`. Contributed to the manual testing section for adding decks,
finding flashcards and adding flashcards.

#### Contributions to team-based tasks

Documented user stories and non-functional requirements in DG. Managed merge conflicts in all team meetings. Edited and
rephrased UG and DG for clarity and consistency.

Tested and reported bugs for other team's apps. (Examples: [1](https://github.com/JWweiyin/ped/issues/4)
, [2](https://github.com/JWweiyin/ped/issues/5), [3](https://github.com/JWweiyin/ped/issues/7))
