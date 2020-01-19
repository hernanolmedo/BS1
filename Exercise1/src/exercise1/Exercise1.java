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
     * @return 
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        String expression="";
        try{
            Scanner input = new Scanner(new File("C:\\Users\\ciber.LAPTOP-0JK7T32O\\Documents\\NetBeansProjects\\BS1\\Exercise1\\src\\exercise1\\expresionNumerica.txt"));
            expression = input.nextLine();
            input.close();
            calc.evaluate(expression);
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        System.out.println(calc.evaluate(expression));
    }
    
}
