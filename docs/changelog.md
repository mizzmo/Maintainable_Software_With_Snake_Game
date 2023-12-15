[Back to README](../README.md)\
\
\
[<-- Development Notes](devNotes.md)



## Changelog
### 9/11/23
- Removed Unused Files / Classes.
- Added JavaDoc to Project.
- Removed Commented Out Code.
- Reworked some code comments.
### 15/11/23 
- Renamed some folders.
- Rearranged Files. Introduced resources and java folder.
- Moved Classes to java folder.
- Moved Images to resources/images folder. Music to resources/music folder.
- Reworked getImage method in GameUtil.
- Added module.info
- Added gitignore.
### 28/11/23
- Reworked Project into a Maven Project.
- Added Dependencies to the pom file.
- Reworked module-info file.
- Reorganised class files.
- Changed some file locations in code to fit with the new Maven Build Method.
- Added some new issues to Git.
### 29/11/23
- Wrote up plan for MVC in DevNotes.
- Added Interface Classes for MVC.
- Implemented Methods for MVC interfaces.
- Implemented Constants in IController Interface.
- Added Class files for MVC.
- Removed Redundant Main Class.
### 30/11/23
- Implemented Getters and Setters in the Model.
- Added some private variables in Model.
- Impmemented part of Initialise method in Model.
- Started to implement InitialiseDisplay method in view.
- Changed variable names so that they follow the coding convetntions. 

### 6/12/23
- Changed comments on SnakeView from JavaDoc to Regular Comments, as JavaDoc is inherited from the Interface Class.
- Changed comments on SnakeController from JavaDoc to Regular Comments, see previous.
- Added two methods to the model interface; getAlive and setAlive, to get and set the liveliness status of the snake, to be used for things like ending the game when the snake dies.
- Added a new variable to SnakeModel, m_Alive.
- Implemented methods in SnakeModel, incrementLength, getAlive, setAlive.
- Reworked SnakeView initialiseDisplay to respect MVC, view now has no interaction with model.
- Added constructors to View, Controller and Model.
- Added a new Class, SnakeMain, that intialised all of the MVC.
- Started to convert the program to JavaFX. 
- Set up JavaFX.
- Fixed an issue where the controller was not initialised in the View beacuse of a issue with how JavaFX handles its instance of View.
- Added an Icon to the JavaFx.
- Implemented FXML.
- Updated Maven POM with dependencies.
- Updated module-info.
- Linked FXML to the Controller and View.

### 7/12/23
- Fixed issue with controller.
- Added image to screen.
- Added Score Label to Screen.
- Create a new Class SnakeObject, from the SnakeObject class in MyFrame.
- Rename interface Movable.
- Add new functions to interface Movable.
- Create a new class SnakeBody that stores information about the snake body parts.
- Added a new method buildSnake in SnakeView that builds the initial snake.
- Added a new method in Controller addSegment that appends a new segment to the list in SnakeObject.
- Add new method to IController handleKeyPress.
- Add method to View, refreshSnake, which rebuilds the entire snake to the new loaction.
- Added constants for WSAD and Arrow Key, Key codes
- Added method handleKeyPress to Controller to handle what happens when a key is pressed.
- Added code to view to start the infinite loop that refreshes the screen.
- Reimplemented eatBody as getSelfCollide in Controller.
- Rename getSelfCollide to checkSelfCollide.
- Removed getSnakeMoving from controller as it is not needed.
- Added handleGameover function to Controller.
- Added 3 new Images, snake-head-"left", "up", "down".
- Added method to change the direction the image is facing.
- Update moveSnake to add snake rotation functionality from view.
- Fixed bug with controller not being set view correctly.
- Add method to SnakeObject that stores the previous direction.
- Updated setDirection to set the previous direction.
- Updated SnakeObject to store the previous X and Y Values.
- Removed method from Controller, turnSnake.
- Completed snakeMovement.
- Found new issue where the snake jumps along, rather than moving slowly.

