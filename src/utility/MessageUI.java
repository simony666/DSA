package utility;

public class MessageUI {
    
    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice!");
      }

      public static void displayExitMessage() {
        System.out.println("\nExiting system...");
      }
    
      public static void displayNotRelated() {
          System.out.println( "\nNot Found, It is not related data available.");
      }
      
      public static void displayEmpty() {
          System.out.println( "\nNot Found, It is Empty.");
      }
      
      public static void clearScreen() {
            // Only can do this in Netbean... sosad
            System.out.print("\n\n\n\n\n\n\n\n\n\n");
        }

}
