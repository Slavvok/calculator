package Calculator;

public class Calculator {

	public String evaluate(String c) {
		return "";
    }

    public static void main(String[] args) {
        Calculator c = new CalculatorImpl();
        System.out.println(c.evaluate("6*(2+2)/(8-33)+1*3+5"));
        System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
        System.out.println(c.evaluate("7*6/2+8")); // Result: 29
        System.out.println(c.evaluate("-12)1//("));
    }
}
