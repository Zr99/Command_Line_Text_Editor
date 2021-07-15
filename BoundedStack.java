public class BoundedStack<T>{
    static final int MAX = 10;

    private final T[] undoStack; // new t[10];
    private int numStored;

    public BoundedStack(){
        numStored = 0;
        undoStack = makeArray(MAX);
    }
    // Whatever properties are needed to implement the stack
    public void push(T item) {

        if(numStored >= (MAX)){
            System.out.println("Stack is full");
        }
       undoStack[numStored] = item;
        numStored++;
    }

    public T pop() {

        numStored--;
        T result = undoStack[numStored];
        undoStack[numStored] = null;
        return result;
    }

    public boolean empty() {
        // Return true if stack is empty, false otherwise

        return (numStored == 0);
    }
    public int stackLength(){

        return numStored;
    }

    public T peek()
    {
        if (empty())
            throw new RuntimeException("Is Empty");


        return undoStack[numStored-1];
    }
    /**
     * Helper for creating an array with T as the element type. The
     * elements are initialized to null.  You will only need this if
     * you choose to implement your stack with an array
     */
    private T[] makeArray(int size) {
        // We would normally use "new T[size]", but we can't because T
        // is a type parameter and Java does not support generic array
        // creation. The following is an imperfect workaround.
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[size];
        return array;
    }

}
