import java.util.Scanner;
import java.util.ArrayList;

/** TurtleGraphics() class. Receive input from
 * user by prompt. Can be per line, or all in one
 * line. When 5 is entered, the next value entered after
 * a white space, or a comma, will be added to the integer
 * spaces, which will then be used to move the turtle
 * that amount of spaces. The current 'grid' location
 * of the floor will be flagged with a 1 that will
 * be used to draw a defined decal by the display()
 * method. When turning, the turtle will keep track of
 * which direction it is facing at all times.
 * The floor may also be reset, and the turtle may
 * be repositioned at the start location.
 * @author    Adam Androulidakis
 * @version   2011-2-28
 */
public class TurtleGraphics
{
  private final int FLOOR_Y_SIZE = 20;
  // amount of rows on the floor 
  private final int FLOOR_X_SIZE = 20;
  // amount of columns on the floor
  private final int PEN_UP = 1; 
  // not ready to draw
  private final int PEN_DOWN = 2; 
  // ready to draw
  private final int TURN_RIGHT = 3;
  // turtle turns clockwise
  private final int TURN_LEFT = 4;
  // turtle turns counter clockwise
  private final int TURTLE_MOVE = 5; 
  // move the turtle
  private final int DISPLAY = 6;
  // display drawing
  private final int CLEAR = 7; 
  // clear the floor
  private final int REPOSITION = 8;
  // start the turtle back at the beginning, and face east
  private final int END = 9;
  // end input
  
  private boolean DONE = false;
  private int[][] floor = new int[FLOOR_Y_SIZE][FLOOR_X_SIZE];
  private int posy = 0, posx = 0; // starting position of turtle
  private ArrayList<String> values = new ArrayList<String>();
  
  private int command;
  // holds the current command to be processed by switch(commands)
  // in executeCommands method
  private int spaces;
  // holds the amount of spaces the 
  // turtle will go when is moving
  private String decal = "*"; 
  // this can be changed to designate what
  // symbol will be drawn on the floor

  private static enum Direction { NORTH, SOUTH, EAST, WEST};
  // enum to contain all of the possible direcitons of our turtle
  private static enum penStatus {UP, DOWN};
  // enum for the status of the pen. It can either be UP, or DOWN
    
  private penStatus pen = penStatus.UP; 
  // class starts with turtle not ready to draw
  private Direction direction = Direction.EAST; 
  // class starts with turtle facing east
  
 /**
 *  Enter a series of commands.
 *  Do not have to enter command and press enter each time.
 *  Entire sequence of commands may be entered on one line
 *  and then executed. Anything after 5, with or without comma
 *  will be the amount to move the turtle forward.
 */
  public void enterCommands()
  {
     DONE = false;
     Scanner input = new Scanner( System.in );
     input.useDelimiter("(\\s)+|,(\\s)*");
     instructions();
    System.out.printf("Enter series of commands:");

        command = input.nextInt();
		values.add(String.valueOf(command));
		
        //if (command == 5)
        //   spaces = input.nextInt();
     
  
  }
  
