package utility;

import java.util.Scanner;
import adt.ListInterface;

public class MessageUI {
    static Scanner sc = new Scanner(System.in);
    
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

      public static void displayInvalidIndexMessage() {
        System.out.println("\nInvalid Index Selected!");
      }
      
      public static boolean comfirmationMessage(){
          System.out.println("\nEnter Y to comfirm or Enter N to negate");
          boolean ans = enterComfirm();
          return ans;
      }
      
      public static boolean enterComfirm(){
          String ans = sc.nextLine();
          ans.toUpperCase();
          if (ans.equalsIgnoreCase("Y")){
              return true;
          }else if (ans.equalsIgnoreCase("N")){
              return false;
          }else {
              displayInvalidChoiceMessage();
              return false;
          }
      }
     
      public static int displayList(ListInterface list){
        int result = list.getNumberOfEntries();
        for (int i = 1;i<=result;i++){
            System.out.printf("%3s. %-100s %10s", String.valueOf(i), list.getEntry(i).toString(),"\n");       
        }
        return result;
    }
}
