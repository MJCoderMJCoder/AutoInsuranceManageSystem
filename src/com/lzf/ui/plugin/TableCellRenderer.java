/**
 * 
 */
package com.lzf.ui.plugin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * 添加按钮显示效果
 * 
 * @author MJCoder
 *
 */
public class TableCellRenderer implements javax.swing.table.TableCellRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing
	 * .JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.BLUE);
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
			panel.add(updateLabel, BorderLayout.CENTER);

			JLabel deleteLabel = new JLabel("<html><u>删除</u></html");
			deleteLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			deleteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			deleteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
			deleteLabel.setForeground(Color.RED);
			deleteLabel.setFont(new Font("楷体", Font.PLAIN, 16));
			deleteLabel.setBackground(Color.WHITE);
			panel.add(deleteLabel, BorderLayout.CENTER);
		}
		return panel;
	}

}
