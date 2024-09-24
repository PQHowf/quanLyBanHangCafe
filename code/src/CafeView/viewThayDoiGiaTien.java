package CafeView;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CafeController.controllerThayDoiGiaTien;
import database.JDBCUtil;

public class viewThayDoiGiaTien extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelTable;

	public viewThayDoiGiaTien() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(viewThayDoiGiaTien.class.getResource("/icon/logo.png")));
		setTitle("QUẢN LÝ BÁN HÀNG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ActionListener controller = new controllerThayDoiGiaTien(this);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_thayDoiGiaTien = new JLabel(" THAY ĐỔI GIÁ TIỀN");
		lb_thayDoiGiaTien.setIcon(new ImageIcon(viewThayDoiGiaTien.class.getResource("/icon/thayDoiGiaTien.png")));
		lb_thayDoiGiaTien.setHorizontalAlignment(SwingConstants.CENTER);
		lb_thayDoiGiaTien.setFont(new Font("Arial", Font.BOLD, 20));
		lb_thayDoiGiaTien.setBounds(10, 11, 764, 74);
		contentPane.add(lb_thayDoiGiaTien);
		
		JLabel lb_chonMon = new JLabel("CHỌN MÓN MUỐN THAY ĐỔI GIÁ TIỀN:");
		lb_chonMon.setFont(new Font("Arial", Font.BOLD, 14));
		lb_chonMon.setBounds(10, 96, 305, 35);
		contentPane.add(lb_chonMon);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"MÃ MÓN", "TÊN MÓN", "GIÁ MÓN"
			}
		));
		table.setFont(new Font("Arial", Font.BOLD, 14));
		modelTable = (DefaultTableModel) table.getModel();
		table.setRowHeight(35);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 142, 764, 324);
		contentPane.add(scrollPane);
		
		JButton btn_xoaMon = new JButton("XÁC NHẬN THAY ĐỔI");
		btn_xoaMon.setFont(new Font("Arial", Font.BOLD, 15));
		btn_xoaMon.setIcon(new ImageIcon(viewXoaMonKhoiMenu.class.getResource("/icon/icon_ok.png")));
		btn_xoaMon.setBounds(289, 488, 244, 48);
		btn_xoaMon.addActionListener(controller);
		contentPane.add(btn_xoaMon);
		
		JButton btn_huy = new JButton("HUỶ");
		btn_huy.setIcon(new ImageIcon(viewXoaMonKhoiMenu.class.getResource("/icon/x.png")));
		btn_huy.setFont(new Font("Arial", Font.BOLD, 15));
		btn_huy.setBounds(566, 488, 174, 48);
		btn_huy.addActionListener(controller);
		contentPane.add(btn_huy);
		
		truyenCSDLVaoMenu();
	}
	
	private void truyenCSDLVaoMenu() {
		String query = "SELECT MaDoUong, TenDoUong, GiaDoUong from menu";
		try(ResultSet rs = JDBCUtil.ketNoiCSDL(query)) {
			while(rs.next()) {
				String maDoUong = rs.getString("MaDoUong");
				String tenDoUong = rs.getString("TenDoUong");
				Float giaDoUong = rs.getFloat("GiaDoUong");
				modelTable.addRow(new Object[] {maDoUong, tenDoUong, giaDoUong});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "KHÔNG THỂ KẾT NỐI CSDL!", "THÔNG BÁO!",
					JOptionPane.ERROR_MESSAGE, new ImageIcon(viewTrangChu.class.getResource("/icon/x.png")));
		}
	}

	public void thayDoiGiaTien() {
		int row = table.getSelectedRow();
		if(row != 1) {
			int luaChon = JOptionPane.showConfirmDialog(this, "BẠN CÓ MUỐN THAY ĐỔI GIÁ TIỀN MÓN ĐÃ CHỌN KHÔNG?", "THÔNG BÁO!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(luaChon==JOptionPane.YES_OPTION) {
				String input_giaDoUong = (String) JOptionPane.showInputDialog(this, "Nhập giá món: ",
						"THÊM MÓN VÀO MENU", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(viewThayDoiGiaTien.class.getResource("/icon/money.png")), null, null);
				String tenMon = (String) modelTable.getValueAt(row, 1);
				String query = "UPDATE menu SET GiaDoUong = '"+input_giaDoUong+"' WHERE tenDoUong = '"+tenMon+"'";
				try(Connection cn = JDBCUtil.getConnection(); PreparedStatement stmt = cn.prepareStatement(query)) {
					int rowDel = stmt.executeUpdate();
					if(rowDel > 0) {
						JOptionPane.showMessageDialog(this, "THAY ĐỔI GIÁ TIỀN THÀNH CÔNG THÀNH CÔNG!", "THÔNG BÁO!",
								JOptionPane.ERROR_MESSAGE, new ImageIcon(viewTrangChu.class.getResource("/icon/icon_ok.png")));
						while(modelTable.getRowCount()!=0) {
							modelTable.removeRow(0);
						}
						truyenCSDLVaoMenu();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "VUI LÒNG CHỌN MỘT MÓN MUỐN THAY ĐỔI GIÁ TIỀN!", "THÔNG BÁO!",
					JOptionPane.ERROR_MESSAGE, new ImageIcon(viewTrangChu.class.getResource("/icon/x.png")));
		}
	}

}
