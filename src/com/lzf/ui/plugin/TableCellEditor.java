package com.lzf.ui.plugin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.lzf.dao.AutoInsurance;
import com.lzf.ui.Home;
import com.lzf.util.DateTimeUtil;

/**
 * 添加按钮响应事件
 * 
 * @author MJCoder
 *
 */
public class TableCellEditor extends AbstractCellEditor implements javax.swing.table.TableCellEditor {
	private Object value;
	private AutoInsurance autoInsuranceDao = new AutoInsurance();

	public interface DeleteListener {
		void deleteListener(Object value, int row);
	}

	private DeleteListener deleteListener;

	public void addDeleteListener(DeleteListener deleteListener) {
		this.deleteListener = deleteListener;
	}

	public interface UpdateListener {
		void updateListener(Object value, int row);
	}

	private UpdateListener updateListener;

	public void addUpdateListener(UpdateListener updateListener) {
		this.updateListener = updateListener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.
	 * JTable, java.lang.Object, boolean, int, int)
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.value = value;

		DefaultTableModel defaultTableModel = ((DefaultTableModel) table.getModel());
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.BLUE);

		// System.out.println(value);
		if ("-6003".equals(value + "")) {
			panel.setLayout(new BorderLayout());
			JLabel addLabel = new JLabel("<html><u>添加</u></html");
			addLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			addLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			addLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			addLabel.setHorizontalAlignment(SwingConstants.CENTER);
			addLabel.setForeground(Color.BLUE);
			addLabel.setFont(new Font("楷体", Font.PLAIN, 16));
			addLabel.setBackground(Color.WHITE);
			addLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					String message = "【";
					String plateNumber = (String) defaultTableModel.getValueAt(row, 0);
					if (plateNumber == null || plateNumber.equals("")) {
						message += "车牌号";
					}
					String autoUse = (String) defaultTableModel.getValueAt(row, 1);
					if (autoUse == null || autoUse.equals("")) {
						message += "、车辆使用性质";
					}
					String brand = (String) defaultTableModel.getValueAt(row, 2);
					if (brand == null || brand.equals("")) {
						message += "、品牌";
					}
					String model = (String) defaultTableModel.getValueAt(row, 3);
					if (model == null || model.equals("")) {
						message += "、型号";
					}
					String vin = (String) defaultTableModel.getValueAt(row, 4);
					if (vin == null || vin.equals("")) {
						message += "、车架号";
					}
					String engineNumber = (String) defaultTableModel.getValueAt(row, 5);
					if (engineNumber == null || engineNumber.equals("")) {
						message += "、发动机号";
					}
					String debutDate = (String) defaultTableModel.getValueAt(row, 6);
					if (debutDate == null || debutDate.equals("")) {
						message += "、初登日期";
					}
					String name = (String) defaultTableModel.getValueAt(row, 7);
					if (name == null || name.equals("")) {
						message += "、姓名";
					}
					String number = (String) defaultTableModel.getValueAt(row, 8);
					if (number == null || number.equals("")) {
						message += "、身份证号";
					}
					String phone = (String) defaultTableModel.getValueAt(row, 9);
					if (phone == null || phone.equals("")) {
						message += "、电话号码";
					}
					String address = (String) defaultTableModel.getValueAt(row, 10);
					if (address == null || address.equals("")) {
						message += "、地址";
					}
					String dueDate = (String) defaultTableModel.getValueAt(row, 11);
					if (dueDate == null || dueDate.equals("")) {
						message += "、保险到期日";
					}
					String department = (String) defaultTableModel.getValueAt(row, 12);
					if (department == null || department.equals("")) {
						message += "、科室";
					}
					String operator = (String) defaultTableModel.getValueAt(row, 13);
					if (operator == null || operator.equals("")) {
						message += "、经办人";
					}
					if ("【".equals(message)) {
						message = "确定要插入一条新数据么？";
					} else {
						message += "】还没有相关内容；确定要插入么？";
					}
					int res = JOptionPane.showConfirmDialog(table, message, "添加", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
						if (autoInsuranceDao.insert(new com.lzf.bean.AutoInsurance(plateNumber, autoUse, brand, model,
								vin, engineNumber, debutDate, name, number, phone, address, dueDate, department,
								operator, (String) defaultTableModel.getValueAt(row, 14),
								DateTimeUtil.getCurrentTime())) > 0) {
							com.lzf.bean.AutoInsurance autoInsurance = autoInsuranceDao.selectNew();
							if (Home.addData(autoInsurance)) {
								defaultTableModel.removeRow(row);
								// defaultTableModel.addRow(autoInsurance);
							}
						} else {
							JOptionPane.showMessageDialog(table, "插入该条新数据失败，请检查你的输入是否正确再稍后重试？", "添加新数据失败",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
					// stopped!!!!
					fireEditingStopped();

				}
			});
			panel.add(addLabel, BorderLayout.CENTER);
		} else {
			panel.setLayout(new GridLayout(1, 2));
			JLabel updateLabel = new JLabel("<html><u>修改更新</u></html");
			updateLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			updateLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			updateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
			updateLabel.setForeground(Color.BLUE);
			updateLabel.setFont(new Font("楷体", Font.PLAIN, 16));
			updateLabel.setBackground(Color.WHITE);
			updateLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					// stopped!!!!
					fireEditingStopped();
					updateListener.updateListener(value, row);
				}
			});
			panel.add(updateLabel);

			JLabel deleteLabel = new JLabel("<html><u>删除</u></html");
			deleteLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			deleteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			deleteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
			deleteLabel.setForeground(Color.RED);
			deleteLabel.setFont(new Font("楷体", Font.PLAIN, 16));
			deleteLabel.setBackground(Color.WHITE);
			deleteLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					// stopped!!!!
					fireEditingStopped();
					deleteListener.deleteListener(value, row);
				}
			});
			panel.add(deleteLabel);
		}
		return panel;
	}

}
