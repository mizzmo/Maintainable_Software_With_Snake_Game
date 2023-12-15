## Testing
My first round of testing will be to check that all of the method getters and setters, 
as well as all of the non-gui based methods work correctly using JUnit.
<br>
I will write tests for all of these methods, and run these tests to check that they work correctly.

- Controller Tests - All Pass.
- SnakeBody Tests - All Pass.
- SnakeModel Tests - All Pass.
- SnakeObject Tests - All Pass
- SnakeFood Tests - All Pass.
- SnakeMusicUtil Tests - All Pass.
- SnakeWall Tests - All Pass.

Next I need to run some user tests to check that the GUI elements all work correctly.
<br>
I will do this by scene, rather than by class to make it easier to structure my tests, 
as some scenes stretch over muliple classes.

### Main Menu Tests.
- Does the background correctly load?
    - There is a jungle background on the menu screen.
- Does the music play?
  - The user can hear the menu music playing while they are on the menu screen.
- Does the music loop correctly?
  - The music does loop after the song finishes playing.
- Are all the buttons clickable?
  - All the buttons are clickable and animated.
- Do all the buttons take you to their labeled location?
  - Does the exit button exit the game?
    - When you press the exit button, the game exits.
  - Does the Leaderboard button take you to the leaderboard page?
    - When you click leaderboard, it changes scenes to the leaderboard page.
  - Does the Settings button take you to the settings page?
    - When you click settings, it takes you to the settings page.
  - Does the Start button take you to the game select screen?
    - When you click start, it changes scenes to the map select page.
- Does the title appear on the screen?
  - There is a large title at the top middle of the screen.

### Leaderboard Tests
- Does the background correctly load?
  - There is a jungle background on the leaderboard screen.
- Does the leaderboard appear?
  - The leaderboard appears in the center of the screen.
- Does the leaderboard title appear? 
  - The title is in the top middle of the screen.
- Does the music continue playing from the main menu?
  - The music seamlessly keeps playing on the leaderboard screen
- Does the back button appear?
  - There is a back button on the middle bottom of the screen.
- Is the back button clickable?
  - The back button is animated and clickable.
- Does the back button take you back to the main menu?
  - The back button takes you back to the main menu when clicked.
- Is the leaderboard shown in order?
  - The leaderboard is in order of high score.
- Is the leaderboard scrollable?
  - The leaderboard can be scrolled down to see entries at the bottom.