### 8/12/23
- Rename Food class to SnakeFood.
- Removed GameUtil Class. Moved its methods to ImageUtil.
- Reworked ImageUtil to work with JavaFX.
- Removed RotateImage in ImageUtil.
- Reworked getImage method in imageUtil.
- Added new comments and JavaDoc to SnakeFood.
- Reworked SnakeFood to work with JavaFX.
- Added new method to SnakeFood, newFruit, which creates a new fruit.
- Added a label to track score to screen.
- Added CSS File and customised Score label.
- Fixed issue where food would appear and dissapear immidiatelly.
- Food is now complete.
- Updated view to handle how collecting food.
- Updated view to add a new segment onto the snake when food is collected.
- Updated score funtion so that it now shows the score as food is collected.
- Updated how the snake self collision is handled, removed an extra check.
- Added a method to view "gameOverScreen" that handles what happens when a game over event is triggered.

### 9/12/23
- Fixed issue where boundary detection is to sensitive.
- Removed Play and MyFrame Classes.
- Renamed Movable to IMovable.
- Renamed ImageUtil to SnakeImageUtil
- Removed SnakeThread Class.
- Renamed MusicPlayer to SnakeMusic.
- Removed Music methods from SnakeView and IView.
- Created a new Interface, IMusic.
- Implemented IMusic in SnakeMusic.
- Added JavaFX Descriptions to my Interfaces.
- Added method to SnakeMusic to set looping of music.
- Added code to SnakeView that calls the SnakeMusic class.
- Added a proper end screen which show the final score, and has a countdown to when the game will restart.
- Updated how the game ending is handled, moved to moveSnake method.

### 10/12/23
- Added mechanism for restarting the game in Controller. Method restartGame.
- Added method to view restartGame that resets the UI elements.
- Added two buttons to the end screen to restart the game and to go back to the main menu. (Not yet implemented.)
- Added CSS to the buttons to style them.
- Commented CSS.
- Implemented Restart Button.
- Renamed Stages and Scenes, made Private Class Vairables.
- Added new method to View, setMenuScene that will change the scene to the menu.
- Added new image "jungle-background".
- Renamed image "cloud-background"
- Removed theEnd image.
- Added method to View, setGameScene.
- Moved the snake game scene from start to setGameScene.
- Reworked how start operates.
- Added UI elements to the menu. 
- Added a settings button and a exit button.
- Added CSS to customise button size.
- Added new class SnakeMusicUtil to handle music files going forward.
- Added music to the menu.
- Added methods to SnakeMusic, "setVolume, volumeMute, increaseVolume, decreaseVolume, pauseMusic".
- Reworked stopMusic so that it disposes of the old media player once done.
- Fixed a bug with restartGame where it would interfere with the menu music.

### 11/12/23
- Reworked how the controller restartGame method works. No longer creates a new model.
- Removed the finished variable from model.
- Removed getters and setters for finished variable from model.
- Added a slider that changes the volume to the settings page.
- Updated setVoulme type to double from float.
- Fixed bug with Volume slider where the volume would reset when you switch scenes.
- Added new method to SnakeMusic, "getVolume".
- Added SnakeLength slider.
- Implemented SnakeLength slider.
- Reworked how the snake is build and refereshed to suit this new slider.
- Added InitialLength to Model.

### 12/12/23
- Created spritesheet for Fruit and Snake assets.
- Added Leaderboard button to main menu.
- Added method to view, setSelectScene.
- Added method, initialiseMenuScreen, to reduce duplicate code. This method initialises a default screen for menu pages.
- Updated View methods to use this new method.
- Added class variables, M_DefaultPane, M_DefaultLabel to allow control of the label and stackpane after its addition in the method.
- Added new background images "ocean-background", "grass-background"
- Updated SnakeImageUtil.
- Added other ImageViews to the screen.
- Added a selection message and a border around the selected map.
- Converted to 3000x2000 Resolution.
- Added code that resizes the backgrounds so they work correctly.
- Implemented Map Select Screen so it is now functional.
- Implemented Speed Increase mechanics, snake speed increases with each fruit consumed.
- Added leaderboard page, method "setLeaderboardScene".
- Implemented setLeaderboardScene.
- Add CSS for leaderboard entries.
- Added code for a text box to enter a username.
- Added a button in the end game that saves the entry of the text box into a txt file and saves the score next to it.
- Updated View class so that line length is shorter than 80 characters.
- Added styling to the text field and button.
- Implementing functionality that reads the text file, parses the output and adds it to the scoreboard.
- Add a border to the scoreboard.
- Move the header into a better position.

