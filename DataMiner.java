import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Demonstration of the use of Pattern and Matchers.
*Patterns are compiled on contruction and match objects
*are initialized with method matcher(String s), 
*A main method is provided in PatternTest.java for testing. 
*@author Adam Androulidakis
*@date 2011-04-14
**/
   public class DataMiner
   {
   // declare variables to store counted results
      private int phoneFound;
      private int courseFound;
      private int datesFound;
      private int webFound;
      private int linesFound;
      private int testFound;
   // declare Patterns
      private Pattern coursePattern;
      private Pattern phonePattern;
      private Pattern datePattern;
      private Pattern webPattern;
      private Pattern linePattern;
   // declare Matchers
      private Matcher lineMatch;
      private Matcher courseMatch;          
      private Matcher phoneMatch;
      private Matcher dateMatch;
      private Matcher webMatch;
   
   /**
   *Constructor initalizes Pattern objects with compile methods and regular expressions,
   *and initializes Matcher objects with method matcher(String s) and statically imported String
   *on construction of DataMiner. 
   *
   **/	
      public DataMiner()
      {
         coursePattern = Pattern.compile("\\W([A-Za-z]{3}(-| |_)[1-9]\\d{2})");
      /* courses like (CSC-240, Ast 101, etc..) are matched as long as the three letters with three 
	  numbers (which will start with digits only 1-9 and 2 consecutive digits, since courses 
	  generally do not start with 0)  are not followed  by a word character(it may include a space at 
	  the beginning) .The hyphen may be detected, an omitted hyphen (a whitespace), 
	  or an accidently typed underscore.  */
         phonePattern = Pattern.compile("(\\(?\\d{3}(-|\\)) ?\\d{3}-\\d{4})");
      // phone numbers like 303-333-3333,(303)333-333, or (303) 333-3333
         datePattern = Pattern.compile("((\\d{1,2}/\\d{1,2}/\\d{2,4})|(\\d{1,2}-\\d{1,2}-\\d{2,4}))");
      /* dates that are hyphenated or use forward slashes, as well as containing 1 or 2 month or day digits, and 2 or 4 year digits.
         Will not match dates with mixes separators, like 4-17/11 (this is intentional) */
         webPattern = Pattern.compile("(https?://([-a-z0-9+/?_|!:,.;]*[-a-z0-9+/?_]))|([w]{3}\\.[-a-z0-9+/?_|!:,.;]*\\.[a-z]{3})");
      /* matches https (or http), domain and subdomain names (of any amount) and some common url punctuation,
      as well as any linked documents, and also simple urls like www.google.com if there is no http(s) to match */
         linePattern = Pattern.compile("(\n)"); // newline character
      
      // initalize matches with specified pattern using statically imported String
         lineMatch = linePattern.matcher( STRING );
         courseMatch = coursePattern.matcher ( STRING );
         phoneMatch = phonePattern.matcher ( STRING );
         dateMatch = datePattern.matcher ( STRING );
         webMatch = webPattern.matcher ( STRING );
      
      }
   
   /**Main entry point of DataMiner.
   *Instantiate DataMiner, execute searching of patterns,
   *and display the results of the counted matches.
   *@param args not used 
   **/
      public static void main(String[] args)
      {
         DataMiner dataMiner = new DataMiner();	
         dataMiner.execute(); 
         dataMiner.displayResults();
      }
   
   /** Find and count matches. A message is displayed indicating a located web page
   *only when webMatch is passed to this method.
   *@param findMatch Take an object of type Matcher to locate matches of designated compiled pattern 
   *@return int value of counted matches
   **/
      public int countMatch (Matcher findMatch)
      {
         int counted = 0;
         while ( findMatch.find() )
         {	
            if (findMatch == webMatch) // if findMatch references to webMatch object, display message
               System.out.printf("\nLocated web page reference: %s", findMatch.group() );
            counted++; //increment count on match
         }	
         return counted;
      }
   
   /**
   * Display the results of the counted matches.
   **/
      public void displayResults()
      {
         System.out.printf("\n\nCounted %d lines", linesFound);
         System.out.printf("\nCounted %d course references", courseFound);
         System.out.printf("\nCounted %d phone numbers", phoneFound);
         System.out.printf("\nCounted %d dates", datesFound);
         System.out.printf("\nCounted %d web page references", webFound);
      }
   
   /**
   * Execute the pattern search
   **/
      public void execute()
      {
      /*  counted number of found matches are returned from 
		int countMatch(Matcher matcher) */
         linesFound = countMatch(lineMatch);
         courseFound = countMatch(courseMatch);
         phoneFound = countMatch(phoneMatch);
         datesFound = countMatch(dateMatch);
         webFound = countMatch(webMatch);
      }
   
   }
