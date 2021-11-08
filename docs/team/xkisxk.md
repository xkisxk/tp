# Jiang Xing Kai - Project Portfolio Page

## Project: CardLi

CardLI is a Command Line Interface (CLI) desktop app designed to help users manage and test their flashcards. CardLI can
help users keep track of all their flashcards and to test their knowledge. It is written in Java and contains about
6kLoC.

Given below are my contributions to the project.

## Summary of Contributions

---

### Features Implemented

- Review Feature:
    - Tests all cards that scored less than 50% across all tests combined.
    - This feature allows users to test themselves on cards that they need help with, increasing the efficiency of their
      studying.
    - The implementation was challenging due to the need of keeping track of the user score and total score of each
      flashcard, which led to the addition of view flashcard statistics feature. Another reason is that the review
      feature, like test feature, works quite differently compared to the other features, so the implementation of the
      command needed multiple revisions to get it working.


- View Statistics Feature:
    - Allows users to view a single of all previous test scores and all accumulated flashcard scores.
    - This feature allows users to keep track of their previous tests, and focus on the tests that require more work.
    - This required an `AnswerList` and `TestHistory` class that keeps track of the compiled answers for each test, and
      as mentioned in the Review Feature, expanded the `Flashcard` class to keep track of the user score and total
      score.

### Feature Enhancements:

- Wrote Junit tests to existing features, mainly `TestManager`, `TestUi` and `TestHistory`
- Allow testing for individual decks or all decks, and repackaged it

### Code Contributed:

Code contribution can be found at the
[RepoSense Link](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=xkisxk&sort=groupTitle&sortWithin=title&since=2021-09-25&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=xkisxk&tabRepo=AY2122S1-CS2113T-F12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

### Documentation:

- User Guide:
    - Added documentation features for features `viewtest`, `viewfc`, `review`, `delete`
      [#75](https://github.com/AY2122S1-CS2113T-F12-1/tp/commit/a1d5f600678dce14bbf3438fbfbe8e78641ef377)
    - Added anchor and How To Use section
      links [#188](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/188/commits/9914206a06c69f1d155fb8045015280d199e1ee7)
- Developer Guide:
    - Wrote implementation details of `test` feature including the UML diagrams.
      [#89](https://github.com/AY2122S1-CS2113T-F12-1/tp/commit/1c3adf0706446b7a775c1ba744394abfbf5edba8)
    - Wrote manual user testing for `view test`
      features. [#207](https://github.com/AY2122S1-CS2113T-F12-1/tp/commit/052ae91db88e401c109b33c6ebee83f14977b0ce)

### Community:

- Reported bugs and suggestions to improve other team's
  [developer guide](https://github.com/nus-cs2113-AY2122S1/tp/pull/1/files)
- Tested and reported bugs for other team's program: [1](https://github.com/xkisxk/ped/issues/1)
  , [2](https://github.com/xkisxk/ped/issues/2),
  [3](https://github.com/xkisxk/ped/issues/3)
- Reviewed teammate's code: [1](https://github.com/AY2122S1-CS2113T-F12-1/tp/pull/191)


