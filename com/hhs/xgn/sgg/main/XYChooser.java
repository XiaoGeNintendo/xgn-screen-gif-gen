package com.hhs.xgn.sgg.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class XYChooser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String title;
	
	XYChooser self=this;
	
	boolean finish=false;
	
	int x,y;
	
	JTextField jtf1,jtf2;
	
	public XYChooser(String title){
		this.title=title;
		
		//Basic
		this.setTitle(title);
		this.setLayout(new GridLayout(4, 2));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//Add the first-line label
		JLabel jl=new JLabel("Input X,Y");
		JButton jb=new JButton("Open mousexy watcher");
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new mouseXYWatcher();
			}
		});
		
		//Second line
		JLabel jlx=new JLabel("Input X:");
		 jtf1=new JTextField("0");
		
		//Third line
		JLabel jly=new JLabel("Input Y:");
		 jtf2=new JTextField("0");
		
		//Fourth line
		JButton ok=new JButton("OK");
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					int x=Integer.parseInt(jtf1.getText());
					int y=Integer.parseInt(jtf2.getText());
					
					if(x<0 || y<0){
						throw new IllegalArgumentException();
					}
					
					finish=true;
					self.x=x;
					self.y=y;
					
					self.dispose();
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null,"Please input non-negative integers");
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null,"Please input non-negative integers");
				}
			}
		});
		
		JButton ss=new JButton("Set to full screen");
		ss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
				jtf1.setText(""+d.width);
				jtf2.setText(""+d.height);
			}
		});
		//Add them together
		add(jl);
		add(jb);
		add(jlx);
		add(jtf1);
		add(jly);
		add(jtf2);
		add(ok);
		add(ss);
		this.setSize(500,200);
		this.setVisible(true);
	}
	
	public void waitUntilFinish(){
		while(!finish){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getInputX(){
		return x;
	}
	
	public int getInputY(){
		return y;
	}
}
