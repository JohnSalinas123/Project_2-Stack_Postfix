import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class ArrayStackTest {

    /**
     * Calculates the value of a given postfix expression.
     * @param postfixString The postfix expression to be evaluated.
     * @param valueOfVar The values from left to right, of each variable a-e.
     * @return The integer value of the evaluated postfix expression.
     */
    public static int evaluatePostfix(String postfixString, int[] valueOfVar ) {
       
        StackInterface<Integer> valueStackInterface = new ResizeableArrayStack<>() ;
            
            char nextItem ;
            int operand1 ;
            int operand2 ;
            int result = 0 ;
            int i = 0 ;

           if (valueOfVar != null && postfixString != null) {

                while(i < postfixString.length()){
                    nextItem = postfixString.charAt(i);
                    if (charVariable(nextItem)){
                        
                            valueStackInterface.push(determineValue(nextItem, valueOfVar));

                    } else {                                                                                                   
                        operand2 = valueStackInterface.pop();
                        operand1= valueStackInterface.pop();
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
           } else {
               // Returns -1 if either postFixString or valueOfVar are null
               return -1;
           }
    
        }

        /**
         * Checks whether input is one of the five variable characters.
         * @param ch The character that is being checked for being a variable.
         * @return True if the character is a variable, false if not.
         */
        // Unit Testing for this method is in this file, since it is private
        private static boolean charVariable(char ch){
            return(ch == 'a') || (ch == 'b') || (ch == 'c') || (ch == 'd') || (ch == 'e');
        }

        /**
         * Determines the value of a given variable character.
         * @param nextItem The variable whos value will be found.
         * @param valuesArray The values for all five possible variables.
         * @return The variable's correct value, or -1 if there is no correct value.
         */
        // Unit Testing for this method is in this file, since it is private
        private static int determineValue(char nextItem, int[] valuesArray) {
            int result = -1;


            switch(nextItem){
                case'a':
                    if (valuesArray.length >= 1)
                        result = valuesArray[0];
                    break;
                case'b':
                    if (valuesArray.length >= 2)
                        result = valuesArray[1];
                    break;
                case'c':
                    if (valuesArray.length >= 3)
                        result = valuesArray[2];
                    break;
                case'd':
                    if (valuesArray.length >= 4)
                        result = valuesArray[3];
                    break;
                case'e':
                    if (valuesArray.length >= 5)
                        result = valuesArray[4];
                    break;
                default:
                    break;
            } 

            return result;
        }


    
    public static void main(String[] args) {

        int[] numbers =  {2,3,4,5,6};

        String postfixStr = "ab*ca-/de*+";

        System.out.println(evaluatePostfix(postfixStr,numbers));  
    }


    /**
     * Testing charVariable (Private method)
     */

    // Testing charVariable with valid input
    @Test
    public void charVariable_ValidTest() {

        char input1 = 'a';
        char input2 = 'b';
        char input3 = 'c';
        char input4 = 'd';
        char input5 = 'e';

        assertTrue(charVariable(input1));
        assertTrue(charVariable(input2));
        assertTrue(charVariable(input3));
        assertTrue(charVariable(input4));
        assertTrue(charVariable(input5));

    }

    // Testing charVariable with invalid input
    @Test
    public void charVariable_InvalidTest() {

        char invalidVar1 = 'g';
        char invalidVar2 = 'o';
        char invalidVar3 = '=';

        assertFalse(charVariable(invalidVar1));
        assertFalse(charVariable(invalidVar2));
        assertFalse(charVariable(invalidVar3));

    }

    /**
     * Testing determingValue (Private method)
     */
    // Testing determineValue with valid input
    @Test
    public void determineValue_ValidInput() {

        int[] valueArray = {2,3,4,5,6};

        int[] shorterArray = {2,3,4};

        char var1 = 'a';
        char var2 = 'b';
        char var3 = 'c';
        char var4 = 'd';
        char var5 = 'e';

        assertEquals(valueArray[0], determineValue(var1,valueArray));
        assertEquals(valueArray[1], determineValue(var2,valueArray));
        assertEquals(valueArray[2], determineValue(var3,valueArray));
        assertEquals(valueArray[3], determineValue(var4,valueArray));
        assertEquals(valueArray[4], determineValue(var5,valueArray));

        assertEquals(shorterArray[0], determineValue(var1,shorterArray));
        assertEquals(shorterArray[1], determineValue(var2,shorterArray));
        assertEquals(shorterArray[2], determineValue(var3,shorterArray));
        assertEquals(-1, determineValue(var4, shorterArray));
        assertEquals(-1, determineValue(var5, shorterArray));

    }

    // Testing determineValue with invalid input
    @Test
    public void determineValue_InvalidInput() {
        int[] valuesArray = {2,3,4,5,6};
        char var1 = 'a';

        int[] invalidArray = {};
        char invalidChar = 'p';
        int expectedResult = -1;


        assertEquals(expectedResult, determineValue(invalidChar,valuesArray));
        assertEquals(expectedResult, determineValue(var1,invalidArray));

    } 

}
