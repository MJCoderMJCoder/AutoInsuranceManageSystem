/**
 * 
 */
package com.lzf.bean;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 车险实体类
 * 
 * @author MJCoder
 *
 */
public class AutoInsurance extends Vector {

	private int id; // id编号
	private String plateNumber; // 车牌号
	private String autoUse; // 车辆使用性质
	private String brand; // 品牌
	private String model; // 型号
	private String vin; // 车架号
	private String engineNumber; // 发动机号
	private String debutDate; // 初登日期
	private String name; // 姓名
	private String number; // 身份证号
	private String phone; // 电话号码
	private String address; // 地址
	private String dueDate; // 保险到期日
	private String department; // 科室
	private String operator; // 经办人
	private String remark; // 备注
	private String last; // 最后修改日期（系统自动记录生成、不可修改）

	public AutoInsurance() {
		this.add(plateNumber);
		this.add(autoUse);
		this.add(brand);
		this.add(model);
		this.add(vin);
		this.add(engineNumber);
		this.add(debutDate);
		this.add(name);
		this.add(number);
		this.add(phone);
		this.add(address);
		this.add(dueDate);
		this.add(department);
		this.add(operator);
		this.add(remark);
		this.add(last);
		this.add(id);
	}

	public AutoInsurance(String plateNumber, String autoUse, String brand, String model, String vin,
			String engineNumber, String debutDate, String name, String number, String phone, String address,
			String dueDate, String department, String operator, String remark, String last) {
		super();
		this.plateNumber = plateNumber;
		this.autoUse = autoUse;
		this.brand = brand;
		this.model = model;
		this.vin = vin;
		this.engineNumber = engineNumber;
		this.debutDate = debutDate;
		this.name = name;
		this.number = number;
		this.phone = phone;
		this.address = address;
		this.dueDate = dueDate;
		this.department = department;
		this.operator = operator;
		this.remark = remark;
		this.last = last;
	}

	public AutoInsurance(int id, String plateNumber, String autoUse, String brand, String model, String vin,
			String engineNumber, String debutDate, String name, String number, String phone, String address,
			String dueDate, String department, String operator, String remark, String last) {
		super();
		this.id = id;
		this.plateNumber = plateNumber;
		this.autoUse = autoUse;
		this.brand = brand;
		this.model = model;
		this.vin = vin;
		this.engineNumber = engineNumber;
		this.debutDate = debutDate;
		this.name = name;
		this.number = number;
		this.phone = phone;
		this.address = address;
		this.dueDate = dueDate;
		this.department = department;
		this.operator = operator;
		this.remark = remark;
		this.last = last;

		this.add(plateNumber);
		this.add(autoUse);
		this.add(brand);
		this.add(model);
		this.add(vin);
		this.add(engineNumber);
		this.add(debutDate);
		this.add(name);
		this.add(number);
		this.add(phone);
		this.add(address);
		this.add(dueDate);
		this.add(department);
		this.add(operator);
		this.add(remark);
		this.add(last);
		this.add(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.set(16, id);
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
		this.set(0, plateNumber);
	}

	public String getAutoUse() {
		return autoUse;
	}

	public void setAutoUse(String autoUse) {
		this.autoUse = autoUse;
		this.set(1, autoUse);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
		this.set(2, brand);
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
		this.set(3, model);
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
		this.set(4, vin);
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
		this.set(5, engineNumber);
	}

	public String getDebutDate() {
		return debutDate;
	}

	public void setDebutDate(String debutDate) {
		this.debutDate = debutDate;
		this.set(6, debutDate);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.set(7, name);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
		this.set(8, number);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		this.set(9, phone);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		this.set(10, address);
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
		this.set(11, dueDate);
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
		this.set(12, department);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
		this.set(13, operator);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		this.set(14, remark);
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
		this.set(15, last);
	}

	@Override
	public String toString() {
		return "AutoInsurance [id=" + id + ", plateNumber=" + plateNumber + ", autoUse=" + autoUse + ", brand=" + brand
				+ ", model=" + model + ", vin=" + vin + ", engineNumber=" + engineNumber + ", debutDate=" + debutDate
				+ ", name=" + name + ", number=" + number + ", phone=" + phone + ", address=" + address + ", dueDate="
				+ dueDate + ", department=" + department + ", operator=" + operator + ", remark=" + remark + ", last="
				+ last + "]";
	}

}
