

## Toby Surtees - Developing Maintainable Software.
![COMP2013 Cousework Snake Logo](assets/comp2013snakeLogo.png)\
Contact: *psyts20@nottingham.ac.uk*
### Contents:
 - [Introduction.](#introduction)
 - [Initial Notes on Code.](docs/initialNotes.md)
 - [Initial Notes on Structure.](docs/notesOnStructure.md)
 - [Diagrams.](docs/diagrams.md)
 - [Coding Conventions.](docs/codingConventions.md)
 - [Development Notes.](docs/devNotes.md)
 - [Changelog.](docs/changelog.md)
 - [Testing Methods & User Tests.](docs/testing.md)
 - [JavaDoc.](javadoc/comp2013)
 - [Code.](comp2013/src/main/java/)
 
<a name="introduction"></a>
### Introduction.
This project is a simple Snake game. The user controls a snake an the aim is to eat fruits
which give you points. You can't hit yourself, the border walls, or the red walls, 
as this will end the game. The goal is high score.

#### Installing and Running the project.
How to run the game:
1) Fork and Download the entire project.
2) Launch IntelliJ, Click import and select the project folder.
3) Navigate to the SnakeMain Class.
4) Click the Green Arrow next to the class name to play the game.
5) If you get an error finding the highScore.txt, you will have to change the file path in leaderboardScene and SnakeView to the correct path for your computer, as this depends on where you stored the file on your system.
#### How to play the game

1. You will be greeted by a main menu, click start.
    1. If you want to change any settings, click settings, then click back when you are done.
    2.  If you want to view the leaderboard, click leaderboard, then click back when you are done.
2. Next you will see a map select screen, select the map you want to play on and click start game.
3. Now you will be in the game, use WSAD or Arrow Keys to move the snake.
    1. You must avoid the **RED WALLS**.
    2. You should try to **COLLECT FRUIT** to **EARN POINTS**.
    3. Golden apples grant bonus points.
    4. Rotten apples take away points.
    5. You must not exceed the **SCREEN BOUNDARIES**.
4.  You can **PRESS ESCAPE** "ESC" to pause the game at any time.
5. The snake will get faster as your score increases.
6. The aim of the game is to get as many points as possible before you die.
7.  After the game ends;
    1. If you wish to restart, click restart.
    2.  If you wish to go back to the main menu, click main menu.
    3.  If you wish to record your score, type your username and click confirm.
8. Remember to HAVE FUN!



