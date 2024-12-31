import java.util.Scanner;

interface Employee {
    void getDetails();
}

interface Manager extends Employee {
    void getDeptDetails();
}

class Head implements Manager {
    private int empId;
    private String empName;
    private int deptId;
    private String deptName;
    
    public void getDetails() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter employee id: ");
        empId = in.nextInt();
        in.nextLine(); 
        System.out.print("Enter employee name: ");
        empName = in.nextLine();
    }
    
    public void getDeptDetails() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter department id: ");
        deptId = in.nextInt();
        in.nextLine(); // Consume newline
        System.out.print("Enter department name: ");
        deptName = in.nextLine();
    }
    

    public void displayDetails() {
        System.out.println("------------------------------");
        System.out.println("Employee id - " + empId);
        System.out.println("Employee name - " + empName);
        System.out.println("Department id - " + deptId);
        System.out.println("Department name - " + deptName);
        System.out.println("------------------------------");
    }
}

public class employee1 {
    public static void main(String[] args) {
        Head head = new Head();
        
        head.getDetails();
        head.getDeptDetails();
        head.displayDetails();

        Head[] harr = new Head[5];
        
    }
}
