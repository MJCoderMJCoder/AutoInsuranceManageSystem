package com.lzf.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.util.Locale;

import javax.swing.JFrame;

import com.lzf.dao.User;
import com.lzf.util.DateTimeUtil;
import com.lzf.util.EncryptUtil;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 登录界面
 * 
 * @author MJCoder
 *
 */
public class Login {

	private JFrame jFrame;
	private JPanel accountPanel;
	private JLabel accountLabel;
	private JTextField accountText;
	private JLabel accountHint;
	private JPanel passwordPanel;
	private JLabel passwordLabel;
	private JLabel passwordHint;
	private JButton loginButton;
	private JPanel loginPanel;
	private JPanel account;
	private JPanel password;
	private JPasswordField passwordField;

	private User userDao = new User(); // 与用户操作相关的数据访问层

	private com.lzf.bean.User userBean = null; // 用户实体类

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					// UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Login window = new Login();
					window.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		userDao.insert(new com.lzf.bean.User("纸纷飞", "admin", "6003", EncryptUtil.encrypt("root"), "软件部",
				DateTimeUtil.getCurrentTime(), "超级管理员", null, "程序员"));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame();
		jFrame.getContentPane().setLocale(Locale.SIMPLIFIED_CHINESE);
		jFrame.getContentPane().setForeground(Color.BLACK);
		jFrame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 16));
		jFrame.getContentPane().setBackground(Color.WHITE);
		jFrame.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));

		account = new JPanel();
		FlowLayout flowLayout = (FlowLayout) account.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(50);
		account.setFont(new Font("楷体", Font.PLAIN, 16));
		account.setForeground(Color.BLACK);
		account.setBackground(Color.WHITE);
		jFrame.getContentPane().add(account);

		accountPanel = new JPanel();
		account.add(accountPanel);
		accountPanel.setForeground(Color.BLACK);
		accountPanel.setFont(new Font("楷体", Font.PLAIN, 16));
		accountPanel.setBackground(Color.WHITE);
		accountPanel.setLayout(new GridLayout(1, 3, 10, 0));

		accountLabel = new JLabel("账号：");
		accountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		accountLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		accountLabel.setForeground(Color.BLACK);
		accountLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		accountLabel.setBackground(Color.WHITE);
		accountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountPanel.add(accountLabel);

		accountText = new JTextField();
		accountText.setText("身份证号");
		accountText.setForeground(Color.GRAY);
		accountText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				accountVerify();
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (accountText.getForeground() == Color.GRAY) {
					accountText.setText("");
				}
				accountText.setForeground(Color.BLACK);
			}
		});
		accountPanel.add(accountText);
		accountText.setHorizontalAlignment(SwingConstants.LEFT);
		accountText.setFont(new Font("楷体", Font.PLAIN, 15));
		accountText.setToolTipText("身份证号");
		accountLabel.setLabelFor(accountText);
		accountText.setBackground(Color.WHITE);
		accountText.setColumns(24);

		accountHint = new JLabel("     *");
		accountHint.setLabelFor(accountText);
		accountHint.setHorizontalTextPosition(SwingConstants.CENTER);
		accountHint.setHorizontalAlignment(SwingConstants.LEFT);
		accountHint.setForeground(Color.RED);
		accountHint.setFont(new Font("楷体", Font.PLAIN, 14));
		accountHint.setBackground(Color.WHITE);
		accountHint.setAlignmentX(Component.CENTER_ALIGNMENT);
		accountPanel.add(accountHint);

		password = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) password.getLayout();
		flowLayout_1.setHgap(0);
		flowLayout_1.setVgap(25);
		password.setForeground(Color.BLACK);
		password.setFont(new Font("楷体", Font.PLAIN, 16));
		password.setBackground(Color.WHITE);
		jFrame.getContentPane().add(password);

		passwordPanel = new JPanel();
		password.add(passwordPanel);
		passwordPanel.setForeground(Color.BLACK);
		passwordPanel.setFont(new Font("楷体", Font.PLAIN, 16));
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.setLayout(new GridLayout(1, 3, 10, 0));

		passwordLabel = new JLabel("密码：");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		passwordLabel.setForeground(Color.BLACK);
		passwordLabel.setFont(new Font("楷体", Font.PLAIN, 16));
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				passwordVerify();
			}
		});
		passwordLabel.setLabelFor(passwordField);
		passwordField.setEchoChar('*');
		passwordField.setColumns(24);
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("楷体", Font.PLAIN, 15));
		passwordField.setBackground(Color.WHITE);
		passwordPanel.add(passwordField);

		passwordHint = new JLabel("     *");
		passwordHint.setLabelFor(passwordField);
		passwordHint.setHorizontalTextPosition(SwingConstants.CENTER);
		passwordHint.setHorizontalAlignment(SwingConstants.LEFT);
		passwordHint.setForeground(Color.RED);
		passwordHint.setFont(new Font("楷体", Font.PLAIN, 14));
		passwordHint.setBackground(Color.WHITE);
		passwordHint.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordPanel.add(passwordHint);

		loginPanel = new JPanel();
		loginPanel.setForeground(Color.BLACK);
		loginPanel.setFont(new Font("楷体", Font.PLAIN, 16));
		loginPanel.setBackground(Color.WHITE);
		jFrame.getContentPane().add(loginPanel);
		loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

		loginButton = new JButton("    登        录    ");
		loginPanel.add(loginButton);
		loginButton.setForeground(Color.BLACK);
		loginButton.setFont(new Font("楷体", Font.PLAIN, 16));
		loginButton.setBackground(Color.WHITE);
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountVerify();
				passwordVerify();
				if (userBean != null) { // 登录成功
					new Home(userBean);
					jFrame.setVisible(false);
					backup();
				}
			}
		});

		jFrame.setType(Type.UTILITY);
		jFrame.setLocale(Locale.SIMPLIFIED_CHINESE);
		jFrame.setTitle("纸纷飞");
		jFrame.setResizable(false);
		// jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/com/lzf/image/zff.jpg")));
		jFrame.setForeground(Color.WHITE);
		jFrame.setBackground(Color.WHITE);
		jFrame.setAlwaysOnTop(true);
		jFrame.setBounds(100, 100, 700, 400);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 获取屏幕的尺寸
		int x = (int) (screenSize.getWidth() - jFrame.getWidth()) / 2;
		int y = (int) (screenSize.getHeight() - jFrame.getHeight()) / 2;
		jFrame.setLocation(x, y);
	}

	/**
	 * 账号验证
	 */
	private void accountVerify() {
		if (accountText.getForeground() == Color.GRAY) {
			accountText.requestFocus();
			accountHint.setText(" 请输入账号");
		} else {
			String account = accountText.getText().trim();
			if (account.equals("") || account == null) {
				accountHint.setText(" 请输入账号");
				accountText.setText("身份证号");
				accountText.setForeground(Color.GRAY);
			} else {
				userBean = userDao.select(account);
				if (userBean == null) {
					accountHint.setText(" 账号不正确");
				} else {
					accountHint.setText("     *");
				}
			}
		}
	}

	/**
	 * 密码验证
	 */
	private void passwordVerify() {
		String password = passwordField.getText().trim();
		if (userBean != null) {
			if (password.equals("") || password == null) {
				userBean = null;
				passwordHint.setText(" 请输入密码");
			} else {
				if (userBean.getPassword().equals(EncryptUtil.encrypt(password))) {
					passwordHint.setText("     *");
					String token = EncryptUtil.encrypt(userBean.getId() + userBean.getNumber() + userBean.getContact()
							+ userBean.getPassword() + System.currentTimeMillis());
					userBean.setToken(token);
					userBean = userDao.update(userBean);
				} else {
					userBean = null;
					passwordHint.setText(" 密码不正确");
				}
			}
		} else {
			accountText.requestFocus();
		}
	}

	/**
	 * 备份功能
	 */
	private void backup() {
		new Thread() {
			public void run() {
				FileInputStream fis = null;
				FileOutputStream fos = null;
				try {
					File file = new File(System.getProperties().getProperty("user.home"), "纸纷飞");
					file.mkdirs();
					file.setExecutable(true, true);
					file.setReadable(true, true);
					file.setWritable(true, true);
					File backup = new File(file,
							new SimpleDateFormat("yyyy年MM月dd日HH点mm分ss秒").format(System.currentTimeMillis()) + ".db");
					backup.createNewFile();
					// System.out.println(backup);

					// 找到文件的路径 /data/data/包名/databases/数据库名称
					File dbFile = new File("AutoInsuranceManageSystem.db");
					// System.out.println(dbFile.getAbsolutePath());
					// 文件复制到sd卡中
					fis = new FileInputStream(dbFile);
					fos = new FileOutputStream(backup);
					int len = 0;
					byte[] buffer = new byte[204800];
					while (-1 != (len = fis.read(buffer))) {
						fos.write(buffer, 0, len);
					}
					fos.flush();
					backup.setReadOnly();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 关闭数据流
					try {
						if (fos != null)
							fos.close();
						if (fis != null)
							fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			};
		}.start();
	}
}
