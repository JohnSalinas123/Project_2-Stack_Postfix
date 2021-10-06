import java.util.EmptyStackException;

/**
 * 
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

   //  < Implementations of the stack operations go here. >

    @Override
    public void push(T newEntry) {
         Node newNode = new Node(newEntry, topNode);
         topNode = newNode;
        
    } // end push

    @Override
    public T pop() {
         T top = peek(); // Might throw EmptyStackException
         // Assertion: topNode != null;
         topNode = topNode.getNextNode();
        return top;
    } // end pop

    @Override
    public T peek() {
         if (isEmpty()) {
            throw new EmptyStackException();
         } else {
            return topNode.getData();
         }
    } // end peek

    @Override
    public boolean isEmpty() {
         return topNode == null;
    } // end isEmpty

    @Override
    public void clear() {
      topNode = null;
    } // end clear


   public char[] convertToPostfix(String infix) {
      StackInterface<Character> operatorStack = new LinkedStack<>();

      String postfix = "";
      char[] postfixChar = postfix.toCharArray();
      char[] infixChar = infix.toCharArray();

      int infixLength = infix.length();

      int currentPosition = 0;

      char topOperator = 'a';
      int nextCharPreced = 0;
      int peekPreced = 0;

      while (currentPosition < infixLength) {
         postfixChar = postfix.toCharArray();

         char nextCharacter = ' ';

         //System.out.println(currentPosition);

         // sets nextCharacter to next nonblank character of infix
         for (int i = currentPosition;i < infixLength;i++) {
            if (infixChar[i] != ' ') {
               nextCharacter = infixChar[i];
               //System.out.println(nextCharacter);
               break;
            }
            currentPosition++;
         }
          

         // determing the precedence of the top of the operator stack
         if (!operatorStack.isEmpty()) {
            char peekChar = operatorStack.peek();
            switch (peekChar) {
               case '(': case ')':
                  peekPreced = 0;
                  break;
               case '*':
                  peekPreced = 2;
                  break;
               case '/':
                  peekPreced = 2;
                  break;
               case '+':
                  peekPreced = 1;
                  break;
               case '-':
                  peekPreced = 1;
                  break;
               default:
                  break;
            }

         }
         
         // determines the precedence if nextCharacter is an operator
         switch (nextCharacter) {
            case '(': case ')':
               nextCharPreced = 0;
                  break;
            case '*':
               nextCharPreced = 2;
               break;
            case '/':
               nextCharPreced = 2;
               break;
            case '+':
               nextCharPreced = 1;
               break;
            case '-':
               nextCharPreced = 1;
               break;
            default:
               break;
         }

         

         if (nextCharacter >= 'a' && nextCharacter <= 'z') {

            postfix = postfix + nextCharacter;
            currentPosition++;

            //System.out.println("Was a letter");

         } else if (nextCharacter == '^') {

            operatorStack.push(nextCharacter);
            currentPosition++;

         } else if (nextCharacter == '+' || nextCharacter == '-' || nextCharacter == '*' || nextCharacter == '/') {

            while (!operatorStack.isEmpty() && nextCharPreced <= peekPreced) {
               postfix = postfix + operatorStack.peek();
               operatorStack.pop();
            }
            //System.out.println("nextCharacter: " + nextCharacter);

            operatorStack.push(nextCharacter);
            //System.out.println("peek testing: " + operatorStack.peek());
            

            currentPosition++;
            //System.out.println("Was an operator");
         } else if (nextCharacter == '(') {

            operatorStack.push(nextCharacter);
            //System.out.println("peek testing: " + operatorStack.peek());
            currentPosition++;
            //System.out.println("Was a (");

         } else if (nextCharacter == ')') {
            //System.out.println("Peek: " + operatorStack.peek());
            //System.out.println("topOperator: " + topOperator);
            //System.out.println("topNode: " + topNode.getData());
            topOperator = operatorStack.pop();
            while (topOperator != '(') {

               postfix = postfix + topOperator;
               topOperator = operatorStack.pop();

            }
            currentPosition++;
            //System.out.println("Was a )");

         } else {
            currentPosition++;
            break;
         }

         //System.out.println("End of loop, data was: " + operatorStack.peek());
         //System.out.println("End of loop");

         //System.out.println("Postfix is: " + postfix);
         //System.out.println("topNode: " + topNode);
      }
      
      while (!operatorStack.isEmpty()) {
            topOperator = operatorStack.pop();
            postfix = postfix + topOperator;
      } 

      postfixChar = postfix.toCharArray();
      return postfixChar;
   }

    

} // end LinkedStack