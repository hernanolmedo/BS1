/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1;

/**
 *
 * @author Hernán Olmedo
 */
public class Calculator {
    public String evaluate(String expression){
        for(int i=0;i<expression.length();i++){
            String character = String.valueOf(expression.charAt(i));
            System.out.println(character);
        }
        return "something";
    }
}
