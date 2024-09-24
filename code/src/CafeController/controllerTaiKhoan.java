package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewTaiKhoan;

public class controllerTaiKhoan implements ActionListener{

	viewTaiKhoan view_taiKhoan;

	public controllerTaiKhoan(viewTaiKhoan view_taiKhoan) {
		this.view_taiKhoan = view_taiKhoan;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("ĐĂNG KÝ TÀI KHOẢN")) {
			this.view_taiKhoan.dangKy();
		} else if(action.equals("THAY ĐỔI THÔNG TIN")) {
			this.view_taiKhoan.thayDoiThongTin();
		} else if(action.equals("XOÁ TÀI KHOẢN")) {
			this.view_taiKhoan.xoaTaiKhoan();
		}
	}

}
