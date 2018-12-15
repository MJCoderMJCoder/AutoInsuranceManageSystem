package com.lzf.ui.plugin;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.io.File;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import com.lzf.ui.Home;
import java.awt.Toolkit;

public class CustomFileChooser extends JDialog {

	private JCheckBox plateNumber;
	private JCheckBox autoUse;
	private JCheckBox brand;
	private JCheckBox model;
	private JCheckBox vin;
	private JCheckBox engineNumber;
	private JCheckBox debutDate;
	private JCheckBox name;
	private JCheckBox number;
	private JCheckBox phone;
	private JCheckBox address;
	private JCheckBox dueDate;
	private JCheckBox department;
	private JCheckBox operator;
	private JCheckBox remark;

	private File selectedFile; // 用户选择的存储目录

	/**
	 * 获取用户当前选择的目录
	 * 
	 * @return
	 */
	public File getSelectedFile() {
		return selectedFile;
	}

	/**
	 * 获取用户已选择的导出字段状态（true：导出、false：不导出）
	 * 
	 * @return
	 */
	public Vector<Boolean> getChooseField() {
		Vector<Boolean> chooseField = new Vector<Boolean>(15); // 用户选择的导出字段状态（true：导出、false：不导出）
		chooseField.add(plateNumber.isSelected());
		chooseField.add(autoUse.isSelected());
		chooseField.add(brand.isSelected());
		chooseField.add(model.isSelected());
		chooseField.add(vin.isSelected());
		chooseField.add(engineNumber.isSelected());
		chooseField.add(debutDate.isSelected());
		chooseField.add(name.isSelected());
		chooseField.add(number.isSelected());
		chooseField.add(phone.isSelected());
		chooseField.add(address.isSelected());
		chooseField.add(dueDate.isSelected());
		chooseField.add(department.isSelected());
		chooseField.add(operator.isSelected());
		chooseField.add(remark.isSelected());
		return chooseField;
	}

	/**
	 * 自定义的文件选择对话框
	 * 
	 * @param owner
	 *            父组件、归属组件
	 * @param title
	 *            对话框标题
	 * @param modal
	 *            指定对话框是否在显示时阻止用户输入到其他顶级窗口。
	 * @param isCheck
	 *            是否显示字段多选的一系列选项
	 * @param fileSelectionMode
	 *            文件选择模式：仅选择文件、仅选择目录
	 * @param approveButtonText
	 *            供用户点击的（确认）按钮文本字段
	 */
	public CustomFileChooser(Frame owner, String title, boolean isCheck, int fileSelectionMode,
			String approveButtonText) {
		super(owner, title, true);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(CustomFileChooser.class.getResource("/com/lzf/image/zff.jpg")));
		// TODO Auto-generated constructor stub
		setResizable(false);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JPanel fileChooserPanel = new JPanel();
		fileChooserPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		getContentPane().add(fileChooserPanel);
		JFileChooser jFileChooser = new JFileChooser(new File(System.getProperties().getProperty("user.home"), "纸纷飞")) {
			@Override
			public void approveSelection() {
				// TODO Auto-generated method stub
				super.approveSelection();
				CustomFileChooser.this.setVisible(false);
				selectedFile = this.getSelectedFile();
				// System.out.println(selectedFile);
			}

			@Override
			public void cancelSelection() {
				// TODO Auto-generated method stub
				super.cancelSelection();
				CustomFileChooser.this.setVisible(false);
			}
		};
		jFileChooser.setFileSelectionMode(fileSelectionMode);
		jFileChooser.setApproveButtonText(approveButtonText);
		fileChooserPanel.add(jFileChooser);

		plateNumber = new JCheckBox("车牌号");
		plateNumber.setHorizontalAlignment(SwingConstants.CENTER);
		plateNumber.setSelected(true);

		autoUse = new JCheckBox("车辆使用性质");
		autoUse.setHorizontalAlignment(SwingConstants.CENTER);
		autoUse.setSelected(true);

		brand = new JCheckBox("品牌");
		brand.setHorizontalAlignment(SwingConstants.CENTER);
		brand.setSelected(true);

		model = new JCheckBox("型号");
		model.setHorizontalAlignment(SwingConstants.CENTER);
		model.setSelected(true);

		vin = new JCheckBox("车架号");
		vin.setHorizontalAlignment(SwingConstants.CENTER);
		vin.setSelected(true);

		engineNumber = new JCheckBox("发动机号");
		engineNumber.setSelected(true);
		engineNumber.setHorizontalAlignment(SwingConstants.CENTER);

		debutDate = new JCheckBox("初登日期");
		debutDate.setSelected(true);
		debutDate.setHorizontalAlignment(SwingConstants.CENTER);

		name = new JCheckBox("姓名");
		name.setSelected(true);
		name.setHorizontalAlignment(SwingConstants.CENTER);

		number = new JCheckBox("身份证号");
		number.setSelected(true);
		number.setHorizontalAlignment(SwingConstants.CENTER);

		phone = new JCheckBox("电话号码");
		phone.setSelected(true);
		phone.setHorizontalAlignment(SwingConstants.CENTER);

		address = new JCheckBox("地址");
		address.setSelected(true);
		address.setHorizontalAlignment(SwingConstants.CENTER);

		dueDate = new JCheckBox("保险到期日");
		dueDate.setSelected(true);
		dueDate.setHorizontalAlignment(SwingConstants.CENTER);

		department = new JCheckBox("科室");
		department.setSelected(true);
		department.setHorizontalAlignment(SwingConstants.CENTER);

		operator = new JCheckBox(" 经办人");
		operator.setSelected(true);
		operator.setHorizontalAlignment(SwingConstants.CENTER);

		remark = new JCheckBox("备注");
		remark.setSelected(true);
		remark.setHorizontalAlignment(SwingConstants.CENTER);
		if (isCheck) {
			setBounds(100, 100, 591, 490);
			getContentPane().add(plateNumber);
			getContentPane().add(autoUse);
			getContentPane().add(brand);
			getContentPane().add(model);
			getContentPane().add(vin);
			getContentPane().add(engineNumber);
			getContentPane().add(debutDate);
			getContentPane().add(name);
			getContentPane().add(number);
			getContentPane().add(phone);
			getContentPane().add(address);
			getContentPane().add(dueDate);
			getContentPane().add(department);
			getContentPane().add(operator);
			getContentPane().add(remark);
		} else {
			setBounds(100, 100, 591, 430);
		}
		setLocationRelativeTo(owner);
	}

}
