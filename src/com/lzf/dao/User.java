package com.lzf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.lzf.util.DatabaseUtil;
import com.lzf.util.EncryptUtil;

/**
 * 与用户有关的数据访问层
 * 
 * @author MJCoder
 *
 */
public class User {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	/**
	 * 创建一条用户记录：插入一条用户数据
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;-1表示该账号已存在
	 */
	public int insert(com.lzf.bean.User user) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "insert into user (name, number, contact, password, department, last, role, remark) values (?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getNumber());
			preparedStatement.setString(3, user.getContact());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getDepartment());
			preparedStatement.setString(6, user.getLast());
			preparedStatement.setString(7, user.getRole());
			preparedStatement.setString(8, user.getRemark());
			temp = preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			if (e.getMessage().contains("UNIQUE constraint")) {
				temp = -1;
			} else {
				e.printStackTrace();
			}
		}
		// System.out.println("SQL数据操作语言（DML）语句的行数、0表示不返回任何内容的SQL语句、-1表示该账号已存在：" +
		// temp);
		return temp;
	}

	/**
	 * 根据账号查找系统中是否有该用户
	 * 
	 * @param account
	 *            身份证号、手机号、编号
	 * @return 用户实体；null表示未找到该用户
	 */
	public com.lzf.bean.User select(String account) {
		com.lzf.bean.User user = null;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "select * from user where number=?"; // or contact=? or id=?
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account);
			// preparedStatement.setString(2, account);
			// preparedStatement.setString(3, account);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new com.lzf.bean.User(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("number"), resultSet.getString("contact"), resultSet.getString("password"),
						resultSet.getString("department"), resultSet.getString("last"), resultSet.getString("role"),
						resultSet.getString("token"), resultSet.getString("remark"));
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("null表示未找到该用户：" + user);
		return user;
	}

	/**
	 * 更新一条用户记录：更新一条用户数据
	 * 
	 * @param user
	 *            一个用户实体类
	 * @return 更新后的用户实体类; null表示更新失败。
	 */
	public com.lzf.bean.User update(com.lzf.bean.User user) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "update user set  name=?, number=?, contact=?, password=?, department=?, last=?, role=?, token=?, remark=? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getNumber());
			preparedStatement.setString(3, user.getContact());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getDepartment());
			preparedStatement.setString(6, user.getLast());
			preparedStatement.setString(7, user.getRole());
			preparedStatement.setString(8, user.getToken());
			preparedStatement.setString(9, user.getRemark());
			preparedStatement.setInt(10, user.getId());
			temp = preparedStatement.executeUpdate(); // SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;-1表示异常
			connection.commit();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			temp = -1;
			e.printStackTrace();
		}
		// System.out.println("SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;-1表示异常：" + temp);
		if (temp >= 0) {
			return user;
		} else {
			return null;
		}
	}

	/**
	 * 查找系统中的所有用户
	 * 
	 * @return 一系列用户实体；
	 */
	public Vector<Vector> select() {
		Vector<Vector> users = new Vector<Vector>();
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "select * from user where remark is null";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Vector<Object> role = new Vector<Object>(2);
				role.add(resultSet.getString("name"));
				role.add(resultSet.getString("number"));
				role.add(resultSet.getString("contact"));
				role.add(resultSet.getString("department"));
				role.add(resultSet.getString("role"));
				role.add(new com.lzf.bean.User(resultSet.getInt("id"), resultSet.getString("name"),
						resultSet.getString("number"), resultSet.getString("contact"), resultSet.getString("password"),
						resultSet.getString("department"), resultSet.getString("last"), resultSet.getString("role"),
						resultSet.getString("token"), resultSet.getString("remark")));
				users.add(role);
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * 根据ID删除对应的用户信息
	 * 
	 * @param id
	 *            User的主键
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int delete(int id) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "delete from user where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			temp = preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
}
