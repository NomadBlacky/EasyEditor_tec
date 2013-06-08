package unUsed;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableTest extends JFrame {

	
	JTableTest(String title) {
		
		String[] columnNames = { "ID", "なまえ", "せんとうりょく"};
		
		String[][] tabledata = { 
				{"01", "Hoge君", "1"},
				{"02", "foo子ちゃん", "50000"},
				{"03", "bar太郎", "300"}
		};
		
		DefaultTableModel model = 
				new DefaultTableModel(tabledata, columnNames);
		JTable table = new JTable(model);
		
		// 列を追加
		model.addColumn("趣味");
		// 行を追加
		String[] huga = {"04","huga","-100","素振り"};
		model.addRow(huga);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(250, 90));

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout(0, 0));
		p.add(sp);

		getContentPane().add(p, BorderLayout.CENTER);
	}


	public static void main(String[] args) {
		JTableTest test = new JTableTest("SwingTest");
		test.setBounds(100,100,400,300);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

}