public class ArrayStackTest {
    
    public static void main(String[] args) {

        String infixStr = "a-b+c";

        StackInterface<Character> stack1 = new ResizeableArrayStack<>() ;

        System.out.print("Result: ");
        System.out.println(stack1.evaluePostfix(postfixString));  
    }
}
