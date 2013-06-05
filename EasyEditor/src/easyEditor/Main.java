package easyEditor;

import java.awt.Dimension;

import javax.swing.JFrame;

import addressBook.AddressBook;

public class Main {

	public static void main(String[] args) {

		EasyEditor easyEditor = new EasyEditor();
		easyEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		easyEditor.setTitle("EasyEditor");
		easyEditor.setSize(new Dimension(600,400));
		easyEditor.setVisible(true);		

	}

}
