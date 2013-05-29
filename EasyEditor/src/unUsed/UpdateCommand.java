package unUsed;

import javax.swing.JTextPane;



public class UpdateCommand implements ICommand {
	
	private JTextPane textPane;
	private String prevText;
	private String newText;
	
	public UpdateCommand(JTextPane pane, String pText, String nText) {
		textPane = pane;
		prevText = pText;
		newText = nText;
	}

	@Override
	public void invoke() {
		prevText = textPane.getText();
		textPane.setText(newText);
	}

	@Override
	public void undo() {
		textPane.setText(prevText);
	}

	@Override
	public void redo() {
		textPane.setText(newText);
	}
	
}
