package addressBook;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableModel;

public class EditFrame extends JFrame {

	private TableModel model;
	private JPanel mainPanel;

	public EditFrame(TableModel tmodel) {
		
		model = tmodel;
		
		this.setBounds(200, 200, 600, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnPrev = new JButton("↑戻る(B)");
		btnPrev.addActionListener(new PrevButtonAction());
		panel.add(btnPrev);

		JButton btnNext = new JButton("次へ↓(N)");
		btnNext.addActionListener(new NextButtonAction());
		panel.add(btnNext);

		JButton btnEntry = new JButton("新規登録(S)");
		btnEntry.addActionListener(new EntryButtonAction());
		panel.add(btnEntry);

		JButton btnDelete = new JButton("削除(D)");
		btnDelete.addActionListener(new DeleteButtonAction());
		panel.add(btnDelete);

		JButton btnExit = new JButton("終了(E)");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panel.add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		mainPanel = new JPanel();
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(mainPanel);
		GridBagLayout gbl_panelMain = new GridBagLayout();
		gbl_panelMain.columnWidths = new int[]{0, 0, 0};
		gbl_panelMain.rowHeights = new int[]{0, 0, 0};
		gbl_panelMain.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelMain.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_panelMain);
		
		init();

		// 余白用のパネル
		JPanel hogePanel = new JPanel();
		hogePanel.setBorder(null);
		getContentPane().add(hogePanel, BorderLayout.NORTH);
	}

	class PrevButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			prevData();
		}
	}

	class NextButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			nextData();
		}
	}

	class EntryButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}
	}

	class DeleteButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}
	}
	
	private ArrayList<JTextField> textFields = new ArrayList<>();
	private int nowDataRow = 0;
	
	public int getNowDataRow() {
		return nowDataRow;
	}
	
	private void init() {

		for(int i = 0; i < model.getColumnCount(); i++) {
			
			JLabel label = new JLabel(model.getColumnName(i));
			GridBagConstraints labelGbc = new GridBagConstraints();
			labelGbc.ipady = 2;
			labelGbc.ipadx = 2;
			labelGbc.insets = new Insets(5, 5, 5, 5);
			labelGbc.anchor = GridBagConstraints.EAST;
			labelGbc.gridx = 0;
			labelGbc.gridy = i;
			mainPanel.add(label, labelGbc);
			
			JTextField textField = new JTextField((String)model.getValueAt(nowDataRow, i));
			textFields.add(textField);
			GridBagConstraints textGbc = new GridBagConstraints();
			textGbc.ipady = 2;
			textGbc.ipadx = 2;
			textGbc.insets = new Insets(5, 5, 5, 5);
			textGbc.fill = GridBagConstraints.HORIZONTAL;
			textGbc.gridx = 1;
			textGbc.gridy = i;
			mainPanel.add(textField, textGbc);
			textField.setColumns(10);
		}

	}

	public void update(int nextIndex) {
		
		if(nextIndex < 0 || nextIndex >= model.getRowCount()) {
			return;
		}
		
		// 編集したデータをテーブルに更新
		for(int i = 0; i < model.getColumnCount(); i++) {
			String text = textFields.get(i).getText();
			model.setValueAt(text, nowDataRow, i);
		}
		
		// 次に編集するデータをテキストフィールドに表示
		for(int i = 0; i < model.getColumnCount(); i++) {
			String text = (String)model.getValueAt(nextIndex, i);
			textFields.get(i).setText(text);
		}
		
		// 表示が完了したら現在のインデックスを更新
		nowDataRow = nextIndex;
	}

	private void showData() {

	}

	private void prevData() {

		if(nowDataRow > 0) {
			update(nowDataRow - 1);
		}

	}

	private void nextData() {

		if(nowDataRow < model.getRowCount()) {
			update(nowDataRow + 1);
		}
	}


}
