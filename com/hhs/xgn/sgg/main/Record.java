package com.hhs.xgn.sgg.main;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

public class Record extends JFrame {

	public BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		image = new ImageIcon(image).getImage();

		// ImageIcon与Image之间的转化: ImageIcon icon = new ImageIcon(image); image =
		// icon.getImage();

		boolean hasAlpha = false;

		BufferedImage bimage = null;

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		try {

			int transparency = Transparency.OPAQUE;

			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			GraphicsDevice gs = ge.getDefaultScreenDevice();

			GraphicsConfiguration gc = gs.getDefaultConfiguration();

			bimage = gc.createCompatibleImage(image.getWidth(null),

					image.getHeight(null), transparency);

		} catch (HeadlessException e) {

		}

		if (bimage == null) {

			int type = BufferedImage.TYPE_INT_RGB;

			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}

			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);

		}

		Graphics g = bimage.createGraphics();

		g.drawImage(image, 0, 0, null);

		g.dispose();

		return bimage;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<BufferedImage> l = new ArrayList<BufferedImage>();

	Timer t;

	Record self = this;

	public Record(int x, int y, int xx, int yy, File gif,int rx,int ry,int rd) {
		this.setTitle("Recording..");
		this.setLayout(new BorderLayout());

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// Add a close button
		JButton jb = new JButton("Stop");
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
				AnimatedGifEncoder age = new AnimatedGifEncoder();
				age.start(gif.getAbsolutePath());
				age.setDelay(rd);

				long sum = 0, delta = System.currentTimeMillis();

				for (int i = 0; i < l.size(); i++) {

					age.addFrame(l.get(i));

					long now = System.currentTimeMillis();

					System.out.println("Mixed " + (i + 1) + " of " + l.size() + " (" + (100 * (i + 1) / l.size()) + "%)"
							+ " time cost:" + (now - delta) + "ms total cost:" + sum + "ms");
					sum += now - delta;
					delta = now;
				}

				age.finish();

				System.out.println("All finished!");

				self.dispose();
			}
		});

		// Timer t
		t = new Timer(rd, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Robot rb = new Robot();
					rb.setAutoWaitForIdle(true);
					BufferedImage bi = rb.createScreenCapture(new Rectangle(x, y, xx, yy));
					Image i = bi.getScaledInstance(rx, ry, BufferedImage.SCALE_FAST);
					
					l.add(toBufferedImage(i));
					System.out.println("Record-image #" + l.size());
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		t.start();

		// Mix together
		add("Center", jb);
		this.pack();
		this.setVisible(true);
	}
}
