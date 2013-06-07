package addressBook;

import javax.swing.table.DefaultTableModel;

public class UnEditableTableModel extends DefaultTableModel {

	@Override
	public boolean isCellEditable(int row, int column) {
		// すべてのセルを編集不可にする
		return false;
	}
}