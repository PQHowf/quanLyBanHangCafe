package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewThanhToan;

public class controllerThanhToan implements ActionListener {

	viewThanhToan view_thanhToan;
	
	public controllerThanhToan(viewThanhToan view_thanhToan) {
		this.view_thanhToan = view_thanhToan;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("HUỶ")) {
			this.view_thanhToan.dispose();
		} else if(action.equals("XÁC NHẬN")) {
			this.view_thanhToan.xacNhan();
		}
	}

}
