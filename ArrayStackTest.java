public class ArrayStackTest {

    public static double evaluePostfix(String postfixString, int[ ] valueOfVar ) {
       
        StackInterface<Integer> valueStackInterface = new ResizeableArrayStack<>() ;
            
            char nextItem ;
            int value = 0 ;
            int operand1 ;
            int operand2 ;
            int result = 0 ;
            int i = 0 ;
    
            while(i < postfixString.length()){
                nextItem = postfixString.charAt(i);
                if (charVariable(nextItem)){
                    switch(nextItem){
                        case'a':
                            value = valueOfVar[0];
                            break;
                        case'b':
                            value = valueOfVar[1];
                            break;
                        case'c':
                            value = valueOfVar[2];
                            break;
                        case'd':
                            value = valueOfVar[3];
                            break;
                        case'e':
                            value = valueOfVar[4];
                            break;
                        default:
                            break; 
                
                    } 
                        valueStackInterface.push(value); 
                } else {
                    operand1 = valueStackInterface.pop();
                    operand2 = valueStackInterface.pop();
                    switch(nextItem){
                        case '+':result = operand1 + operand2;
                        break;
                        case '*':result = operand1 * operand2;
                            break;
                        case '-':result = operand1 - operand2;
                            break;
                        case '/':result = operand1 / operand2;
                            break;
                            default:break;
                    }
                    valueStackInterface.push(result);
                }
                i++ ;
    
            }
            return valueStackInterface.pop();
        }
        private static boolean charVariable(char ch){
            return(ch == 'a') || (ch == 'b') || (ch == 'c') || (ch == 'd') || (ch == 'e');
        }



    
    public static void main(String[] args) {

        int[] numbers =  { 2,3,4,5,6};

        String postfixStr = "a-b+c";

        System.out.print("Result: ");
        System.out.println(evaluePostfix(postfixStr,numbers));  
    }
}