 /**
 * Chooses which command to execute depending
 * on the value of command. Ever time is executed.
 * Does not take an argument, simply uses the 
 * global value command. Gives cases to control 
 * actions for readying the pen to draw (and not)
 * Which direction to face when turning in any
 * direction. Move a certain amount of spaces and
 * flag the floor location if the pen is down,
 * displaying the floor, clearing the floor, 
 * reposition the turtle to start location, 
 * end input, and deal with inappropriate input.
 */
  public void executeCommands()
  {
	for( String counter : values )
	{
     switch(command)
     {
        case PEN_UP: 
           pen = penStatus.UP; // not ready to draw!
           break;
        case PEN_DOWN:
           pen = penStatus.DOWN; // ready to draw!
           break;
        case TURN_RIGHT: // allows full clockwise turn
           if (direction == Direction.WEST)
              direction = Direction.NORTH;
           else if (direction == Direction.NORTH)
              direction = Direction.EAST;
           else if (direction == Direction.EAST)
              direction = Direction.SOUTH;
           else if (direction == Direction.SOUTH)
              direction = Direction.WEST;
           break;
        case TURN_LEFT: // allows full counter-clockwise turn
           if (direction == Direction.WEST)
              direction = Direction.SOUTH;
           else if (direction == Direction.SOUTH)
              direction = direction.EAST;
           else if (direction == Direction.EAST)
              direction = direction.NORTH;
           else if (direction == Direction.NORTH)
              direction = direction.WEST;
           break;
        case TURTLE_MOVE:
           if (direction == Direction.WEST)
           { 
              for (int count = 1; count <= spaces; count++)
              {
                 if(pen == penStatus.DOWN)
                    floor[posy][posx] = 1;
                    // mark this location to be drawn
                 if (posx > 0) posx--;
                 //allows movement if the pen is not ready to draw
                 // and will not exceed the bounds of the floor
              }
           }
           else if (direction == Direction.EAST)
           {
              for (int count = 1; count <= spaces; count++)
              {
                 if(pen == penStatus.DOWN)
                    floor[posy][posx] = 1;
                    // mark this location to be drawn
                 if (posx < FLOOR_X_SIZE - 1) posx++;
                 //allows movement if the pen is not ready to draw
                 // and will not exceed the bounds of the floor
              }
           }
           else if (direction == Direction.NORTH)
           {
              for (int count = 1; count <= spaces; count++)
              {
                 if (pen == penStatus.DOWN)
                    floor[posy][posx] = 1;
                    // mark this location to be drawn
                 if (posy > 0) posy--; 
                 //allows movement if the pen is not ready to draw
                 // and will not exceed the bounds of the floor
              }
           }
           else if (direction == Direction.SOUTH)
           {
              for (int count = 1; count <= spaces; count++)
              {
                 if (pen == penStatus.DOWN)
                    floor[posy][posx] = 1;
                    // mark this location to be drawn
                 if (posy < FLOOR_Y_SIZE - 1) posy++;
                    //allows movement if the pen is not ready to draw
                 // and will not exceed the bounds of the floor
              }
           }
           break;
        case DISPLAY: // show the floor
           display();
           break;
        case CLEAR: // this clears the floor
           floor = new int[FLOOR_Y_SIZE][FLOOR_X_SIZE];
           break;
        case REPOSITION: // move back to starting point facing EAST, and bring the pen up
           posx = 0;
           posy = 0;
           direction = Direction.EAST;
           pen = penStatus.UP;
           break;
        case END: // finished entering commands
           DONE = true;
           break;
        default: System.out.printf("Not an appropriate command: %s\n", command);
     } // end of switch(command)
	} // end of for ( String counter : values )
  } // end of executeCommands()
 /**
 *  Cycle through and display what we have drawn on our 20x20 floor
 *
 */
  private void display()
  {
     // loop through the rows until reaching
     //the maximum value of the array
     for ( int y = 0 ; y < FLOOR_Y_SIZE; y++) 
     {
         // loop through the columns until reaching
         // the maximum value of the array
        for (int x = 0; x < FLOOR_X_SIZE  ; x++)
        {
           // if there is a 1 in this location, draw the decal
           if (floor[y][x] == 1) 
              System.out.printf("%s", decal);
           else
              System.out.printf(" ");
        }
        System.out.println(); 
        // go to next line when finished with current row
     }
  }
 /**
 * Display a list of instructions for the user. 
 */    
  private void instructions()
  {
     System.out.printf("Instructions:\n");
     System.out.printf("1 Pen UP\n");
     System.out.printf("2 PEN DOWN\n");
     System.out.printf("3 TURN RIGHT\n");
     System.out.printf("4 TURN LEFT\n");
     System.out.printf("5 MOVE THE TURTLE A GIVEN AMOUNT OF SPACES");
     System.out.printf("Ex. 5,10 moves forward 10 spaces\n");
     System.out.printf("6 DISPLAY THE FLOOR\n");
     System.out.printf("7 CLEAR THE FLOOR\n");
     System.out.printf("8 REPOSITION THE TURTLE AT START LOCATION\n");
     System.out.printf("9 STOP ENTERING COMMANDS\n"); 
     System.out.printf("SAMPLE COMMAND SEQUENCE:\n");
     System.out.printf("5,6 2 5,6 3 5,6 4 5,6 3 5,6 3 5,6 4 5,6"); 
     System.out.printf(" 3 5,6 3 5,6 4 5,6 3 5,6 3 5,6 4 5,6 6\n");
  }

}