### 13/12/23
- Created a new high resolution sprite for the snake.
- Replaced the old sprites with the new ones in imageUtil.
- Updated gc.drawImage to include height and width restrictions.
- Removed Unused Images.
- Added new packages for View, Model and Controller.
- Sorted classes into these packages.
- Refactored code to work with these new locations.
- Fixed issue where snake speed would not reset after a restart.
- Fixed issue where Countdown would overlap with previous countdown.
- Updated where fruit can be placed on the screen.
- Added images for golden fruit to ImageUtil.
- Added functionality to bonus fruits, golden is +500 score, rotten is -100 score.
- Added timeline that waits 5 seconds before removing the bonus fruit.
- Fixed issue where timeline would generate a new fruit after game over if you died while it was still running.
- Removed melon image as it is too big and the wrong aspect ratio.
- Add a KeyListner to get when ESC is pressed.
- Added a case to the Controller to handle when ESC is pressed.
- Added method to view "pauseGame" to handle what happens when the game is paused.
- Added variable M_GamePaused to track if the game is paused.
- Added a volume slider to the pausemenu.
- Added feature where if you press ESC again, it takes you out of the menu.
- Added Restart Button to pause menu.
- Added Main Menu button to pause menu.
- Split the unpause functionality into a seperate method so that other methods can use it if need be.
- Added variable M_GameOver to track if the game is over or not.
- Added M_GameOver to be false as a condition of the pause menu appearing.
- Fixed a bug with the Fruit timeline where it wouldnt stop after the bonus fruit was eaten.
- Added variable to model "m_ColourMode"
- Added getters and setters for m_ColourMode.
- Created new checkbox for Colour Mode in Settings.
- Added CSS to the checkbox. Applied to Checkbox.
- Removed the FXML file as it wasnt used.
- Added the effect to the snake in SnakeView. 
- Added the effect to the fruit in drawFruit in SnakeFood.
- Fixed issue where the effect would stop the screen being cleared.
- Updated checkbox so that it will appear already checked if the colour mode has already been set.

### 14/12/23
- Updated POM File.
- Added new class "SnakeWall".
- Added contructor to SnakeWall.
- Added new method to SnakeWall "checkHitWall" that checks if the snake has hit the wall.
- Added method to SnakeWall, "drawWall" to draw the wall to the screen.
- Added method "rotateWall" to SnakeWall, that will change the direction the wall is layed on.
- Added private variable M_SnakeWall to View to store the snakeWall object.
- Added a new canvas to View "m_WallCanvas", implemented the first wall drawing in view.
- Added new method to SnakeWall, "newWall" which generates new coordinates for the wall.
- Added a check to drawWall which checks if the wall is about to be built ontop of the snake.
- Added timeline to setGameScene which builds a new wall every 5 seconds.
- Added checks to Controller which check every time the snake moves, if it has hit the wall or not.
- Added checkbox to settings to toggle if you want to use walls or not.
- Added variable to model "m_WallMode".
- Added getters and setters for WallMode variable.
- Added a check to newWall to stop the wall spawning ontop of the snake.
- Fixed issue where the map wouldnt change back to default after going back to the main menu.
- Added code to implemet the checkbox to disable the walls if not checked.
- Update code that removes the wall after a game over.
- Added new classes for each scene in a new directory "SnakeScenes".
- Implemented MainMenuScene in its new class.
- Implemented GameScene in its new class.
- Implemented SettingsScene in its new class.
- Implemented Leaderboard Scene in its new class.
- Implemented MapSelect Secne in its new class.
- Refactored the View Class to follow the coding conventions.
- Adding all class test classes and methods to be implemented.

### 15/12/23
- Updated module-info to include Junit testing.
- Update POM File to use JUnit 4.
- Added tests for SnakeBody.
- Added code so that you cant resize the game window.
- Added tests for SnakeModel
- Added tests for SnakeObject.
- Removed unused methods from snakeObject, increase/reduceSpeed.
- Added tests for Controller.