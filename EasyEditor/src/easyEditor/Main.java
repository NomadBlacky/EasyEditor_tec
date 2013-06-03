package easyEditor;

import java.awt.Dimension;

import addressBook.AddressBook;

public class Main {

	public static void main(String[] args) {

		/*
			EasyEditor easyEditor = new EasyEditor();
			easyEditor.setTitle("EasyEditor");
			easyEditor.setSize(new Dimension(600,400));
			easyEditor.setVisible(true);
		*/
		
		AddressBook editCsv = new AddressBook();
		editCsv.setTitle("CSV-mode");
		editCsv.setSize(new Dimension(600,400));
		editCsv.setVisible(true);

	}

}
