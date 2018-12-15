package com.lzf.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.util.Vector;

import javax.accessibility.AccessibleContext;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.lzf.bean.AutoInsurance;
import com.lzf.bean.User;
import com.lzf.dao.Permission;
import com.lzf.ui.plugin.CustomFileChooser;
import com.lzf.ui.plugin.ModalDialog;
import com.lzf.ui.plugin.TableCellEditor;
import com.lzf.ui.plugin.TableCellEditor.DeleteListener;
import com.lzf.ui.plugin.TableCellEditor.UpdateListener;
import com.lzf.util.DateTimeUtil;
import com.lzf.util.ExcelUtil;

public class Home extends JFrame {

	private int width = 655; // 默认宽度
	private com.lzf.dao.AutoInsurance autoInsuranceDao = new com.lzf.dao.AutoInsurance(); // 车险实体的数据访问层
	private static Vector<AutoInsurance> data = null; // 当前界面显示的数据或是查询到的数据
	private ExcelUtil excelUtil = new ExcelUtil(); // Excel工具类

	/**
	 * 用户手动添加数据的调用方法
	 * 
	 * @param autoInsurance
	 * @return
	 */
	public static boolean addData(AutoInsurance autoInsurance) {
		return data.add(autoInsurance);
	}

	/**
	 * 用户手动修改数据的调用方法
	 * 
	 * @param plateNumber
	 * @param autoUse
	 * @param brand
	 * @param model
	 * @param vin
	 * @param engineNumber
	 * @param debutDate
	 * @param name
	 * @param number
	 * @param phone
	 * @param address
	 * @param dueDate
	 * @param department
	 * @param operator
	 * @param remark
	 * @param last
	 * @param id
	 */
	public static void setData(String plateNumber, String autoUse, String brand, String model, String vin,
			String engineNumber, String debutDate, String name, String number, String phone, String address,
			String dueDate, String department, String operator, String remark, String last, int id) {
		for (AutoInsurance autoInsurance : data) {
			if (autoInsurance.getId() == id) {
				autoInsurance.setPlateNumber(plateNumber);
				autoInsurance.setAutoUse(autoUse);
				autoInsurance.setBrand(brand);
				autoInsurance.setModel(model);
				autoInsurance.setVin(vin);
				autoInsurance.setEngineNumber(engineNumber);
				autoInsurance.setDebutDate(debutDate);
				autoInsurance.setName(name);
				autoInsurance.setNumber(number);
				autoInsurance.setPhone(phone);
				autoInsurance.setAddress(address);
				autoInsurance.setDueDate(dueDate);
				autoInsurance.setDepartment(department);
				autoInsurance.setOperator(operator);
				autoInsurance.setRemark(remark);
				autoInsurance.setLast(last);
				break;
			}
		}
	}

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	// Home frame = new Home(null);
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public Home(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vector<com.lzf.bean.Permission> permissions = new Permission().select(user.getRole());
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					setTitle("纸纷飞");
					setIconImage(
							Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/com/lzf/image/zff.jpg")));
					setForeground(Color.BLACK);
					setFont(new Font("楷体", Font.PLAIN, 16));
					setBackground(Color.WHITE);
					// 获取屏幕的尺寸
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					width = (int) screenSize.getWidth();
					// taskBar.bottom 获取底部任务栏高度
					Insets taskBar = Toolkit.getDefaultToolkit().getScreenInsets(Home.this.getGraphicsConfiguration());
					setBounds(0, 0, width, (int) screenSize.getHeight() - taskBar.bottom);
					// setBounds(0, 0, width, width);
					JPanel contentPanel = new JPanel();
					contentPanel.setForeground(Color.BLACK);
					contentPanel.setBackground(Color.WHITE);
					contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
					setContentPane(contentPanel);
					contentPanel.setLayout(new BorderLayout(0, 0));

					JPanel selectPanel = new JPanel();
					selectPanel.setBorder(new TitledBorder(null, "查询", TitledBorder.LEADING, TitledBorder.TOP, null,
							Color.LIGHT_GRAY));
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.SEARCH)) {
						contentPanel.add(selectPanel, BorderLayout.NORTH);
					}
					selectPanel.setForeground(Color.BLACK);
					selectPanel.setBackground(Color.WHITE);
					FlowLayout selectFlowLayout = (FlowLayout) selectPanel.getLayout();
					selectFlowLayout.setHgap(0);

					// 车牌号
					JPanel plateNumberPanel = new JPanel();
					selectPanel.add(plateNumberPanel);
					plateNumberPanel.setForeground(Color.BLACK);
					plateNumberPanel.setBackground(Color.WHITE);
					plateNumberPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel plateNumberLabel = new JLabel("车牌号：");
					plateNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					plateNumberLabel.setForeground(Color.BLACK);
					plateNumberLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					plateNumberLabel.setBackground(Color.WHITE);
					plateNumberPanel.add(plateNumberLabel);

					JTextField plateNumberTextField = new JTextField();
					plateNumberTextField.setHorizontalAlignment(SwingConstants.LEFT);
					plateNumberTextField.setForeground(Color.BLACK);
					plateNumberTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					plateNumberTextField.setBackground(Color.WHITE);
					plateNumberLabel.setLabelFor(plateNumberTextField);
					plateNumberPanel.add(plateNumberTextField);
					plateNumberTextField.setColumns(20);

					// 车辆使用性质
					JPanel autoUsePanel = new JPanel();
					selectPanel.add(autoUsePanel);
					autoUsePanel.setForeground(Color.BLACK);
					autoUsePanel.setBackground(Color.WHITE);
					autoUsePanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel autoUseLabel = new JLabel("车辆使用性质：");
					autoUseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					autoUseLabel.setForeground(Color.BLACK);
					autoUseLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					autoUseLabel.setBackground(Color.WHITE);
					autoUsePanel.add(autoUseLabel);

