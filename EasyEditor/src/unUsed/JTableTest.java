package unUsed;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JFrame {

	private String[][] tabledata = { 
			{ "日本", "3勝", "0敗", "1分" },
			{ "クロアチア", "3勝", "1敗", "0分" }, 
			{ "ブラジル", "1勝", "2敗", "1分" },
			{ "オーストラリア", "2勝", "2敗", "0分" } 
			};

	private String[] tabledata2 = { "イギリス", "2勝", "0敗", "2分" };

	private String[] columnNames = { "COUNTRY", "WIN", "LOST", "EVEN" };

	public static void main(String[] args) {
		JTableTest test = new JTableTest("SwingTest");

		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

	JTableTest(String title) {
		setTitle(title);
		setBounds(10, 10, 300, 200);

		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

		JTable table = new JTable(tableModel);

		for (int i = 0; i < 4; i++) {
			tableModel.addRow(tabledata[i]);
		}

		tableModel.addRow(tabledata2);

		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(250, 90));

		JPanel p = new JPanel();
		p.add(sp);

		getContentPane().add(p, BorderLayout.CENTER);
	}
}