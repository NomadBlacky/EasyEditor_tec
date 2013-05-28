package easyEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

public class UndoTextPane extends JTextPane implements UndoableEditListener, KeyListener {

	UndoManager uManager = new UndoManager();

	@Override
	public void undoableEditHappened(UndoableEditEvent e) {
		// TODO
		uManager.addEdit(e.getEdit());
	}
	
// UndoableEditListener --------------------------------------------------

	public UndoTextPane() {
		Document doc = this.getDocument();
		doc.addUndoableEditListener(this);
		this.addKeyListener(this);
	}

// KeyListener --------------------------------------------------

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		
		if(e.isControlDown() && uManager.canUndo()) {
			if(key == KeyEvent.VK_Z) {
				uManager.undo();
				e.consume();
			}
			
			if(key == KeyEvent.VK_Y && uManager.canRedo()) {
				uManager.redo();
				e.consume();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
// ButtonEvent --------------------------------------------------
	
	public void undo() {
		if(uManager.canUndo()) {
			uManager.undo()
		}
	}
	
	public void redo() {
		
	}
}
