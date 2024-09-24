package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewDangNhap;

public class controllerDangNhap implements ActionListener{

	viewDangNhap view_dangNhap;
	
	public controllerDangNhap(viewDangNhap view_dangNhap) {
		this.view_dangNhap = view_dangNhap;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("ĐĂNG NHẬP")) {
			this.view_dangNhap.dangNhap();
		}
	}

}
