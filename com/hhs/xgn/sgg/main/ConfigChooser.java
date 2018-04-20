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

public class ConfigChooser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String title;
	
	ConfigChooser self=this;
	
	boolean finish=false;
	
	int x,y,d;
	
	JTextField jtf1,jtf2,jtf3;
	
	public ConfigChooser(int x,int y,int xx,int yy){
		
		
		//Basic
		this.setTitle("Settings");
		this.setLayout(new GridLayout(5, 2));
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//Add the first-line label
		JLabel jl=new JLabel("Set revolutions and time delta");
		
		
		//Second line
		JLabel jlx=new JLabel("Revolution X:");
		 jtf1=new JTextField(""+(xx-x));
		
		//Third line
		JLabel jly=new JLabel("Revolution Y:");
		 jtf2=new JTextField(""+(yy-y));
		
		 //Delta
		 JLabel jtd=new JLabel("Time Delta:");
		 jtf3=new JTextField("100");
		 
		//Fourth line
		JButton ok=new JButton("OK");
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					int x=Integer.parseInt(jtf1.getText());
					int y=Integer.parseInt(jtf2.getText());
					int d=Integer.parseInt(jtf3.getText());
					if(x<0 || y<0 || d<0){
						throw new IllegalArgumentException();
					}
					
					finish=true;
					self.x=x;
					self.y=y;
					self.d=d;
					
					self.dispose();
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null,"Please input non-negative integers");
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null,"Please input non-negative integers");
				}
			}
		});
		
		//Add them together
		add(jl);
		add(new JLabel());
		add(jlx);
		add(jtf1);
		add(jly);
		add(jtf2);
		add(jtd);
		add(jtf3);
		add(ok);
		
		this.setSize(500,300);
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
	
	public int getRx(){
		return x;
	}
	
	public int getRy(){
		return y;
	}
	public int getRd(){
		return d;
	}
}
