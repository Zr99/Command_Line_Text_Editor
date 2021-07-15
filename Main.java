import java.util.Scanner;
import java.util.Stack;

class Main extends BoundedStack
{
    BoundedStack<Command> undoStack = new BoundedStack<>();
    BoundedStack<String> redoStack = new BoundedStack<>();

    Command tryNewCommand(Command com) {
        if (com.canBeDone()) {
            com.perform();
        }
        return com;
    }
    //Something here
    void undo() {
    undoStack.pop();
    }

    void redo() {

    }

    void run() {
        Document doc = new Document();

        boolean finished = false;
        Scanner scanner = new Scanner(System.in);
        doc.show();

        while (! finished) {
            System.out.print("Command > ");
            String line = scanner.nextLine();  // Read command line from keyboard
            int i = 0;                         // Current position in command line
            while (!finished && i < line.length()) {
                switch (line.charAt(i)) {
                    case 'i': // Insert the next character from command line (if any)
                        if (i + 1 < line.length()) {
                            i++;
                            Object a = tryNewCommand(new Insert(doc, line.charAt(i)));
                            undoStack.push((Command) a);
                        }
                        break;
                    case 'd': // Delete the character at the cursor
                        tryNewCommand(new Delete(doc));
                        break;
                    case 'f': // Move the cursor forward
                        tryNewCommand(new MoveCursor(doc, +1));

                        break;
                    case 'b': // Move the cursor back
                        tryNewCommand(new MoveCursor(doc, -1));
                        break;
                    case 'e': // Exit the editor
                        finished = true;
                        break;
                    case 'z': // Undo a command
                        undo();

                        break;
                    case 'y': // Redo a command
                        redo();
                        break;
                }
                i++;
            }
            doc.show();
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}