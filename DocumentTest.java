import org.junit.*;
import static org.junit.Assert.*;

public class DocumentTest {

    Document doc;
    Document k =null;
    Document koc;
    @org.junit.Before
    public void setup() {
        doc = new Document();
        koc = new Document();
        doc.insert('a');
        doc.moveCursor(1);
        doc.insert('b');
        doc.moveCursor(1);
        doc.insert('c');
        doc.moveCursor(1);
        doc.insert('d');
        doc.moveCursor(1);

    }

    @org.junit.Test
    public void testTextLength() {
        assertEquals("Document should have 4 characters", 4, doc.getLength());
    }

    @org.junit.Test
    public void testCursorPosition() {
        assertEquals("Should have cursor at 4", 4, doc.getCursorPosition());
    }

    @Test
    public void testMovingCursorA() {
        doc.moveCursor(-1);
        doc.moveCursor(-1);
        assertEquals("Cursor should have moved to 2", 2, doc.getCursorPosition());
    }

    @Test
    //Check if the current document object is pointing to null
    public void testObjectNotNull(){
        assertNotNull("Your current Document Object is Null!",doc);
    }

    @Test
    public void testCurrentCharacterAtPosition(){
        koc.insert('a');
        assertEquals('a',koc.charAtCursor());

    }

    @Test
    public void testDelete(){
        koc.insert('k');

        if(koc.charAtCursor() == 'k'){
            System.out.println("k Found");
        }
        koc.delete();
        koc.insert('b');
        System.out.println("After delete method the current word is"+koc.charAtCursor());


    }
}



