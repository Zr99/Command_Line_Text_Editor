public class Document {
    // This class does no error checking

    String text = "";
    int cursorPosition = 0;

    public String getText() {  // Used for testing
        return text;
    }

    public int getLength() {
        return text.length();
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public char charAtCursor() {
        // Assumes that cursorPosition < length
        return text.charAt(cursorPosition);
    }

    public void insert(char c) {
        text = text.substring(0, cursorPosition)
                + c
                + text.substring(cursorPosition, text.length());
    }

    public void delete() {
        text = text.substring(0, cursorPosition)
             + text.substring(cursorPosition + 1, text.length());
    }

    public void moveCursor(int direction) {
        cursorPosition += direction;
    }

    public void show() {
        System.out.println("Document: " + text);
        System.out.println("Cursor position " + cursorPosition);
    }
}
