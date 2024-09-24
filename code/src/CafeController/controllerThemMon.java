package CafeController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CafeView.viewThemMonVaoMenu;

public class controllerThemMon implements ActionListener {
	viewThemMonVaoMenu view_themMonVaoMenu;
	
	
	public controllerThemMon(viewThemMonVaoMenu view_themMonVaoMenu) {
		this.view_themMonVaoMenu = view_themMonVaoMenu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("THÊM MÓN")) {
			this.view_themMonVaoMenu.themMon();
		} else if(action.equals("HUỶ")) {
			this.view_themMonVaoMenu.dispose();
		}
	}

}
