package com.kingdee.test;

import java.awt.*;
import java.awt.event.*;

public class LibraryHelper extends Frame {
	private Panel l = new Panel(new GridLayout(7, 3));
	private Button b1, b2, b3;
	public Frame f = this;
	private Image backGroundPicture = Toolkit.getDefaultToolkit().getImage("backpicture.jpg");

	public static void main(String[] args) {
		new LibraryHelper("鍥句功绠＄悊绯荤粺");
	}

	LibraryHelper(String name) {
		super(name);
		setLayout(null);
		setLocation(200,100);
		setSize(1000, 650);
		setVisible(true);
		setResizable(false);//璁剧疆鏄惁鍙互璋冩暣澶у皬.
		b1 = new Button("绠＄悊鍛樼櫥闄�");
		b2 = new Button("鐢ㄦ埛鐧婚檰");
		b3 = new Button("閫�鍑虹郴缁�");
		b1.setBackground(new Color(150, 200, 140));
		b2.setBackground(new Color(150, 200, 140));
		b3.setBackground(new Color(150, 200, 140));
		b1.setFont(new Font(null, Font.BOLD, 20));
		b2.setFont(new Font(null, Font.BOLD, 20));
		b3.setFont(new Font(null, Font.BOLD, 20));
		b1.addActionListener(new Monitor());
		b2.addActionListener(new Monitor());
		b3.addActionListener(new Monitor());
		int i = 0;
		l.setBounds(400, 0, 600, 650);
		l.setBackground(Color.lightGray);
		for(i=0; i<4; i++) {
			l.add(new Panel());
		}
		l.add(b1);
		for(i=0; i<5; i++) {
			l.add(new Panel());
		}
		l.add(b2);
		for(i=0; i<5; i++) {
			l.add(new Panel());
		}
		l.add(b3);
		for(i=0; i<4; i++) {
			l.add(new Panel());
		}
		add(l);
		repaint();
		//addKeyListener(new KeyEventHandler());
		addWindowListener(new FrameWindowCloser());
	}

	public void paint(Graphics g) {
		g.drawImage(backGroundPicture, 0, 0, 400, 650, this);
	}

	private class Monitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == b3) {
				new ConfirmDialog(f, "閫�鍑哄璇濇", "纭畾閫�鍑�?");
			} else if(e.getSource() == b1) {
				f.setEnabled(false);
				TestEcho te = new TestEcho(f);
			} else if(e.getSource() == b2) {
				f.setVisible(false);
				new SelectMenu2(f);
			}
		}
	}

	private class FrameWindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			new ConfirmDialog(f, "閫�鍑哄璇濇", "纭畾閫�鍑�?");
		}
	}
}