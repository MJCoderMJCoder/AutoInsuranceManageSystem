package com.lzf.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.lzf.dao.Permission;
import com.lzf.dao.User;
import com.lzf.ui.plugin.TableCellEditor;
import com.lzf.ui.plugin.TableCellEditor.DeleteListener;
import com.lzf.ui.plugin.TableCellEditor.UpdateListener;
import com.lzf.util.DateTimeUtil;
import com.lzf.util.EncryptUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class UserDetail extends JDialog {
	private User userDao = new User();

	/***
	 * 
	 * Create the dialog.
	 */
	public UserDetail(Window owner, String title) {
		super(owner, title);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setFont(new Font("楷体", Font.PLAIN, 14));
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserDetail.class.getResource("/com/lzf/image/zff.jpg")));
		setForeground(Color.BLACK);
		setFont(new Font("楷体", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setBounds(100, 100, 494, 299);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);
		getContentPane().setLayout(borderLayout);
		JPanel contentPanel = new JPanel();
		contentPanel.setForeground(Color.BLACK);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(6, 3, 10, 10));
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		nameLabel.setBackground(Color.WHITE);
		contentPanel.add(nameLabel);
		JTextField nameTextField = new JTextField();
		nameLabel.setLabelFor(nameTextField);
		nameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		nameTextField.setForeground(Color.BLACK);
		nameTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		nameTextField.setBackground(Color.WHITE);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(15);
		JLabel nameHint = new JLabel("   *");
		nameHint.setLabelFor(nameTextField);
		nameHint.setHorizontalAlignment(SwingConstants.LEFT);
		nameHint.setForeground(Color.RED);
		nameHint.setFont(new Font("楷体", Font.PLAIN, 13));
		nameHint.setBackground(Color.WHITE);
		contentPanel.add(nameHint);
		JLabel numberLabel = new JLabel("身份证号：");
		numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numberLabel.setBackground(Color.WHITE);
		numberLabel.setForeground(Color.BLACK);
		numberLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		contentPanel.add(numberLabel);
		JTextField numberTextField = new JTextField();
		numberLabel.setLabelFor(numberTextField);
		numberTextField.setHorizontalAlignment(SwingConstants.LEFT);
		numberTextField.setForeground(Color.BLACK);
		numberTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		numberTextField.setBackground(Color.WHITE);
		contentPanel.add(numberTextField);
		numberTextField.setColumns(15);
		JLabel numberHint = new JLabel("   *");
		numberHint.setLabelFor(numberTextField);
		numberHint.setForeground(Color.RED);
		numberHint.setFont(new Font("楷体", Font.PLAIN, 13));
		numberHint.setBackground(Color.WHITE);
		contentPanel.add(numberHint);
		JLabel contactLabel = new JLabel("联系方式：");
		contactLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contactLabel.setForeground(Color.BLACK);
		contactLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		contactLabel.setBackground(Color.WHITE);
		contentPanel.add(contactLabel);
		JTextField contactTextField = new JTextField();
		contactLabel.setLabelFor(contactTextField);
		contactTextField.setHorizontalAlignment(SwingConstants.LEFT);
		contactTextField.setForeground(Color.BLACK);
		contactTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		contactTextField.setBackground(Color.WHITE);
		contentPanel.add(contactTextField);
		contactTextField.setColumns(15);
		JLabel contactHint = new JLabel("   *");
		contactHint.setLabelFor(contactTextField);
		contactHint.setHorizontalAlignment(SwingConstants.LEFT);
		contactHint.setForeground(Color.RED);
		contactHint.setFont(new Font("楷体", Font.PLAIN, 13));
		contactHint.setBackground(Color.WHITE);
		contentPanel.add(contactHint);
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		passwordLabel.setBackground(Color.WHITE);
		contentPanel.add(passwordLabel);
		JPasswordField passwordTextField = new JPasswordField();
		passwordLabel.setLabelFor(passwordTextField);
		passwordTextField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordTextField.setForeground(Color.BLACK);
		passwordTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		passwordTextField.setBackground(Color.WHITE);
		passwordTextField.setEchoChar('*');
		contentPanel.add(passwordTextField);
		passwordTextField.setColumns(15);
		JLabel passwordHint = new JLabel("   *");
		passwordHint.setLabelFor(passwordTextField);
		passwordHint.setHorizontalAlignment(SwingConstants.LEFT);
		passwordHint.setForeground(Color.RED);
		passwordHint.setFont(new Font("楷体", Font.PLAIN, 13));
		passwordHint.setBackground(Color.WHITE);
		contentPanel.add(passwordHint);
		JLabel departmentLabel = new JLabel("科室：");
		departmentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		departmentLabel.setForeground(Color.BLACK);
		departmentLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		departmentLabel.setBackground(Color.WHITE);
		contentPanel.add(departmentLabel);
		JTextField departmentTextField = new JTextField();
		departmentLabel.setLabelFor(departmentTextField);
		departmentTextField.setHorizontalAlignment(SwingConstants.LEFT);
		departmentTextField.setForeground(Color.BLACK);
		departmentTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		departmentTextField.setBackground(Color.WHITE);
		contentPanel.add(departmentTextField);
		departmentTextField.setColumns(15);
		JLabel departmentHint = new JLabel("   *");
		departmentHint.setLabelFor(departmentTextField);
		departmentHint.setHorizontalAlignment(SwingConstants.LEFT);
		departmentHint.setForeground(Color.RED);
		departmentHint.setFont(new Font("楷体", Font.PLAIN, 13));
		departmentHint.setBackground(Color.WHITE);
		contentPanel.add(departmentHint);
		JLabel roleLabel = new JLabel("角色：");
		roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roleLabel.setForeground(Color.BLACK);
		roleLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		roleLabel.setBackground(Color.WHITE);
		contentPanel.add(roleLabel);
		JComboBox roleComboBox = new JComboBox();
		roleLabel.setLabelFor(roleComboBox);
		Vector<Vector> roles = new Permission().select();
		String[] items = new String[roles.size() + 1];
		items[0] = "";
		for (int i = 0; i < roles.size(); i++) {
			items[i + 1] = roles.get(i).get(0).toString();
		}
		roleComboBox.setModel(new DefaultComboBoxModel(items));
		roleComboBox.setMaximumRowCount(100);
		roleComboBox.setForeground(Color.BLACK);
		roleComboBox.setFont(new Font("楷体", Font.PLAIN, 14));
		roleComboBox.setBackground(Color.WHITE);
		contentPanel.add(roleComboBox);
		JLabel roleHint = new JLabel("   *");
		roleHint.setLabelFor(roleComboBox);
		roleHint.setHorizontalAlignment(SwingConstants.LEFT);
		roleHint.setForeground(Color.RED);
		roleHint.setFont(new Font("楷体", Font.PLAIN, 13));
		roleHint.setBackground(Color.WHITE);
		contentPanel.add(roleHint);
		JPanel buttonPane = new JPanel();
		buttonPane.setForeground(Color.BLACK);
		buttonPane.setBackground(Color.WHITE);
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setVgap(10);
		fl_buttonPane.setHgap(50);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("确定");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText().trim();
				String number = numberTextField.getText().trim();
				String contact = contactTextField.getText().trim();
				String password = passwordTextField.getText().trim();
				String department = departmentTextField.getText().trim();
				String role = roleComboBox.getSelectedItem().toString().trim();
				if (name.length() <= 0) {
					nameHint.setText("   请输入用户姓名");
				} else if (number.length() <= 0) {
					numberHint.setText("   请输入身份证号");
				} else if (contact.length() <= 0) {
					contactHint.setText("   请输入联系方式");
				} else if (password.length() <= 0) {
					passwordHint.setText("   请输入初始密码");
				} else if (department.length() <= 0) {
					departmentHint.setText("   请输入科室部门");
				} else if (role.length() <= 0) {
					roleHint.setText("   请选择一个角色");
				} else {
					int temp = userDao
							.insert(new com.lzf.bean.User(name, number, contact, EncryptUtil.encrypt(password),
									department, DateTimeUtil.getCurrentTime(), role, null, null));
					if (temp == -1) {
						JOptionPane.showMessageDialog(UserDetail.this, "该用户已存在", "用户已存在",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (temp > 0) {
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(UserDetail.this, "添加该用户时遇到异常，请稍后重试？", "添加用户异常",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		okButton.setBackground(Color.WHITE);
		okButton.setForeground(Color.BLACK);
		okButton.setFont(new Font("楷体", Font.PLAIN, 14));
		okButton.setActionCommand("");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("取消");
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFont(new Font("楷体", Font.PLAIN, 14));
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setActionCommand("");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	/***
	 * 
	 * Create the dialog.
	 */
	public UserDetail(Window owner, String title, com.lzf.bean.User user, JTable table) {
		super(owner, title);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setFont(new Font("楷体", Font.PLAIN, 14));
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserDetail.class.getResource("/com/lzf/image/zff.jpg")));
		setForeground(Color.BLACK);
		setFont(new Font("楷体", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setBounds(100, 100, 494, 299);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);
		getContentPane().setLayout(borderLayout);
		JPanel contentPanel = new JPanel();
		contentPanel.setForeground(Color.BLACK);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(6, 3, 10, 10));
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.BLACK);
		nameLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		nameLabel.setBackground(Color.WHITE);
		contentPanel.add(nameLabel);
		JTextField nameTextField = new JTextField(user.getName());
		nameLabel.setLabelFor(nameTextField);
		nameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		nameTextField.setForeground(Color.BLACK);
		nameTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		nameTextField.setBackground(Color.WHITE);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(15);
		JLabel nameHint = new JLabel("   *");
		nameHint.setLabelFor(nameTextField);
		nameHint.setHorizontalAlignment(SwingConstants.LEFT);
		nameHint.setForeground(Color.RED);
		nameHint.setFont(new Font("楷体", Font.PLAIN, 13));
		nameHint.setBackground(Color.WHITE);
		contentPanel.add(nameHint);
		JLabel numberLabel = new JLabel("身份证号：");
		numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		numberLabel.setBackground(Color.WHITE);
		numberLabel.setForeground(Color.BLACK);
		numberLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		contentPanel.add(numberLabel);
		JTextField numberTextField = new JTextField(user.getNumber());
		numberLabel.setLabelFor(numberTextField);
		numberTextField.setHorizontalAlignment(SwingConstants.LEFT);
		numberTextField.setForeground(Color.BLACK);
		numberTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		numberTextField.setBackground(Color.WHITE);
		contentPanel.add(numberTextField);
		numberTextField.setColumns(15);
		JLabel numberHint = new JLabel("   *");
		numberHint.setLabelFor(numberTextField);
		numberHint.setForeground(Color.RED);
		numberHint.setFont(new Font("楷体", Font.PLAIN, 13));
		numberHint.setBackground(Color.WHITE);
		contentPanel.add(numberHint);
		JLabel contactLabel = new JLabel("联系方式：");
		contactLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contactLabel.setForeground(Color.BLACK);
		contactLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		contactLabel.setBackground(Color.WHITE);
		contentPanel.add(contactLabel);
		JTextField contactTextField = new JTextField(user.getContact());
		contactLabel.setLabelFor(contactTextField);
		contactTextField.setHorizontalAlignment(SwingConstants.LEFT);
		contactTextField.setForeground(Color.BLACK);
		contactTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		contactTextField.setBackground(Color.WHITE);
		contentPanel.add(contactTextField);
		contactTextField.setColumns(15);
		JLabel contactHint = new JLabel("   *");
		contactHint.setLabelFor(contactTextField);
		contactHint.setHorizontalAlignment(SwingConstants.LEFT);
		contactHint.setForeground(Color.RED);
		contactHint.setFont(new Font("楷体", Font.PLAIN, 13));
		contactHint.setBackground(Color.WHITE);
		contentPanel.add(contactHint);
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		passwordLabel.setBackground(Color.WHITE);
		contentPanel.add(passwordLabel);
		JPasswordField passwordTextField = new JPasswordField(user.getPassword());
		passwordLabel.setLabelFor(passwordTextField);
		passwordTextField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordTextField.setForeground(Color.BLACK);
		passwordTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		passwordTextField.setBackground(Color.WHITE);
		passwordTextField.setEchoChar('*');
		contentPanel.add(passwordTextField);
		passwordTextField.setColumns(15);
		JLabel passwordHint = new JLabel("   *");
		passwordHint.setLabelFor(passwordTextField);
		passwordHint.setHorizontalAlignment(SwingConstants.LEFT);
		passwordHint.setForeground(Color.RED);
		passwordHint.setFont(new Font("楷体", Font.PLAIN, 13));
		passwordHint.setBackground(Color.WHITE);
		contentPanel.add(passwordHint);
		JLabel departmentLabel = new JLabel("科室：");
		departmentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		departmentLabel.setForeground(Color.BLACK);
		departmentLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		departmentLabel.setBackground(Color.WHITE);
		contentPanel.add(departmentLabel);
		JTextField departmentTextField = new JTextField(user.getDepartment());
		departmentLabel.setLabelFor(departmentTextField);
		departmentTextField.setHorizontalAlignment(SwingConstants.LEFT);
		departmentTextField.setForeground(Color.BLACK);
		departmentTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		departmentTextField.setBackground(Color.WHITE);
		contentPanel.add(departmentTextField);
		departmentTextField.setColumns(15);
		JLabel departmentHint = new JLabel("   *");
		departmentHint.setLabelFor(departmentTextField);
		departmentHint.setHorizontalAlignment(SwingConstants.LEFT);
		departmentHint.setForeground(Color.RED);
		departmentHint.setFont(new Font("楷体", Font.PLAIN, 13));
		departmentHint.setBackground(Color.WHITE);
		contentPanel.add(departmentHint);
		JLabel roleLabel = new JLabel("角色：");
		roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roleLabel.setForeground(Color.BLACK);
		roleLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		roleLabel.setBackground(Color.WHITE);
		contentPanel.add(roleLabel);
		JComboBox roleComboBox = new JComboBox();
		roleLabel.setLabelFor(roleComboBox);
		Vector<Vector> roles = new Permission().select();
		String[] items = new String[roles.size() + 1];
		items[0] = "";
		for (int i = 0; i < roles.size(); i++) {
			items[i + 1] = roles.get(i).get(0).toString();
		}
		roleComboBox.setModel(new DefaultComboBoxModel(items));
		roleComboBox.setMaximumRowCount(100);
		roleComboBox.setForeground(Color.BLACK);
		roleComboBox.setFont(new Font("楷体", Font.PLAIN, 14));
		roleComboBox.setBackground(Color.WHITE);
		contentPanel.add(roleComboBox);
		roleComboBox.setSelectedItem(user.getRole());
		JLabel roleHint = new JLabel("   *");
		roleHint.setLabelFor(roleComboBox);
		roleHint.setHorizontalAlignment(SwingConstants.LEFT);
		roleHint.setForeground(Color.RED);
		roleHint.setFont(new Font("楷体", Font.PLAIN, 13));
		roleHint.setBackground(Color.WHITE);
		contentPanel.add(roleHint);
		JPanel buttonPane = new JPanel();
		buttonPane.setForeground(Color.BLACK);
		buttonPane.setBackground(Color.WHITE);
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setVgap(10);
		fl_buttonPane.setHgap(50);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("确定");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = nameTextField.getText().trim();
					String number = numberTextField.getText().trim();
					String contact = contactTextField.getText().trim();
					String password = passwordTextField.getText().trim();
					String department = departmentTextField.getText().trim();
					Object role = roleComboBox.getSelectedItem();
					if (name.length() <= 0) {
						nameHint.setText("   请输入用户姓名");
					} else if (number.length() <= 0) {
						numberHint.setText("   请输入身份证号");
					} else if (contact.length() <= 0) {
						contactHint.setText("   请输入联系方式");
					} else if (password.length() <= 0) {
						passwordHint.setText("   请输入初始密码");
					} else if (department.length() <= 0) {
						departmentHint.setText("   请输入科室部门");
					} else if (role == null || role.toString().trim().length() <= 0) {
						roleHint.setText("   请选择一个角色");
					} else {
						user.setName(name);
						user.setNumber(number);
						user.setContact(contact);
						user.setPassword(EncryptUtil.encrypt(password));
						user.setDepartment(department);
						user.setRole(role.toString().trim());
						if ((userDao.update(user)) != null) {
							if (table != null) {
								Vector<String> columnNames = new Vector<String>(2);
								columnNames.add("姓名");
								columnNames.add("身份证号");
								columnNames.add("联系电话");
								columnNames.add("科室部门");
								columnNames.add("角色权限");
								columnNames.add("         ");
								DefaultTableModel defaultTableModel = new DefaultTableModel(userDao.select(),
										columnNames) {
									boolean[] columnEditables = new boolean[] { false, false, false, false, false,
											true };

									public boolean isCellEditable(int row, int column) {
										return columnEditables[column];
									}
								};
								table.setModel(defaultTableModel);
								TableCellEditor tableCellEditor = new TableCellEditor();
								table.getColumnModel().getColumn(5).setCellEditor(tableCellEditor); // 添加操作单元格内的按钮响应事件
								table.getColumnModel().getColumn(5)
										.setCellRenderer(new com.lzf.ui.plugin.TableCellRenderer()); // 添加操作单元格内的按钮显示效果
								tableCellEditor.addDeleteListener(new DeleteListener() {
									@Override
									public void deleteListener(Object value, int row) {
										int res = JOptionPane.showConfirmDialog(null, "确定要删除该条数据么？", "删除",
												JOptionPane.YES_NO_OPTION);
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
										new UserDetail(owner, "修改用户信息", (com.lzf.bean.User) value, table);
									}
								});
							}
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(UserDetail.this, "修改用户信息时遇到异常，请稍后重试？", "修改用户异常",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			okButton.setBackground(Color.WHITE);
			okButton.setForeground(Color.BLACK);
			okButton.setFont(new Font("楷体", Font.PLAIN, 14));
			okButton.setActionCommand("");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			JButton cancelButton = new JButton("取消");
			cancelButton.setForeground(Color.BLACK);
			cancelButton.setFont(new Font("楷体", Font.PLAIN, 14));
			cancelButton.setBackground(Color.WHITE);
			cancelButton.setActionCommand("");
			buttonPane.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		setVisible(true);
	}
}
