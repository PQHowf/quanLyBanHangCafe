package CafeView;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CafeController.controllerDangKy;
import database.JDBCUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;

public class viewDangKy extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tF_tenTaiKhoan;
	private JTextField tF_matKhau;
	private ButtonGroup btnGroup;
	private ImageIcon icon_x, icon_ok;

	public viewDangKy() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewTrangChu.class.getResource("/icon/logo.png")));
		setTitle("QUẢN LÝ BÁN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ActionListener action = new controllerDangKy(this);

		JLabel lb_dangKyTaiKhoan = new JLabel(" ĐĂNG KÝ TÀI KHOẢN");
		lb_dangKyTaiKhoan.setIcon(new ImageIcon(viewDangKy.class.getResource("/icon/dangKy.png")));
		lb_dangKyTaiKhoan.setFont(new Font("Arial", Font.BOLD, 18));
		lb_dangKyTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dangKyTaiKhoan.setBounds(10, 11, 487, 56);
		contentPane.add(lb_dangKyTaiKhoan);

		JLabel lb_tenTaiKhoan = new JLabel("TÊN TÀI KHOẢN:");
		lb_tenTaiKhoan.setFont(new Font("Arial", Font.BOLD, 14));
		lb_tenTaiKhoan.setBounds(10, 80, 118, 34);
		contentPane.add(lb_tenTaiKhoan);

		JLabel lb_matKhau = new JLabel("MẬT KHẨU:");
		lb_matKhau.setFont(new Font("Arial", Font.BOLD, 14));
		lb_matKhau.setBounds(10, 140, 118, 34);
		contentPane.add(lb_matKhau);

		JLabel lb_chucVu = new JLabel("CHỨC VỤ");
		lb_chucVu.setFont(new Font("Arial", Font.BOLD, 14));
		lb_chucVu.setBounds(10, 200, 118, 34);
		contentPane.add(lb_chucVu);

		tF_tenTaiKhoan = new JTextField();
		tF_tenTaiKhoan.setBounds(166, 78, 223, 36);
		contentPane.add(tF_tenTaiKhoan);
		tF_tenTaiKhoan.setColumns(10);

		tF_matKhau = new JTextField();
		tF_matKhau.setColumns(10);
		tF_matKhau.setBounds(166, 138, 223, 36);
		contentPane.add(tF_matKhau);

		JButton btn_xacNhan = new JButton("XÁC NHẬN");
		btn_xacNhan.setBackground(SystemColor.controlHighlight);
		btn_xacNhan.setFont(new Font("Arial", Font.BOLD, 14));
		btn_xacNhan.setBounds(73, 276, 157, 56);
		btn_xacNhan.addActionListener(action);
		contentPane.add(btn_xacNhan);

		JButton btn_huyBo = new JButton("HUỶ BỎ");
		btn_huyBo.setBackground(SystemColor.controlHighlight);
		btn_huyBo.setFont(new Font("Arial", Font.BOLD, 14));
		btn_huyBo.setBounds(272, 276, 157, 56);
		btn_huyBo.addActionListener(action);
		contentPane.add(btn_huyBo);

		JRadioButton rdbtn_quanLy = new JRadioButton("QUẢN LÝ");
		rdbtn_quanLy.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtn_quanLy.setBounds(166, 207, 109, 23);
		rdbtn_quanLy.setActionCommand("Quản lý");
		contentPane.add(rdbtn_quanLy);

		JRadioButton rdbtn_nhanVien = new JRadioButton("NHÂN VIÊN");
		rdbtn_nhanVien.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtn_nhanVien.setBounds(280, 207, 109, 23);
		rdbtn_nhanVien.setActionCommand("Nhân viên");
		contentPane.add(rdbtn_nhanVien);

		btnGroup = new ButtonGroup();
		btnGroup.add(rdbtn_nhanVien);
		btnGroup.add(rdbtn_quanLy);

		icon_x = new ImageIcon(viewTrangChu.class.getResource("/icon/x.png"));
		icon_ok = new ImageIcon(viewTrangChu.class.getResource("/icon/icon_ok.png"));
	}

	public void XacNhanDangKy() {
		String tenTaiKhoan = tF_tenTaiKhoan.getText();
		String matKhau = tF_matKhau.getText();
		String rdbtn_value = btnGroup.getSelection().getActionCommand();
		if ((tenTaiKhoan.isEmpty() || matKhau.isEmpty()) || (tenTaiKhoan.isEmpty() && matKhau.isEmpty())) {
			JOptionPane.showMessageDialog(this, "TÀI KHOẢN HOẶC MẬT KHẨU KHÔNG ĐƯỢC ĐỂ TRỐNG!", "THÔNG BÁO!",
					JOptionPane.INFORMATION_MESSAGE, icon_x);
		} else if (btnGroup.getSelection() == null) {
			JOptionPane.showMessageDialog(this, "PHẢI CHỌN CHỨC VỤ CỦA TÀI KHOẢN!", "THÔNG BÁO!",
					JOptionPane.INFORMATION_MESSAGE, icon_x);
		} else {
			String query = "SELECT EXISTS (SELECT 1 FROM taikhoan WHERE tenTaiKhoan = '" + tenTaiKhoan + "')";
			try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
				while (rs.next()) {
					boolean exists = rs.getBoolean(1);
					if (exists) {
						JOptionPane.showMessageDialog(this, "TÀI KHOẢN ĐÃ TỒN TẠI TRONG HỆ THỐNG!", "THÔNG BÁO!",
								JOptionPane.INFORMATION_MESSAGE, icon_x);
					} else {
						String insert = "INSERT INTO taikhoan VALUES ('"+tenTaiKhoan.trim()+"', '"+ matKhau+"', '"+rdbtn_value+"')";
						try (Connection cn = JDBCUtil.getConnection();
								PreparedStatement stmt = cn.prepareStatement(insert)) {
							int rowsInserted = stmt.executeUpdate();
							if (rowsInserted > 0) {
								JOptionPane.showMessageDialog(this, "ĐĂNG KÝ TÀI KHOẢN THÀNH CÔNG!", "THÔNG BÁO!",
										JOptionPane.INFORMATION_MESSAGE, icon_ok);
								while(viewTaiKhoan.modelTable.getRowCount()!=0) {
									viewTaiKhoan.modelTable.removeRow(0);
								}
								truyenCSDL();
								this.setVisible(false);
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this, "LỖI!", "THÔNG BÁO!", JOptionPane.INFORMATION_MESSAGE,
									icon_x);
						}
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "LỖI!", "THÔNG BÁO!", JOptionPane.INFORMATION_MESSAGE, icon_x);
			}
		}
	}

	private void truyenCSDL() {
		String query = "SELECT tenTaiKhoan, matKhau, chucVu from taiKhoan";
		try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while (rs.next()) {
				String tenTaiKhoan = rs.getString("tenTaiKhoan");
				String matKhau = rs.getString("matKhau");
				String chucVu = rs.getString("chucVu");
				viewTaiKhoan.modelTable.addRow(new Object[] { tenTaiKhoan, matKhau, chucVu });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "KHÔNG THỂ KẾT NỐI CSDL!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE,
					icon_x);
		}
	}
}