					JTextField autoUseTextField = new JTextField();
					autoUseTextField.setHorizontalAlignment(SwingConstants.LEFT);
					autoUseTextField.setForeground(Color.BLACK);
					autoUseTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					autoUseTextField.setBackground(Color.WHITE);
					autoUseLabel.setLabelFor(autoUseTextField);
					autoUsePanel.add(autoUseTextField);
					autoUseTextField.setColumns(20);

					// 品牌
					JPanel brandPanel = new JPanel();
					selectPanel.add(brandPanel);
					brandPanel.setForeground(Color.BLACK);
					brandPanel.setBackground(Color.WHITE);
					brandPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel brandLabel = new JLabel("品牌：");
					brandLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					brandLabel.setForeground(Color.BLACK);
					brandLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					brandLabel.setBackground(Color.WHITE);
					brandPanel.add(brandLabel);

					JTextField brandTextField = new JTextField();
					brandTextField.setHorizontalAlignment(SwingConstants.LEFT);
					brandTextField.setForeground(Color.BLACK);
					brandTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					brandTextField.setBackground(Color.WHITE);
					brandLabel.setLabelFor(brandTextField);
					brandPanel.add(brandTextField);
					brandTextField.setColumns(20);

					// 型号
					JPanel modelPanel = new JPanel();
					selectPanel.add(modelPanel);
					modelPanel.setForeground(Color.BLACK);
					modelPanel.setBackground(Color.WHITE);
					modelPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel modelLabel = new JLabel("型号：");
					modelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					modelLabel.setForeground(Color.BLACK);
					modelLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					modelLabel.setBackground(Color.WHITE);
					modelPanel.add(modelLabel);

					JTextField modelTextField = new JTextField();
					modelTextField.setHorizontalAlignment(SwingConstants.LEFT);
					modelTextField.setForeground(Color.BLACK);
					modelTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					modelTextField.setBackground(Color.WHITE);
					modelLabel.setLabelFor(modelTextField);
					modelPanel.add(modelTextField);
					modelTextField.setColumns(20);

					// 车架号
					JPanel vinPanel = new JPanel();
					selectPanel.add(vinPanel);
					vinPanel.setForeground(Color.BLACK);
					vinPanel.setBackground(Color.WHITE);
					vinPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel vinLabel = new JLabel("车架号：");
					vinLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					vinLabel.setForeground(Color.BLACK);
					vinLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					vinLabel.setBackground(Color.WHITE);
					vinPanel.add(vinLabel);

					JTextField vinTextField = new JTextField();
					vinTextField.setHorizontalAlignment(SwingConstants.LEFT);
					vinTextField.setForeground(Color.BLACK);
					vinTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					vinTextField.setBackground(Color.WHITE);
					vinLabel.setLabelFor(vinTextField);
					vinPanel.add(vinTextField);
					vinTextField.setColumns(20);

					// 发动机号
					JPanel engineNumberPanel = new JPanel();
					selectPanel.add(engineNumberPanel);
					engineNumberPanel.setForeground(Color.BLACK);
					engineNumberPanel.setBackground(Color.WHITE);
					engineNumberPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel engineNumberLabel = new JLabel("发动机号：");
					engineNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					engineNumberLabel.setForeground(Color.BLACK);
					engineNumberLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					engineNumberLabel.setBackground(Color.WHITE);
					engineNumberPanel.add(engineNumberLabel);

					JTextField engineNumberTextField = new JTextField();
					engineNumberTextField.setHorizontalAlignment(SwingConstants.LEFT);
					engineNumberTextField.setForeground(Color.BLACK);
					engineNumberTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					engineNumberTextField.setBackground(Color.WHITE);
					engineNumberLabel.setLabelFor(engineNumberTextField);
					engineNumberPanel.add(engineNumberTextField);
					engineNumberTextField.setColumns(20);

					// 初登日期
					JPanel debutDatePanel = new JPanel();
					selectPanel.add(debutDatePanel);
					debutDatePanel.setForeground(Color.BLACK);
					debutDatePanel.setBackground(Color.WHITE);
					debutDatePanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel debutDateLabel = new JLabel("初登日期：");
					debutDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					debutDateLabel.setForeground(Color.BLACK);
					debutDateLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					debutDateLabel.setBackground(Color.WHITE);
					debutDatePanel.add(debutDateLabel);

					JTextField debutDateTextField = new JTextField();
					debutDateTextField.setHorizontalAlignment(SwingConstants.LEFT);
					debutDateTextField.setForeground(Color.BLACK);
					debutDateTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					debutDateTextField.setBackground(Color.WHITE);
					debutDateLabel.setLabelFor(debutDateTextField);
					debutDatePanel.add(debutDateTextField);
					debutDateTextField.setColumns(20);

					// 姓名
					JPanel namePanel = new JPanel();
					selectPanel.add(namePanel);
					namePanel.setForeground(Color.BLACK);
					namePanel.setBackground(Color.WHITE);
					namePanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel nameLabel = new JLabel("姓名：");
					nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					nameLabel.setForeground(Color.BLACK);
					nameLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					nameLabel.setBackground(Color.WHITE);
					namePanel.add(nameLabel);

					JTextField nameTextField = new JTextField();
					nameTextField.setHorizontalAlignment(SwingConstants.LEFT);
					nameTextField.setForeground(Color.BLACK);
					nameTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					nameTextField.setBackground(Color.WHITE);
					nameLabel.setLabelFor(nameTextField);
					namePanel.add(nameTextField);
					nameTextField.setColumns(20);

