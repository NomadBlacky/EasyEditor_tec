import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


public class EasyEditor extends JFrame { 
	
	// テキストフィールド
	JTextPane textPane;
	// 書式とかいろいろいじれる（らしい）
	StyledDocument doc;
	
	public EasyEditor() {
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnLoad = new JButton("Open");
		btnLoad.addActionListener(new OpenButtonAction());
		toolBar.add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveButtonAction());
		toolBar.add(btnSave);
		
		textPane = new JTextPane();
		doc = textPane.getStyledDocument();
		JScrollPane scrollPane = new JScrollPane(textPane);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
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
	
	
	public static void main(String[] args) {

		EasyEditor easyEditor = new EasyEditor();
		easyEditor.setTitle("EasyEditor");
		easyEditor.setSize(new Dimension(600,400));
		easyEditor.setVisible(true);
	}

}
