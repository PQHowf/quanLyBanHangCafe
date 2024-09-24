package CafeView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import CafeController.controllerDangNhap;
import database.JDBCUtil;
import java.awt.SystemColor;

public class viewDangNhap extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tF_tenTaiKhoan;
	private JPasswordField tF_matKhau;
	private ImageIcon hienIcon;
	private ImageIcon anIcon;
	private JButton btn_hienMatKhau;
	private boolean showPassword;
	private ImageIcon icon_x;
	private ImageIcon icon_ok;
	private viewTrangChu view_TrangChu;
	public static String tenTaiKhoan;

	public viewDangNhap() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewTrangChu.class.getResource("/icon/logo.png")));
		setTitle("QUẢN LÝ BÁN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1851, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pn_dangNhap = new JPanel();
		pn_dangNhap.setBackground(SystemColor.activeCaption);
		pn_dangNhap.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		pn_dangNhap.setBounds(347, 194, 497, 360);
		contentPane.add(pn_dangNhap);
		pn_dangNhap.setLayout(null);

		int x = (this.getWidth() - pn_dangNhap.getWidth()) / 2;
		int y = (this.getHeight() - pn_dangNhap.getHeight()) / 2;
		pn_dangNhap.setLocation(x, y);

		ActionListener action = new controllerDangNhap(this);

		JLabel lb_tenTaiKhoan = new JLabel("TÊN TÀI KHOẢN:");
		lb_tenTaiKhoan.setFont(new Font("Arial", Font.BOLD, 14));
		lb_tenTaiKhoan.setBounds(25, 88, 123, 47);
		pn_dangNhap.add(lb_tenTaiKhoan);

		JLabel lb_matKhau = new JLabel("MẬT KHẨU:");
		lb_matKhau.setFont(new Font("Arial", Font.BOLD, 14));
		lb_matKhau.setBounds(25, 146, 123, 47);
		pn_dangNhap.add(lb_matKhau);

		tF_tenTaiKhoan = new JTextField();
		tF_tenTaiKhoan.setBounds(174, 88, 282, 47);
		pn_dangNhap.add(tF_tenTaiKhoan);
		tF_tenTaiKhoan.setColumns(10);

		tF_matKhau = new JPasswordField();
		tF_matKhau.setColumns(10);
		tF_matKhau.setBounds(174, 146, 282, 47);
		pn_dangNhap.add(tF_matKhau);

		JButton btn_dangNhap = new JButton("ĐĂNG NHẬP");
		btn_dangNhap.setBackground(new Color(255, 255, 255));
		btn_dangNhap.setFont(new Font("Arial", Font.BOLD, 14));
		btn_dangNhap.setBounds(262, 234, 193, 47);
		btn_dangNhap.addActionListener(action);
		pn_dangNhap.add(btn_dangNhap);

		hienIcon = new ImageIcon(viewDangNhap.class.getResource("/icon/hienMatKhau.png"));
		anIcon = new ImageIcon(viewDangNhap.class.getResource("/icon/anMatKhau.png"));
		btn_hienMatKhau = new JButton(anIcon);
		btn_hienMatKhau.setBounds(458, 159, 29, 23);
		btn_hienMatKhau.addActionListener(action);
		pn_dangNhap.add(btn_hienMatKhau);
		btn_hienMatKhau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPassword = !showPassword;
				if (showPassword) {
					tF_matKhau.setEchoChar((char) 0); // Hiển thị mật khẩu
					btn_hienMatKhau.setIcon(hienIcon);
				} else {
					tF_matKhau.setEchoChar('•'); // Ẩn mật khẩu
					btn_hienMatKhau.setIcon(anIcon);
				}
			}
		});

		JPanel pn_logo = new JPanel();
		pn_logo.setBackground(SystemColor.activeCaption);
		pn_logo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		pn_logo.setBounds(677, 63, 497, 127);
		contentPane.add(pn_logo);
		pn_logo.setLayout(null);

		JLabel lb_logo = new JLabel("  QUẢN LÝ BÁN HÀNG CAFE");
		lb_logo.setBounds(2, 2, 493, 123);
		pn_logo.add(lb_logo);
		lb_logo.setIcon(new ImageIcon(viewDangNhap.class.getResource("/icon/banHangCafe.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setFont(new Font("Arial", Font.BOLD, 20));

		icon_x = new ImageIcon(viewDangNhap.class.getResource("/icon/x.png"));
		icon_ok = new ImageIcon(viewDangNhap.class.getResource("/icon/icon_ok.png"));

		this.setVisible(true);

		// Khởi tạo View_trangChu
		view_TrangChu = new viewTrangChu();
		view_TrangChu.setVisible(false);
	}

	public String dangNhap() {
		String TaiKhoan = tF_tenTaiKhoan.getText();
		String matKhau = tF_matKhau.getText();
		String query = "SELECT EXISTS (SELECT 1 FROM taikhoan WHERE tenTaiKhoan = '" + TaiKhoan + "' AND matKhau = '"
				+ matKhau + "')";
		try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while (rs.next()) {
				boolean exists = rs.getBoolean(1);
				if ((TaiKhoan.isEmpty() || matKhau.isEmpty())
						&& (TaiKhoan.isEmpty() && matKhau.isEmpty())) {
					JOptionPane.showMessageDialog(this, "TÀI KHOẢN HOẶC MẬT KHẨU KHÔNG ĐƯỢC ĐỂ TRỐNG!", "THÔNG BÁO!",
							JOptionPane.INFORMATION_MESSAGE, icon_x);
				} else if (exists) {
					tenTaiKhoan = TaiKhoan;
					JOptionPane.showMessageDialog(this, "ĐĂNG NHẬP THÀNH CÔNG!", "THÔNG BÁO",
							JOptionPane.INFORMATION_MESSAGE, icon_ok);
					this.setVisible(false);
					view_TrangChu.setVisible(true);
					return tenTaiKhoan;
				} else {
					JOptionPane.showMessageDialog(this, "TÀI KHOẢN HOẶC MẬT KHẨU SAI!", "THÔNG BÁO",
							JOptionPane.INFORMATION_MESSAGE, icon_x);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
