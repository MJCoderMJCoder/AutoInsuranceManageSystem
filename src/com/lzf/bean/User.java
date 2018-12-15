/**
 * 
 */
package com.lzf.bean;

/**
 * 用户实体类
 * 
 * @author MJCoder
 */
public class User {

	private int id; // 主键ID
	private String name; // 姓名
	private String number; // 身份证号：唯一
	private String contact; // 联系方式
	private String password; // 密码
	private String department; // 部门
	private String last; // 最后修改日期
	private String role; // 角色权限
	private String token; // 令牌
	private String remark; // 备注信息

	public User() {
		super();
	}

	public User(String name, String number, String contact, String password, String department, String last,
			String role, String token, String remark) {
		super();
		this.name = name;
		this.number = number;
		this.contact = contact;
		this.password = password;
		this.department = department;
		this.last = last;
		this.role = role;
		this.token = token;
		this.remark = remark;
	}

	public User(int id, String name, String number, String contact, String password, String department, String last,
			String role, String token, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.contact = contact;
		this.password = password;
		this.department = department;
		this.last = last;
		this.role = role;
		this.token = token;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", number=" + number + ", contact=" + contact + ", password="
				+ password + ", department=" + department + ", last=" + last + ", role=" + role + ", token=" + token
				+ ", remark=" + remark + "]";
	}
}
