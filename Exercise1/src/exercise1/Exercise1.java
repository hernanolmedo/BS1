package exercise1;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Hernán Olmedo
 */
public class Exercise1 {
    /**
     * @param args the command line arguments 
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        String expression = new String();
        try{
            try (Scanner input = new Scanner(new File(args[0]))) {
                expression = input.nextLine();
            }
            expression=calc.evaluate(expression);
        }
        catch(Exception ex){
            System.out.println("Syntax Error");
        }
        System.out.println(expression);
    }
    
}
