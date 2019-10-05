package mouseCommands;

import java.util.Stack;

public class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		return result;
	}

	// For testing
	IUndoable topUndoCommand() {
		if (undoStack.empty())
			return null;
		else
			return undoStack.peek();
	}
	// For testing
	IUndoable topRedoCommand() {
		if (redoStack.empty())
			return null;
		else
			return redoStack.peek();
	}
}
