/**
 * 
 */
package com.lzf.bean;

/**
 * 
 * 角色权限实体类
 * 
 * @author MJCoder
 *
 */
public class Permission {
	private String role;
	private String function;
	private String remark;

	/**
	 * 
	 */
	public Permission() {
		// TODO Auto-generated constructor stub
	}

	public Permission(String role, String function, String remark) {
		super();
		this.role = role;
		this.function = function;
		this.remark = remark;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Role [role=" + role + ", function=" + function + ", remark=" + remark + "]";
	}

}
