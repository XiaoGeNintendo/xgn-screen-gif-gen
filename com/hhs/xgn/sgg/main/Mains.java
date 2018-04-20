package com.hhs.xgn.sgg.main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Mains {

	public static void main(String[] args) {
		System.out.println("Screen Gif Generator!");
		System.out.println("By XiaoGeNintendo from Hell Hole Studios");
		
		//Open the file output box
		JFileChooser jfc=new JFileChooser(new File("/"));
		
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.setDialogTitle("Choose a file to save..");
		int result=jfc.showSaveDialog(null);
		
		if(result==JFileChooser.APPROVE_OPTION){
			
			new main(jfc.getSelectedFile());
			
		}else{
			JOptionPane.showMessageDialog(null, "Operation canceled or error occurred.","Program exit",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

}

class main{
	File gif;
	int x,y,xx,yy;
	int rx,ry,rd;
	
	public main(File f){
		this.gif=f;
		
		XYChooser xyc=new XYChooser("Choose the left-up point of the screen capture.");
		
		xyc.waitUntilFinish();
		
		x=xyc.getInputX();
		y=xyc.getInputY();
		
		System.out.println("Left up="+x+" "+y);
		
		xyc=new XYChooser("Choose the right-down point of the screen capture.");
		
		xyc.waitUntilFinish();
		
		xx=xyc.getInputX();
		yy=xyc.getInputY();
		
		System.out.println("Right down="+xx+" "+yy);
		
		ConfigChooser config=new ConfigChooser(x,y,xx,yy);
		
		config.waitUntilFinish();
		
		rx=config.getRx();
		ry=config.getRy();
		rd=config.getRd();
		
		System.out.println("Config="+rx+" "+ry+" "+rd);
		
		System.out.println("Start recording in 3 seconds...");
		
		int left=3;
		while(left!=0){
			System.out.println(left);
			left--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		new Record(x,y,xx,yy,gif,rx,ry,rd);
	}
	
}
