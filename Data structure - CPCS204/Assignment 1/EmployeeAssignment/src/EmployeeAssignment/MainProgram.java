
package EmployeeAssignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
    

public class MainProgram {

    public static void main(String[] args) throws FileNotFoundException {
        try{
            
            //put commands file here
            File sourceFile = new File(""); 
            Scanner input = new Scanner(sourceFile);
            
            //put output file here
            File targetFile = new File(""); 
            PrintWriter output = new PrintWriter(targetFile);
            
            output.append("\t\t\t\t\t\tWelcome to Shopping Center Employee Management System \n" +
                          "\t\t\t\t\t-------------------------------------------------------------------------\n");
            
            //array for linked list centers
            Center[] centers = null;
            while(input.hasNext()){
                String command = input.next();
                switch(command){
                    case "STARTUP":
                        centers = startUp(output);
                        break;

                    case "DISPLAY_ALL_CENTERS":
                        displayAllCenters(output, centers);
                        break;
                        
                    case "DISPLAY_PRODUCTS_FOR_EMPLOYEE":
                        int id = input.nextInt();
                        displayProductForEmployee(id, output, centers);
                        break;
                        
                    case "NUM_OF_EMPLOYEES":
                        numberOfEmployees(input.next(), output, centers);
                        break;
                        
                    case "DISPLAY_BASED_ON_PRODUCT":
                        displayBasedOnProduct(input.next(), output, centers);
                        break;
                        
                    case "CHANGE_TO_NULL_PRODUCT":
                        changeProductToNull(input.nextInt(), output, centers);
                        break;
                        
                    case "SWAP_BETWEEN_EMPLOYEES":
                        output.append("\t\t----------------------------------------------\n");
                        output.append("\t\tSwap Employees Between Two Centers\n");
                        swap(input.nextInt(), input.nextInt(), output, centers);
                        break;
                        
                    case "QUIT":
                        output.append("\n============================\n" +
                                      "\tBest Wishes\n" +
                                      "============================");
                        output.flush();
                        output.close();
                        break;
                        
                    default:
                        break;
                }
            }
        }catch(FileNotFoundException exception){
            System.out.println("Commands.txt File not found!");
            System.exit(0);
        }
    }
    
    public static Center[] startUp(PrintWriter output) {
        Center[] centers = null;
        
        try{
            
            //intialInformation file here
            File sourceFile = new File("");
            Scanner input = new Scanner(sourceFile);
            
            //data
            int numOfEmployees = input.nextInt();
            centers = new Center[input.nextInt()];
            String[] products = new String[input.nextInt()];
            
            //for storing centers in array
            for(int i = 0; i < centers.length; i++){
                centers[i] = new Center(input.next());
            }
            
            //for storing products
            for(int i = 0; i < products.length; i++){
                products[i] = input.next();
            }
            
            //storing employees in linked list
            for(int i = 0; i < centers.length; i++){
                for(int j = 1; j <= (numOfEmployees / centers.length); j++){
                    centers[i].addEmployee(input.nextInt(), input.next(), input.next(), products[j-1], (i+1));
                }
            }
            
            
            for(Center e: centers) {
                e.setCenterID(input.nextInt());
                e.setCenterName(input.next());
            }
            
        }catch(FileNotFoundException exception){
            System.out.println("intialInformation.txt File not found!");
            System.exit(0);
        }catch(Exception e){
            
        }
        
        return centers;
    }
    
    public static void displayAllCenters(PrintWriter output, Center[] center) {
        output.append("\t\tInformation of Employees in Each Center\n");
        for(Center e: center){
            output.append("\t\t\tEmployees in " + e.getCenterName() + " Center\n");
            output.append("\tID\tName\t\t\tProduct\n");
            output.append(e.printAllEmployees(e.getHead()));
            output.append("\t\t----------------------------------------------\n");
        }
        
    }
    
    public static void displayProductForEmployee(int id,PrintWriter output, Center[] centers) {
        Employee employee = null;
        for(int i = 0; i < centers.length; i++){
            employee = centers[i].searchEmployeeByID(id);
            if(employee != null && employee.getEmpID() == id)
                break;
        }
        if(employee != null && employee.getEmpID() == id)
            output.append("\tEmployee: " + employee.getFullName()+ ", " + String.format("%03d", employee.getEmpID()) + ", is assigned to " + employee.getProduct()+ ", in " + centers[employee.getCenter() - 1].getCenterName() + " center\n");
        else
            output.append("\tNo employee of this number is found\n");
    }
    
    public static void numberOfEmployees(String centerName, PrintWriter output, Center[] centers) {
        output.append("\t\t----------------------------------------------\n");
        Center center = null;
        for(Center e: centers) {
            if(e.getCenterName().equals(centerName)) {
                center = e;
                break;
            }
        }
        output.append("\tNumber of employess in " + center.getCenterName() + " center: " + center.numberOfEmployeesInCenter() + "\n");
    }
    
    public static void displayBasedOnProduct(String product, PrintWriter output, Center[] centers) {
        output.append("\t\t----------------------------------------------\n");
        output.append("\t\tEmployees for Product " + product + "\n");
        output.append("\tID\tName\t\tCenter\n");
        for(Center e: centers) {
            Employee employee = e.searchEmployeeByProduct(product);
            output.append("\t" + String.format("%03d", employee.getEmpID()) + "\t" + employee.getFullName() + "\t\t" + e.getCenterName() + "\n");
        }
    }
    
    public static void changeProductToNull(int id, PrintWriter output, Center[] centers){
        output.append("\t\t----------------------------------------------\n");
        output.append("\t\tChange Assigned Product to Null\n");
        Employee employee = null;
        for (Center e: centers) {
            employee = e.searchEmployeeByID(id);
            if(employee != null && employee.getEmpID() == id)
                break;
        }
        employee.setProduct(null);
        output.append("\tProducts for  " + employee.getFullName() + " : " + employee.getEmpID() + " has been changed to null\n");
    }
    
    public static void swap(int id1, int id2, PrintWriter output, Center[] centers) {
        Employee employee1 = null;
        Employee employee2 = null;
        for (Center e: centers) {
            employee1 = e.searchEmployeeByID(id1);
            if(employee1 != null && employee1.getEmpID() == id1)
                break;
        }
        for (Center e: centers) {
            employee2 = e.searchEmployeeByID(id2);
            if(employee2 != null && employee2.getEmpID() == id2)
                break;
        }
        output.append("\t" + employee1.getFullName() + " in " + centers[employee1.getCenter() - 1].getCenterName() + " center is SWAPED WITH " + employee2.getFullName() + " in " + centers[employee2.getCenter() - 1].getCenterName()+ " center\n");
        Employee temp = new Employee(employee2.getEmpID(), employee2.getfName(), employee2.getlName());
        centers[employee2.getCenter() - 1].swapEmployees(employee1, employee2);
        centers[employee1.getCenter() - 1].swapEmployees(temp, employee1);
        output.append("\t\t----------------------------------------------\n");
    }
}