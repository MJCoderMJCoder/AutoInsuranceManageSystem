/**
 * 
 */
package com.lzf.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.lzf.util.DatabaseUtil;
import com.lzf.util.DateTimeUtil;

/**
 * 与车险有关的数据访问层
 * 
 * @author MJCoder
 *
 */
public class AutoInsurance {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	/**
	 * 创建一条车险记录：插入一条车险数据
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int insert(com.lzf.bean.AutoInsurance autoInsurance) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "insert into autoInsurance (plateNumber, autoUse, brand, model, vin, engineNumber, debutDate,name,number,phone,address,dueDate,department,operator,remark,last) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,
					(autoInsurance.getPlateNumber() == null) ? "" : autoInsurance.getPlateNumber());
			preparedStatement.setString(2, (autoInsurance.getAutoUse() == null) ? "" : autoInsurance.getAutoUse());
			preparedStatement.setString(3, (autoInsurance.getBrand() == null) ? "" : autoInsurance.getBrand());
			preparedStatement.setString(4, (autoInsurance.getModel() == null) ? "" : autoInsurance.getModel());
			preparedStatement.setString(5, (autoInsurance.getVin() == null) ? "" : autoInsurance.getVin());
			preparedStatement.setString(6,
					(autoInsurance.getEngineNumber() == null) ? "" : autoInsurance.getEngineNumber());
			preparedStatement.setString(7, (autoInsurance.getDebutDate() == null) ? "" : autoInsurance.getDebutDate());
			preparedStatement.setString(8, (autoInsurance.getName() == null) ? "" : autoInsurance.getName());
			preparedStatement.setString(9, (autoInsurance.getNumber() == null) ? "" : autoInsurance.getNumber());
			preparedStatement.setString(10, (autoInsurance.getPhone() == null) ? "" : autoInsurance.getPhone());
			preparedStatement.setString(11, (autoInsurance.getAddress() == null) ? "" : autoInsurance.getAddress());
			preparedStatement.setString(12, (autoInsurance.getDueDate() == null) ? "" : autoInsurance.getDueDate());
			preparedStatement.setString(13,
					(autoInsurance.getDepartment() == null) ? "" : autoInsurance.getDepartment());
			preparedStatement.setString(14, (autoInsurance.getOperator() == null) ? "" : autoInsurance.getOperator());
			preparedStatement.setString(15, (autoInsurance.getRemark() == null) ? "" : autoInsurance.getRemark());
			preparedStatement.setString(16, (autoInsurance.getLast() == null) ? "" : autoInsurance.getLast());
			temp = preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 创建一系列车险记录：插入一系列车险数据
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int batchInsert(Vector<com.lzf.bean.AutoInsurance> autoInsurances) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			for (com.lzf.bean.AutoInsurance autoInsurance : autoInsurances) {
				autoInsurance.setLast(DateTimeUtil.getCurrentTime());
				String sql = "insert into autoInsurance (plateNumber, autoUse, brand, model, vin, engineNumber, debutDate,name,number,phone,address,dueDate,department,operator,remark,last) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,
						(autoInsurance.getPlateNumber() == null) ? "" : autoInsurance.getPlateNumber());
				preparedStatement.setString(2, (autoInsurance.getAutoUse() == null) ? "" : autoInsurance.getAutoUse());
				preparedStatement.setString(3, (autoInsurance.getBrand() == null) ? "" : autoInsurance.getBrand());
				preparedStatement.setString(4, (autoInsurance.getModel() == null) ? "" : autoInsurance.getModel());
				preparedStatement.setString(5, (autoInsurance.getVin() == null) ? "" : autoInsurance.getVin());
				preparedStatement.setString(6,
						(autoInsurance.getEngineNumber() == null) ? "" : autoInsurance.getEngineNumber());
				preparedStatement.setString(7,
						(autoInsurance.getDebutDate() == null) ? "" : autoInsurance.getDebutDate());
				preparedStatement.setString(8, (autoInsurance.getName() == null) ? "" : autoInsurance.getName());
				preparedStatement.setString(9, (autoInsurance.getNumber() == null) ? "" : autoInsurance.getNumber());
				preparedStatement.setString(10, (autoInsurance.getPhone() == null) ? "" : autoInsurance.getPhone());
				preparedStatement.setString(11, (autoInsurance.getAddress() == null) ? "" : autoInsurance.getAddress());
				preparedStatement.setString(12, (autoInsurance.getDueDate() == null) ? "" : autoInsurance.getDueDate());
				preparedStatement.setString(13,
						(autoInsurance.getDepartment() == null) ? "" : autoInsurance.getDepartment());
				preparedStatement.setString(14,
						(autoInsurance.getOperator() == null) ? "" : autoInsurance.getOperator());
				preparedStatement.setString(15, (autoInsurance.getRemark() == null) ? "" : autoInsurance.getRemark());
				preparedStatement.setString(16, (autoInsurance.getLast() == null) ? "" : autoInsurance.getLast());
				temp += preparedStatement.executeUpdate();
			}
			connection.commit();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 根据相关条件查找系统中所有的车险数据
	 * 
	 * @param plateNumber
	 *            车牌号
	 * @param autoUse
	 *            车辆使用性质
	 * @param brand
	 *            品牌
	 * @param model
	 *            型号
	 * @param vin
	 *            车架号
	 * @param engineNumber
	 *            发动机号
	 * @param debutDate
	 *            初登日期
	 * @param name
	 *            姓名
	 * @param number
	 *            身份证号
	 * @param phone
	 *            电话号码
	 * @param address
	 *            地址
	 * @param dueDate
	 *            保险到期日
	 * @param department
	 *            科室
	 * @param operator
	 *            经办人
	 * @param remark
	 *            备注
	 * @param last
	 *            最后修改日期
	 * @return 一个包含了一系列AutoInsurance的动态数组
	 */
	public Vector<com.lzf.bean.AutoInsurance> select(String plateNumber, String autoUse, String brand, String model,
			String vin, String engineNumber, String debutDate, String name, String number, String phone, String address,
			String dueDate, String department, String operator, String remark, String last) {
		Vector<com.lzf.bean.AutoInsurance> data = new Vector<com.lzf.bean.AutoInsurance>();
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "select * from autoInsurance where  plateNumber like '%'||?||'%' and autoUse like '%'||?||'%' and brand like '%'||?||'%' and model like '%'||?||'%' and vin like '%'||?||'%' and engineNumber like '%'||?||'%' and debutDate like '%'||?||'%' and name like '%'||?||'%' and number like '%'||?||'%' and phone like '%'||?||'%' and address like '%'||?||'%' and dueDate like '%'||?||'%' and department like '%'||?||'%' and operator like '%'||?||'%' and remark like '%'||?||'%' and last like '%'||?||'%';";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, (plateNumber == null) ? "" : plateNumber);
			preparedStatement.setString(2, (autoUse == null) ? "" : autoUse);
			preparedStatement.setString(3, (brand == null) ? "" : brand);
			preparedStatement.setString(4, (model == null) ? "" : model);
			preparedStatement.setString(5, (vin == null) ? "" : vin);
			preparedStatement.setString(6, (engineNumber == null) ? "" : engineNumber);
			preparedStatement.setString(7, (debutDate == null) ? "" : debutDate);
			preparedStatement.setString(8, (name == null) ? "" : name);
			preparedStatement.setString(9, (number == null) ? "" : number);
			preparedStatement.setString(10, (phone == null) ? "" : phone);
			preparedStatement.setString(11, (address == null) ? "" : address);
			preparedStatement.setString(12, (dueDate == null) ? "" : dueDate);
			preparedStatement.setString(13, (department == null) ? "" : department);
			preparedStatement.setString(14, (operator == null) ? "" : operator);
			preparedStatement.setString(15, (remark == null) ? "" : remark);
			preparedStatement.setString(16, (last == null) ? "" : last);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				data.add(new com.lzf.bean.AutoInsurance(resultSet.getInt("id"), resultSet.getString("plateNumber"),
						resultSet.getString("autoUse"), resultSet.getString("brand"), resultSet.getString("model"),
						resultSet.getString("vin"), resultSet.getString("engineNumber"),
						resultSet.getString("debutDate"), resultSet.getString("name"), resultSet.getString("number"),
						resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("dueDate"),
						resultSet.getString("department"), resultSet.getString("operator"),
						resultSet.getString("remark"), resultSet.getString("last")));
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 查询当前最新插入的一条数据（id最大的一条数据）
	 * 
	 * @return 一个AutoInsurance实体
	 */
	public com.lzf.bean.AutoInsurance selectNew() {
		com.lzf.bean.AutoInsurance autoInsurance = null;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "select * from autoInsurance where  id= (select max(id) from autoInsurance)";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				autoInsurance = new com.lzf.bean.AutoInsurance(resultSet.getInt("id"),
						resultSet.getString("plateNumber"), resultSet.getString("autoUse"),
						resultSet.getString("brand"), resultSet.getString("model"), resultSet.getString("vin"),
						resultSet.getString("engineNumber"), resultSet.getString("debutDate"),
						resultSet.getString("name"), resultSet.getString("number"), resultSet.getString("phone"),
						resultSet.getString("address"), resultSet.getString("dueDate"),
						resultSet.getString("department"), resultSet.getString("operator"),
						resultSet.getString("remark"), resultSet.getString("last"));
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autoInsurance;
	}

