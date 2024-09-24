package CafeView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import CafeController.controllerTrangChu;
import database.JDBCUtil;

import javax.swing.JTextField;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.JDesktopPane;

public class viewTrangChu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane panel_menuChucNang;
	private JTable table_menu;
	private JTextField tF_timKiem;
	private JTable table_hoaDon;
	public static JTextField tF_tongTien;
	public static JTextField tF_soBan;
	public static DefaultTableModel model_table_menu;
	public static DefaultTableModel model_table_hoaDon;
	private ImageIcon icon_x;
	private JSpinner spinner_soLuong;
	private ImageIcon icon_number;
	private JPanel panel_chonBan;

	public viewTrangChu() {
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
		

		panel_menuChucNang = new JDesktopPane();
		panel_menuChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_menuChucNang.setBounds(0, 0, 0, 777);
		contentPane.add(panel_menuChucNang);
		panel_menuChucNang.setLayout(null);

		JLabel lb_themMonVaoMenu = new JLabel("THÊM MÓN VÀO MENU");
		lb_themMonVaoMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkChucVu()) {
					themMonVaoMenu();
				}
				
			}
		});
		lb_themMonVaoMenu.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/themMonVaoMenu.png")));
		lb_themMonVaoMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lb_themMonVaoMenu.setFont(new Font("Arial", Font.BOLD, 14));
		lb_themMonVaoMenu.setBounds(10, 190, 224, 58);
		panel_menuChucNang.add(lb_themMonVaoMenu);

		ActionListener controller = new controllerTrangChu(this);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 170, 224, 2);
		panel_menuChucNang.add(separator);

		JLabel lb_logo = new JLabel("BÁN HÀNG CAFE");
		lb_logo.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/banHangCafe.png")));
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setFont(new Font("Arial", Font.BOLD, 18));
		lb_logo.setBounds(10, 48, 224, 114);
		panel_menuChucNang.add(lb_logo);

		JLabel lb_xoaMonKhoiMenu = new JLabel("XOÁ MÓN KHỎI MENU");
		lb_xoaMonKhoiMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkChucVu()) {
					xoaMonKhoiMenu();
				}
				
			}
		});
		lb_xoaMonKhoiMenu.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/xoaMonKhoiMenu.png")));
		lb_xoaMonKhoiMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lb_xoaMonKhoiMenu.setFont(new Font("Arial", Font.BOLD, 14));
		lb_xoaMonKhoiMenu.setBounds(10, 270, 224, 58);
		panel_menuChucNang.add(lb_xoaMonKhoiMenu);

		JLabel lb_thayDoiGiaTien = new JLabel("THAY ĐỔI GIÁ TIỀN");
		lb_thayDoiGiaTien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (checkChucVu()) {
					thayDoiGiaTien();
				}
				
			}
		});
		lb_thayDoiGiaTien.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/thayDoiGiaTien.png")));
		lb_thayDoiGiaTien.setHorizontalAlignment(SwingConstants.CENTER);
		lb_thayDoiGiaTien.setFont(new Font("Arial", Font.BOLD, 14));
		lb_thayDoiGiaTien.setBounds(10, 350, 224, 58);
		panel_menuChucNang.add(lb_thayDoiGiaTien);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 534, 224, 2);
		panel_menuChucNang.add(separator_1);

		JLabel lb_dangXuat = new JLabel("ĐĂNG XUẤT");
		lb_dangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dangXuat();
			}
		});
		lb_dangXuat.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/dangXuat.png")));
		lb_dangXuat.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dangXuat.setFont(new Font("Arial", Font.BOLD, 14));
		lb_dangXuat.setBounds(10, 676, 224, 71);
		panel_menuChucNang.add(lb_dangXuat);

		JLabel dongMenuChucNang = new JLabel("");
		dongMenuChucNang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dongMenuChucNang();
			}
		});
		dongMenuChucNang.setHorizontalAlignment(SwingConstants.CENTER);
		dongMenuChucNang.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/x_black.png")));
		dongMenuChucNang.setBounds(186, 11, 48, 37);
		panel_menuChucNang.add(dongMenuChucNang);

		JLabel lb_taiKhoan = new JLabel("TÀI KHOẢN");
		lb_taiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				taiKhoan();
			}
		});
		lb_taiKhoan.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/dangKy.png")));
		lb_taiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lb_taiKhoan.setFont(new Font("Arial", Font.BOLD, 14));
		lb_taiKhoan.setBounds(10, 430, 224, 58);
		panel_menuChucNang.add(lb_taiKhoan);

		JLabel lb_moMenu = new JLabel("");
		lb_moMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lb_moMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moMenuChucNang();
			}
		});
		lb_moMenu.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/menu.png")));
		lb_moMenu.setBounds(11, 11, 82, 50);
		contentPane.add(lb_moMenu);

		JLabel lb_QuanLyBanHang = new JLabel("QUẢN LÝ BÁN HÀNG CAFE");
		lb_QuanLyBanHang.setHorizontalAlignment(SwingConstants.CENTER);
		lb_QuanLyBanHang.setFont(new Font("Arial", Font.BOLD, 20));
		lb_QuanLyBanHang.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/banHangCafe.png")));
		lb_QuanLyBanHang.setBounds(103, 11, 1722, 50);
		contentPane.add(lb_QuanLyBanHang);

		panel_chonBan = new JPanel();
		panel_chonBan.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_chonBan.setBounds(10, 78, 234, 668);
		contentPane.add(panel_chonBan);
		panel_chonBan.setLayout(null);

		JLabel lb_chonBan = new JLabel(" CHỌN BÀN");
		lb_chonBan.setBounds(10, 11, 214, 66);
		panel_chonBan.add(lb_chonBan);
		lb_chonBan.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/table.png")));
		lb_chonBan.setHorizontalAlignment(SwingConstants.CENTER);
		lb_chonBan.setFont(new Font("Arial", Font.BOLD, 16));

		JButton btn_ban1 = new JButton("BÀN 1");
		btn_ban1.setBackground(new Color(255, 255, 255));
		btn_ban1.setBounds(20, 88, 90, 80);
		panel_chonBan.add(btn_ban1);
		btn_ban1.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban1.addActionListener(controller);

		JButton btn_ban2 = new JButton("BÀN 2");
		btn_ban2.setBackground(new Color(255, 255, 255));
		btn_ban2.setBounds(122, 88, 90, 80);
		panel_chonBan.add(btn_ban2);
		btn_ban2.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban2.addActionListener(controller);

		JButton btn_ban3 = new JButton("BÀN 3");
		btn_ban3.setBackground(new Color(255, 255, 255));
		btn_ban3.setBounds(21, 179, 90, 80);
		panel_chonBan.add(btn_ban3);
		btn_ban3.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban3.addActionListener(controller);

		JButton btn_ban4 = new JButton("BÀN 4");
		btn_ban4.setBackground(new Color(255, 255, 255));
		btn_ban4.setBounds(122, 179, 90, 80);
		panel_chonBan.add(btn_ban4);
		btn_ban4.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban4.addActionListener(controller);

		JButton btn_ban5 = new JButton("BÀN 5");
		btn_ban5.setBackground(new Color(255, 255, 255));
		btn_ban5.setBounds(21, 270, 90, 80);
		panel_chonBan.add(btn_ban5);
		btn_ban5.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban5.addActionListener(controller);

		JButton btn_ban6 = new JButton("BÀN 6");
		btn_ban6.setBackground(new Color(255, 255, 255));
		btn_ban6.setBounds(122, 270, 90, 80);
		panel_chonBan.add(btn_ban6);
		btn_ban6.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban6.addActionListener(controller);

		JButton btn_ban7 = new JButton("BÀN 7");
		btn_ban7.setBackground(new Color(255, 255, 255));
		btn_ban7.setBounds(21, 361, 90, 80);
		panel_chonBan.add(btn_ban7);
		btn_ban7.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban7.addActionListener(controller);

		JButton btn_ban8 = new JButton("BÀN 8");
		btn_ban8.setBackground(new Color(255, 255, 255));
		btn_ban8.setBounds(122, 361, 90, 80);
		panel_chonBan.add(btn_ban8);
		btn_ban8.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban8.addActionListener(controller);

		JButton btn_ban9 = new JButton("BÀN 9");
		btn_ban9.setBackground(new Color(255, 255, 255));
		btn_ban9.setBounds(21, 452, 90, 80);
		panel_chonBan.add(btn_ban9);
		btn_ban9.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban9.addActionListener(controller);

		JButton btn_ban10 = new JButton("BÀN 10");
		btn_ban10.setBackground(new Color(255, 255, 255));
		btn_ban10.setBounds(122, 452, 90, 80);
		panel_chonBan.add(btn_ban10);
		btn_ban10.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban10.addActionListener(controller);

		JButton btn_ban11 = new JButton("BÀN 11");
		btn_ban11.setBackground(new Color(255, 255, 255));
		btn_ban11.setBounds(21, 543, 90, 80);
		panel_chonBan.add(btn_ban11);
		btn_ban11.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban11.addActionListener(controller);

		JButton btn_ban12 = new JButton("BÀN 12");
		btn_ban12.setBackground(new Color(255, 255, 255));
		btn_ban12.setBounds(122, 543, 90, 80);
		panel_chonBan.add(btn_ban12);
		btn_ban12.setFont(new Font("Arial", Font.BOLD, 14));
		btn_ban12.addActionListener(controller);

		JPanel panel_menu = new JPanel();
		panel_menu.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_menu.setBounds(254, 78, 891, 668);
		contentPane.add(panel_menu);
		panel_menu.setLayout(null);

		JLabel lb_menuCafe = new JLabel(" MENU");
		lb_menuCafe.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/menuCafe.png")));
		lb_menuCafe.setHorizontalAlignment(SwingConstants.CENTER);
		lb_menuCafe.setFont(new Font("Arial", Font.BOLD, 16));
		lb_menuCafe.setBounds(10, 29, 353, 66);
		panel_menu.add(lb_menuCafe);

		table_menu = new JTable();
		table_menu.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_menu.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "MÃ MÓN", "TÊN MÓN", "GIÁ MÓN" }));
		table_menu.setFont(new Font("Arial", Font.BOLD, 14));
		table_menu.setRowHeight(35);
		DefaultTableCellRenderer center_menu = new DefaultTableCellRenderer();
		center_menu.setHorizontalAlignment(JLabel.CENTER);
		table_menu.getColumnModel().getColumn(0).setCellRenderer(center_menu);

		model_table_menu = (DefaultTableModel) table_menu.getModel();

		JScrollPane scrollPane_menu = new JScrollPane(table_menu);
		scrollPane_menu.setBounds(10, 116, 869, 480);
		panel_menu.add(scrollPane_menu);

		JLabel lb_timKiem = new JLabel("NHẬP TÊN MÓN:");
		lb_timKiem.setHorizontalAlignment(SwingConstants.CENTER);
		lb_timKiem.setFont(new Font("Arial", Font.BOLD, 16));
		lb_timKiem.setBounds(373, 29, 187, 66);
		panel_menu.add(lb_timKiem);

		tF_timKiem = new JTextField();
		tF_timKiem.setFont(new Font("Arial", Font.BOLD, 14));
		tF_timKiem.setBounds(552, 42, 187, 44);
		panel_menu.add(tF_timKiem);
		tF_timKiem.setColumns(10);

		JButton btn_timKiem = new JButton("TÌM MÓN");
		btn_timKiem.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/find.png")));
		btn_timKiem.setBackground(new Color(255, 255, 255));
		btn_timKiem.setFont(new Font("Arial", Font.BOLD, 14));
		btn_timKiem.setBounds(749, 13, 130, 40);
		panel_menu.add(btn_timKiem);
		btn_timKiem.addActionListener(controller);

		JButton btn_huyTim = new JButton("HUỶ TÌM");
		btn_huyTim.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/cancelFind.png")));
		btn_huyTim.setFont(new Font("Arial", Font.BOLD, 14));
		btn_huyTim.setBackground(Color.WHITE);
		btn_huyTim.setBounds(749, 65, 130, 40);
		panel_menu.add(btn_huyTim);
		btn_huyTim.addActionListener(controller);

		spinner_soLuong = new JSpinner();
		spinner_soLuong.setBounds(531, 613, 62, 32);
		panel_menu.add(spinner_soLuong);

		JLabel lb_nhapSoLuong = new JLabel("SỐ LƯỢNG: ");
		lb_nhapSoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_nhapSoLuong.setFont(new Font("Arial", Font.BOLD, 14));
		lb_nhapSoLuong.setBounds(407, 607, 114, 44);
		panel_menu.add(lb_nhapSoLuong);

		JButton btn_chonMon = new JButton("CHỌN MÓN");
		btn_chonMon.setBackground(new Color(255, 255, 255));
		btn_chonMon.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/icon_ok.png")));
		btn_chonMon.setFont(new Font("Arial", Font.BOLD, 14));
		btn_chonMon.setBounds(664, 607, 159, 44);
		panel_menu.add(btn_chonMon);
		btn_chonMon.addActionListener(controller);

		JPanel panel_hoaDon = new JPanel();
		panel_hoaDon.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_hoaDon.setBounds(1155, 78, 670, 668);
		contentPane.add(panel_hoaDon);
		panel_hoaDon.setLayout(null);

		JLabel lb_hoaDon = new JLabel(" HOÁ ĐƠN");
		lb_hoaDon.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/bill.png")));
		lb_hoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lb_hoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lb_hoaDon.setBounds(10, 11, 354, 66);
		panel_hoaDon.add(lb_hoaDon);

		table_hoaDon = new JTable();
		table_hoaDon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "MÃ MÓN", "TÊN MÓN", "GIÁ MÓN", "SỐ LƯỢNG", "THÀNH TIỀN" }));
		table_hoaDon.setFont(new Font("Arial", Font.BOLD, 14));
		table_hoaDon.setRowHeight(35);
		DefaultTableCellRenderer center_hoaDon = new DefaultTableCellRenderer();
		center_hoaDon.setHorizontalAlignment(JLabel.CENTER);
		table_hoaDon.getColumnModel().getColumn(0).setCellRenderer(center_hoaDon);
		model_table_hoaDon = (DefaultTableModel) table_hoaDon.getModel();

		JScrollPane scroll_hoaDon = new JScrollPane(table_hoaDon);
		scroll_hoaDon.setBounds(10, 88, 650, 434);
		panel_hoaDon.add(scroll_hoaDon);

		JLabel lb_tongTien = new JLabel("TỔNG TIỀN:");
		lb_tongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongTien.setFont(new Font("Arial", Font.BOLD, 14));
		lb_tongTien.setBounds(384, 533, 86, 44);
		panel_hoaDon.add(lb_tongTien);

		tF_tongTien = new JTextField();
		tF_tongTien.setFont(new Font("Arial", Font.BOLD, 14));
		tF_tongTien.setBounds(480, 534, 180, 43);
		panel_hoaDon.add(tF_tongTien);
		tF_tongTien.setColumns(10);

		JButton btn_thanhToan = new JButton("THANH TOÁN");
		btn_thanhToan.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/pay.png")));
		btn_thanhToan.setFont(new Font("Arial", Font.BOLD, 14));
		btn_thanhToan.setBackground(Color.WHITE);
		btn_thanhToan.setBounds(152, 600, 197, 57);
		btn_thanhToan.addActionListener(controller);
		panel_hoaDon.add(btn_thanhToan);

		JButton btn_huyHoaDon = new JButton("HUỶ HOÁ ĐƠN");
		btn_huyHoaDon.setIcon(new ImageIcon(viewTrangChu.class.getResource("/icon/cancelBill.png")));
		btn_huyHoaDon.setFont(new Font("Arial", Font.BOLD, 14));
		btn_huyHoaDon.setBackground(Color.WHITE);
		btn_huyHoaDon.setBounds(413, 600, 197, 57);
		btn_huyHoaDon.addActionListener(controller);
		panel_hoaDon.add(btn_huyHoaDon);

		JButton btn_thayDoiSoLuong = new JButton("THAY ĐỔI SỐ LƯỢNG");
		btn_thayDoiSoLuong.setBackground(Color.WHITE);
		btn_thayDoiSoLuong.setFont(new Font("Arial", Font.BOLD, 13));
		btn_thayDoiSoLuong.setBounds(10, 533, 180, 44);
		btn_thayDoiSoLuong.addActionListener(controller);
		panel_hoaDon.add(btn_thayDoiSoLuong);

		tF_soBan = new JTextField();
		tF_soBan.setFont(new Font("Arial", Font.BOLD, 16));
		tF_soBan.setBounds(374, 11, 286, 66);
		panel_hoaDon.add(tF_soBan);
		tF_soBan.setColumns(10);
		
		JButton btn_xoaMon = new JButton("XOÁ MÓN");
		btn_xoaMon.setFont(new Font("Arial", Font.BOLD, 13));
		btn_xoaMon.setBackground(Color.WHITE);
		btn_xoaMon.setBounds(200, 533, 180, 44);
		panel_hoaDon.add(btn_xoaMon);
		btn_xoaMon.addActionListener(controller);

		this.setVisible(true);

		truyenCSDLVaoMenu();

		icon_x = new ImageIcon(viewTrangChu.class.getResource("/icon/x.png"));
		icon_number = new ImageIcon(viewTrangChu.class.getResource("/icon/number.png"));

	}

	private void moMenuChucNang() {
		// width244 height777
		// Tạo luồng song song
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 244; i++) {
					panel_menuChucNang.setSize(i, 777);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				panel_chonBan.setVisible(false);
			}
		}).start();
	}

	private void dongMenuChucNang() {
		// width244 height777
		// Tạo luồng song song
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 244; i >= 0; i--) {
					panel_menuChucNang.setSize(i, 777);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				panel_chonBan.setVisible(true);
			}
		}).start();
	}

	private void dangXuat() {
		this.setVisible(false);
		viewDangNhap view_dangNhap = new viewDangNhap();
		view_dangNhap.setVisible(true);
	}

	private void truyenCSDLVaoMenu() {
		String query = "SELECT MaDoUong, TenDoUong, GiaDoUong from menu";
		try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while (rs.next()) {
				String maDoUong = rs.getString("MaDoUong");
				String tenDoUong = rs.getString("TenDoUong");
				Float giaDoUong = rs.getFloat("GiaDoUong");
				model_table_menu.addRow(new Object[] { maDoUong, tenDoUong, giaDoUong });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "KHÔNG THỂ KẾT NỐI CSDL!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE,
					icon_x);
		}
	}

	public void setTextFieldSoBan(String soBan) {
		tF_soBan.setText(soBan);
	}

	public void timMon() {
		String tenMon = tF_timKiem.getText();
		String query = "SELECT MaDoUong, TenDoUong, GiaDoUong from menu WHERE TenDoUong = '" + tenMon + "'";
		while (model_table_menu.getRowCount() != 0) {
			model_table_menu.removeRow(0);
		}
		try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while (rs.next()) {
				String maDoUong = rs.getString("MaDoUong");
				String tenDoUong = rs.getString("TenDoUong");
				Float giaDoUong = rs.getFloat("GiaDoUong");
				model_table_menu.addRow(new Object[] { maDoUong, tenDoUong, giaDoUong });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "KHÔNG THỂ KẾT NỐI CSDL!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE,
					icon_x);
		}
	}

	public void huyTim() {
		while (model_table_menu.getRowCount() != 0) {
			model_table_menu.removeRow(0);
		}
		truyenCSDLVaoMenu();
		tF_timKiem.setText("");
	}

	public void thayDoiTextFieldTongTien() {
		float tongTien = 0;
		for (int i = 0; i < model_table_hoaDon.getRowCount(); i++) {
			Object tongTien_O = model_table_hoaDon.getValueAt(i, 4);
			tongTien += ((Number) tongTien_O).floatValue();
		}
		String tongTien_Text = String.valueOf(tongTien);
		tF_tongTien.setText(tongTien_Text);
	}

	public void chonMon() {
		int row = table_menu.getSelectedRow();
		if (row != -1) {
			int soLuong = (int) spinner_soLuong.getValue();
			if (soLuong != 0) {
				String maDoUong = (String) model_table_menu.getValueAt(row, 0);
				String tenDoUong = (String) model_table_menu.getValueAt(row, 1);
				Float giaDoUong = (Float) model_table_menu.getValueAt(row, 2);
				Float thanhTien = soLuong * giaDoUong;
				boolean check = false;
				for (int i = 0; i < model_table_hoaDon.getRowCount(); i++) {
					String maDoUongTrongHoaDon = (String) model_table_hoaDon.getValueAt(i, 0);
					if (maDoUong.equals(maDoUongTrongHoaDon)) {
						int soLuongCu = (int) model_table_hoaDon.getValueAt(i, 3);
						int soLuongMoi = soLuongCu + soLuong;
						model_table_hoaDon.setValueAt(soLuongMoi, i, 3);
						model_table_hoaDon.setValueAt(soLuongMoi * giaDoUong, i, 4);
						check = true;
						break;
					}
				}
				if (!check) {
					model_table_hoaDon.addRow(new Object[] { maDoUong, tenDoUong, giaDoUong, soLuong, thanhTien });
				}
			} else {
				JOptionPane.showMessageDialog(this, "VUI LÒNG NHẬP SỐ LƯỢNG!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE,
						icon_x);
			}
			thayDoiTextFieldTongTien();
		} else {
			JOptionPane.showMessageDialog(this, "VUI LÒNG CHỌN MỘT ĐỒ UỐNG!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE,
					icon_x);
		}
	}

	public void thayDoiSoLuong() {
		int row = table_hoaDon.getSelectedRow();
		if (row != -1) {
			String input_soLuong = (String) JOptionPane.showInputDialog(this, "NHẬP SỐ LƯỢNG MỚI: ",
					"THAY ĐỔI SỐ LƯỢNG", JOptionPane.INFORMATION_MESSAGE, icon_number, null, null);
			if (input_soLuong == null) {

			} else if (!input_soLuong.equals("0")) {
				model_table_hoaDon.setValueAt(input_soLuong, row, 3);
				float giaDoUong = (float) model_table_hoaDon.getValueAt(row, 2);
				int soLuong_int = Integer.valueOf(input_soLuong);
				float thanhTien = giaDoUong * soLuong_int;
				model_table_hoaDon.setValueAt(thanhTien, row, 4);
				thayDoiTextFieldTongTien();
			} else {
				model_table_hoaDon.removeRow(row);
				thayDoiTextFieldTongTien();
			}

		} else {
			JOptionPane.showMessageDialog(this, "VUI LÒNG CHỌN MỘT ĐỒ UỐNG!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE,
					icon_x);
		}
		if (model_table_hoaDon.getRowCount() == 0) {
			tF_tongTien.setText("");
		}
	}

	public void huyHoaDon() {
		while (model_table_hoaDon.getRowCount() != 0) {
			model_table_hoaDon.removeRow(0);
		}
		tF_tongTien.setText("");
		tF_soBan.setText("");
	}

	public void thanhToan() {
		viewThanhToan view_thanhToan = new viewThanhToan();
		view_thanhToan.setVisible(true);
		view_thanhToan.setLocationRelativeTo(contentPane);
		huyHoaDon();
	}

	public void themMonVaoMenu() {
		viewThemMonVaoMenu view_themMonVaoMenu = new viewThemMonVaoMenu();
		view_themMonVaoMenu.setVisible(true);
		view_themMonVaoMenu.setLocationRelativeTo(contentPane);
	}

	public void xoaMonKhoiMenu() {
		viewXoaMonKhoiMenu view_xoaMonKhoiMenu = new viewXoaMonKhoiMenu();
		view_xoaMonKhoiMenu.setVisible(true);
		view_xoaMonKhoiMenu.setLocationRelativeTo(contentPane);
	}

	public void thayDoiGiaTien() {
		viewThayDoiGiaTien view_thayDoiGiaTien = new viewThayDoiGiaTien();
		view_thayDoiGiaTien.setVisible(true);
		view_thayDoiGiaTien.setLocationRelativeTo(contentPane);
	}

	public boolean checkChucVu() {
		String query = "SELECT chucVu FROM taikhoan WHERE tenTaiKhoan = '" + viewDangNhap.tenTaiKhoan + "'";
		try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while (rs.next()) {
				if (rs.getString("chucVu").equals("Quản lý")) {
					return true;
				} else {
					JOptionPane.showMessageDialog(this,
							"TÀI KHOẢN CỦA BẠN KHÔNG PHẢI QUẢN LÝ, KHÔNG THỂ SỬ DỤNG CHỨC NĂNG NÀY", "THÔNG BÁO!",
							JOptionPane.ERROR_MESSAGE, icon_x);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "LỖI!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE, icon_x);
		}
		return false;
	}

	public void taiKhoan() {
		String query = "SELECT chucVu FROM taikhoan WHERE tenTaiKhoan = '" + viewDangNhap.tenTaiKhoan + "'";
		try (ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while (rs.next()) {
				if (rs.getString("chucVu").equals("Quản lý")) {
					viewTaiKhoan view_taiKhoan = new viewTaiKhoan();
					view_taiKhoan.setVisible(true);
					view_taiKhoan.setLocationRelativeTo(contentPane);
				} else {
					JOptionPane.showMessageDialog(this,
							"TÀI KHOẢN CỦA BẠN KHÔNG PHẢI QUẢN LÝ, KHÔNG THỂ SỬ DỤNG CHỨC NĂNG NÀY", "THÔNG BÁO!",
							JOptionPane.ERROR_MESSAGE, icon_x);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "LỖI!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE, icon_x);
		}
	}

	public void xoaMonKhoiHoaDon() {
		int row = table_hoaDon.getSelectedRow();
		if(row != 1) {
			int luaChon = JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN XOÁ MÓN ĐÃ CHỌN KHÔNG?", "THÔNG BÁO!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(luaChon==JOptionPane.YES_OPTION) {
				model_table_hoaDon.removeRow(row);
				thayDoiTextFieldTongTien();
			}
		} else {
			JOptionPane.showMessageDialog(this, "VUI LÒNG CHỌN MỘT MÓN MUỐN XOÁ!", "THÔNG BÁO!",
					JOptionPane.ERROR_MESSAGE, new ImageIcon(viewTrangChu.class.getResource("/icon/x.png")));
		}
	}
}
