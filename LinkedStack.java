import java.util.EmptyStackException;

/**
 * Stack implemented using linked data.
 */
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain
  
   public LinkedStack()
   {
      topNode = null;
   } // end default constructor


	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
   
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node

   
   /** Adds newEntry to the top of the stack.
    * @param newEntry The entry being added to the top of the stack.
    */
    @Override
    public void push(T newEntry) {
         Node newNode = new Node(newEntry, topNode);
         topNode = newNode;
        
    } // end push

    
    /** Remove the top of the stack and return the data of that element.
     * @return T The data of the element at the top of the stack.
     */
    @Override
    public T pop() {
         T top = peek(); // Might throw EmptyStackException
         // Assertion: topNode != null;
         topNode = topNode.getNextNode();
        return top;
    } // end pop

    
    /** Looks at the data of element at the top of the stack, if there is one 
     * returns the data, if not it throws an exception.
     * @return T The data of the element at the top of the stack.
     */
    @Override
    public T peek() {
         if (isEmpty()) {
            throw new EmptyStackException();
         } else {
            return topNode.getData();
         }
    } // end peek

    
    /** Checks if the stack is empty.
     * @return boolean True if the stack is empty, false if not.
     */
    @Override
    public boolean isEmpty() {
         return topNode == null;
    } // end isEmpty

    /**
     * Removes the topNode reference to the linked data, clearing the stack.
     */
    @Override
    public void clear() {
      topNode = null;
    } // end clear
   

} // end LinkedStack