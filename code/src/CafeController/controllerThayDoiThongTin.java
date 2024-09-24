package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewTaiKhoan;
import CafeView.viewThayDoiTaiKhoan;

public class controllerThayDoiThongTin implements ActionListener{

	viewTaiKhoan view_taiKhoan;
	viewThayDoiTaiKhoan view;
	
	public controllerThayDoiThongTin(viewThayDoiTaiKhoan view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("XÁC NHẬN")) {
			String tenTaiKhoan = view_taiKhoan.tenTaiKhoan;
			this.view.xacNhan(tenTaiKhoan);
		} else if(action.equals("HUỶ BỎ")) {
			this.view.dispose();
		}
	}

}
