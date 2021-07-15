import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoundedStackTest {

    BoundedStack<String> stringStack;

    @org.junit.Before
    public void setUp() throws Exception {
        stringStack = new BoundedStack<String>();


    }

    @org.junit.Test
    public void empty() {
        Assert.assertTrue("Stack should start empty", stringStack.empty());
    }

    @Test
    public void testStackLength(){
        stringStack.push("fdgfg");
        stringStack.push("fdgfg");
        stringStack.push("fdgfg");
        Assert.assertEquals(3, stringStack.stackLength());
    }

    @Test
    public void testStackInput(){
        stringStack.push("b");
        Assert.assertEquals("b", stringStack.peek());
    }

    @Test
    public void testPopFunction(){
        stringStack.push("k");
        stringStack.pop();
        stringStack.push("b");
        Assert.assertEquals("b", stringStack.peek());
    }
}

