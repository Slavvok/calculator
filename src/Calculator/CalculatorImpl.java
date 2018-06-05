package Calculator;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.regex.*;

public class CalculatorImpl extends Calculator
{
    public boolean isNum(char token){
        
        if ((token >= '0') && (token <= '9')){
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isOperator(char token){
        switch (token) {
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }
    
    public int opPrecedence (String token) {
        switch (token) {
            case ")":
                return 4;
            case "(":
                return 0;
            case "*":
            case "/":
                return 2;
            
            default:
                return 1;
        }
    }
    
    public double calculation(Stack<Double> num, Stack<String> op) {
        double val1 = num.pop();
        double val2 = num.pop();
        String operator = op.pop();
        double result = 0;
        switch (operator){
            case "+":
                result = val2 + val1;
                break;
            case "-":
                result = val2 - val1;
                break;
            case "*":
                result = val2 * val1;
                break;
            case "/":
                result = val2 / val1;
                break;
        }
        return result;
    }
    
    public boolean isEval(String c) {
    	Pattern p = Pattern.compile("\\d+[\\+\\-\\/\\*+\\(\\)]+\\.{1}+\\d");
    	Matcher m = p.matcher(c);
    	if (m.find()) {
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public String evaluate(String c){
        Stack<Double> num = new Stack<Double>();
        Stack<String> op = new Stack<String>();

        for (int i=0;i<c.length();i++){
            char token = c.charAt(i);
            String symb = Character.toString(token);
            
            if (isNum(token)) {
            	symb = "";
            	while (i < c.length() && ((c.charAt(i) == '.') || (isNum(c.charAt(i))))) {
            		symb += c.charAt(i);
            		i++;
            	}
            	i--;
                Double nom = Double.parseDouble(symb);
                num.push(nom);
            }
            
            else if (isOperator(token)) {
            	try {
                while ((op.size() != 0) && (opPrecedence(symb) <= opPrecedence(op.peek()))){
                    Double res = calculation(num, op);
                    num.push(res);
                }
                op.push(symb);
            	} catch (Exception e) {
            		return null;
            	}
            }
            
            else if (token == '(') {
                    op.push(symb);
            }
            
            else if (token == ')') {
            		try {
                    while (op.peek().charAt(0) != '(') {
                        num.push(calculation(num, op));
                    } 
                    op.pop();
            		}
            		catch (Exception e) {
            			return null;
            		}
                }
        }
            /*if (op.size() >= 2 && (opPrecedence(numb) <= opPrecedence(op.peek()))){
                
                
                System.out.println(res);
            }*/
        try {
        	while (op.size() > 0) {
                num.push(calculation(num, op));
        }
        } catch (Exception e){
        	return null;
        }
        
        if (op.isEmpty() && num.size() == 1) {
        	String formattedDouble = new DecimalFormat("#0.0000", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(num.peek());
            return (formattedDouble); 
        }
        else {
        	return null;
        }
        
    }

}
