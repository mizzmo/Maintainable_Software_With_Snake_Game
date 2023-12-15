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

### Settings Tests
- Does the background appear?
  - A jungle background appears.
- Does the title appear?
  - There is a title in the top middle of the screen.
- Is there a volume scroll bar?
  - There is a volume scrollbar in the middle of the screen with a message displaying its volume.
- Is there a snake size scroll bar?
  - There is a snake size scroll bar in the middle of the screen that displays the current snake size.
- Are there two checkboxes?
  - There are two checkboxes, one for high contrast mode and one for walls.
- Is there a back button?
  - There is a back button at the bottom of the screeen.
- Does the back button work?
  - The back button takes you back to the main menu.
- Can you click the checkboxes?
  - You can click both checkboxes.
- Do the checkboxes work?
  - The checkboxes seem to work when you click them, high contrast mode is turned on when you go into game, and the walls are turned off when you uncheck them. The opposite is also true.
- Do the checkboxes stay checked when you exit and reeneter the settings menu?
  - When you exit and reenter the settings, the checkboxes stay checked to where you put them.
- Does the snake size scrollbar work?
  - The snake scrollbar seems to work, the value updates above it, and when you enter the game, the snakes size has changed to whatever you set it as.
- Does the volume scrollbar work?
  - The volume scrollbar works as you can hear and see the volume change as you move it around.
- Do the scrollbars stay where they are when you exit and reenter the settings menu?
  - The scrollbars stay where you leave them when you exit and reenter the settings screen.

### Map Select Tests
- Is there a background?
  - There is a jungle background.
- Is there a title?
  - There is a title in the top middle of the screen.
- Are there images of the possible options to pick from?
  - There are three images on the screen, one has a white ring around it to indicate it is currently selected.
- Are there two buttons to click?
  - There are two buttons, back and start game. You can click both.
- Is there a label that tells you which map you have chosen?
  - There is a label above the selected image that tell you the name and a message saying you have selected it.
- Can you click the images?
  - You can click the images to change the selection.
- Does the selection change when you click the images?
  - The white box and message follows which box you click, so you can tell what is selected.
- Does the correct map appear when start the game.
  - The selected map appeared when i started the game, this worked for all images.
- Does the back button work?
  - The back button takes you back to the main menu.
- Does the start game button work?
  - The start game button takes you to the game page and starts the game.

### Game Scene Tests.
- Does a background appear when you start the game? 
- Does a wall appear if you have walls enabled?
- Does music play?
- Does the music loop?
- Is there a score label?
- Does the snake appear in the middle of the screen?
- Can you move the snake?
- Can you move the snake with WSAD?
- Can you move the snake with arrow keys?
- Does the snake body follow where the head moves?
- Does a fruit appear somewhere on the screen?
- Can you eat the fruit as the snake?
- Does the fruit dissapear and another appear when you eat it?
- Does the score increase when you eat a fruit?
- Does the snake grow by 1 when you eat a fruit?
- Does the snake get faster when you eat a fruit?
- Does the game end when you hit a wall?
- Does the game end when you hit yourself?
- Does the game end when you go out of bounds?
- Do the walls change position and orientation every 5 seconds.
- Can you still hit the walls when they move?
- Do bonus fruits occasionally appear?
- Does the score increase more with a golden apple?
- Does the score decrease with a rotten apple?
### Pause Menu Tests.
- Does the pause menu appear when you press ESC?
- Does the pause menu disappear when you press ESC again?
- Is there a title?
- Is there a volume slider on the screen?
- Is there a maim menu button?
- Does the main menu button work?
- Is there a restart button?
- Does the restart button work?
- Is there a label telling you how to unpause?
- Is there a dark strip behind the pause menu?
- Does the snake stop moving?
- Does the fruit stay where it is?
- Do the walls stop moving?
- Do the walls start moving again when you unpause?
- Does the snake keep moving after unpausing?
- Can the fruit still be eaten after unpausing?
### Game Over Tests. 
- Is there a game over message?
- Does the snake dissapear?
- Does the fruit dissapear?
- Does the wall dissapaer?
- Is there a final score label?
- Is there a countdown to restart?
- Does the game restart when the time is up?
- Is there a restart button?
- Is there a main menu button?
- Does the restart button work?
- Does the main menu button work?
- Can you enter your username?
- Is there a confirm button?
- Does the confirm button work?
- Is there a message telling you you have submitted your username?
- Can you type another username after clicking confirm?
- Is the username added to the leaderboard?