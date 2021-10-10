public class LinkedStackTest {
    
    public static void main(String[] args) {
        /**
         * Demo the functionality of the algorithm convertToPostfix
         */

        // creating two stacks
        StackInterface<Character> stack1 = new LinkedStack<>();
        StackInterface<Character> stack2 = new LinkedStack<>();

        // infix expressions to be converted to postfix for demo
        String infix1 = "a/b*(c+(d-e))"; 
        String infix2 = "a*b/(c-a)+d*e";

        // changing result of converToPostfix to string
        String postfix1 = String.valueOf(stack1.convertToPostfix(infix1));
        String postfix2 = String.valueOf(stack2.convertToPostfix(infix2));

        // outputing results formated to show the starting input and the output of algorithm
        System.out.println("Coverting infix to postfix: \n");
        System.out.println("Infix: " + infix1 + " ->  Postfix: " + postfix1 + "\n");
        System.out.println("Infix: " + infix2 + " ->  Postfix: " + postfix2 + "\n");
       


    }

}
