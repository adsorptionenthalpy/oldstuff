import java.io.File;
import java.util.Scanner;
/**
 * Test file for TurtleGraphics()
 * 
 * @author    Adam Androulidakis
 * @version   2011-2-28
 */
public class TurtleGraphicsTest
{

    /**
     * Main method for testing TurtleGraphics()
     * 
     * @param  args not used   
     * @return      
     */
    public static void main( String[] args )
    {   
    
        TurtleGraphics drawing = new TurtleGraphics(); // instantialize our TurtleGraphics object
        
        drawing.enterCommands(); // enter commands by prompt
		drawing.executeCommands(); // execute the commands
        
    }

}
