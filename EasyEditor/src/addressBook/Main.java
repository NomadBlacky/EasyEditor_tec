package addressBook;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		AddressBook frame = new AddressBook();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("AddressBook");
		frame.setBounds(100, 100, 600, 400);
		frame.setVisible(true);
	}

}
