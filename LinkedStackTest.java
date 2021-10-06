public class LinkedStackTest {
    
    public static void main(String[] args) {

        String infixStr = "a-b+c";

        StackInterface<Character> stack1 = new LinkedStack<>();

        System.out.print("Result: ");
        System.out.println(stack1.convertToPostfix(infixStr));


    }

}