					// 身份证号
					JPanel numberPanel = new JPanel();
					selectPanel.add(numberPanel);
					numberPanel.setForeground(Color.BLACK);
					numberPanel.setBackground(Color.WHITE);
					numberPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel numberLabel = new JLabel("身份证号：");
					numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					numberLabel.setForeground(Color.BLACK);
					numberLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					numberLabel.setBackground(Color.WHITE);
					numberPanel.add(numberLabel);

					JTextField numberTextField = new JTextField();
					numberTextField.setHorizontalAlignment(SwingConstants.LEFT);
					numberTextField.setForeground(Color.BLACK);
					numberTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					numberTextField.setBackground(Color.WHITE);
					numberLabel.setLabelFor(numberTextField);
					numberPanel.add(numberTextField);
					numberTextField.setColumns(20);

					// 电话号码
					JPanel phonePanel = new JPanel();
					selectPanel.add(phonePanel);
					phonePanel.setForeground(Color.BLACK);
					phonePanel.setBackground(Color.WHITE);
					phonePanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel phoneLabel = new JLabel("电话号码：");
					phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					phoneLabel.setForeground(Color.BLACK);
					phoneLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					phoneLabel.setBackground(Color.WHITE);
					phonePanel.add(phoneLabel);

					JTextField phoneTextField = new JTextField();
					phoneTextField.setHorizontalAlignment(SwingConstants.LEFT);
					phoneTextField.setForeground(Color.BLACK);
					phoneTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					phoneTextField.setBackground(Color.WHITE);
					phoneLabel.setLabelFor(phoneTextField);
					phonePanel.add(phoneTextField);
					phoneTextField.setColumns(20);

					// 地址
					JPanel addressPanel = new JPanel();
					selectPanel.add(addressPanel);
					addressPanel.setForeground(Color.BLACK);
					addressPanel.setBackground(Color.WHITE);
					addressPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel addressLabel = new JLabel("地址：");
					addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					addressLabel.setForeground(Color.BLACK);
					addressLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					addressLabel.setBackground(Color.WHITE);
					addressPanel.add(addressLabel);

					JTextField addressTextField = new JTextField();
					addressTextField.setHorizontalAlignment(SwingConstants.LEFT);
					addressTextField.setForeground(Color.BLACK);
					addressTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					addressTextField.setBackground(Color.WHITE);
					addressLabel.setLabelFor(addressTextField);
					addressPanel.add(addressTextField);
					addressTextField.setColumns(20);

					// 保险到期日
					JPanel dueDatePanel = new JPanel();
					selectPanel.add(dueDatePanel);
					dueDatePanel.setForeground(Color.BLACK);
					dueDatePanel.setBackground(Color.WHITE);
					dueDatePanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel dueDateLabel = new JLabel("保险到期日：");
					dueDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					dueDateLabel.setForeground(Color.BLACK);
					dueDateLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					dueDateLabel.setBackground(Color.WHITE);
					dueDatePanel.add(dueDateLabel);

					JTextField dueDateTextField = new JTextField();
					dueDateTextField.setHorizontalAlignment(SwingConstants.LEFT);
					dueDateTextField.setForeground(Color.BLACK);
					dueDateTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					dueDateTextField.setBackground(Color.WHITE);
					dueDateLabel.setLabelFor(dueDateTextField);
					dueDatePanel.add(dueDateTextField);
					dueDateTextField.setColumns(20);

					// 科室
					JPanel departmentPanel = new JPanel();
					selectPanel.add(departmentPanel);
					departmentPanel.setForeground(Color.BLACK);
					departmentPanel.setBackground(Color.WHITE);
					departmentPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel departmentLabel = new JLabel("科室：");
					departmentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					departmentLabel.setForeground(Color.BLACK);
					departmentLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					departmentLabel.setBackground(Color.WHITE);
					departmentPanel.add(departmentLabel);

					JTextField departmentTextField = new JTextField();
					departmentTextField.setHorizontalAlignment(SwingConstants.LEFT);
					departmentTextField.setForeground(Color.BLACK);
					departmentTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					departmentTextField.setBackground(Color.WHITE);
					departmentLabel.setLabelFor(departmentTextField);
					departmentPanel.add(departmentTextField);
					departmentTextField.setColumns(20);

					// 经办人
					JPanel operatorPanel = new JPanel();
					selectPanel.add(operatorPanel);
					operatorPanel.setForeground(Color.BLACK);
					operatorPanel.setBackground(Color.WHITE);
					operatorPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel operatorLabel = new JLabel("经办人：");
					operatorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					operatorLabel.setForeground(Color.BLACK);
					operatorLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					operatorLabel.setBackground(Color.WHITE);
					operatorPanel.add(operatorLabel);

					JTextField operatorTextField = new JTextField();
					operatorTextField.setHorizontalAlignment(SwingConstants.LEFT);
					operatorTextField.setForeground(Color.BLACK);
					operatorTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					operatorTextField.setBackground(Color.WHITE);
					operatorLabel.setLabelFor(operatorTextField);
					operatorPanel.add(operatorTextField);
					operatorTextField.setColumns(20);

					// 备注
					JPanel remarkPanel = new JPanel();
					selectPanel.add(remarkPanel);
					remarkPanel.setForeground(Color.BLACK);
					remarkPanel.setBackground(Color.WHITE);
					remarkPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel remarkLabel = new JLabel("备注：");
					remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					remarkLabel.setForeground(Color.BLACK);
					remarkLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					remarkLabel.setBackground(Color.WHITE);
					remarkPanel.add(remarkLabel);

					JTextField remarkTextField = new JTextField();
					remarkTextField.setHorizontalAlignment(SwingConstants.LEFT);
					remarkTextField.setForeground(Color.BLACK);
					remarkTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					remarkTextField.setBackground(Color.WHITE);
					remarkLabel.setLabelFor(remarkTextField);
					remarkPanel.add(remarkTextField);
					remarkTextField.setColumns(20);

