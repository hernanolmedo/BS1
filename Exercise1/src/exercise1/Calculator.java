/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1;


import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Hernán Olmedo
 */
public class Calculator {
    public String evaluate(String expression){        
        String[] parts = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?<=\\D)(?=\\D)");
        List<String> list=Arrays.asList(parts);
        Stack<Integer> openBracketsPosition = new Stack();
        for(int i=0;i<parts.length;i++){
            switch(parts[i]) {
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
                    list.set(openBracketPosition,expressionResolver(list.subList(openBracketPosition,i)));
                    list.subList(openBracketPosition+1, i).clear();
            }
        }
        return "something";
    }
    private String expressionResolver(List<String> listExpression){
        int result;
        int pending=0;
        int i=0;
        String[] pendings=new String[2];
        String operator1="";
        String operator2="";
        String number1="";
        String number2="";
        while(listExpression.size()>1){
            number1=listExpression.get(i);
            number2=listExpression.get(i+2);
            operator1=listExpression.get(i+1);
            operator2=listExpression.get(i+3);
            if(operator1=="*"||operator1=="/"){
                listExpression.set(i,resolveOperation(number1,number2,operator1));
                listExpression.subList(i+1, i+2).clear();
                if(pending==1&&(operator2=="+"||operator2=="-")){
                    listExpression.set(i,resolveOperation(pendings[0],number1,pendings[1]));
                    pending=0;
                }
            }
            else if(operator2=="+"||operator2=="-"){
                listExpression.set(i,resolveOperation(number1,number2,operator1));
                listExpression.subList(i+1, i+2).clear();
                if(pending==1){
                    listExpression.set(i,resolveOperation(pendings[0],number1,pendings[1]));
                    pending=0;
                }
            }
            else{
                pendings[0]=number1;
                pendings[1]=operator1;
                pending=1;
                listExpression.subList(i, i+1).clear();
            }
        }
        return listExpression.get(0);
    }
    
    private String resolveOperation(String stringNumber1,String stringNumber2,String operator){
        int number1=Integer.parseInt(stringNumber1);
        int number2=Integer.parseInt(stringNumber2);
        int result=0;
        if(operator=="*"){
            result=number1*number2;
        }
        else if(operator=="/"){
            result=number1/number2;
        }
        else if(operator=="+"){
            result=number1+number2;
        }
        else if(operator=="-"){
            result=number1-number2;
        }
        return Integer.toString(result);
    }
}
