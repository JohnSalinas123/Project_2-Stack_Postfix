import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 
 */

public final class ResizeableArrayStack<T> implements StackInterface<T>
{
    private T[] stack;    // Array of stack entries
    private int topIndex; // Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
  
   public ResizeableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   public ResizeableArrayStack(int initialCapacity)
   {
        integrityOK = false;
        checkCapacity(initialCapacity);
        
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
  } // end constructor

    //  < Implementations of the private methods go here; checkCapacity and checkIntegrity
    //    are analogous to those in Chapter 2. >

    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a bag whose " +
                                            "capacity exceeds allowed " +
                                            "maximum of " + MAX_CAPACITY);
        }

    } // end checkCapacity

    private void ensureCapacity() {
        if (topIndex >= stack.length - 1) {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    } // end ensureCapacity

    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayStack object is corrupt");
        }
    } // end checkIntegrity

    //  < Implementations of the stack operations go here. >

    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } // end push

    @Override
    public T pop() {
        checkIntegrity();
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    } // end pop 

    @Override
    public T peek() {
        checkIntegrity();
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack[topIndex];
        }
    } // end peek

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    } // end isEmpty

    @Override
    public void clear() {
        checkIntegrity();

        // Remove refferences to the objects in the stack
        // but do not deallocate the array
        while (topIndex > -1) {
            stack[topIndex] = null;
            topIndex--;
        }
        // Assertion: topIndex is -1
    } // end clear

    @Override
    public char[] convertToPostfix(String infixStr) {
        // TODO Auto-generated method stub
        return null;
    }

    
    
} // end ArrayStack
