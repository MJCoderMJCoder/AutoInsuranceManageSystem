package com.lzf.util;

import java.io.File;
import java.util.Vector;

import com.lzf.bean.AutoInsurance;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Excel 文件
 * <p>
 * Created by MJCoder on 2018-06-06.
 */

public class ExcelUtil {

	private String sheetName = "纸纷飞"; // 工作表名字前缀

	private int sheetCount; // 工作表计数

	private int unselectedFieldCount = 0; // 没有选择的字段计数

	/**
	 * Excel写入
	 * 
	 * @param directory
	 *            用户已经选择的要导出的目录位置
	 * @param fileName
	 * @param columnNames
	 *            Excel：sheet的头部标题
	 * @param data
	 *            Excel：数据
	 * @param chooseField
	 *            用户已经选择的将要导出字段
	 */
	public void writeExcel(File directory, String fileName, Vector<String> columnNames, Vector<AutoInsurance> data,
			Vector<Boolean> chooseField) {
		try {
			File file = new File(directory, fileName + ".xls");
			if (file.exists()) { // 该文件已经存在；接着已经存在的部分内容继续添加数据或是断点续传补充数据
				appendExcelData(file, columnNames, data, chooseField);
			} else { // 重新开始新建该文件
				if (file.createNewFile()) { // 当且仅当不存在具有此抽象路径名指定的名称的文件时，原子地创建由此抽象路径名指定的一个新的空文件。
					createExcelData(file, columnNames, data, chooseField);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取Excel中的所有文件
	 * 
	 * @param file
	 *            用户已经选择的Excel文件
	 * @return
	 */
	public Vector<AutoInsurance> readExcel(File file) {
		Vector<AutoInsurance> autoInsurances = new Vector<AutoInsurance>();
		try {
			// 获得工作簿对象
			Workbook workbook = Workbook.getWorkbook(file);
			// 获得所有工作表
			Sheet[] sheets = workbook.getSheets();
			// 遍历工作表
			if (sheets != null) {
				Vector<String> existFields = new Vector<String>(); // 当前所要读取的工作表中的已有的那些字段
				for (Sheet sheet : sheets) {
					existFields.clear();
					for (int row = 0; row < sheet.getRows(); row++) { // 获得行数：sheet.getRows()
						AutoInsurance autoInsurance = new AutoInsurance();
						for (int col = 0; col < sheet.getColumns(); col++) { // 获得列数：sheet.getColumns()
							// 读取数据
							if (row == 0) {
								existFields.add(sheet.getCell(col, row).getContents().trim());
							} else if (col < existFields.size()) {
								if (existFields.get(col).equals("车牌号")) {
									autoInsurance.setPlateNumber(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("车辆使用性质")) {
									autoInsurance.setAutoUse(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("品牌")) {
									autoInsurance.setBrand(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("型号")) {
									autoInsurance.setModel(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("车架号")) {
									autoInsurance.setVin(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("发动机号")) {
									autoInsurance.setEngineNumber(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("初登日期")) {
									autoInsurance.setDebutDate(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("姓名")) {
									autoInsurance.setName(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("身份证号")) {
									autoInsurance.setNumber(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("电话号码")) {
									autoInsurance.setPhone(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("地址")) {
									autoInsurance.setAddress(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("保险到期日")) {
									autoInsurance.setDueDate(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("科室")) {
									autoInsurance.setDepartment(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("经办人")) {
									autoInsurance.setOperator(sheet.getCell(col, row).getContents().trim());
								} else if (existFields.get(col).equals("备注")) {
									autoInsurance.setRemark(sheet.getCell(col, row).getContents().trim());
								}
							}
						}
						if (row != 0) {
							autoInsurances.add(autoInsurance);
						}
					}
				}
			}
			workbook.close(); // 关闭文件
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(autoInsurances.toString());
		return autoInsurances;
	}

	/**
	 * 给已有的Excel中添加（附加）数据
	 *
	 * @param context
	 * @param file
	 *            Excel文件
	 * @param title
	 *            Excel：sheet的头部标题
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 */
	private void createExcelData(File file, Vector<String> columnNames, Vector<AutoInsurance> data,
			Vector<Boolean> chooseField) {
		try {
			sheetCount = 1;
			// 创建Excel工作表
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(file);
			createSheet(writableWorkbook, columnNames, data, chooseField);
			writableWorkbook.write(); // 写入数据
			writableWorkbook.close(); // 关闭文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给已有的Excel中添加（附加）数据
	 *
	 * @param context
	 * @param file
	 *            Excel文件
	 * @param title
	 *            Excel：sheet的头部标题
	 * @throws IOException
	 * @throws BiffException
	 * @throws WriteException
	 */
	private void appendExcelData(File file, Vector<String> columnNames, Vector<AutoInsurance> data,
			Vector<Boolean> chooseField) {
		try {
			Vector<AutoInsurance> cache = new Vector<AutoInsurance>();
			sheetCount = 1;
			// 创建只读的 Excel 工作薄的对象副本
			Workbook workbook = Workbook.getWorkbook(file);
			// 创建真实写入的 Excel 工作薄对象
			WritableWorkbook writableWorkbook = Workbook.createWorkbook(file, workbook);
			// 修改文本内容：例修改sheet2中cell B3的label内容
			WritableSheet sheet = writableWorkbook.getSheet(0);
			int rows = sheet.getRows();
			if (data != null) {
				for (int i = 0; i < data.size(); i++) {
					if ((i + rows) > 60000) {
						data.removeAll(cache);
						sheetCount++;
						createSheet(writableWorkbook, columnNames, data, chooseField);
					} else {
						if (i + rows > 0) {
							sheet.setRowView(i + rows, 500); // 将行高度设为500
							AutoInsurance autoInsurance = data.get(i);
							unselectedFieldCount = 0; // 没有选择的字段计数
							if (chooseField.get(0)) {
								sheet.addCell(new Label(0 - unselectedFieldCount, i + rows,
										autoInsurance.getPlateNumber(), getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(1)) {
								sheet.addCell(new Label(1 - unselectedFieldCount, i + rows, autoInsurance.getAutoUse(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(2)) {
								sheet.addCell(new Label(2 - unselectedFieldCount, i + rows, autoInsurance.getBrand(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(3)) {
								sheet.addCell(new Label(3 - unselectedFieldCount, i + rows, autoInsurance.getModel(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(4)) {
								sheet.addCell(new Label(4 - unselectedFieldCount, i + rows, autoInsurance.getVin(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(5)) {
								sheet.addCell(new Label(5 - unselectedFieldCount, i + rows,
										autoInsurance.getEngineNumber(), getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(6)) {
								sheet.addCell(new Label(6 - unselectedFieldCount, i + rows,
										autoInsurance.getDebutDate(), getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(7)) {
								sheet.addCell(new Label(7 - unselectedFieldCount, i + rows, autoInsurance.getName(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(8)) {
								sheet.addCell(new Label(8 - unselectedFieldCount, i + rows, autoInsurance.getNumber(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(9)) {
								sheet.addCell(new Label(9 - unselectedFieldCount, i + rows, autoInsurance.getPhone(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(10)) {
								sheet.addCell(new Label(10 - unselectedFieldCount, i + rows, autoInsurance.getAddress(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(11)) {
								sheet.addCell(new Label(11 - unselectedFieldCount, i + rows, autoInsurance.getDueDate(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(12)) {
								sheet.addCell(new Label(12 - unselectedFieldCount, i + rows,
										autoInsurance.getDepartment(), getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(13)) {
								sheet.addCell(new Label(13 - unselectedFieldCount, i + rows,
										autoInsurance.getOperator(), getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							if (chooseField.get(14)) {
								sheet.addCell(new Label(14 - unselectedFieldCount, i + rows, autoInsurance.getRemark(),
										getCellFormat()));
							} else {
								++unselectedFieldCount;
							}
							cache.add(autoInsurance);
						} else {
							sheet.setRowView(0, 500); // 将第一行的高度设为500
							WritableFont writableFont = new WritableFont(WritableFont.createFont("楷体"), 12);// 定义字体
							WritableCellFormat titleFormat = new WritableCellFormat(writableFont);
							titleFormat.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
							titleFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
							titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GRAY_25);// 灰色边框
							titleFormat.setBackground(Colour.WHITE);// 白色背景
							unselectedFieldCount = 0; // 没有选择的字段计数
							for (int j = 0; j < columnNames.size() - 2; j++) {
								// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
								// 在Label对象的子对象中指明单元格的位置和内容
								// 将定义好的单元格添加到工作表中
								if (chooseField.get(j)) {
									sheet.setColumnView(j - unselectedFieldCount, columnNames.get(j).length() * 5);
									sheet.addCell(
											new Label(j - unselectedFieldCount, 0, columnNames.get(j), titleFormat));
								} else {
									++unselectedFieldCount;
								}
								// 设置冻结单元格，下拉时第一行固定
								sheet.getSettings().setVerticalFreeze(1);
							}
						}
					}
				}
			}
			writableWorkbook.write(); // 写入数据
			writableWorkbook.close(); // 关闭文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建工作表，并向工作表中插入数据
	 * 
	 * @param writableWorkbook
	 * @param columnNames
	 * @param data
	 * @throws WriteException
	 */
	private void createSheet(WritableWorkbook writableWorkbook, Vector<String> columnNames, Vector<AutoInsurance> data,
			Vector<Boolean> chooseField) throws WriteException {
		Vector<AutoInsurance> cache = new Vector<AutoInsurance>();
		// 添加第一个工作表并设置第一个Sheet的名字
		WritableSheet sheet = writableWorkbook.createSheet(sheetName + sheetCount, sheetCount);
		sheet.setRowView(0, 500); // 将第一行的高度设为500
		WritableFont writableFont = new WritableFont(WritableFont.createFont("楷体"), 12);// 定义字体
		WritableCellFormat titleFormat = new WritableCellFormat(writableFont);
		titleFormat.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
		titleFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
		titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GRAY_25);// 灰色边框
		titleFormat.setBackground(Colour.WHITE);// 白色背景
		unselectedFieldCount = 0; // 没有选择的字段计数
		for (int i = 0; i < columnNames.size() - 2; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			// 将定义好的单元格添加到工作表中
			if (chooseField.get(i)) {
				sheet.setColumnView(i - unselectedFieldCount, columnNames.get(i).length() * 5);
				sheet.addCell(new Label(i - unselectedFieldCount, 0, columnNames.get(i), titleFormat));
			} else {
				++unselectedFieldCount;
			}
			// 设置冻结单元格，下拉时第一行固定
			sheet.getSettings().setVerticalFreeze(1);
		}
		if (data != null) {
			int rows = sheet.getRows();
			for (int i = 0; i < data.size(); i++) {
				if ((i + rows) > 60000) {
					data.removeAll(cache);
					sheetCount++;
					createSheet(writableWorkbook, columnNames, data, chooseField);
				} else {
					sheet.setRowView(i + rows, 500); // 将行高度设为500
					AutoInsurance autoInsurance = data.get(i);
					unselectedFieldCount = 0; // 没有选择的字段计数
					if (chooseField.get(0)) {
						sheet.addCell(new Label(0 - unselectedFieldCount, i + rows, autoInsurance.getPlateNumber(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(1)) {
						sheet.addCell(new Label(1 - unselectedFieldCount, i + rows, autoInsurance.getAutoUse(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(2)) {
						sheet.addCell(new Label(2 - unselectedFieldCount, i + rows, autoInsurance.getBrand(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(3)) {
						sheet.addCell(new Label(3 - unselectedFieldCount, i + rows, autoInsurance.getModel(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(4)) {
						sheet.addCell(
								new Label(4 - unselectedFieldCount, i + rows, autoInsurance.getVin(), getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(5)) {
						sheet.addCell(new Label(5 - unselectedFieldCount, i + rows, autoInsurance.getEngineNumber(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(6)) {
						sheet.addCell(new Label(6 - unselectedFieldCount, i + rows, autoInsurance.getDebutDate(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(7)) {
						sheet.addCell(new Label(7 - unselectedFieldCount, i + rows, autoInsurance.getName(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(8)) {
						sheet.addCell(new Label(8 - unselectedFieldCount, i + rows, autoInsurance.getNumber(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(9)) {
						sheet.addCell(new Label(9 - unselectedFieldCount, i + rows, autoInsurance.getPhone(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(10)) {
						sheet.addCell(new Label(10 - unselectedFieldCount, i + rows, autoInsurance.getAddress(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(11)) {
						sheet.addCell(new Label(11 - unselectedFieldCount, i + rows, autoInsurance.getDueDate(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(12)) {
						sheet.addCell(new Label(12 - unselectedFieldCount, i + rows, autoInsurance.getDepartment(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(13)) {
						sheet.addCell(new Label(13 - unselectedFieldCount, i + rows, autoInsurance.getOperator(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					if (chooseField.get(14)) {
						sheet.addCell(new Label(14 - unselectedFieldCount, i + rows, autoInsurance.getRemark(),
								getCellFormat()));
					} else {
						++unselectedFieldCount;
					}
					cache.add(autoInsurance);
				}
			}
		}
	}

	/**
	 * 获取每个单元格的可写的单元格格式
	 * 
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat getCellFormat() throws WriteException {
		WritableFont writableFont = new WritableFont(WritableFont.createFont("楷体"), 12);// 定义字体
		WritableCellFormat cellFormat = new WritableCellFormat(writableFont);
		cellFormat.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
		cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.GRAY_80);// 灰色边框
		cellFormat.setBackground(Colour.WHITE);// 白色背景
		return cellFormat;
	}
}
