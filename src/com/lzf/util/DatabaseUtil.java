package com.lzf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 数据库工具类
 * 
 * @author MJCoder
 *
 */
public class DatabaseUtil {

	/**
	 * 在先前创建的数据库中创建各种表。
	 */
	static {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);// 开启事务(start transaction)
			String sql = "CREATE TABLE [autoInsurance]("
					+ "    [id] INTEGER PRIMARY KEY ASC AUTOINCREMENT NOT NULL ON CONFLICT ROLLBACK UNIQUE ON CONFLICT ROLLBACK, "
					+ "    [plateNumber] TEXT, " + "    [autoUse] TEXT, " + "    [brand] TEXT, " + "    [model] TEXT, "
					+ "    [vin] TEXT, " + "    [engineNumber] TEXT, " + "    [debutDate] TEXT, " + "    [name] TEXT, "
					+ "    [number] TEXT, " + "    [phone] TEXT, " + "    [address] TEXT, " + "    [dueDate] TEXT, "
					+ "    [department] TEXT, " + "    [operator] TEXT, " + "    [remark] TEXT, " + "    [last] TEXT);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			// sql = "CREATE TABLE [function]("
			// + " [id] INTEGER PRIMARY KEY ASC AUTOINCREMENT NOT NULL ON CONFLICT ROLLBACK
			// UNIQUE ON CONFLICT ROLLBACK, "
			// + " [name] TEXT NOT NULL ON CONFLICT ROLLBACK UNIQUE ON CONFLICT ROLLBACK, "
			// + " [method] TEXT, " + " [ui] TEXT, " + " [trigger] TEXT, "
			// + " [parentId] INTEGER REFERENCES function([id]), " + " [remark] TEXT);";
			// preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.execute();
			sql = "CREATE TABLE [permission]("
					+ "    [id] INTEGER PRIMARY KEY ASC AUTOINCREMENT NOT NULL ON CONFLICT ROLLBACK UNIQUE ON CONFLICT ROLLBACK, "
					+ "    [role] TEXT NOT NULL ON CONFLICT ROLLBACK, "
					+ "    [function] TEXT NOT NULL ON CONFLICT ROLLBACK, " + "    [remark] TEXT);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			sql = "CREATE TABLE [user]("
					+ "    [id] INTEGER PRIMARY KEY ASC ON CONFLICT ROLLBACK AUTOINCREMENT NOT NULL ON CONFLICT ROLLBACK UNIQUE ON CONFLICT ROLLBACK, "
					+ "    [name] TEXT NOT NULL ON CONFLICT ROLLBACK, "
					+ "    [number] TEXT NOT NULL UNIQUE ON CONFLICT ROLLBACK, "
					+ "    [contact] TEXT NOT NULL ON CONFLICT ROLLBACK, "
					+ "    [password] TEXT NOT NULL ON CONFLICT ROLLBACK, "
					+ "    [department] TEXT NOT NULL ON CONFLICT ROLLBACK, "
					+ "    [last] TEXT NOT NULL ON CONFLICT ROLLBACK, " + "    [role] TEXT, " + "    [token] TEXT, "
					+ "    [remark] TEXT);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			// sql = "INSERT INTO function (id, name, method, ui, trigger, parentId, remark)
			// VALUES "
			// + "(1, '查询功能', null, 'selectPanel', 'searchButton', null, null),"
			// + "(2, '导入功能', null, 'importPanel', 'importButton', null, null),"
			// + "(3, '添加单条数据', null, 'insertPanel', 'insertButton', null, null),"
			// + "(4, '导出功能', null, 'exportPanel', 'exportButton', null, null),"
			// + "(5, '导出Excel模板', 'exportPopupMenu.show', 'templateExport',
			// 'templateExport', 4, null),"
			// + "(6, 'VLOOKUP导出（字段可选）', 'exportPopupMenu.show', 'vlookupExport',
			// 'vlookupExport', 4, null),"
			// + "(7, '批量查询导出（字段可选）', 'exportPopupMenu.show', 'batchExport', 'batchExport',
			// 4, null),"
			// + "(8, '备份导出（字段可选）', 'exportPopupMenu.show', 'backupExport', 'backupExport',
			// 4, null),"
			// + "(9, '权限管理', null, 'permissionPanel', 'permissioinButton', null, null),"
			// + "(10, '添加新角色', 'permissionPopupMenu.show', 'insertRole', 'insertRole', 9,
			// null),"
			// + "(11, '管理已有角色', 'permissionPopupMenu.show', 'manageRole', 'manageRole', 9,
			// null),"
			// + "(12, '用户管理', null, 'userPanel', 'userButton', null, null),"
			// + "(13, '添加新用户', 'userPopupMenu.show', 'insertUser', 'insertUser', 12,
			// null),"
			// + "(14, '管理已有用户', 'userPopupMenu.show', 'manageUser', 'manageUser', 12,
			// null),"
			// + "(15, '个人信息', null, 'personalInfoPanel', 'personalInfoButton', null,
			// null);";
			// preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.execute();
			connection.commit();// 提交事务(commit)
			preparedStatement.close();
			connection.close();
			// System.out.println("表创建成功");
		} catch (Exception e) {
			if (e.getMessage().contains("already exists") && e.getMessage().contains("table")) {
				// 表已经存在，无需创建
				// System.out.println("表已经存在，无需创建");
			} else {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 连接到一个现有的数据库。如果数据库不存在，那么它就会被创建，最后将返回一个数据库的已连接对象。
	 * 
	 * @return 数据库的已连接对象
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:AutoInsuranceManageSystem.db"); // 车险管理系统
			// System.out.println("打开数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
