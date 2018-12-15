/**
 * 
 */
package com.lzf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.lzf.util.DatabaseUtil;

/**
 * 与权限有关的数据访问层
 * 
 * @author MJCoder
 *
 */
public class Permission {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	/**
	 * 创建一个角色对应的所有权限：插入一个角色对应的所有权限数据
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int insert(Vector<com.lzf.bean.Permission> permissions) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			for (com.lzf.bean.Permission permission : permissions) {
				String sql = "insert into permission (role, function, remark) values (?, ?, ?);";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, permission.getRole());
				preparedStatement.setString(2, permission.getFunction());
				preparedStatement.setString(3, permission.getRemark());
				temp += preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			connection.commit();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 修改已有角色的相关权限
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int insert(Vector<com.lzf.bean.Permission> permissions, String role) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "delete from permission where role = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, role);
			temp += preparedStatement.executeUpdate();
			preparedStatement.close();
			for (com.lzf.bean.Permission permission : permissions) {
				sql = "insert into permission (role, function, remark) values (?, ?, ?);";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, permission.getRole());
				preparedStatement.setString(2, permission.getFunction());
				preparedStatement.setString(3, permission.getRemark());
				temp += preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			connection.commit();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 根据角色名称查找对应的相关权限
	 * 
	 * @return 一系列Permission实体
	 */
	public Vector<com.lzf.bean.Permission> select(String role) {
		Vector<com.lzf.bean.Permission> permissions = new Vector<com.lzf.bean.Permission>();
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "SELECT * FROM permission WHERE role = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, role);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				permissions.add(new com.lzf.bean.Permission(resultSet.getString("role"),
						resultSet.getString("function"), resultSet.getString("remark")));
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissions;
	}

	/**
	 * 查询所有的角色名称
	 * 
	 * @return 一系列Permission实体
	 */
	public Vector<Vector> select() {
		Vector<Vector> roles = new Vector<Vector>();
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "SELECT DISTINCT role FROM permission";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Vector<String> role = new Vector<String>(2);
				role.add(resultSet.getString("role"));
				role.add(resultSet.getString("role"));
				roles.add(role);
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	/**
	 * 删除role对应的一系列Permission数据
	 * 
	 * @param role
	 *            Permission的角色
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int delete(String role) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "delete from permission where role = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, role);
			temp = preparedStatement.executeUpdate();
			preparedStatement.close();
			sql = "UPDATE user SET role = null WHERE role = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, role);
			temp = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.commit();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