					// 最后修改日期（系统自动记录生成、不可修改）
					JPanel lastPanel = new JPanel();
					selectPanel.add(lastPanel);
					lastPanel.setForeground(Color.BLACK);
					lastPanel.setBackground(Color.WHITE);
					lastPanel.setLayout(new GridLayout(1, 2, 0, 0));

					JLabel lastLabel = new JLabel("最后修改日期：");
					lastLabel.setHorizontalAlignment(SwingConstants.RIGHT);
					lastLabel.setForeground(Color.BLACK);
					lastLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					lastLabel.setBackground(Color.WHITE);
					lastPanel.add(lastLabel);

					JTextField lastTextField = new JTextField();
					lastTextField.setHorizontalAlignment(SwingConstants.LEFT);
					lastTextField.setForeground(Color.BLACK);
					lastTextField.setFont(new Font("楷体", Font.PLAIN, 16));
					lastTextField.setBackground(Color.WHITE);
					lastLabel.setLabelFor(lastTextField);
					lastPanel.add(lastTextField);
					lastTextField.setColumns(20);

					// 查询
					JPanel searchPanel = new JPanel();
					searchPanel.setForeground(Color.BLACK);
					searchPanel.setBackground(Color.WHITE);
					selectPanel.add(searchPanel);
					searchPanel.setLayout(new GridLayout(1, 3, 0, 0));

					JLabel leftLabel = new JLabel("");
					leftLabel.setHorizontalAlignment(SwingConstants.CENTER);
					leftLabel.setForeground(Color.BLACK);
					leftLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					leftLabel.setBackground(Color.WHITE);
					searchPanel.add(leftLabel);

					JButton searchButton = new JButton("查询");
					leftLabel.setLabelFor(searchButton);
					searchButton.setSelectedIcon(new ImageIcon(Home.class.getResource("/com/lzf/image/search.png")));
					searchButton.setIcon(new ImageIcon(Home.class.getResource("/com/lzf/image/search.png")));
					searchPanel.add(searchButton);
					searchButton.setForeground(Color.BLACK);
					searchButton.setFont(new Font("楷体", Font.PLAIN, 16));
					searchButton.setBackground(Color.LIGHT_GRAY);
					searchButton.setSize(20, 20);

					JLabel rightLabel = new JLabel("");
					rightLabel.setLabelFor(searchButton);
					rightLabel.setHorizontalAlignment(SwingConstants.CENTER);
					rightLabel.setFont(new Font("楷体", Font.PLAIN, 16));
					rightLabel.setForeground(Color.BLACK);
					rightLabel.setBackground(Color.WHITE);
					searchPanel.add(rightLabel);

