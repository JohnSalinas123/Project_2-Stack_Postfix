import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * An implementation of the ADT stack using a resizeable array.
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

    
    /** Checks that the capacity is not greater than the max capacity allowed.
     * @param capacity The given capacity to be checked for validity.
     */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a bag whose " +
                                            "capacity exceeds allowed " +
                                            "maximum of " + MAX_CAPACITY);
        }

    } // end checkCapacity

    /**
     * Checks if stack is at size limit, if it is create a new stack of double size.
     */
    private void ensureCapacity() {
        if (topIndex >= stack.length - 1) {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    } // end ensureCapacity

    /**
     * Check if the integrity of the stack is maintained.
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

    
    /** Remove the top of the stack and return the data of that element.
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

    
    /** Looks at the data of element at the top of the stack, if there is one 
     * returns the data, if not it throws an exception.
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
     * Clears the stack by removing references to all the objects.
     */
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
 
}