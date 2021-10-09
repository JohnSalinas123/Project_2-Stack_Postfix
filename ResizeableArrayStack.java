import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Stack implemented using a resizeable array.
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

    
    /** Checks whether the capacity of the stack has exceeded the max capacity, if so
     * an IllegalStateException is thrown.
     * @param capacity The capacity of the stack.
     */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a bag whose " +
                                            "capacity exceeds allowed " +
                                            "maximum of " + MAX_CAPACITY);
        }

    } // end checkCapacity

    /**
     * Ensures that there is enough capacity in the stack, if not it makes 
     * a new stack
     * with double the capacities.
     */
    private void ensureCapacity() {
        if (topIndex >= stack.length - 1) {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    } // end ensureCapacity

    /**
     * Checks the integrity of the stack, if not then a SecurityException
     * is thrown.
     */
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayStack object is corrupt");
        }
    } // end checkIntegrity

    
    /** Adds newEntry to the top of the stack.
     * @param newEntry The entry being added to the top of the stack.
     */
    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } // end push

    
    /** Removes the top of the stack and return the data of that element.
     * @return T The data of the element at the top of the stack.
     */
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

    /** Remove the top of the stack and return the data of that element.
     * @return T The data of the element at the top of the stack.
     */
    @Override
    public T peek() {
        checkIntegrity();
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stack[topIndex];
        }
    } // end peek

    
    /** Checks if the stack is empty.
     * @return boolean True if the stack is empty, false if not.
     */
    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    } // end isEmpty

    /**
     * Sets each array index of the stack to null, clearing the stack.
     */
    @Override
    public void clear() {
        checkIntegrity();
        while (topIndex > -1) {
            stack[topIndex] = null;
            topIndex--;
        }
        // Assertion: topIndex is -1
    } // end clear

    
    /** 
     * @param infixStr
     * @return char[]
     */
    @Override
    public char[] convertToPostfix(String infixStr) {
        // TODO Auto-generated method stub
        return null;
    }

    
    
} // end ArrayStack
