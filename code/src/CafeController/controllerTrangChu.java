package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewTrangChu;

public class controllerTrangChu implements ActionListener {
	viewTrangChu view_trangChu;
	
	public controllerTrangChu(viewTrangChu view_trangChu) {
		this.view_trangChu = view_trangChu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("BÀN 1")) {
			String soBan = "BÀN 1";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 2")) {
			String soBan = "BÀN 2";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 3")) {
			String soBan = "BÀN 3";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 4")) {
			String soBan = "BÀN 4";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 5")) {
			String soBan = "BÀN 5";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 6")) {
			String soBan = "BÀN 6";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 7")) {
			String soBan = "BÀN 7";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 8")) {
			String soBan = "BÀN 8";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 9")) {
			String soBan = "BÀN 9";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 10")) {
			String soBan = "BÀN 10";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 11")) {
			String soBan = "BÀN 11";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("BÀN 12")) {
			String soBan = "BÀN 12";
			this.view_trangChu.setTextFieldSoBan(soBan);
		} else if(action.equals("TÌM MÓN")) {
			this.view_trangChu.timMon();
		} else if(action.equals("HUỶ TÌM")) {
			this.view_trangChu.huyTim();
		} else if(action.equals("CHỌN MÓN")) {
			this.view_trangChu.chonMon();
		} else if(action.equals("THAY ĐỔI SỐ LƯỢNG")) {
			this.view_trangChu.thayDoiSoLuong();
		} else if(action.equals("HUỶ HOÁ ĐƠN")) {
			this.view_trangChu.huyHoaDon();
		} else if(action.equals("THANH TOÁN")) {
			this.view_trangChu.thanhToan();
		} else if(action.equals("TÀI KHOẢN")) {
			this.view_trangChu.taiKhoan();
		} else if(action.equals("XOÁ MÓN")) {
			this.view_trangChu.xoaMonKhoiHoaDon();
		}
	}

}
