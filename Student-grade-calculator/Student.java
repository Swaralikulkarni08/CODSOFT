import java.util.*;

class Student {
    public static void main(String[] args) {
        int applied_maths, discrete_maths, python, data_structure, microprocessor, computer_graphics, total_marks;
        float average;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter marks of 6 subjects: ");
        applied_maths = sc.nextInt();
        discrete_maths = sc.nextInt();
        python = sc.nextInt();
        data_structure = sc.nextInt();
        microprocessor = sc.nextInt();
        computer_graphics = sc.nextInt();
        total_marks = applied_maths + discrete_maths + python + data_structure + microprocessor + computer_graphics;
        average = total_marks / 6;
        System.out.println("Total marks obtained: " + total_marks);
        System.out.println("Average is:" + average);
        if (average >= 70.0) {
            System.out.println("A Grade");
        } else if (average <= 69.0 && average >= 60.0) {
            System.out.println("B Grade");
        } else if (average <= 59.0 && average >= 50.0) {
            System.out.println("C Grade");
        } else if (average <= 49.0 && average >= 45.0) {
            System.out.println("D Grade");
        } else if (average <= 44.0 && average >= 40.0) {
            System.out.println("E Grade");
        } else {
            System.out.println("Fail");
        }
        sc.close();
    }
}