package com.lzf.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.lzf.dao.Permission;
import com.lzf.dao.User;
import com.lzf.ui.plugin.TableCellEditor;
import com.lzf.ui.plugin.TableCellEditor.DeleteListener;
import com.lzf.ui.plugin.TableCellEditor.UpdateListener;

public class ManageUser extends JDialog {

	private User userDao = new User();

	/**
	 * Create the dialog.
	 */
	public ManageUser(Frame owner, String title) {
		super(owner, title, true);
		setResizable(false);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setFont(new Font("楷体", Font.PLAIN, 14));
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManageUser.class.getResource("/com/lzf/image/zff.jpg")));
		setForeground(Color.BLACK);
		setFont(new Font("楷体", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(new BorderLayout());

		JTable table = new JTable();
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);
		table.setGridColor(Color.LIGHT_GRAY);
		Vector<String> columnNames = new Vector<String>(2);
		columnNames.add("姓名");
		columnNames.add("身份证号");
		columnNames.add("联系电话");
		columnNames.add("科室部门");
		columnNames.add("角色权限");
		columnNames.add("         ");
		DefaultTableModel defaultTableModel = new DefaultTableModel(userDao.select(), columnNames) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(defaultTableModel);
		// 设置表数据居中显示
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, defaultTableCellRenderer);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("楷体", Font.PLAIN, 14));
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		JTableHeader jTableHeader = table.getTableHeader();
		jTableHeader.setFont(new Font("楷体", Font.PLAIN, 14));
		DefaultTableCellRenderer tableCellRenderer = (DefaultTableCellRenderer) jTableHeader.getDefaultRenderer();
		tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		jTableHeader.setDefaultRenderer(tableCellRenderer);
		jTableHeader.setReorderingAllowed(false); // 不可整列移动
		TableCellEditor tableCellEditor = new TableCellEditor();
		table.getColumnModel().getColumn(5).setCellEditor(tableCellEditor); // 添加操作单元格内的按钮响应事件
		table.getColumnModel().getColumn(5).setCellRenderer(new com.lzf.ui.plugin.TableCellRenderer()); // 添加操作单元格内的按钮显示效果
		tableCellEditor.addDeleteListener(new DeleteListener() {
			@Override
			public void deleteListener(Object value, int row) {
				int res = JOptionPane.showConfirmDialog(null, "确定要删除该条数据么？", "删除", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					if (userDao.delete(((com.lzf.bean.User) value).getId()) > 0) {
						defaultTableModel.removeRow(row);
					} else {
						JOptionPane.showMessageDialog(table, "删除该条数据失败，请刷新或稍后重试？", "删除数据失败",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		tableCellEditor.addUpdateListener(new UpdateListener() {
			@Override
			public void updateListener(Object value, int row) {
				new UserDetail(ManageUser.this, "修改用户信息", (com.lzf.bean.User) value, table);
			}
		});

		JScrollPane dataScrollPane = new JScrollPane(table);
		dataScrollPane.setFont(new Font("楷体", Font.PLAIN, 14));
		dataScrollPane.setForeground(Color.BLACK);
		dataScrollPane.setBackground(Color.WHITE);
		getContentPane().add(dataScrollPane, BorderLayout.CENTER);
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
