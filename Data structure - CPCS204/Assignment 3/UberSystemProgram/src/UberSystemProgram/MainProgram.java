
package UberSystemProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainProgram {
    
    public static void main(String[] args) throws FileNotFoundException {
        //put input file here
        File sourceFile = new File(""); 
        Scanner input = new Scanner(sourceFile);
            
        //put output file here
        File targetFile = new File(""); 
        PrintWriter output = new PrintWriter(targetFile);
        
        UberTree root = new UberTree();
        
        output.append("---------Welcome to Uber Booking System---------\n\n");
        while(input.hasNext()){
            String choice = input.next();
            
            switch(choice){
                case "Add_Captain":
                    output.append("Command Add_Captain: ");
                    int id = input.nextInt();
                    String name = input.next();
                    Add_Captain(root, id, name, output);
                    output.append("\n\n--------------------------------------------------------\n\n");
                    break;
                case "BOOK_RIDE":
                    output.append("Command BOOK_RIDE: ");
                    id = input.nextInt();
                    Book_Ride(root, id, output);
                    output.append("\n\n--------------------------------------------------------\n\n");
                    break;
                case "DISPLAY_CAPTAIN_INFO":
                    output.append("Command DISPLAY_CAPTAIN_INFO: ");
                    id = input.nextInt();
                    Display_Captain_Info(root, id, output);
                    output.append("\n\n--------------------------------------------------------\n\n");
                    break;
                case "FINISH_RIDE":
                    output.append("Command FINISH_RIDE: ");
                    id = input.nextInt();
                    int satisfiction = input.nextInt();
                    Finish_Ride(root, id, satisfiction, output);
                    output.append("\n\n--------------------------------------------------------\n\n");
                    break;
                case "DELETE_CAPTAIN":
                    output.append("Command DELETE_CAPTAIN: ");
                    id = input.nextInt();
                    Delete_Captain(root, id, output);
                    output.append("\n\n--------------------------------------------------------\n\n");
                    break;
                case "DISPLAY_ALL_CAPTAINS":
                    output.append("Command DISPLAY_ALL_CAPTAINS:\n");
                    Display_All_Captains(root, output);
                    output.append("\n\n--------------------------------------------------------\n\n");
                    break;
                case "Quit":
                    output.append("Thank you for using Uber System, Good Bye!");
                    break;
            }
        }
        output.close();
        output.flush();
    }
    
    public static void Add_Captain(UberTree root, int id, String name, PrintWriter output) {
        output.append("Add a new captain record in the system\n\n").append(root.addCaptain(id, name));
    }
    
    public static void Book_Ride(UberTree root, int id, PrintWriter output) {
        output.append(root.bookRide(id));
    }
    
    public static void Finish_Ride(UberTree root, int id, int satisfiction, PrintWriter output) {
        output.append(root.finishRide(id, satisfiction));
    }
    
    public static void Display_Captain_Info(UberTree root, int id, PrintWriter output) {
        output.append(root.displayCaptainById(id));
    }
    
    public static void Delete_Captain(UberTree root, int id, PrintWriter output) {
        output.append(root.deleteCaptain(id));
    }
    
    public static void Display_All_Captains(UberTree root, PrintWriter output) {
        output.append(root.displayAllCaptainsInfo());
    }
}