					JTable table = new JTable();
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setRowHeight(25);
					table.setGridColor(Color.LIGHT_GRAY);
					table.setFillsViewportHeight(true);
					table.setAutoCreateRowSorter(true);
					Vector<String> columnNames = new Vector<String>(17);
					columnNames.add("车牌号");
					columnNames.add("车辆使用性质");
					columnNames.add("品牌");
					columnNames.add("型号");
					columnNames.add("车架号");
					columnNames.add("发动机号");
					columnNames.add("初登日期");
					columnNames.add("姓名");
					columnNames.add("身份证号");
					columnNames.add("电话号码");
					columnNames.add("地址");
					columnNames.add("保险到期日");
					columnNames.add("科室");
					columnNames.add("经办人");
					columnNames.add("备注");
					columnNames.add("最后修改日期");
					columnNames.add("         ");
					data = autoInsuranceDao.select("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
					DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames) {
						boolean[] columnEditables = new boolean[] { true, true, true, true, true, true, true, true,
								true, true, true, true, true, true, true, false, true };

						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
					table.setModel(defaultTableModel);
					// 设置表数据居中显示
					DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
					defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
					table.setDefaultRenderer(Object.class, defaultTableCellRenderer);

					JTableHeader jTableHeader = table.getTableHeader();
					jTableHeader.setFont(new Font("楷体", Font.PLAIN, 16));
					DefaultTableCellRenderer tableCellRenderer = (DefaultTableCellRenderer) jTableHeader
							.getDefaultRenderer();
					tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
					jTableHeader.setDefaultRenderer(tableCellRenderer);
					jTableHeader.setReorderingAllowed(false); // 不可整列移动
					table.setForeground(Color.BLACK);
					table.setFont(new Font("楷体", Font.PLAIN, 16));
					table.setBackground(Color.WHITE);
					fitTableColumns(table, jTableHeader); // 表单元格的列宽度自适应
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.ADD)) {
						TableCellEditor tableCellEditor = new TableCellEditor();
						table.getColumnModel().getColumn(16).setCellEditor(tableCellEditor); // 添加操作单元格内的按钮响应事件
						table.getColumnModel().getColumn(16).setCellRenderer(new com.lzf.ui.plugin.TableCellRenderer()); // 添加操作单元格内的按钮显示效果
						tableCellEditor.addDeleteListener(new DeleteListener() {
							@Override
							public void deleteListener(Object value, int row) {
								int res = JOptionPane.showConfirmDialog(null, "确定要删除该条数据么？", "删除",
										JOptionPane.YES_NO_OPTION);
								if (res == JOptionPane.YES_OPTION) {
									if (autoInsuranceDao.delete((int) value) > 0) {
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
								String message = "";
								String plateNumber = (String) defaultTableModel.getValueAt(row, 0);
								String autoUse = (String) defaultTableModel.getValueAt(row, 1);
								String brand = (String) defaultTableModel.getValueAt(row, 2);
								String model = (String) defaultTableModel.getValueAt(row, 3);
								String vin = (String) defaultTableModel.getValueAt(row, 4);
								String engineNumber = (String) defaultTableModel.getValueAt(row, 5);
								String debutDate = (String) defaultTableModel.getValueAt(row, 6);
								String name = (String) defaultTableModel.getValueAt(row, 7);
								String number = (String) defaultTableModel.getValueAt(row, 8);
								String phone = (String) defaultTableModel.getValueAt(row, 9);
								String address = (String) defaultTableModel.getValueAt(row, 10);
								String dueDate = (String) defaultTableModel.getValueAt(row, 11);
								String department = (String) defaultTableModel.getValueAt(row, 12);
								String operator = (String) defaultTableModel.getValueAt(row, 13);
								String remark = (String) defaultTableModel.getValueAt(row, 14);
								com.lzf.bean.AutoInsurance autoInsurance = autoInsuranceDao.select((int) value);
								if (autoInsurance.getPlateNumber().equals(plateNumber)
										&& autoInsurance.getAutoUse().equals(autoUse)
										&& autoInsurance.getBrand().equals(brand)
										&& autoInsurance.getModel().equals(model) && autoInsurance.getVin().equals(vin)
										&& autoInsurance.getEngineNumber().equals(engineNumber)
										&& autoInsurance.getDebutDate().equals(debutDate)
										&& autoInsurance.getName().equals(name)
										&& autoInsurance.getNumber().equals(number)
										&& autoInsurance.getPhone().equals(phone)
										&& autoInsurance.getAddress().equals(address)
										&& autoInsurance.getDueDate().equals(dueDate)
										&& autoInsurance.getDepartment().equals(department)
										&& autoInsurance.getOperator().equals(operator)
										&& autoInsurance.getRemark().equals(remark)) {
									message = "检测到您并未修改相关内容，确定要更新么？";
								} else {
									message = "确定要修改么？";
								}
								int res = JOptionPane.showConfirmDialog(null, message, "修改更新",
										JOptionPane.YES_NO_OPTION);
								if (res == JOptionPane.YES_OPTION) {
									if (autoInsuranceDao.update(plateNumber, autoUse, brand, model, vin, engineNumber,
											debutDate, name, number, phone, address, dueDate, department, operator,
											remark, DateTimeUtil.getCurrentTime(), (int) value) > 0) {
										Home.setData(plateNumber, autoUse, brand, model, vin, engineNumber, debutDate,
												name, number, phone, address, dueDate, department, operator, remark,
												DateTimeUtil.getCurrentTime(), (int) value);
										JOptionPane.showMessageDialog(table, "数据修改成功，已更新完成。", "修改更新成功",
												JOptionPane.INFORMATION_MESSAGE);
									} else {
										JOptionPane.showMessageDialog(table, "修改数据失败，请检查你的输入是否正确再稍后重试？", "修改数据失败",
												JOptionPane.INFORMATION_MESSAGE);
									}
								}
							}
						});
					}
					JScrollPane dataScrollPane = new JScrollPane(table);
					dataScrollPane.setFont(new Font("楷体", Font.PLAIN, 16));
					dataScrollPane.setForeground(Color.BLACK);
					dataScrollPane.setBackground(Color.WHITE);
					contentPanel.add(dataScrollPane, BorderLayout.CENTER);

					searchButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String plateNumber = plateNumberTextField.getText().trim();
							String autoUse = autoUseTextField.getText().trim();
							String brand = brandTextField.getText().trim();
							String model = modelTextField.getText().trim();
							String vin = vinTextField.getText().trim();
							String engineNumber = engineNumberTextField.getText().trim();
							String debutDate = debutDateTextField.getText().trim();
							String name = nameTextField.getText().trim();
							String number = numberTextField.getText().trim();
							String phone = phoneTextField.getText().trim();
							String address = addressTextField.getText().trim();
							String dueDate = dueDateTextField.getText().trim();
							String department = departmentTextField.getText().trim();
							String operator = operatorTextField.getText().trim();
							String remark = remarkTextField.getText().trim();
							String last = lastTextField.getText().trim();
							data = autoInsuranceDao.select(plateNumber, autoUse, brand, model, vin, engineNumber,
									debutDate, name, number, phone, address, dueDate, department, operator, remark,
									last);
							defaultTableModel.setDataVector(data, columnNames);
							fitTableColumns(table, jTableHeader); // 表单元格的列宽度自适应
							if ((user.getRemark() != null && user.getRemark().equals("程序员"))
									|| permissions.toString().contains(RoleDetail.ADD)) {
								TableCellEditor tableCellEditor = new TableCellEditor();
								table.getColumnModel().getColumn(16).setCellEditor(tableCellEditor); // 添加操作单元格内的按钮响应事件
								table.getColumnModel().getColumn(16)
										.setCellRenderer(new com.lzf.ui.plugin.TableCellRenderer()); // 添加操作单元格内的按钮显示效果
								tableCellEditor.addDeleteListener(new DeleteListener() {
									@Override
									public void deleteListener(Object value, int row) {
										int res = JOptionPane.showConfirmDialog(null, "确定要删除该条数据么？", "删除",
												JOptionPane.YES_NO_OPTION);
										if (res == JOptionPane.YES_OPTION) {
											if (autoInsuranceDao.delete((int) value) > 0) {
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
										String message = "";
										String plateNumber = (String) defaultTableModel.getValueAt(row, 0);
										String autoUse = (String) defaultTableModel.getValueAt(row, 1);
										String brand = (String) defaultTableModel.getValueAt(row, 2);
										String model = (String) defaultTableModel.getValueAt(row, 3);
										String vin = (String) defaultTableModel.getValueAt(row, 4);
										String engineNumber = (String) defaultTableModel.getValueAt(row, 5);
										String debutDate = (String) defaultTableModel.getValueAt(row, 6);
										String name = (String) defaultTableModel.getValueAt(row, 7);
										String number = (String) defaultTableModel.getValueAt(row, 8);
										String phone = (String) defaultTableModel.getValueAt(row, 9);
										String address = (String) defaultTableModel.getValueAt(row, 10);
										String dueDate = (String) defaultTableModel.getValueAt(row, 11);
										String department = (String) defaultTableModel.getValueAt(row, 12);
										String operator = (String) defaultTableModel.getValueAt(row, 13);
										String remark = (String) defaultTableModel.getValueAt(row, 14);
										com.lzf.bean.AutoInsurance autoInsurance = autoInsuranceDao.select((int) value);
										if (autoInsurance.getPlateNumber().equals(plateNumber)
												&& autoInsurance.getAutoUse().equals(autoUse)
												&& autoInsurance.getBrand().equals(brand)
												&& autoInsurance.getModel().equals(model)
												&& autoInsurance.getVin().equals(vin)
												&& autoInsurance.getEngineNumber().equals(engineNumber)
												&& autoInsurance.getDebutDate().equals(debutDate)
												&& autoInsurance.getName().equals(name)
												&& autoInsurance.getNumber().equals(number)
												&& autoInsurance.getPhone().equals(phone)
												&& autoInsurance.getAddress().equals(address)
												&& autoInsurance.getDueDate().equals(dueDate)
												&& autoInsurance.getDepartment().equals(department)
												&& autoInsurance.getOperator().equals(operator)
												&& autoInsurance.getRemark().equals(remark)) {
											message = "检测到您并未修改相关内容，确定要更新么？";
										} else {
											message = "确定要修改么？";
										}
										int res = JOptionPane.showConfirmDialog(null, message, "修改更新",
												JOptionPane.YES_NO_OPTION);
										if (res == JOptionPane.YES_OPTION) {
											if (autoInsuranceDao.update(plateNumber, autoUse, brand, model, vin,
													engineNumber, debutDate, name, number, phone, address, dueDate,
													department, operator, remark, DateTimeUtil.getCurrentTime(),
													(int) value) > 0) {
												Home.setData(plateNumber, autoUse, brand, model, vin, engineNumber,
														debutDate, name, number, phone, address, dueDate, department,
														operator, remark, DateTimeUtil.getCurrentTime(), (int) value);
												JOptionPane.showMessageDialog(table, "数据修改成功，已更新完成。", "修改更新成功",
														JOptionPane.INFORMATION_MESSAGE);
											} else {
												JOptionPane.showMessageDialog(table, "修改数据失败，请检查你的输入是否正确再稍后重试？",
														"修改数据失败", JOptionPane.INFORMATION_MESSAGE);
											}
										}
									}
								});
							}
						}
					});

					// 底部面板
					JPanel bottomPanel = new JPanel();
					bottomPanel.setForeground(Color.BLACK);
					bottomPanel.setBackground(Color.WHITE);
					bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 5));
					contentPanel.add(bottomPanel, BorderLayout.SOUTH);

					// 导出
					JPanel exportPanel = new JPanel();
					exportPanel.setForeground(Color.BLACK);
					exportPanel.setBackground(Color.WHITE);
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.EXPORT)) {
						bottomPanel.add(exportPanel);
					}
					exportPanel.setLayout(new GridLayout(1, 1, 0, 0));

					JPopupMenu exportPopupMenu = new JPopupMenu();
					exportPopupMenu.setLabel("");
					exportPopupMenu.setForeground(Color.BLACK);
					exportPopupMenu.setFont(new Font("楷体", Font.PLAIN, 15));
					exportPopupMenu.setBackground(Color.WHITE);

					JMenuItem templateExport = new JMenuItem("导出Excel模板");
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.EXPORT_TEMPLATE)) {
						exportPopupMenu.add(templateExport);
					}
					templateExport.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							CustomFileChooser customFileChooser = new CustomFileChooser(Home.this, "导出Excel模板", false,
									JFileChooser.DIRECTORIES_ONLY, "选择要导出的位置");
							customFileChooser.setVisible(true);
							File file = customFileChooser.getSelectedFile();
							Vector<Boolean> chooseField = customFileChooser.getChooseField();
							// System.out.println(chooseField.toString());
							if (file != null) {
								ModalDialog modalDialog = new ModalDialog(Home.this);
								excelUtil.writeExcel(file, "Excel模板" + System.currentTimeMillis(), columnNames, null,
										chooseField);
								modalDialog.setVisible(false);
							}
						}
					});

					JMenuItem vlookupExport = new JMenuItem("VLOOKUP导出（字段可选）");

					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.EXPORT_VLOOKUP)) {
						exportPopupMenu.add(vlookupExport);
					}
					vlookupExport.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							CustomFileChooser customFileChooser = new CustomFileChooser(Home.this, "VLOOKUP导出（字段可选）",
									true, JFileChooser.FILES_ONLY, "VLOOKUP导出选择");
							customFileChooser.setVisible(true);
							File file = customFileChooser.getSelectedFile();
							Vector<Boolean> chooseField = customFileChooser.getChooseField();
							if (file != null) {
								ModalDialog modalDialog = new ModalDialog(Home.this);
								String fileName = "从《" + file.getName() + "》VLOOKUP导出（字段可选）"
										+ System.currentTimeMillis();
								Vector<AutoInsurance> caches = new Vector<AutoInsurance>();
								for (AutoInsurance autoInsurance : excelUtil.readExcel(file)) {
									if (!caches.contains(autoInsurance)) {
										caches.add(autoInsurance);
										Vector<AutoInsurance> autoInsurances = autoInsuranceDao.select(
												autoInsurance.getPlateNumber(), autoInsurance.getAutoUse(),
												autoInsurance.getBrand(), autoInsurance.getModel(),
												autoInsurance.getVin(), autoInsurance.getEngineNumber(),
												autoInsurance.getDebutDate(), autoInsurance.getName(),
												autoInsurance.getNumber(), autoInsurance.getPhone(),
												autoInsurance.getAddress(), autoInsurance.getDueDate(),
												autoInsurance.getDepartment(), autoInsurance.getOperator(),
												autoInsurance.getRemark(), autoInsurance.getLast());
										excelUtil.writeExcel(file.getParentFile(), fileName, columnNames,
												autoInsurances, chooseField);
									}
								}
								modalDialog.setVisible(false);
							}
						}
					});

					JMenuItem batchExport = new JMenuItem("批量查询导出（字段可选）");
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.EXPORT_BATCH)) {
						exportPopupMenu.add(batchExport);
					}
					batchExport.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							CustomFileChooser customFileChooser = new CustomFileChooser(Home.this, "批量查询导出（字段可选）", true,
									JFileChooser.DIRECTORIES_ONLY, "选择要导出的位置");
							customFileChooser.setVisible(true);
							File file = customFileChooser.getSelectedFile();
							Vector<Boolean> chooseField = customFileChooser.getChooseField();
							if (file != null) {
								ModalDialog modalDialog = new ModalDialog(Home.this);
								excelUtil.writeExcel(file, "批量查询导出（字段可选）" + System.currentTimeMillis(), columnNames,
										data, chooseField);
								modalDialog.setVisible(false);
							}
						}
					});

					JMenuItem backupExport = new JMenuItem("备份导出（字段可选）");
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.EXPORT_BACKUP)) {
						exportPopupMenu.add(backupExport);
					}
					backupExport.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							CustomFileChooser customFileChooser = new CustomFileChooser(Home.this, "备份导出（字段可选）", true,
									JFileChooser.FILES_ONLY, "选择备份文件");
							customFileChooser.setVisible(true);
							File file = customFileChooser.getSelectedFile();
							Vector<Boolean> chooseField = customFileChooser.getChooseField();
							if (file != null) {
								ModalDialog modalDialog = new ModalDialog(Home.this);
								excelUtil.writeExcel(file.getParentFile(), "备份导出（字段可选）" + System.currentTimeMillis(),
										columnNames, autoInsuranceDao.select(file), chooseField);
								modalDialog.setVisible(false);
							}
						}
					});

					JButton exportButton = new JButton("导出   >");
					exportButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							exportPopupMenu.show(exportButton, 0, exportButton.getY() + exportButton.getHeight());
						}
					});
					exportButton.setForeground(Color.BLACK);
					exportButton.setFont(new Font("楷体", Font.PLAIN, 16));
					exportButton.setBackground(Color.WHITE);
					exportPanel.add(exportButton);

					// 权限管理
					JPanel permissionPanel = new JPanel();
					permissionPanel.setForeground(Color.BLACK);
					permissionPanel.setBackground(Color.WHITE);
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.PERMISSION)) {
						bottomPanel.add(permissionPanel);
					}
					permissionPanel.setLayout(new GridLayout(1, 1, 0, 0));

					JPopupMenu permissionPopupMenu = new JPopupMenu();
					permissionPopupMenu.setLabel("");
					permissionPopupMenu.setForeground(Color.BLACK);
					permissionPopupMenu.setFont(new Font("楷体", Font.PLAIN, 15));
					permissionPopupMenu.setBackground(Color.WHITE);

					JMenuItem insertRole = new JMenuItem("添加新角色");
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.PERMISSION_ADD)) {
						permissionPopupMenu.add(insertRole);
					}
					insertRole.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new RoleDetail(Home.this, "添加新角色");
						}
					});

					JMenuItem manageRole = new JMenuItem("管理已有角色");
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.PERMISSION_MANAGE)) {
						permissionPopupMenu.add(manageRole);
					}
					manageRole.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new ManageRole(Home.this, "管理已有角色");
						}
					});

					JButton permissioinButton = new JButton("权限管理   >");
					permissioinButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							permissionPopupMenu.show(permissioinButton, 0,
									permissioinButton.getY() + permissioinButton.getHeight());
						}
					});
					permissioinButton.setForeground(Color.BLACK);
					permissioinButton.setFont(new Font("楷体", Font.PLAIN, 16));
					permissioinButton.setBackground(Color.WHITE);
					permissionPanel.add(permissioinButton);

					// 用户管理
					JPanel userPanel = new JPanel();
					userPanel.setForeground(Color.BLACK);
					userPanel.setBackground(Color.WHITE);
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.USER)) {
						bottomPanel.add(userPanel);
					}
					userPanel.setLayout(new GridLayout(1, 1, 0, 0));

					JPopupMenu userPopupMenu = new JPopupMenu();
					userPopupMenu.setLabel("");
					userPopupMenu.setForeground(Color.BLACK);
					userPopupMenu.setFont(new Font("楷体", Font.PLAIN, 15));
					userPopupMenu.setBackground(Color.WHITE);

					JMenuItem insertUser = new JMenuItem("添加新用户");
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.USER_ADD)) {
						userPopupMenu.add(insertUser);
					}
					insertUser.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new UserDetail(Home.this, "添加新用户");
						}
					});

					JMenuItem manageUser = new JMenuItem("管理已有用户");
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.USER_MANAGE)) {
						userPopupMenu.add(manageUser);
					}
					manageUser.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new ManageUser(Home.this, "管理已有用户");
						}
					});

					JButton userButton = new JButton("用户管理   >");
					userButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							userPopupMenu.show(userButton, 0, userButton.getY() + userButton.getHeight());
						}
					});
					userButton.setForeground(Color.BLACK);
					userButton.setFont(new Font("楷体", Font.PLAIN, 16));
					userButton.setBackground(Color.WHITE);
					userPanel.add(userButton);

					// 导入
					JPanel importPanel = new JPanel();
					importPanel.setForeground(Color.BLACK);
					importPanel.setBackground(Color.WHITE);
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.IMPORT)) {
						bottomPanel.add(importPanel);
					}
					importPanel.setLayout(new GridLayout(1, 1, 0, 0));

					JButton importButton = new JButton("导入");
					importButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							CustomFileChooser customFileChooser = new CustomFileChooser(Home.this, "导入Excel文件", false,
									JFileChooser.FILES_ONLY, "选择要导入的Excel");
							customFileChooser.setVisible(true);
							File file = customFileChooser.getSelectedFile();
							if (file != null) {
								ModalDialog modalDialog = new ModalDialog(Home.this);
								Vector<AutoInsurance> autoInsurances = excelUtil.readExcel(file);
								if ((autoInsuranceDao.batchInsert(autoInsurances) <= 0)) {
									autoInsuranceDao.batchInsert(autoInsurances);
								}
								searchButton.doClick();
								modalDialog.setVisible(false);
							}
						}
					});
					importButton.setForeground(Color.BLACK);
					importButton.setFont(new Font("楷体", Font.PLAIN, 16));
					importButton.setBackground(Color.WHITE);
					importPanel.add(importButton);

					// 插入单条数据
					JPanel insertPanel = new JPanel();
					insertPanel.setForeground(Color.BLACK);
					insertPanel.setBackground(Color.WHITE);
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.ADD)) {
						bottomPanel.add(insertPanel);
					}
					insertPanel.setLayout(new GridLayout(1, 1, 0, 0));

					JButton insertButton = new JButton("添加单条数据");
					insertButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							AutoInsurance autoInsurance = new AutoInsurance();
							autoInsurance.setId(-6003);
							defaultTableModel.addRow(autoInsurance);
							// JScrollBar jscrollBar = dataScrollPane.getVerticalScrollBar();
							// if (jscrollBar != null)
							// jscrollBar.setValue(jscrollBar.getMaximum());
							int rowCount = table.getRowCount();
							table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
							table.scrollRectToVisible(table.getCellRect(rowCount - 1, 0, true));
						}
					});
					insertButton.setForeground(Color.BLACK);
					insertButton.setFont(new Font("楷体", Font.PLAIN, 16));
					insertButton.setBackground(Color.WHITE);
					insertPanel.add(insertButton);

					// 个人信息
					JPanel personalInfoPanel = new JPanel();
					personalInfoPanel.setForeground(Color.BLACK);
					personalInfoPanel.setBackground(Color.WHITE);
					if ((user.getRemark() != null && user.getRemark().equals("程序员"))
							|| permissions.toString().contains(RoleDetail.INFO)) {
						bottomPanel.add(personalInfoPanel);
					}
					userPanel.setLayout(new GridLayout(1, 1, 0, 0));

					JButton personalInfoButton = new JButton("个人信息");
					personalInfoButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new UserDetail(Home.this, "个人信息", user, null);
						}
					});
					personalInfoButton.setForeground(Color.BLACK);
					personalInfoButton.setFont(new Font("楷体", Font.PLAIN, 16));
					personalInfoButton.setBackground(Color.WHITE);
					personalInfoPanel.add(personalInfoButton);

					addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) { // 窗口即将关闭时
							super.windowClosing(e);
							if (Home.this.getCursor().getType() != Cursor.WAIT_CURSOR) {
								int i = JOptionPane.showConfirmDialog(contentPanel, "确定要退出吗？", "退出",
										JOptionPane.YES_NO_OPTION);
								if (i == JOptionPane.YES_OPTION) {
									System.exit(0);
								}
							}
						}
					});

					addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) { // 窗口大小改变时
							super.componentResized(e);
							searchPanel.setPreferredSize(new Dimension(lastPanel.getWidth(), lastPanel.getHeight()));
							selectPanel.setPreferredSize(
									new Dimension(width, searchPanel.getY() + searchPanel.getHeight() + 8));
							bottomPanel.setPreferredSize(
									new Dimension(width, personalInfoPanel.getY() + personalInfoPanel.getHeight()));
							selectPanel.setVisible(false);
							selectPanel.setVisible(true);
							bottomPanel.setVisible(false);
							bottomPanel.setVisible(true);
						}

						@Override
						public void componentShown(ComponentEvent e) { // 窗口即将显示时
							super.componentShown(e);
							searchPanel.setSize(lastPanel.getWidth(), lastPanel.getHeight());
							selectPanel.setSize(width, searchPanel.getY() + searchPanel.getHeight() + 8);
							bottomPanel.setSize(width, personalInfoPanel.getY() + personalInfoPanel.getHeight());
						}
					});
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 表单元格的列宽度自适应
	 * 
	 * @param myTable
	 * @param header
	 */
	private void fitTableColumns(JTable myTable, JTableHeader header) {
		int rowCount = myTable.getRowCount();
		// Enumeration columns = myTable.getColumnModel().getColumns();
		// while (columns.hasMoreElements()) {
		for (int i = 0; i < myTable.getColumnCount(); i++) {
			// TableColumn column = (TableColumn) columns.nextElement();
			TableColumn column = myTable.getColumn(myTable.getColumnName(i));
			int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
			int width = (int) myTable.getTableHeader().getDefaultRenderer()
					.getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
					.getPreferredSize().getWidth();
			for (int row = 0; row < rowCount; row++) {
				int preferedWidth = (int) myTable.getCellRenderer(row, col)
						.getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
						.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			header.setResizingColumn(column); // 此行很重要
			column.setWidth(2 * width + myTable.getIntercellSpacing().width);
		}
	}
}
