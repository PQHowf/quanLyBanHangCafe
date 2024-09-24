package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewThayDoiGiaTien;

public class controllerThayDoiGiaTien implements ActionListener{
	
	viewThayDoiGiaTien view_thayDoiGiaTien;
	
	public controllerThayDoiGiaTien(viewThayDoiGiaTien view_thayDoiGiaTien) {
		this.view_thayDoiGiaTien = view_thayDoiGiaTien;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("XÁC NHẬN THAY ĐỔI")) {
			this.view_thayDoiGiaTien.thayDoiGiaTien();
		} else if(action.equals("HUỶ")) {
			this.view_thayDoiGiaTien.dispose();
		}
	}

}
