package easyEditor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;


public class EasyEditor_CSV extends JFrame {
	
	private JTable table;
	private DefaultTableModel tableModel;

	public EasyEditor_CSV() {

		// コンポーネントの配置とリスナーの設定

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnLoad = new JButton("  Open  ");
		btnLoad.addActionListener(new OpenButtonAction());
		toolBar.add(btnLoad);

		JButton btnSave = new JButton("  Save  ");
//		btnSave.addActionListener(new SaveButtonAction());
		toolBar.add(btnSave);

		JButton btnUndo = new JButton("  Undo  ");
//		btnUndo.addActionListener(new UndoButtonAction());
		toolBar.add(btnUndo);

		JButton btnRedo = new JButton("  Redo  ");
//		btnRedo.addActionListener(new RedoButtonAction());
		toolBar.add(btnRedo);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);

	}

	// "Open"ボタン押下時
	class OpenButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// ファイル選択ダイアログを表示
			JFileChooser jChooser = new JFileChooser();
			jChooser.setCurrentDirectory(new File("."));
			int selected = jChooser.showOpenDialog(null);

			// 「開く」ボタン押下時
			if(selected == JFileChooser.APPROVE_OPTION) {

				// 選択したファイルを取得
				File file = jChooser.getSelectedFile();
				setTitle(String.format("%s - %s", file.getName(), file.getPath()));

				try {
					Scanner scan = new Scanner(file);
					String[] tableName = null;
					DefaultTableModel defModel = null;
					boolean first = true;
					// ファイルを読み込む
					while (scan.hasNext()) {
						if(first) {
							first = false;
							tableName = scan.nextLine().split(",");
							defModel = new DefaultTableModel(tableName, 0);
						}
						else {
							String[] line = scan.nextLine().split(",");
							defModel.addRow(line);
						}
					}
					
					tableModel = defModel;

				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "ファイルが見つかりません。", "エラー", JOptionPane.WARNING_MESSAGE);
					setTitle("EasyEditor");
				}
			}
		}
	}

	
/*
	
	// "Save"ボタン押下時
	class SaveButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// ファイル選択ダイアログを表示
			JFileChooser jChooser = new JFileChooser();
			int selected = jChooser.showSaveDialog(null);

			// 「保存」ボタン押下時
			if(selected == JFileChooser.APPROVE_OPTION) {

				// 保存するファイル（パス）を取得
				File file = jChooser.getSelectedFile();

				// ファイルが存在する（上書き保存の）場合
				if(file.exists()) {
					// 確認メッセージを表示
					int opt = JOptionPane.showConfirmDialog(null, file.getName().concat("はすでに存在します。上書きしますか？"),
							"上書き保存", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opt != JOptionPane.OK_OPTION) {
						// OK が選択されなければ何もしない
						return;
					}
				}
				try {
					// テキストフィールドの内容を書き込む
					FileWriter fw = new FileWriter(file);
					fw.write(textPane.getText());
					fw.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "保存に失敗しました。", "エラー", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	// "Undo"ボタン押下時
	class UndoButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Undo実行
			textPane.undo();
		}
	}

	// "Redo"ボタン押下時
	class RedoButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Redo実行
			textPane.redo();
		}
	}
*/
	
}
