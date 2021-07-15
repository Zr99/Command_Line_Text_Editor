public abstract class Command {
    protected Document doc;
    protected BoundedStack<Character> stack = new BoundedStack<>();
    public Command(Document pdoc) { doc = pdoc; }
    public abstract boolean canBeDone();
    public abstract void perform();
    public abstract void undo();
}


///////////////  INSERT COMMAND /////////////////////
class Insert extends Command {
    char c;

    public Insert(Document pdoc, char pc) {
        super(pdoc);
        c = pc;

    }

    public boolean canBeDone() {
        return true;
    }

    public void perform() {
        doc.insert(c);
        doc.moveCursor(+1);
        stack.push(c);
    }

    public void undo() {
        doc.moveCursor(-1);
        doc.delete();

    }
}

///////////////  DELETE COMMAND /////////////////////
class Delete extends Command {
    char c;

    public Delete(Document pdoc) {

        super(pdoc);

    }

    public boolean canBeDone() {
        return doc.getCursorPosition() < doc.getLength();

    }

    public void perform() {
        c = doc.charAtCursor();
        doc.delete();
        stack.push(c);
    }

    public void undo() {
        doc.insert(c);

    }
}

///////////////  MOVE CURSOR COMMAND /////////////////////
class MoveCursor extends Command {
    int direction;

    public MoveCursor(Document pdoc, int dir) {
        super(pdoc);
        direction = dir;
    }

    public boolean canBeDone() {
        if (direction < 0) {

            return doc.getCursorPosition() > 0;
        } else {
            return doc.getCursorPosition() < doc.getLength();
        }
    }

    public void perform() {
        stack.push('m');
        doc.moveCursor(direction);
    }

    public void undo() {

        doc.moveCursor(-direction);
    }

}
