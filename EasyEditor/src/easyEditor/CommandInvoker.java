package easyEditor;

import java.util.Stack;

public class CommandInvoker {

	private Stack<ICommand> undoStack;
	private Stack<ICommand> redoStack;
	
	public CommandInvoker() {
		undoStack = new Stack<ICommand>();
		redoStack = new Stack<ICommand>();
	}
	
	public void invoke(ICommand command) {
		command.invoke();
		redoStack.clear();
		undoStack.push(command);
	}
	
	public void undo() {
		if(undoStack.isEmpty()) {
			return;
		}
		ICommand command = undoStack.pop();
		command.undo();
		redoStack.push(command);
	}
	
	public void redo() {
		if(redoStack.isEmpty()) {
			return;
		}
		ICommand command = redoStack.pop();
		command.undo();
		undoStack.push(command);
	}
}
