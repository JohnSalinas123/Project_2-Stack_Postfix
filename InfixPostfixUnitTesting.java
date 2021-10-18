import org.junit.Test;

import static org.junit.Assert.*;

public class InfixPostfixUnitTesting {

    /**
     * Testing ConvertToPostFix
     */

    // Tests ConvertToPostfix with a valid infix input
    @Test
    public void ConvertToPostFix_ValidTest() {
        String infixStr1 = "a*b/(c-a)+d*e";
        String expectedPostfix1 = "ab*ca-/de*+";

        String infixStr2 = "a/b*(c+(d-e))";
        String expectedPostfix2 = "ab/cde-+*";


        assertEquals(expectedPostfix1,LinkedStackTest.convertToPostfix(infixStr1));
        assertEquals(expectedPostfix2,LinkedStackTest.convertToPostfix(infixStr2));
    }

    // Tests ConvertToPostfix with a null input
    @Test
    public void ConvertToPostFix_NullTest() {
        String infixNull = null;
        String expectedReturn = null;

        assertEquals(expectedReturn,infixNull);

    }


    /**
     * Testing EvaluatePostfix
     */

    // Tests EvaluatePostfix with valid postfix and values for variables
    @Test
    public void EvaluatePostfix_ValidInput(){
        int[] varValues = {2,3,4,5,6};
        String postfixStr = "ab*ca-/de*+";

        int expectedValue =  33;

        assertEquals(expectedValue,ArrayStackTest.evaluatePostfix(postfixStr, varValues));

    }

    // Tests EvaluatePostfix with a null input for either postfix or variable values
    @Test
    public void EvaluatePostfix_NullInput() {
        // If either input is null, expected return is -1
        int expectedReturn = -1;

        // Case 1: variable values input is null
        int[] varValues1 = null;
        String postfixStr1 = "ab*ca-/de*+";
        
        assertEquals(expectedReturn,ArrayStackTest.evaluatePostfix(postfixStr1, varValues1));

        // Case 2: postfix input is null
        int[] varValues2 = {2,3,4,5,6};
        String postfixStr2 = null;

        assertEquals(expectedReturn,ArrayStackTest.evaluatePostfix(postfixStr2, varValues2));

        // Case 3: postfix input and variable values input are both null
        int[] varValues3 = null;
        String postfixStr3 = null;

        assertEquals(expectedReturn,ArrayStackTest.evaluatePostfix(postfixStr3, varValues3));
    }

}
