package easyEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;


public class EasyEditor extends JFrame {

	// テキストフィールド
	UndoTextPane textPane;

	public EasyEditor() {

		// コンポーネントの配置とリスナーの設定

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnLoad = new JButton("  Open  ");
		btnLoad.addActionListener(new OpenButtonAction());
		toolBar.add(btnLoad);

		JButton btnSave = new JButton("  Save  ");
		btnSave.addActionListener(new SaveButtonAction());
		toolBar.add(btnSave);

		JButton btnUndo = new JButton("  Undo  ");
		btnUndo.addActionListener(new UndoButtonAction());
		toolBar.add(btnUndo);

		JButton btnRedo = new JButton("  Redo  ");
		btnRedo.addActionListener(new RedoButtonAction());
		toolBar.add(btnRedo);

		textPane = new UndoTextPane();
		JScrollPane scrollPane = new JScrollPane(textPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

	// ファイルを開く
	public void fileOpen() {

		// ファイル選択ダイアログを表示
		JFileChooser jChooser = new JFileChooser();
		int selected = jChooser.showOpenDialog(null);

		// 「開く」ボタン押下時
		if(selected == JFileChooser.APPROVE_OPTION) {

			// 選択したファイルを取得
			File file = jChooser.getSelectedFile();

			try {
				Scanner scan = new Scanner(file);
				// 文字列の連結が高速なStringBuilderを使用
				StringBuilder text = new StringBuilder();

				// ファイルを読み込む
				while (scan.hasNext()) {
					text.append(scan.nextLine().concat("\n"));
				}

				// テキストフィールドに表示
				textPane.setText(text.toString());
				// タイトル変更
				setTitle(String.format("%s - %s", file.getName(), file.getPath()));
				// 編集履歴をクリア
				textPane.clearHistory();

				scan.close();

			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "ファイルが見つかりません。", "エラー", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	// ファイルを保存する
	public void fileSave() {

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

// ActionListener -----------------------------------------------

	// "Open"ボタン押下時
	class OpenButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fileOpen();
		}
	}

	// "Save"ボタン押下時
	class SaveButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fileSave();
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

// main --------------------------------------------------

	public static void main(String[] args) {

		EasyEditor easyEditor = new EasyEditor();
		easyEditor.setTitle("EasyEditor");
		easyEditor.setSize(new Dimension(600,400));
		easyEditor.setVisible(true);
	}

}
