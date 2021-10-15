public class LinkedStackTest {

    /** Converts the input infix expression to a postfix expression.
    * @param infix The infix input to be converted to postfix.
    * @return char[] The converted postfix expression.
    */
   public static char[] convertToPostfix(String infix) {
        // create a new stack, whose data are of the character type
        StackInterface<Character> operatorStack = new LinkedStack<>();

        String postfix = "";
        char[] postfixChar;
        char topOperator = ' ';
        char[] infixChar = new char[25];

        int infixLength = 0;
        int currentPosition = 0;
        int nextCharPreced = 0;
        int peekPreced = 0;

        if (infix != null && !infix.isEmpty()) {
            infixChar = infix.toCharArray();
            infixLength = infix.length();
        } else {
            System.out.println("Input string infix is either empty/null!");
        }

        while (currentPosition < infixLength) {
            //postfixChar = postfix.toCharArray();
            char nextCharacter = infixChar[currentPosition];
            
            // determine the precedence of the operator at the top of the stack
            if (!operatorStack.isEmpty()) {
                peekPreced = getPrecedence(operatorStack.peek());
            }
            // determine the precedence of nextCharacter
            nextCharPreced = getPrecedence(nextCharacter);

            // determine what operator is next, and perform actions accordingly
            if (nextCharacter >= 'a' && nextCharacter <= 'z') {
                postfix += nextCharacter;
                currentPosition++;
            } else if (nextCharacter == '^' || nextCharacter == '(') {
                operatorStack.push(nextCharacter);
                currentPosition++;
            } else if (nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {
                while (!operatorStack.isEmpty() && nextCharPreced <= peekPreced) {
                    postfix += operatorStack.peek();
                    operatorStack.pop();
                }
                operatorStack.push(nextCharacter);
                currentPosition++;
            } else if (nextCharacter == ')') {
                    topOperator = operatorStack.pop();
                    while (topOperator != '(') {
                        postfix = postfix + topOperator;
                        topOperator = operatorStack.pop();
                    }
                    currentPosition++;
            } else {
                currentPosition++;
            }
        }

        while (!operatorStack.isEmpty()) {
            topOperator = operatorStack.pop();
            postfix = postfix + topOperator;
        }
        postfixChar = postfix.toCharArray();
        return postfixChar;
    } // end of ConvertToPostfix

    /**
     * Determines the precedence of a given operator.
     * @param operator The operator whose precedence will be determined.
     * @return Integer value of the operator's precedence.
     */
    private static int getPrecedence(char operator) {
        int precedence = 0;
       
        switch (operator) {
          case '*':
             precedence = 2;
             break;
          case '/':
             precedence = 2;
             break;
          case '+':
             precedence = 1;
             break;
          case '-':
             precedence = 1;
             break;
          default:
             break;
       }
 
       return precedence;
     } // end of getPrecedence
    
    public static void main(String[] args) {
        /**
         * Demo the functionality of the algorithm convertToPostfix
         */

        // infix expressions to be converted to postfix for demo
        String infix1 = "a/b*(c+(d-e))"; 
        String infix2 = "a*b/(c-a)+d*e";

        // changing result of converToPostfix to string
        String postfix1 = String.valueOf(convertToPostfix(infix1));
        String postfix2 = String.valueOf(convertToPostfix(infix2));

        // outputing results formated to show the starting input and the output of algorithm
        System.out.println("Coverting infix to postfix: \n");
        System.out.println("Infix: " + infix1 + " ->  Postfix: " + postfix1 + "\n");
        System.out.println("Infix: " + infix2 + " ->  Postfix: " + postfix2 + "\n");
       


    }

}
