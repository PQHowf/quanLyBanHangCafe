package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewDangKy;

public class controllerDangKy implements ActionListener {

	viewDangKy view_dangKy;
	
	public controllerDangKy(viewDangKy view_dangKy) {
		this.view_dangKy = view_dangKy;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("XÁC NHẬN")) {
			this.view_dangKy.XacNhanDangKy();
		} else if(action.equals("HUỶ BỎ")) {
			this.view_dangKy.dispose();
		}
	}

}
