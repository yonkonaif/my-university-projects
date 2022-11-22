
package EmployeeAssignment;

public class Center {
    private String centerName;
    private int centerID;
    private Employee head;
    
    public Center(){
    }

    public Center(String centerName) {
        this.centerName = centerName;
    }

    public Center(String centerName, int centerID) {
        this.centerName = centerName;
        this.centerID = centerID;
    }

    public String getCenterName() {
        return centerName;
    }

    public int getCenterID() {
        return centerID;
    }

    public Employee getHead() {
        return head;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    //--------------------------------------------------------------------------
    
    public void addEmployee(int ID, String fName, String lName){
        if(head == null)
            head = new Employee(ID, fName, lName);
        else{
            Employee helpPtr = head;
            while(helpPtr.getNext() != null){
                helpPtr = helpPtr.getNext();
            }
            helpPtr.setNext(new Employee(ID, fName, lName));
        }
    }
    
    //overloaded method
    public void addEmployee(int ID, String fName, String lName, String product, int center){
        if(head == null)
            head = new Employee(ID, fName, lName, product, center);
        else{
            Employee helpPtr = head;
            while(helpPtr.getNext() != null){
                helpPtr = helpPtr.getNext();
            }
            helpPtr.setNext(new Employee(ID, fName, lName, product, center));
        }
    }
    
    public Employee searchEmployeeByID(int ID){
        Employee helpPtr = head;
        
        while(helpPtr != null){
            if(helpPtr.getEmpID() == ID)
                break;
            helpPtr = helpPtr.getNext();
        }
        
        return helpPtr;
    }
    
    public Employee searchEmployeeByProduct(String product){
        Employee helpPtr = head;
        
        while(helpPtr != null){
            if(helpPtr.getProduct().equals(product))
                break;
            helpPtr = helpPtr.getNext();
        }
        
        return helpPtr;
    }
    
    public void swapEmployees(Employee newEmployee, Employee oldEmployee){
        oldEmployee.setEmpID(newEmployee.getEmpID());
        oldEmployee.setfName(newEmployee.getfName());
        oldEmployee.setlName(newEmployee.getlName());
    }
    
    public Employee deleteEmployeeByID(int ID) {
        Employee helpPtr = head;
        while(helpPtr.getNext() != null){
            if(helpPtr.getNext().getEmpID() == ID)
                helpPtr.setNext(helpPtr.getNext().getNext());
        }
        return head;
    }

    public String printAllEmployees(Employee head){
        StringBuilder string = new StringBuilder();
        Employee helpPtr = head;
        while(helpPtr != null){
            string.append("\t").append(String.format("%03d", helpPtr.getEmpID())).append("\t").append(helpPtr.getFullName()).append("\t\t").append(helpPtr.getProduct()).append("\n");
            helpPtr = helpPtr.getNext();
        }
        return string.toString();
    }
    
    //returns how many employees in the center
    public int numberOfEmployeesInCenter(){
        Employee helpPtr = head;
        int num = 0;
        while(helpPtr != null){
            num++;
            helpPtr = helpPtr.getNext();
        }
        return num;
    }
    
}
