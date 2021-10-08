import java.util.EmptyStackException;

/**
 * A class of stacks who entries are stored in a chain of nodes.
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
    * @param newEntry The entry being added to the stack.
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


    /**
     * Determines the precedence of a given operator.
     * @param operator The operator whose precedence will be determined.
     * @return Integer value of the operator's precedence.
     */
    private int getPrecedence(char operator) {
       int precedence = 0;
      
       switch (operator) {
         //case '(': case ')':
            //precedence = 0;
            //break;
         case '*':
            precedence = 2;
            break;
         case '/':
            precedence = 2;
            break;
         case '+':
            precedence = 1;
            break;
         case '-':
            precedence = 1;
            break;
         default:
            break;
      }

      return precedence;
    }


   
   /** Converts the input infix expression to a postfix expression.
    * @param infix The infix input to be converted to postfix.
    * @return char[] The converted postfix expression.
    */
   public char[] convertToPostfix(String infix) {
      // create a new stack, whose data are of the character type
      StackInterface<Character> operatorStack = new LinkedStack<>();

      String postfix = "";
      char[] postfixChar = postfix.toCharArray();
      char[] infixChar = infix.toCharArray();
      char topOperator = ' ';

      int infixLength = infix.length();
      int currentPosition = 0;
      int nextCharPreced = 0;
      int peekPreced = 0;

      while (currentPosition < infixLength) {

         postfixChar = postfix.toCharArray();
         char nextCharacter = ' ';

         // sets nextCharacter to next nonblank character of infix
         for (int i = currentPosition;i < infixLength;i++) {
            if (infixChar[i] != ' ') {
               nextCharacter = infixChar[i];
               break;
            }
            currentPosition++;
         }
          
         // determine the precedence of the operator at the top of the stack
         if (!operatorStack.isEmpty()) {
            peekPreced = getPrecedence(operatorStack.peek());
         }
         
         // determines the precedence of nextCharacter
         nextCharPreced = getPrecedence(nextCharacter);


         if (nextCharacter >= 'a' && nextCharacter <= 'z') {

            postfix = postfix + nextCharacter;
            currentPosition++;

         } else if (nextCharacter == '^') {

            operatorStack.push(nextCharacter);
            currentPosition++;

         } else if (nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {

            while (!operatorStack.isEmpty() && nextCharPreced <= peekPreced) {

               postfix = postfix + operatorStack.peek();
               operatorStack.pop();

            }

            operatorStack.push(nextCharacter);
            currentPosition++;

         } else if (nextCharacter == '(') {

            operatorStack.push(nextCharacter);
            currentPosition++;

         } else if (nextCharacter == ')') {

            topOperator = operatorStack.pop();
            while (topOperator != '(') {

               postfix = postfix + topOperator;
               topOperator = operatorStack.pop();

            }
            currentPosition++;

         } else {

            currentPosition++;
            break;
            
         }

      }
      
      while (!operatorStack.isEmpty()) {
            topOperator = operatorStack.pop();
            postfix = postfix + topOperator;
      } 

      postfixChar = postfix.toCharArray();
      return postfixChar;
   }

} // end LinkedStack