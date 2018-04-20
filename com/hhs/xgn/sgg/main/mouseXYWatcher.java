package com.hhs.xgn.sgg.main;

import java.awt.BorderLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class mouseXYWatcher extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel a;
	public mouseXYWatcher(){
		this.setTitle("Mouse Position Watcher");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		a=new JLabel("Loading..");
		
		
		Timer t=new Timer(1, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Point mousePoint = MouseInfo.getPointerInfo().getLocation();
				
				a.setText("X:"+mousePoint.getX()+" Y:"+mousePoint.getY());
			}
		});
		
		t.start();
		
		this.add("Center",a);
		this.setSize(200,60);
		this.setVisible(true);
	}
}
