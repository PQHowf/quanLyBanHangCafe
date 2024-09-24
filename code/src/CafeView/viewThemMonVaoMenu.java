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

import CafeController.controllerThemMon;
import database.JDBCUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class viewThemMonVaoMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tF_tenMon_themMon;
	private JTextField tF_giaMon_themMon;

	public viewThemMonVaoMenu() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewTrangChu.class.getResource("/icon/logo.png")));
		setTitle("QUẢN LÝ BÁN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ActionListener action = new controllerThemMon(this);
		
		JLabel lb_themMonVaoMenu = new JLabel(" THÊM MÓN VÀO MENU");
		lb_themMonVaoMenu.setIcon(new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/themMonVaoMenu.png")));
		lb_themMonVaoMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lb_themMonVaoMenu.setFont(new Font("Arial", Font.BOLD, 20));
		lb_themMonVaoMenu.setBounds(10, 11, 464, 74);
		contentPane.add(lb_themMonVaoMenu);
		
		JLabel lb_nhapTenMon = new JLabel("NHẬP TÊN MÓN: ");
		lb_nhapTenMon.setFont(new Font("Arial", Font.BOLD, 14));
		lb_nhapTenMon.setBounds(10, 96, 139, 36);
		contentPane.add(lb_nhapTenMon);
		
		tF_tenMon_themMon = new JTextField();
		tF_tenMon_themMon.setFont(new Font("Arial", Font.BOLD, 14));
		tF_tenMon_themMon.setBounds(159, 96, 315, 36);
		contentPane.add(tF_tenMon_themMon);
		tF_tenMon_themMon.setColumns(10);
		
		JLabel lb_nhapGiaMon = new JLabel("NHẬP GIÁ MÓN: ");
		lb_nhapGiaMon.setFont(new Font("Arial", Font.BOLD, 14));
		lb_nhapGiaMon.setBounds(10, 173, 139, 36);
		contentPane.add(lb_nhapGiaMon);
		
		tF_giaMon_themMon = new JTextField();
		tF_giaMon_themMon.setFont(new Font("Arial", Font.BOLD, 14));
		tF_giaMon_themMon.setColumns(10);
		tF_giaMon_themMon.setBounds(159, 173, 315, 36);
		contentPane.add(tF_giaMon_themMon);
		
		JButton btn_themMon = new JButton("THÊM MÓN");
		btn_themMon.setBackground(Color.WHITE);
		btn_themMon.setFont(new Font("Arial", Font.BOLD, 15));
		btn_themMon.setIcon(new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/icon_ok.png")));
		btn_themMon.setBounds(29, 265, 198, 62);
		btn_themMon.addActionListener(action);
		contentPane.add(btn_themMon);
		
		JButton btn_huy = new JButton("HUỶ");
		btn_huy.setBackground(Color.WHITE);
		btn_huy.setIcon(new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/x.png")));
		btn_huy.setFont(new Font("Arial", Font.BOLD, 15));
		btn_huy.setBounds(256, 265, 198, 62);
		btn_huy.addActionListener(action);
		contentPane.add(btn_huy);
	}

	private void truyenCSDLVaoMenu() {
		String query = "SELECT MaDoUong, TenDoUong, GiaDoUong from menu";
		try(ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while(rs.next()) {
				String maDoUong = rs.getString("MaDoUong");
				String tenDoUong = rs.getString("TenDoUong");
				Float giaDoUong = rs.getFloat("GiaDoUong");
				viewTrangChu.model_table_menu.addRow(new Object[] {maDoUong, tenDoUong, giaDoUong});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "KHÔNG THỂ KẾT NỐI CSDL!", "THÔNG BÁO!",
					JOptionPane.ERROR_MESSAGE, new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/x.png")));
		}
	}
	
	public void themMon() {
		String tenMon = tF_tenMon_themMon.getText();
		String giaMon = tF_giaMon_themMon.getText();
		if(tenMon!=null && giaMon!=null && tenMon.trim().matches("[\\p{L}\\s]+") && giaMon.matches("\\d+")) {
			String check = "SELECT EXISTS (SELECT 1 FROM menu WHERE TenDoUong = '"+tenMon.trim()+"')";
			try (ResultSet rs = JDBCUtil.ketNoiCSDL(check)){
				while(rs.next()) {
					if(rs.getBoolean(1)) {
						JOptionPane.showMessageDialog(this, "MÓN ĐÃ TỒN TẠI TRONG HỆ THỐNG!", "THÔNG BÁO!",
								JOptionPane.INFORMATION_MESSAGE, new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/x.png")));
					} else {
						float giaMon_Float = Float.valueOf(giaMon);
						String query = "INSERT INTO menu(TenDoUong, GiaDoUong) VALUES('"+tenMon+"', '"+giaMon_Float+"')";
						try(Connection cn = JDBCUtil.getConnection(); PreparedStatement stmt = cn.prepareStatement(query)) {
							int rowInserted = stmt.executeUpdate();
							if(rowInserted>0) {
								JOptionPane.showMessageDialog(this, "THÊM MÓN THÀNH CÔNG!", "THÔNG BÁO!",
										JOptionPane.INFORMATION_MESSAGE, new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/icon_ok.png")));
								while(viewTrangChu.model_table_menu.getRowCount()!=0) {
									viewTrangChu.model_table_menu.removeRow(0);
								}
								truyenCSDLVaoMenu();
								tF_tenMon_themMon.setText("");
								tF_giaMon_themMon.setText("");
							} else {
								JOptionPane.showMessageDialog(this, "THÊM MÓN THẤT BẠI!", "THÔNG BÁO!",
										JOptionPane.INFORMATION_MESSAGE, new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/x.png")));
							}
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "NHẬP SAI ĐỊNH DẠNG!", "THÔNG BÁO!",
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon(viewThemMonVaoMenu.class.getResource("/icon/x.png")));
		}
		
	}

}
