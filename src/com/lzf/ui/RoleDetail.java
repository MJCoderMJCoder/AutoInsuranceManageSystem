package com.lzf.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.lzf.dao.Permission;
import com.lzf.ui.plugin.CheckTreeManager;
import com.lzf.ui.plugin.CheckTreeSelectionModel;
import com.lzf.ui.plugin.TableCellEditor;
import com.lzf.ui.plugin.TableCellEditor.DeleteListener;
import com.lzf.ui.plugin.TableCellEditor.UpdateListener;
import com.lzf.util.DatabaseUtil;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class RoleDetail extends JDialog {
	public static final String SEARCH = "查询功能";
	public static final String EXPORT = "导出功能";
	public static final String EXPORT_TEMPLATE = "导出Excel模板";
	public static final String EXPORT_VLOOKUP = "VLOOKUP导出（字段可选）";
	public static final String EXPORT_BATCH = "批量查询导出（字段可选）";
	public static final String EXPORT_BACKUP = "备份导出（字段可选）";
	public static final String PERMISSION = "权限管理";
	public static final String PERMISSION_ADD = "添加新角色";
	public static final String PERMISSION_MANAGE = "管理已有角色";
	public static final String USER = "用户管理";
	public static final String USER_ADD = "添加新用户";
	public static final String USER_MANAGE = "管理已有用户";
	public static final String IMPORT = "导入功能";
	public static final String ADD = "添加单条数据";
	public static final String INFO = "个人信息";

	private Permission permissionDao = new Permission();

	/**
	 * 添加新角色
	 */
	public RoleDetail(Window owner, String title) {
		super(owner, title);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setFont(new Font("楷体", Font.PLAIN, 16));
		getContentPane().setBackground(Color.WHITE);
		setTitle(title);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RoleDetail.class.getResource("/com/lzf/image/zff.jpg")));
		setForeground(Color.BLACK);
		setFont(new Font("楷体", Font.PLAIN, 16));
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 400, 360);
		getContentPane().setLayout(new BorderLayout());

		JTree tree = new JTree();
		tree.setForeground(Color.BLACK);
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("所有功能") {
			{
				add(new DefaultMutableTreeNode(SEARCH));
				DefaultMutableTreeNode node_1;
				node_1 = new DefaultMutableTreeNode(EXPORT);
				node_1.add(new DefaultMutableTreeNode(EXPORT_TEMPLATE));
				node_1.add(new DefaultMutableTreeNode(EXPORT_VLOOKUP));
				node_1.add(new DefaultMutableTreeNode(EXPORT_BATCH));
				node_1.add(new DefaultMutableTreeNode(EXPORT_BACKUP));
				add(node_1);
				node_1 = new DefaultMutableTreeNode(PERMISSION);
				node_1.add(new DefaultMutableTreeNode(PERMISSION_ADD));
				node_1.add(new DefaultMutableTreeNode(PERMISSION_MANAGE));
				add(node_1);
				node_1 = new DefaultMutableTreeNode(USER);
				node_1.add(new DefaultMutableTreeNode(USER_ADD));
				node_1.add(new DefaultMutableTreeNode(USER_MANAGE));
				add(node_1);
				add(new DefaultMutableTreeNode(IMPORT));
				add(new DefaultMutableTreeNode(ADD));
				add(new DefaultMutableTreeNode(INFO));
			}
		}));
		tree.setFont(new Font("楷体", Font.PLAIN, 14));
		tree.setBackground(Color.WHITE);
		DefaultTreeCellRenderer defaultTreeCellRenderer = new DefaultTreeCellRenderer();
		defaultTreeCellRenderer.setLeafIcon(null);
		defaultTreeCellRenderer.setOpenIcon(null);
		defaultTreeCellRenderer.setClosedIcon(null);
		tree.setCellRenderer(defaultTreeCellRenderer);
		CheckTreeManager checkTreeManager = new CheckTreeManager(tree);
		JScrollPane scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setForeground(Color.BLACK);
		bottomPanel.setBackground(Color.WHITE);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel rolePanel = new JPanel();
		rolePanel.setToolTipText("请输入角色名称");
		rolePanel.setForeground(Color.BLACK);
		rolePanel.setBackground(Color.WHITE);
		rolePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		bottomPanel.add(rolePanel);

		JLabel roleLabel = new JLabel("角色名称：");
		roleLabel.setToolTipText("");
		rolePanel.add(roleLabel);
		roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roleLabel.setForeground(Color.BLACK);
		roleLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		roleLabel.setBackground(Color.WHITE);

		JTextField roleTextField = new JTextField();
		roleLabel.setLabelFor(roleTextField);
		rolePanel.add(roleTextField);
		roleTextField.setToolTipText("");
		roleTextField.setHorizontalAlignment(SwingConstants.LEFT);
		roleTextField.setForeground(Color.BLACK);
		roleTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		roleTextField.setBackground(Color.WHITE);
		roleTextField.setColumns(20);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		btnPanel.setForeground(Color.BLACK);
		btnPanel.setBackground(Color.WHITE);
		bottomPanel.add(btnPanel);

		JButton addButton = new JButton("确定");
		btnPanel.add(addButton);
		addButton.setForeground(Color.BLACK);
		addButton.setFont(new Font("楷体", Font.PLAIN, 14));
		addButton.setBackground(Color.WHITE);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String roleName = roleTextField.getText().trim();
				if (roleName.length() > 0) {
					if (!roleName.contains(SEARCH) && !roleName.contains(EXPORT) && !roleName.contains(EXPORT_TEMPLATE)
							&& !roleName.contains(EXPORT_VLOOKUP) && !roleName.contains(EXPORT_BATCH)
							&& !roleName.contains(EXPORT_BACKUP) && !roleName.contains(PERMISSION)
							&& !roleName.contains(PERMISSION_ADD) && !roleName.contains(PERMISSION_MANAGE)
							&& !roleName.contains(USER) && !roleName.contains(USER_ADD)
							&& !roleName.contains(USER_MANAGE) && !roleName.contains(IMPORT) && !roleName.contains(ADD)
							&& !roleName.contains(INFO) && permissionDao.select(roleName).size() <= 0) {
						Vector<com.lzf.bean.Permission> permissions = new Vector<com.lzf.bean.Permission>();
						TreePath[] treePaths = checkTreeManager.getSelectionModel().getSelectionPaths();
						if (treePaths.length > 0) {
							for (TreePath treePath : treePaths) {
								// System.out.println(treePath);
								if (treePath.getPathCount() == 1) {
									permissions.add(new com.lzf.bean.Permission(roleName, SEARCH, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_TEMPLATE, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_VLOOKUP, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BATCH, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BACKUP, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_ADD, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_MANAGE, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, USER, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, USER_ADD, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, USER_MANAGE, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, IMPORT, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, ADD, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, INFO, ""));
								} else if (treePath.getPathCount() == 2) {
									String function = treePath.getLastPathComponent() + "";
									permissions.add(new com.lzf.bean.Permission(roleName, function, ""));
									if (function.equals(EXPORT)) {
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_TEMPLATE, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_VLOOKUP, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BATCH, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BACKUP, ""));
									} else if (function.equals(PERMISSION)) {
										permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_ADD, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_MANAGE, ""));
									} else if (function.equals(USER)) {
										permissions.add(new com.lzf.bean.Permission(roleName, USER_ADD, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, USER_MANAGE, ""));
									}
								} else if (treePath.getPathCount() == 3) {
									com.lzf.bean.Permission permission = new com.lzf.bean.Permission(roleName,
											treePath.getPathComponent(1) + "", "");
									if (!permissions.contains(permission)) {
										permissions.add(permission);
									}
									permissions.add(new com.lzf.bean.Permission(roleName,
											treePath.getLastPathComponent() + "", ""));
								}
							}
							if (permissionDao.insert(permissions) > 0) {
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(RoleDetail.this, "添加该角色对应权限时遇到异常，请稍后重试？", "添加角色权限异常",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(RoleDetail.this, "请选择相关功能，以授予该角色相关权限？", "还未授予任何权限",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(RoleDetail.this, "该角色名称已存在", "该角色名称已存在",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(RoleDetail.this, "请输入角色名称？", "角色名称不能为空",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton cancelButton = new JButton("取消");
		btnPanel.add(cancelButton);
		cancelButton.setFont(new Font("楷体", Font.PLAIN, 14));
		cancelButton.setBackground(Color.WHITE);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	/**
	 * 修改已有角色
	 * 
	 * @param owner
	 * @param title
	 * @param role
	 */
	public RoleDetail(Window owner, String title, String role, JTable table) {
		super(owner, title);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setFont(new Font("楷体", Font.PLAIN, 16));
		getContentPane().setBackground(Color.WHITE);
		setTitle(title);
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RoleDetail.class.getResource("/com/lzf/image/zff.jpg")));
		setForeground(Color.BLACK);
		setFont(new Font("楷体", Font.PLAIN, 16));
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 400, 360);
		getContentPane().setLayout(new BorderLayout());

		JTree tree = new JTree();
		tree.setForeground(Color.BLACK);
		DefaultTreeCellRenderer defaultTreeCellRenderer = new DefaultTreeCellRenderer();
		defaultTreeCellRenderer.setLeafIcon(null);
		defaultTreeCellRenderer.setOpenIcon(null);
		defaultTreeCellRenderer.setClosedIcon(null);
		tree.setCellRenderer(defaultTreeCellRenderer);
		CheckTreeManager checkTreeManager = new CheckTreeManager(tree);
		CheckTreeSelectionModel checkTreeSelectionModel = checkTreeManager.getSelectionModel();
		DefaultMutableTreeNode searchTreeNode = new DefaultMutableTreeNode(SEARCH);
		DefaultMutableTreeNode exportTreeNode = new DefaultMutableTreeNode(EXPORT);
		DefaultMutableTreeNode exportTemplateTreeNode = new DefaultMutableTreeNode(EXPORT_TEMPLATE);
		DefaultMutableTreeNode exportVlookupTreeNode = new DefaultMutableTreeNode(EXPORT_VLOOKUP);
		DefaultMutableTreeNode exportBatchTreeNode = new DefaultMutableTreeNode(EXPORT_BATCH);
		DefaultMutableTreeNode exportBackupTreeNode = new DefaultMutableTreeNode(EXPORT_BACKUP);
		DefaultMutableTreeNode permissionTreeNode = new DefaultMutableTreeNode(PERMISSION);
		DefaultMutableTreeNode permissionAddTreeNode = new DefaultMutableTreeNode(PERMISSION_ADD);
		DefaultMutableTreeNode permissionManageTreeNode = new DefaultMutableTreeNode(PERMISSION_MANAGE);
		DefaultMutableTreeNode userTreeNode = new DefaultMutableTreeNode(USER);
		DefaultMutableTreeNode userAddTreeNode = new DefaultMutableTreeNode(USER_ADD);
		DefaultMutableTreeNode userManageTreeNode = new DefaultMutableTreeNode(USER_MANAGE);
		DefaultMutableTreeNode importTreeNode = new DefaultMutableTreeNode(IMPORT);
		DefaultMutableTreeNode addTreeNode = new DefaultMutableTreeNode(ADD);
		DefaultMutableTreeNode infoTreeNode = new DefaultMutableTreeNode(INFO);
		DefaultMutableTreeNode allTreeNode = new DefaultMutableTreeNode("所有功能") {
			{
				add(searchTreeNode);
				exportTreeNode.add(exportTemplateTreeNode);
				exportTreeNode.add(exportVlookupTreeNode);
				exportTreeNode.add(exportBatchTreeNode);
				exportTreeNode.add(exportBackupTreeNode);
				add(exportTreeNode);
				permissionTreeNode.add(permissionAddTreeNode);
				permissionTreeNode.add(permissionManageTreeNode);
				add(permissionTreeNode);
				userTreeNode.add(userAddTreeNode);
				userTreeNode.add(userManageTreeNode);
				add(userTreeNode);
				add(importTreeNode);
				add(addTreeNode);
				add(infoTreeNode);
			}
		};
		Vector<com.lzf.bean.Permission> permissions = permissionDao.select(role);
		for (com.lzf.bean.Permission permission : permissions) {
			if (permission.getFunction().equals(SEARCH)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(searchTreeNode.getPath()));
			} else if (permission.getFunction().equals(EXPORT_TEMPLATE)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(exportTemplateTreeNode.getPath()));
			} else if (permission.getFunction().equals(EXPORT_VLOOKUP)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(exportVlookupTreeNode.getPath()));
			} else if (permission.getFunction().equals(EXPORT_BATCH)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(exportBatchTreeNode.getPath()));
			} else if (permission.getFunction().equals(EXPORT_BACKUP)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(exportBackupTreeNode.getPath()));
			} else if (permission.getFunction().equals(PERMISSION_ADD)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(permissionAddTreeNode.getPath()));
			} else if (permission.getFunction().equals(PERMISSION_MANAGE)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(permissionManageTreeNode.getPath()));
			} else if (permission.getFunction().equals(USER_ADD)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(userAddTreeNode.getPath()));
			} else if (permission.getFunction().equals(USER_MANAGE)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(userManageTreeNode.getPath()));
			} else if (permission.getFunction().equals(IMPORT)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(importTreeNode.getPath()));
			} else if (permission.getFunction().equals(ADD)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(addTreeNode.getPath()));
			} else if (permission.getFunction().equals(INFO)) {
				checkTreeSelectionModel.addSelectionPath(new TreePath(infoTreeNode.getPath()));
			}
		}
		tree.setModel(new DefaultTreeModel(allTreeNode));
		tree.setFont(new Font("楷体", Font.PLAIN, 14));
		tree.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setForeground(Color.BLACK);
		bottomPanel.setBackground(Color.WHITE);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel rolePanel = new JPanel();
		rolePanel.setToolTipText("请输入角色名称");
		rolePanel.setForeground(Color.BLACK);
		rolePanel.setBackground(Color.WHITE);
		rolePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		bottomPanel.add(rolePanel);

		JLabel roleLabel = new JLabel("角色名称：");
		roleLabel.setToolTipText("");
		rolePanel.add(roleLabel);
		roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roleLabel.setForeground(Color.BLACK);
		roleLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		roleLabel.setBackground(Color.WHITE);

		JTextField roleTextField = new JTextField(role);
		roleLabel.setLabelFor(roleTextField);
		rolePanel.add(roleTextField);
		roleTextField.setToolTipText("");
		roleTextField.setHorizontalAlignment(SwingConstants.LEFT);
		roleTextField.setForeground(Color.BLACK);
		roleTextField.setFont(new Font("楷体", Font.PLAIN, 14));
		roleTextField.setBackground(Color.WHITE);
		roleTextField.setColumns(20);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		btnPanel.setForeground(Color.BLACK);
		btnPanel.setBackground(Color.WHITE);
		bottomPanel.add(btnPanel);

		JButton addButton = new JButton("确定");
		btnPanel.add(addButton);
		addButton.setForeground(Color.BLACK);
		addButton.setFont(new Font("楷体", Font.PLAIN, 14));
		addButton.setBackground(Color.WHITE);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String roleName = roleTextField.getText().trim();
				if (roleName.length() > 0) {
					if (!roleName.contains(SEARCH) && !roleName.contains(EXPORT) && !roleName.contains(EXPORT_TEMPLATE)
							&& !roleName.contains(EXPORT_VLOOKUP) && !roleName.contains(EXPORT_BATCH)
							&& !roleName.contains(EXPORT_BACKUP) && !roleName.contains(PERMISSION)
							&& !roleName.contains(PERMISSION_ADD) && !roleName.contains(PERMISSION_MANAGE)
							&& !roleName.contains(USER) && !roleName.contains(USER_ADD)
							&& !roleName.contains(USER_MANAGE) && !roleName.contains(IMPORT) && !roleName.contains(ADD)
							&& !roleName.contains(INFO)
							&& (roleName.equals(role) || permissionDao.select(roleName).size() <= 0)) {
						Vector<com.lzf.bean.Permission> permissions = new Vector<com.lzf.bean.Permission>();
						TreePath[] treePaths = checkTreeSelectionModel.getSelectionPaths();
						if (treePaths.length > 0) {
							for (TreePath treePath : treePaths) {
								// System.out.println(treePath);
								if (treePath.getPathCount() == 1) {
									permissions.add(new com.lzf.bean.Permission(roleName, SEARCH, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_TEMPLATE, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_VLOOKUP, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BATCH, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BACKUP, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_ADD, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_MANAGE, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, USER, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, USER_ADD, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, USER_MANAGE, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, IMPORT, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, ADD, ""));
									permissions.add(new com.lzf.bean.Permission(roleName, INFO, ""));
								} else if (treePath.getPathCount() == 2) {
									String function = treePath.getLastPathComponent() + "";
									permissions.add(new com.lzf.bean.Permission(roleName, function, ""));
									if (function.equals(EXPORT)) {
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_TEMPLATE, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_VLOOKUP, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BATCH, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, EXPORT_BACKUP, ""));
									} else if (function.equals(PERMISSION)) {
										permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_ADD, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, PERMISSION_MANAGE, ""));
									} else if (function.equals(USER)) {
										permissions.add(new com.lzf.bean.Permission(roleName, USER_ADD, ""));
										permissions.add(new com.lzf.bean.Permission(roleName, USER_MANAGE, ""));
									}
								} else if (treePath.getPathCount() == 3) {
									com.lzf.bean.Permission permission = new com.lzf.bean.Permission(roleName,
											treePath.getPathComponent(1) + "", "");
									if (!permissions.contains(permission)) {
										permissions.add(permission);
									}
									permissions.add(new com.lzf.bean.Permission(roleName,
											treePath.getLastPathComponent() + "", ""));
								}
							}
							if (permissionDao.insert(permissions, role) > 0) {
								Vector<String> columnNames = new Vector<String>(2);
								columnNames.add("         ");
								columnNames.add("         ");
								DefaultTableModel defaultTableModel = new DefaultTableModel(permissionDao.select(),
										columnNames) {
									boolean[] columnEditables = new boolean[] { false, true };

									public boolean isCellEditable(int row, int column) {
										return columnEditables[column];
									}
								};
								table.setModel(defaultTableModel);
								TableCellEditor tableCellEditor = new TableCellEditor();
								table.getColumnModel().getColumn(1).setCellEditor(tableCellEditor); // 添加操作单元格内的按钮响应事件
								table.getColumnModel().getColumn(1)
										.setCellRenderer(new com.lzf.ui.plugin.TableCellRenderer()); // 添加操作单元格内的按钮显示效果
								tableCellEditor.addDeleteListener(new DeleteListener() {
									@Override
									public void deleteListener(Object value, int row) {
										int res = JOptionPane.showConfirmDialog(null, "确定要删除该条数据么？", "删除",
												JOptionPane.YES_NO_OPTION);
										if (res == JOptionPane.YES_OPTION) {
											if (permissionDao.delete((String) value) > 0) {
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
										new RoleDetail(owner, "修改已有角色的相关权限", (String) value, table);
									}
								});
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(RoleDetail.this, "修改该角色对应权限时遇到异常，请稍后重试？", "修改角色权限异常",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(RoleDetail.this, "请选择相关功能，以授予该角色相关权限？", "还未授予任何权限",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(RoleDetail.this, "该角色名称已存在", "该角色名称已存在",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(RoleDetail.this, "请输入角色名称？", "角色名称不能为空",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton cancelButton = new JButton("取消");
		btnPanel.add(cancelButton);
		cancelButton.setFont(new Font("楷体", Font.PLAIN, 14));
		cancelButton.setBackground(Color.WHITE);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		setLocationRelativeTo(owner);
		setVisible(true);
	}

}
