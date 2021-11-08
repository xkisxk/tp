# Thaddeus Lim - Project Portfolio Page


### Project: CardLI

CardLI is a desktop app that helps you create, organise, and review flashcards via a Command Line
Interface. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to edit card content and deck name.
    * What it does: Allows the user to change the front and back of a card, as well as the name of decks.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands 
  and the app should provide a convenient way to rectify them.
    
* **New Feature**: Added the ability to move a card from one deck to another.
  * What it does: Allows the user to transfer a card from one deck to another.
  * Justification: This feature improves the product significantly because a user can make mistakes in placing a card in
    a certain deck and may wish to move it to another deck in a convenient way rather than having to delete the card and
  add the card in another deck.
  * Comments: This feature was slightly harder to implement compared to the `edit` feature as it needed access to 
  both the system level and the deck level, whereas `edit` either used the system level or the deck level but not both
  at the same time

* **New Feature**: FlashCard Testing.
  * What it does: Through creating the initial Answer, AnswerList and TestManager classes, users can test his/her
  knowledge with the flashcards.
  * Justification: This feature is essential to the product as the purpose of flashcards is to test oneself with them.

* **New Feature**: Capability to skip questions during tests.
  * What it does: Added the ability to skip a question during tests.
  * Justification: This feature improves the product significantly as a user may not know the answer to a flashcard
  and may want to come back to the question later.
  * Comments: This feature was more of a challenge than initially thought. There were many bugs to take care of and the
  underlying method of testing had to be changed to enable this feature. Instead of a for loop testing all the 
  flashcards in a deck, the use of a counter variable to keep track of the current question being tested and a flag
  to check if a question has already been answered had to be developed.

* **New Feature**: Added help command.
  * What it does: Added the ability to ask for the list of commands.
  * Justification: This feature improves the product significantly as a user may not want to refer to the user guide
  for instructions.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=ThaddeusLim99&tabRepo=AY2122S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Project management**:
    * Managed milestone V1.0 and V2.1
    * Ensured tracker progress for V2.0 and V2.1
    * Reviewed team's PR. Example: [\#188](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/188)

* **Enhancements to existing features**:
    * Created CardLI logo
    * Fixed some PED bugs and wrote additional tests for existing features to increase test coverage: [\#173](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/173), [\#176](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/176/commits/4a65edacd11031aa9171799af420bb412d415ddb)

* **Documentation**:
    * User Guide:
        * Added logo: [\#176](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/176/commits/4a65edacd11031aa9171799af420bb412d415ddb)
        * Added documentation for the features `edit` (both card and deck): [\#76](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/76/commits/3b7d831011e154f6b354cf6f130344fb7f947b60), [\#87](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/87/commits/be81fd470d6bb00ae978f801be4b194aa8dffeb7)
            * Including Sequence diagrams for `edit`
        * Added documentation for the features `move`: [\#92](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/92/commits/c22db9da9d6b8532e5f78b5759969e74bf468a86)
            * Including Sequence diagrams for `move`
    * Developer Guide:
        * Added logo: [\#176](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/176/commits/4a65edacd11031aa9171799af420bb412d415ddb)
        * Added implementation details of the `edit` feature (for both cards and deck): [\#65](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/65/commits/f996afecdada4c5118fba93f1a43893fed89c2ed)
        * Added implementation details of the `move` feature: [\#92](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/92/commits/7580984d8ec14af1bca8d4f213ff4e6b30e79074)
        * Added table of content and enabled anchor links: [\#191](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/191)
        * Added manual testing instructions for `edit` and `move`: [\#199](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/199/commits/720dc0808b9bb05d96f607781850e9f4efc29cfc)

* **Community**:
    * Reported bugs and suggestions for other teams' [developer guide](https://github.com/nus-cs2113-AY2122S1/tp/pull/33#pullrequestreview-792605786)
    * Tested and reported bugs for other team's program.
      Examples: [1](https://github.com/ThaddeusLim99/ped/issues/1) ,[5](https://github.com/ThaddeusLim99/ped/issues/5)
      , [6](https://github.com/ThaddeusLim99/ped/issues/6)