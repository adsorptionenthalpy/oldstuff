import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Demonstration of the use of Pattern and Matchers.
*Patterns are compiled on contruction and match objects
*are initialized with method matcher(String s), which
*are using a statically imported string. This is the main method for testing.
*For use with DataMiner.java
*@author Adam Androulidakis
*@date 2011-04-14
**/
public class PatternTest
{
	public static void main(String[] args)
	{
		String thaString = "80303 80303-9999 ZIP CODE: 80303 Boulder CO 80303";
		Pattern mahPattern = Pattern.compile("\\s*(\\d{5}-\\d{4})|(\\d{5})\\s*");
		Matcher mahMatch = mahPattern.matcher (thaString);
	
		while (mahMatch.find() )
		{
			System.out.printf("\n%s\n", mahMatch.group() );
		}
	
	}
	
}
