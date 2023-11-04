## Initial notes on the structure of the code.
[<-- Back to README](../README.md)\
[Diagrams -->](diagrams.md)\

[Main Class -- ](#main-class---no-methods)[Food Class -- ](#food-class---constructor-2-methods)[GameUtil Class -- ](#gameutil-class---3-methods)
[ImageUtil Class -- ](#imageutil-class---constructor)[MusicPlayer Class -- ](#musicplayer-class---constructor-2-methods)[Movable Class -- ](#movable-interface-class---1-method)
[MyFrame Class -- ](#myframe-class---constructor-4-methods)[MyThread Class -- ](#mythread-class---no-methods)[MySnake Class -- ](#mysnake-class---constructor-8-methods)
[SnakeObject Class -- ](#snakeobject-abstract-class---2-methods)[Paddle Class -- ](#paddle-class---constructor-1-method-6-unused-methods)[Play Class -- ](#play-class---4-methods)
[Snake Class](#snake-class---2-methods)
### Main Class - (No Methods)
- All that main does is print a string.
- It has no relations to other classes or code.

### Food Class - (Constructor, 2 Methods) 
- Has 3 uses in "Play" class.
- Constructor takes no values, picks a random food image, gets height and width and sets a random position for the food to go.
- Has method "eaten" that checks if food has been eaten and increases the score accordingly. Has one use in the "Play" class.
- Has a method "draw" which draws an image at given coordinates. Has 2 uses in "Play" class.

### GameUtil Class - (3 Methods)
- Has uses in "ImageUtil" and "MyFrame".
- Has method "getImage" which takes a path to an image, reads it and returns it as BufferedImage.
- Has method "rotateImage" which takes an image and rotates it to a specified amount.

### ImageUtil Class - (Constructor)
- Has uses in "Food", "MyFrame" and "Play" classes.
- Only has a constructor that sets indexes to certain images.

### MusicPlayer Class - (Constructor, 2 Methods)
- Has uses in "Play" and "MusicPlayer" classes.
- Extends thread.
- Constructor takes a filename and initialises a variable with it.
- Has method "Play" which creates a new thread and plays the music file.
- Has method "getMusicPlay" which takes a filename and plays that file using a new "MusicPlayer" Object.

### Movable Interface Class - (1 Method)
- Has one use in the "MyFrame" class.
- Movable is an interface that contains one void method, "move".
- This method has 1 implementation in the "MyFrame" class.

### MyFrame Class - (Constructor, 4 Methods)
- Has uses in "Food" and "Play" and "MyFrame".
- Implements Movable.
- Extends JPanel.
- Used to create the game window.
- Constructor sets the icon of the page.
- Method "loadFrame" adds basic elements to the page, including a title and page size. Sets the window to visible.
- There are 3 methods "keyTyped", "keyPressed" and "keyReleased" that seem to have no implementation.
- There is a class "MyThread" inside the MyFrame Class.
- There is another class, "MySnake" inside of "MyFrame"
- There is a further abstract class inside of "MyFrame" called "SnakeObject".

### MyThread Class - (No Methods)
- Has 1 use in "Play".
- "MyThread" seems to run the parent class and infinitely repaint the window every 30 miliseconds.

### MySnake Class - (Constructor, 8 Methods)
- Has uses in "Food" and "Play".
- "MySnake" extends "SnakeObject" and implements "Movable"
- It initialises some private variables.
- The constructor takes two values, x and y.
- The constructor takes these values and sets them to this.y and this.x. There is no explaination as to what these do.
- Sets the speed and the initial length of the snake.
- Has a "getLength" method that returns the length variable. This is used once in the "Food" class.
- Has a "changeLength" method that changes the length variable. This is used once in the "Food" class.
- has a "keyPressed" method; If up is pressed, up is set to true and the snake image is rotated to that direction. This is the same for the other keys. (Down, left and right). This is used once in the "Play" class.
- Has a method "move" which checks which direction the snake should be moving and adjusts the speed in that direction accordingly. This is used once in the same class.
- Has a method "draw" which calls "outofBounds" and "eatBody". This method draws the body and the head and then calls "move". This is used once in "MySnake" class and once in the "Food" class.
- Has a method "eatBody". I think this method is for detecting whether the body has hit itself. This is used once in the same class.
- Has a method "drawBody" which draws each point in the snake onto the screen. This is used once in the same class.
- Has a method "outofBounds" which detects if you are out of bounds on the screen. This is used once in the same class.

### SnakeObject Abstract Class - (2 Methods)
- Has uses in "Food" and "MyFrame".
- Abstract class.
- Declares some variables.
- Has an abstract method "draw". Has one use in "MySnake" class and one use in the "Food" class.
- Has a public method "getRectangle" which returns a new Rectangle object that is initialised to a specified size. Has two uses in the "Food" class.

### Paddle Class - (Constructor, 1 Method, 6 Unused Methods)
- This class defines some variables.
- It has a constructor that takes some inputs and assigns them. This constructor is never used.
- Has a method "makeRectangle" which takes a height and width and returns a rectangle of that size.
- Has an unused method "move" which sets the location of a ballpoint and a paddleface.
- Has a unused method "moveLeft" which subtracts a value from "moveAmount".
- Has a unused method "moveRight" which sets the value for "moveAmount".
- Has an unused method "stop" which sets the moveAmount to 0.
- Has an unused method "getPaddleFace" which returns "paddleFace".
- Has an unused method "moveTo" which sets the location of "ballPoint" and "paddleFace".

### Play Class - (4 Methods)
- Extends "MyFrame".
- Sets up some public variables.
- Method "keyPressed" calls "keyPressed" in super class "MyFrame" and class "MySnake".
- Method "paint" draws the background and food onto the screen.
- Method "drawScore" draws the score onto the screen. Has one use in the same class.
- Method "main" loads the frame and plays music.

### Snake Class - (2 Methods)
- Declares a serial variable with no uses.
- Has method "move" that returns the value x with no uses.
- Has method "stop" with no uses. Sets moving = 0.