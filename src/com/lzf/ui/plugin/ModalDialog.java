package com.lzf.ui.plugin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JRootPane;

import com.lzf.ui.Home;

/**
 * ModalDialog对话框在显示时阻止用户输入到其他顶级窗口。
 * 
 * @author MJCoder
 *
 */
public class ModalDialog extends JDialog {

	public ModalDialog(Frame owner) {
		// @param modal 如果{@code true}，则模态类型属性设置为{@code
		// DEFAULT_MODALITY_TYPE}，否则对话框是无模式的
		super(owner, "");
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModalDialog.class.getResource("/com/lzf/image/zff.jpg")));
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);// 采用指定的窗口装饰风格
		// 获取屏幕的尺寸
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// taskBar.bottom 获取底部任务栏高度
		Insets taskBar = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration());
		setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight() - taskBar.bottom);
		getContentPane().setBackground(new Color(255, 255, 255, 0));
		setBackground(new Color(255, 255, 255, 100));
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setVisible(true);
	}
}
