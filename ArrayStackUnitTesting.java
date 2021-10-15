import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStackUnitTesting {
    /**
     * Testing Unit for ArrayStack
     */

     @Test
     public void testEvaluePostfix(){
         int[] numbers = {2,3,4,5,6};
         String postfixStr = "ae+bd−/" ;

        int[] expectedArray =  "-1"  ;

        assertEquals(expectedArray,evaluePostfix)


     }
}
