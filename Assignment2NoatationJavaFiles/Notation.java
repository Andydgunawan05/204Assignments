/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: This class is a that converts between infix and postfix expressions and evaluates them.
 * Due: 10/08/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/
public class Notation {

	 // evaluates a postfix expression and returns the result as a double
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		// checks for empty expression
		if (postfixExpr.isEmpty()) { 
	            throw new InvalidNotationFormatException();
	    }
		  
	    MyStack<Double> stack = new MyStack<>(postfixExpr.length());
	    
	    // processes each character in the postfix expression
	    for (char ch : postfixExpr.toCharArray()) {
	        if (Character.isWhitespace(ch)) continue;     
	        if (Character.isDigit(ch)) {
	            stack.push((double) (ch - '0'));  
	        } else {
	            try {
	                double operand2 = stack.pop();
	                double operand1 = stack.pop();
	                
	                switch (ch) {
	                    case '+': stack.push(operand1 + operand2); break;
	                    case '-': stack.push(operand1 - operand2); break;
	                    case '*': stack.push(operand1 * operand2); break;
	                    case '/': stack.push(operand1 / operand2); break;
	                    default: throw new InvalidNotationFormatException();
	                }
	            } catch (StackUnderflowException e) {
	                throw new InvalidNotationFormatException();
	            }
	        }
	    }
	    
	    // result should be the only element in stack
	    try {
	        double result = stack.pop();
	        if (!stack.isEmpty()) {
	            throw new InvalidNotationFormatException();  
	        }
	        return result;
	    } catch (StackUnderflowException e) {
	        throw new InvalidNotationFormatException();
	    }
	}

	
	 // Converts a postfix expression to an infix expression
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
    	// checks for empty expression
        if (postfix.isEmpty()) { 
            throw new InvalidNotationFormatException();
        }
        
        MyStack<String> stack = new MyStack<>(postfix.length());
        
     // processes each character in the postfix expression
        for (char ch : postfix.toCharArray()) {
            if (Character.isWhitespace(ch)) continue;
            
            if (Character.isDigit(ch)) {
                stack.push(Character.toString(ch)); // push operand
            } else {
                try {
                    String operand2 = stack.pop();
                    String operand1 = stack.pop();
                    String infixExpr = "(" + operand1 + ch + operand2 + ")";
                    stack.push(infixExpr);
                } catch (StackUnderflowException e) {
                    throw new InvalidNotationFormatException();
                }
            }
        }
        
        // final expression in stack should be the infix expression
        try {
            String result = stack.pop();
            if (!stack.isEmpty()) throw new InvalidNotationFormatException();
            return result;
        } catch (StackUnderflowException e) {
            throw new InvalidNotationFormatException();
        }
    }

    // Converts an infix expression to a postfix expression
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        if (infix.isEmpty()) { // Check for empty expression
            throw new InvalidNotationFormatException();
        }
        
        // processes each character in the infix expression
        MyStack<Character> stack = new MyStack<>(infix.length());
        String postfix = "";

        for (char ch : infix.toCharArray()) {
            if (Character.isWhitespace(ch)) continue;

            if (Character.isDigit(ch)) {
                postfix += ch;
            } else if (ch == '(') {  // push '(' to stack
                stack.push(ch);
            } else if (ch == ')') {  // pop until ')' is found
                try {
                    while (!stack.isEmpty() && stack.top() != '(') {
                        postfix += stack.pop();
                    }
                    if (stack.isEmpty()) { // no  '(' found
                        throw new InvalidNotationFormatException();
                    }
                    stack.pop(); // pop the '('
                } catch (StackUnderflowException e) {
                    throw new InvalidNotationFormatException();
                }
            } else if (isOperator(ch)) {
            	// pop operators with higher precedence and append them 
                try {
                    while (!stack.isEmpty() && precedence(stack.top()) >= precedence(ch)) {
                        postfix += stack.pop();
                    }
                    stack.push(ch);
                } catch (StackUnderflowException e) {
                    throw new InvalidNotationFormatException();
                }
            } else {
                throw new InvalidNotationFormatException(); // unsupported characters
            }
        }

        // pop the rest of the operators in the stack
        try {
            while (!stack.isEmpty()) {
                char top = stack.pop();
                if (top == '(') { // unmatched '('
                    throw new InvalidNotationFormatException();
                }
                postfix += top;
            }
        } catch (StackUnderflowException e) {
            throw new InvalidNotationFormatException();
        }

        return postfix;
    }

    // method to determine the precedence for the operators 
	private static int precedence(char operator) {
	    if (operator == '+' || operator == '-') return 1;
	    if (operator == '*' || operator == '/') return 2;
	    return -1;
	}
	
    // checks if a character is a valid operator
	  private static boolean isOperator(char ch) {
	        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	    }
}