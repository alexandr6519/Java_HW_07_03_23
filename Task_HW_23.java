package Java_HW_07_03_23;
 import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.util.Scanner;

public class Task_HW_23 {   

    static int get_sum(int a, int b) {
        return a + b;
    }

    static int get_subtraction(int a, int b) {
        return a - b;
    }

    static int get_product(int a, int b) {
        return a * b;
    }

    static double get_division(int a, int b) {
        return (double) a / (double) b;
    }

    static String get_result_of_operation(int a, int b, String sign_operation) {
        switch (sign_operation) {
        case "+":
            return String.format("%d %s %d = %d \n", a, sign_operation, b, get_sum(a, b));
        case "-":
            return String.format("%d %s %d = %d \n", a, sign_operation, b, get_subtraction(a, b));
        case "*":
            return String.format("%d %s %d = %d \n", a, sign_operation, b, get_product(a, b));
        case "/":
            if (b != 0) {
                return String.format("%d %s %d = %.4f \n", a, sign_operation, b, get_division(a, b));
            } else {
                return String.format("ERROR: division by zero!!! \n");
            }
        default:  return String.format("Wrong operation!!! \n");
        }
    }

    static void writeResultsToFile(String result){
        try (FileWriter fw = new FileWriter("file_for_calculator.txt", true)) {
            fw.write(result);
            fw.write("\n");
            fw.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.printf("Enter first number: ");
        int a = 0;
        if (!scn.hasNextInt()) {
            System.out.println("You entered wrong number!!!");
        } else {
            a = scn.nextInt();
            Scanner sc = new Scanner(System.in);
            System.out.printf("Enter sign of operation from list: +, -, *, / \n");
            String sign_operation = "";
            if (sc.hasNextLine()) {
                sign_operation = sc.nextLine();
            }
            if (sign_operation.equals("+") || sign_operation.equals("-") || sign_operation.equals("*")
                    || sign_operation.equals("/")) {
                int b = 0;
                System.out.printf("Enter second number: ");
                if (!scn.hasNextInt()) {
                    System.out.println("You entered wrong number!!!");
                } else {
                    b = scn.nextInt();                    
                    String result = get_result_of_operation(a, b, sign_operation);
                    System.out.println(result);
                    writeResultsToFile(result);
                }
            } else {
                System.out.println("You entered wrong operation!!!");
            }
            scn.close();
            sc.close();
        }
    }    
}
