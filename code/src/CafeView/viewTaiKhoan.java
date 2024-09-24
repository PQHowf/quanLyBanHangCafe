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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CafeController.controllerTaiKhoan;
import database.JDBCUtil;

import javax.swing.JButton;
import java.awt.SystemColor;

public class viewTaiKhoan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	public static DefaultTableModel modelTable;
	private ImageIcon icon_x;
	public static String tenTaiKhoan;

	public viewTaiKhoan() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewTrangChu.class.getResource("/icon/logo.png")));
		setTitle("QUẢN LÝ BÁN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ActionListener controller = new controllerTaiKhoan(this);

		JLabel lb_thongTinTaiKhoan = new JLabel(" THÔNG TIN TÀI KHOẢN");
		lb_thongTinTaiKhoan.setIcon(new ImageIcon(viewTaiKhoan.class.getResource("/icon/dangKy.png")));
		lb_thongTinTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lb_thongTinTaiKhoan.setFont(new Font("Arial", Font.BOLD, 18));
		lb_thongTinTaiKhoan.setBounds(10, 11, 654, 57);
		contentPane.add(lb_thongTinTaiKhoan);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "TÊN TÀI KHOẢN", "MẬT KHẨU", "CHỨC VỤ" }));
		table.setFont(new Font("Arial", Font.BOLD, 14));
		table.setRowHeight(35);
		modelTable = (DefaultTableModel) table.getModel();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 79, 654, 323);
		contentPane.add(scrollPane);

		JButton btn_dangKy = new JButton("ĐĂNG KÝ TÀI KHOẢN");
		btn_dangKy.setBackground(SystemColor.controlHighlight);
		btn_dangKy.setFont(new Font("Arial", Font.BOLD, 14));
		btn_dangKy.setBounds(10, 450, 214, 49);
		btn_dangKy.addActionListener(controller);
		contentPane.add(btn_dangKy);

		JButton btn_thayDoiThongTin = new JButton("THAY ĐỔI THÔNG TIN");
		btn_thayDoiThongTin.setBackground(SystemColor.controlHighlight);
		btn_thayDoiThongTin.setFont(new Font("Arial", Font.BOLD, 14));
		btn_thayDoiThongTin.setBounds(234, 450, 210, 49);
		btn_thayDoiThongTin.addActionListener(controller);
		contentPane.add(btn_thayDoiThongTin);
		
		JButton btn_xoaTaiKhoan = new JButton("XOÁ TÀI KHOẢN");
		btn_xoaTaiKhoan.setFont(new Font("Arial", Font.BOLD, 14));
		btn_xoaTaiKhoan.setBackground(SystemColor.controlHighlight);
		btn_xoaTaiKhoan.setBounds(454, 450, 210, 49);
		btn_xoaTaiKhoan.addActionListener(controller);
		contentPane.add(btn_xoaTaiKhoan);

		truyenCSDL();

		icon_x = new ImageIcon(viewTrangChu.class.getResource("/icon/x.png"));
	}

	public void truyenCSDL() {
		String query = "SELECT tenTaiKhoan, matKhau, chucVu from taiKhoan";
		try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while (rs.next()) {
				String tenTaiKhoan = rs.getString("tenTaiKhoan");
				String matKhau = rs.getString("matKhau");
				String chucVu = rs.getString("chucVu");
				modelTable.addRow(new Object[] { tenTaiKhoan, matKhau, chucVu });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "KHÔNG THỂ KẾT NỐI CSDL!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE,
					icon_x);
		}
	}

	public void dangKy() {
		viewDangKy view_dangKy = new viewDangKy();
		view_dangKy.setVisible(true);
		view_dangKy.setLocationRelativeTo(contentPane);
	}

	public void thayDoiThongTin() {
		int row = table.getSelectedRow();
		if(row != -1) {
			int luaChon = JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN THAY ĐỔI THÔNG TIN TÀI KHOẢN ĐÃ CHỌN KHÔNG?", "THÔNG BÁO!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(luaChon==JOptionPane.YES_OPTION) {
				tenTaiKhoan = (String) table.getValueAt(row, 0);
				String matKhau = (String) table.getValueAt(row, 1);
				String chucVu = (String) table.getValueAt(row, 2);
				viewThayDoiTaiKhoan view_thayDoiTaiKhoan = new viewThayDoiTaiKhoan(tenTaiKhoan, matKhau, chucVu);
				view_thayDoiTaiKhoan.setVisible(true);
				view_thayDoiTaiKhoan.setLocationRelativeTo(contentPane);
			}
		} else {
			JOptionPane.showMessageDialog(this, "VUI LÒNG CHỌN MỘT TÀI KHOẢN MUỐN THAY ĐỔI THÔNG TIN!", "THÔNG BÁO!",
					JOptionPane.ERROR_MESSAGE, new ImageIcon(viewTrangChu.class.getResource("/icon/x.png")));
		}
	}

	public void xoaTaiKhoan() {
		int row = table.getSelectedRow();
		if(row != -1) {
			int luaChon = JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN XOÁ TÀI KHOẢN ĐÃ CHỌN KHÔNG?", "THÔNG BÁO!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(luaChon==JOptionPane.YES_OPTION) {
				String tenTaiKhoan = (String) modelTable.getValueAt(row, 0);
				String query = "DELETE FROM taikhoan WHERE tenTaiKhoan = '"+tenTaiKhoan+"'";
				try(Connection cn = JDBCUtil.getConnection(); PreparedStatement stmt = cn.prepareStatement(query)) {
					int rowDel = stmt.executeUpdate();
					if(rowDel > 0) {
						JOptionPane.showMessageDialog(this, "XOÁ TÀI KHOẢN THÀNH CÔNG!", "THÔNG BÁO!",
								JOptionPane.ERROR_MESSAGE, new ImageIcon(viewTrangChu.class.getResource("/icon/icon_ok.png")));
						while(modelTable.getRowCount()!=0) {
							modelTable.removeRow(0);
						}
						truyenCSDL();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
