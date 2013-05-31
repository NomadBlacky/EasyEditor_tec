package easyEditor;

import java.awt.Dimension;

public class Main {

	public static void main(String[] args) {

		/*
			EasyEditor easyEditor = new EasyEditor();
			easyEditor.setTitle("EasyEditor");
			easyEditor.setSize(new Dimension(600,400));
			easyEditor.setVisible(true);
		*/
		
		EasyEditor_CSV editCsv = new EasyEditor_CSV();
		editCsv.setTitle("CSV-mode");
		editCsv.setSize(new Dimension(600,400));
		editCsv.setVisible(true);

	}

}