	/**
	 * 删除一条AutoInsurance对应的数据
	 * 
	 * @param id
	 *            AutoInsurance的主键ID
	 * 
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int delete(int id) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "delete from autoInsurance where id = ?";
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

	/**
	 * 更新一条AutoInsurance对应的数据
	 * 
	 * 
	 * @param plateNumber
	 *            车牌号
	 * @param autoUse
	 *            车辆使用性质
	 * @param brand
	 *            品牌
	 * @param model
	 *            型号
	 * @param vin
	 *            车架号
	 * @param engineNumber
	 *            发动机号
	 * @param debutDate
	 *            初登日期
	 * @param name
	 *            姓名
	 * @param number
	 *            身份证号
	 * @param phone
	 *            电话号码
	 * @param address
	 *            地址
	 * @param dueDate
	 *            保险到期日
	 * @param department
	 *            科室
	 * @param operator
	 *            经办人
	 * @param remark
	 *            备注
	 * @param last
	 *            最后修改日期
	 * @param id
	 *            value对应AutoInsurance的主键ID
	 * @return SQL数据操作语言（DML）语句的行数；0表示不返回任何内容的SQL语句;
	 */
	public int update(String plateNumber, String autoUse, String brand, String model, String vin, String engineNumber,
			String debutDate, String name, String number, String phone, String address, String dueDate,
			String department, String operator, String remark, String last, int id) {
		int temp = 0;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "update autoInsurance set  plateNumber=?, autoUse=?, brand=?, model=?, vin=?, engineNumber=?, debutDate=?, name=?, number=?, phone=?, address=?, dueDate=?, department=?, operator=?, remark=?, last=? where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, plateNumber);
			preparedStatement.setString(2, autoUse);
			preparedStatement.setString(3, brand);
			preparedStatement.setString(4, model);
			preparedStatement.setString(5, vin);
			preparedStatement.setString(6, engineNumber);
			preparedStatement.setString(7, debutDate);
			preparedStatement.setString(8, name);
			preparedStatement.setString(9, number);
			preparedStatement.setString(10, phone);
			preparedStatement.setString(11, address);
			preparedStatement.setString(12, dueDate);
			preparedStatement.setString(13, department);
			preparedStatement.setString(14, operator);
			preparedStatement.setString(15, remark);
			preparedStatement.setString(16, last);
			preparedStatement.setInt(17, id);
			temp = preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 根据主键ID精准查询对应的一条数据
	 * 
	 * @return 一个AutoInsurance实体
	 */
	public com.lzf.bean.AutoInsurance select(int id) {
		com.lzf.bean.AutoInsurance autoInsurance = null;
		try {
			connection = DatabaseUtil.getConnection();
			connection.setAutoCommit(false);
			String sql = "select * from autoInsurance where  id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				autoInsurance = new com.lzf.bean.AutoInsurance(resultSet.getInt("id"),
						resultSet.getString("plateNumber"), resultSet.getString("autoUse"),
						resultSet.getString("brand"), resultSet.getString("model"), resultSet.getString("vin"),
						resultSet.getString("engineNumber"), resultSet.getString("debutDate"),
						resultSet.getString("name"), resultSet.getString("number"), resultSet.getString("phone"),
						resultSet.getString("address"), resultSet.getString("dueDate"),
						resultSet.getString("department"), resultSet.getString("operator"),
						resultSet.getString("remark"), resultSet.getString("last"));
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return autoInsurance;
	}

	/**
	 * 查找系统中所有的车险数据(备份导出)
	 * 
	 * @return 一个包含了一系列AutoInsurance的动态数组
	 */
	public Vector<com.lzf.bean.AutoInsurance> select(File file) {
		Vector<com.lzf.bean.AutoInsurance> data = new Vector<com.lzf.bean.AutoInsurance>();
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath()); // 车险管理系统
			// System.out.println("打开数据库成功");
			connection.setAutoCommit(false);
			String sql = "select * from autoInsurance";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				data.add(new com.lzf.bean.AutoInsurance(resultSet.getInt("id"), resultSet.getString("plateNumber"),
						resultSet.getString("autoUse"), resultSet.getString("brand"), resultSet.getString("model"),
						resultSet.getString("vin"), resultSet.getString("engineNumber"),
						resultSet.getString("debutDate"), resultSet.getString("name"), resultSet.getString("number"),
						resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("dueDate"),
						resultSet.getString("department"), resultSet.getString("operator"),
						resultSet.getString("remark"), resultSet.getString("last")));
			}
			connection.commit();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
