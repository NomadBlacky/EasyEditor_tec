package easyEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


public class EasyEditor extends JFrame {

	// テキストフィールド
	JTextPane textPane;
	// 書式とかいろいろいじれる（らしい）
	StyledDocument doc;
	// TODO
	UpdateCommand uCommand;
	CommandInvoker cInvoker;

	public EasyEditor() {

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
		toolBar.add(btnUndo);

		JButton btnRedo = new JButton("  Redo  ");
		toolBar.add(btnRedo);

		textPane = new JTextPane();
		doc = textPane.getStyledDocument();
		JScrollPane scrollPane = new JScrollPane(textPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		uCommand = new UpdateCommand(textPane, "", "");
		cInvoker = new CommandInvoker();
	}

	// "Open"ボタン押下時
	class OpenButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// ファイル選択ダイアログを表示
			JFileChooser jChooser = new JFileChooser();
			int selected = jChooser.showOpenDialog(null);

			// 「開く」ボタン押下時
			if(selected == JFileChooser.APPROVE_OPTION) {

				// 選択したファイルを取得
				File file = jChooser.getSelectedFile();
				setTitle(String.format("%s - %s", file.getName(), file.getPath()));

				try {
					Scanner scan = new Scanner(file);
					// 文字列の連結が高速なStringBuilderを使用
					StringBuilder text = new StringBuilder();

					// ファイルを読み込む
					while (scan.hasNext()) {
						text.append(scan.nextLine().concat("\n"));
					}
					textPane.setText("");
					doc.insertString(0, text.toString(), null);

					// TODO
					uCommand = new UpdateCommand(textPane, textPane.getText(), textPane.getText());

				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "ファイルが見つかりません。", "エラー", JOptionPane.WARNING_MESSAGE);
					setTitle("EasyEditor");
				} catch (BadLocationException e1) {
					JOptionPane.showMessageDialog(null, "読み込みに失敗しました。", "エラー", JOptionPane.WARNING_MESSAGE);
					setTitle("EasyEditor");
				}
			}
		}
	}

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

	class UndoButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			cInvoker.undo();
		}

	}

	class RedoButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			cInvoker.redo();
		}

	}

	// テキストが編集されたとき
	class TextChanged implements DocumentListener, KeyListener {

		@Override
		public void changedUpdate(DocumentEvent e) {
			//cInvoker.invoke(command);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
		}
		@Override
		public void removeUpdate(DocumentEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {

			cInvoker.invoke(uCommand);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

	}


	public static void main(String[] args) {

		EasyEditor easyEditor = new EasyEditor();
		easyEditor.setTitle("EasyEditor");
		easyEditor.setSize(new Dimension(600,400));
		easyEditor.setVisible(true);
	}

}
