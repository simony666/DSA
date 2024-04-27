package utility;

import java.util.Scanner;
import adt.ListInterface;
import java.io.IOException;

public class MessageUI {

    static Scanner sc = new Scanner(System.in);

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice!");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system...");
    }

    public static void displayNotRelated() {
        System.out.println("\nNot Found, It is not related data available.");
    }

    public static void displayEmpty() {
        System.out.println("\nNot Found, It is Empty.");
    }

    //author: Poh Chu Ren
    public static void displayInvalidIndexMessage() {
        System.out.println("\nInvalid Index Selected!");
    }

    //author: Goh Qin Long
    public static boolean comfirmationMessage() {
        System.out.println("\nEnter <Y> to comfirm or Enter <N> to negate");
        boolean ans = false;
        ans = enterComfirm();
        return ans;
    }

    //author: Goh Qin Long
    public static boolean enterComfirm() {
        String ans = sc.nextLine();
        ans.toUpperCase();
        if (ans.equalsIgnoreCase("Y")) {
            return true;
        } else if (ans.equalsIgnoreCase("N")) {
            return false;
        } else {
            displayInvalidChoiceMessage();
            return false;
        }
    }

    //author: Goh Qin Long
    public static void pressEnter() {
        System.out.print("Press <ENTER> to continue..");
        try {
            System.in.read();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //author: Yong Choy Mun
    public static int displayList(ListInterface list) {
        int result = list.getNumberOfEntries();
        if (list.isEmpty()){
            System.out.println("=====No Record=====");
        }else{
            displayListRecursive(list, 1);
        }
        return result;
    }
    
    //author: Yong Choy Mun
    private static void displayListRecursive(ListInterface list,int index){
        if (index <= list.getNumberOfEntries()){
            System.out.printf("%3s. %-100s %10s", String.valueOf(index), list.getEntry(index).toString(), "\n");
            displayListRecursive(list,++index);
        }
    }
    
    //author: Yong Choy Mun
    public static void clearScreen() {
        // Only can do this in Netbean... sosad
        System.out.print("\n\n\n\n\n\n\n\n\n\n");
    }
}
