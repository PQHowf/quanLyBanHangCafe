package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewXoaMonKhoiMenu;

public class controllerXoaMonKhoiMenu implements ActionListener {
	
	viewXoaMonKhoiMenu view_xoaMonKhoiMenu;
	
	public controllerXoaMonKhoiMenu(viewXoaMonKhoiMenu view_xoaMonKhoiMenu) {
		this.view_xoaMonKhoiMenu = view_xoaMonKhoiMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("XÁC NHẬN XOÁ")) {
			this.view_xoaMonKhoiMenu.xacNhanXoa();
		} else if(action.equals("HUỶ")) {
			this.view_xoaMonKhoiMenu.dispose();
		}
	}

}
