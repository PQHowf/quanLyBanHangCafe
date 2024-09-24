package CafeView;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CafeController.controllerThanhToan;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;

public class viewThanhToan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tF_soBan;
	private JLabel lb_danhSachDoUong;
	private JTable table_danhSachDoUong;
	private JButton btn_xacNhan;
	private JButton btn_huy;
	private DefaultTableModel tableModel_danhSachDoUong;
	private JLabel lb_tongTien;
	private JTextField tF_tongTien_thanhToan;

	public viewThanhToan() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewTrangChu.class.getResource("/icon/logo.png")));
		setTitle("QUẢN LÝ BÁN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ActionListener controller = new controllerThanhToan(this);
		
		JLabel lb_hoaDon = new JLabel("HOÁ ĐƠN");
		lb_hoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lb_hoaDon.setIcon(new ImageIcon(viewThanhToan.class.getResource("/icon/bill.png")));
		lb_hoaDon.setFont(new Font("Arial", Font.BOLD, 20));
		lb_hoaDon.setBounds(10, 11, 756, 77);
		contentPane.add(lb_hoaDon);
		
		JLabel lb_soBan = new JLabel("SỐ BÀN:");
		lb_soBan.setFont(new Font("Arial", Font.BOLD, 14));
		lb_soBan.setBounds(10, 99, 86, 45);
		contentPane.add(lb_soBan);
		
		tF_soBan = new JTextField();
		tF_soBan.setBounds(106, 99, 660, 45);
		contentPane.add(tF_soBan);
		tF_soBan.setColumns(10);
		
		lb_danhSachDoUong = new JLabel("DANH SÁCH ĐỒ UỐNG:");
		lb_danhSachDoUong.setFont(new Font("Arial", Font.BOLD, 14));
		lb_danhSachDoUong.setBounds(10, 155, 189, 45);
		contentPane.add(lb_danhSachDoUong);
		
		
		table_danhSachDoUong = new JTable();
		table_danhSachDoUong.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"TÊN MÓN", "GIÁ MÓN", "SỐ LƯỢNG", "THÀNH TIỀN"
			}
		));
		table_danhSachDoUong.setFont(new Font("Arial", Font.BOLD, 14));
		table_danhSachDoUong.setRowHeight(35);
		
		tableModel_danhSachDoUong = (DefaultTableModel) table_danhSachDoUong.getModel();
		
		JScrollPane scroll_danhSach = new JScrollPane(table_danhSachDoUong);
		scroll_danhSach.setBounds(10, 211, 756, 348);
		contentPane.add(scroll_danhSach);
		
		btn_xacNhan = new JButton("XÁC NHẬN");
		btn_xacNhan.setBackground(Color.WHITE);
		btn_xacNhan.setIcon(new ImageIcon(viewThanhToan.class.getResource("/icon/icon_ok.png")));
		btn_xacNhan.setFont(new Font("Arial", Font.BOLD, 15));
		btn_xacNhan.setBounds(347, 583, 189, 67);
		btn_xacNhan.addActionListener(controller);
		contentPane.add(btn_xacNhan);
		
		btn_huy = new JButton("HUỶ");
		btn_huy.setBackground(Color.WHITE);
		btn_huy.setIcon(new ImageIcon(viewThanhToan.class.getResource("/icon/x.png")));
		btn_huy.setFont(new Font("Arial", Font.BOLD, 15));
		btn_huy.setBounds(577, 583, 189, 67);
		btn_huy.addActionListener(controller);
		contentPane.add(btn_huy);
		
		lb_tongTien = new JLabel("TỔNG TIỀN:");
		lb_tongTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_tongTien.setFont(new Font("Arial", Font.BOLD, 14));
		lb_tongTien.setBounds(10, 583, 116, 67);
		contentPane.add(lb_tongTien);
		
		tF_tongTien_thanhToan = new JTextField();
		tF_tongTien_thanhToan.setFont(new Font("Arial", Font.BOLD, 14));
		tF_tongTien_thanhToan.setBounds(136, 583, 161, 67);
		contentPane.add(tF_tongTien_thanhToan);
		tF_tongTien_thanhToan.setColumns(10);
		
		this.setVisible(true);
		truyenHoaDon();
	}
	
	public void truyenHoaDon() {
		tF_soBan.setText(viewTrangChu.tF_soBan.getText());
		for(int i=0; i<viewTrangChu.model_table_hoaDon.getRowCount(); i++) {
			String tenMon = (String) viewTrangChu.model_table_hoaDon.getValueAt(i, 1);
	        float giaMon = Float.parseFloat(viewTrangChu.model_table_hoaDon.getValueAt(i, 2).toString());
	        int soLuong = Integer.parseInt(viewTrangChu.model_table_hoaDon.getValueAt(i, 3).toString());
	        float thanhTien = Float.parseFloat(viewTrangChu.model_table_hoaDon.getValueAt(i, 4).toString());
			tableModel_danhSachDoUong.addRow(new Object[] {tenMon, giaMon, soLuong, thanhTien});
		}
		tF_tongTien_thanhToan.setText(viewTrangChu.tF_tongTien.getText());
	}

	public void xacNhan() {
		JOptionPane.showMessageDialog(this, "IN HOÁ ĐƠN THÀNH CÔNG!", "THÔNG BÁO!",
				JOptionPane.INFORMATION_MESSAGE, new ImageIcon(viewThanhToan.class.getResource("/icon/icon_ok.png")));
		this.setVisible(false);
	}
}
