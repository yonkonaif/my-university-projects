
package EmployeeAssignment;

public class Employee {
    private int EmpID;
    private String fName;
    private String lName;
    private String product;
    private String phone;
    private int age;
    private int center;
    private Employee next;

    public Employee() {
    }

    public Employee(int EmpID, String fName, String lName){
        this.EmpID = EmpID;
        this.fName = fName;
        this.lName = lName;
    }
    
    public Employee(int EmpID, String fName, String lName, String product, int center){
        this.EmpID = EmpID;
        this.fName = fName;
        this.lName = lName;
        this.center = center;
        this.product = product;
    }
    
    public int getEmpID() {
        return EmpID;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getProduct() {
        return product;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public int getCenter() {
        return center;
    }

    public Employee getNext() {
        return next;
    }

    public void setEmpID(int EmpID) {
        this.EmpID = EmpID;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    public String getFullName(){
         return (fName + " " + lName);
    }
    
}