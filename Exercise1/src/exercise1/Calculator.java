/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Hernán Olmedo
 */
public class Calculator {
    public String evaluate(String expression){  
        int i=0;
        String[] parts = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?<=\\D)(?=\\D)");
        List<String> list=new ArrayList<>(Arrays.asList(parts));
        Stack<Integer> openBracketsPosition = new Stack();
        while(list.size()>i){
            switch(list.get(i)) {
                case "(":
                case "[":
                case "{":
                    openBracketsPosition.push(i);
                  break;
                case ")":
                case "]":
                case "}":
                    int openBracketPosition;
                    openBracketPosition = openBracketsPosition.pop();
                    list.set(openBracketPosition,expressionResolver(list.subList(openBracketPosition+1,i)));
                    list.subList(openBracketPosition+1, i-1).clear();
                    i=openBracketPosition;
            }
            i++;
        }
        return expressionResolver(list);
    }
    private String expressionResolver(List<String> listExpression){
        int pending=0;
        String[] pendings=new String[2];
        while(listExpression.size()>1){
            String number1=listExpression.get(0);
            String number2=listExpression.get(2);
            String operator1=listExpression.get(1);
            String operator2=(listExpression.size()>3)?listExpression.get(3):"";
            if(operator1.equals("*")||operator1.equals("/")){
                listExpression.set(0,resolveOperation(number1,number2,operator1));
                listExpression.subList(1, 3).clear();
                if(pending==1&&(operator2.equals("+")||operator2.equals("-")||operator2.equals(""))){
                    listExpression.set(0,resolveOperation(pendings[0],listExpression.get(0),pendings[1]));
                    pending=0;
                }
            }
            else if(operator2.equals("+")||operator2.equals("-")||operator2.equals("")){
                listExpression.set(0,resolveOperation(number1,number2,operator1));
                listExpression.subList(1, 3).clear();
                if(pending==1){
                    listExpression.set(0,resolveOperation(pendings[0],listExpression.get(0),pendings[1]));
                    pending=0;
                }
            }
            else{
                pendings[0]=number1;
                pendings[1]=operator1;
                pending=1;
                listExpression.subList(0, 2).clear();
            }
        }
        return listExpression.get(0);
    }
    
    private String resolveOperation(String stringNumber1,String stringNumber2,String operator){
        int number1=Integer.parseInt(stringNumber1);
        int number2=Integer.parseInt(stringNumber2);
        int result=0;
        switch (operator) {
            case "*":
                result=number1*number2;
                break;
            case "/":
                result=number1/number2;
                break;
            case "+":
                result=number1+number2;
                break;
            case "-":
                result=number1-number2;
                break;
        }
        return Integer.toString(result);
    }
}
