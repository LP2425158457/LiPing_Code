package com.kingdee.test;

import java.awt.*;
import java.awt.event.*;

public class SelectMenu1 extends Frame implements ActionListener {
	private Frame f = null;
	private Frame f1 = this;
	Panel p1 = new Panel();
	Panel p3 = new Panel();
	Label l = new Label("========== 娆㈣繋杩涘叆绠＄悊鍛樼晫闈� ==========");
	Button b1 = new Button("閲囩紪鍏ュ簱");
	Button b2 = new Button("娓呴櫎搴撳瓨");
	Button b3 = new Button("鏄剧ず搴撳瓨涔﹀彿");
	Button b4 = new Button("鏌ョ湅涔︾睄淇℃伅");
	Button b5 = new Button("钁楄�呰憲浣滃垪琛�");
	Button b6 = new Button("娉ㄥ唽鏂扮敤鎴�");
	Button b7 = new Button("鎾ら攢鐢ㄦ埛");
	Button b8 = new Button("閫�鍑虹鐞嗗憳鐣岄潰");
	private Image backGroundPicture = Toolkit.getDefaultToolkit().getImage("backpicture1.jpg");

	SelectMenu1(Frame fe) {
		f = fe;
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		l.setFont(new Font(null, Font.BOLD, 20));
		setLayout(null);//蹇呰.
		setBounds(250, 120, 900, 600);
		setResizable(false);
		p1.setBounds(300, 50, 600, 100);
		p1.add(l);
		p3.setBounds(350, 150, 500, 400);
		p3.setLayout(new GridLayout(4, 2, 100, 50));
		b1.setBackground(new Color(150, 200, 140));
		b2.setBackground(new Color(150, 200, 140));
		b3.setBackground(new Color(150, 200, 140));
		b4.setBackground(new Color(150, 200, 140));
		b5.setBackground(new Color(150, 200, 140));
		b6.setBackground(new Color(150, 200, 140));
		b7.setBackground(new Color(150, 200, 140));
		b8.setBackground(new Color(150, 200, 140));
		b1.setFont(new Font(null, Font.BOLD, 15));
		b2.setFont(new Font(null, Font.BOLD, 15));
		b3.setFont(new Font(null, Font.BOLD, 15));
		b4.setFont(new Font(null, Font.BOLD, 15));
		b5.setFont(new Font(null, Font.BOLD, 15));
		b6.setFont(new Font(null, Font.BOLD, 15));
		b7.setFont(new Font(null, Font.BOLD, 15));
		b8.setFont(new Font(null, Font.BOLD, 15));
		p3.add(b1);
		p3.add(b2);
		p3.add(b3);
		p3.add(b4);
		p3.add(b5);
		p3.add(b6);
		p3.add(b7);
		p3.add(b8);
		add(p1);
		add(p3);
		addWindowListener(new WindowCloserAdapter());
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(backGroundPicture, 0, 0, 300, 600, this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			new AddBook("閲囩紪鍏ュ簱", f1);
			f1.setEnabled(false);
		} else if(e.getSource() == b2) {
			new DeleteBook("娓呴櫎搴撳瓨", f1);
			f1.setEnabled(false);
		} else if(e.getSource() == b3) {
			new DispBookNums("鏄剧ず搴撳瓨涔﹀彿", f1);
			f1.setEnabled(false);
		} else if(e.getSource() == b4) {
			new SearchInfo("鏌ユ壘鎸囧畾涔︾睄淇℃伅", f1);
			f1.setEnabled(false);
		} else if(e.getSource() == b5) {
			new AuthorBookList("钁楄�呰憲浣滃垪琛�", f1);
			f1.setEnabled(false);
		} else if(e.getSource() == b6) {
			new AddUserFrame("娣诲姞鏂扮敤鎴风晫闈�", f1);
			f1.setEnabled(false);
		} else if(e.getSource() == b7) {
			new DeleteUserFrame("鍒犻櫎鐢ㄦ埛鐣岄潰", f1);
			f1.setEnabled(false);
		} else if(e.getSource() == b8) {
			f1.setVisible(false);
			f.setVisible(true);
		}
	}

	public class WindowCloserAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			f1.setVisible(false);
			f.setVisible(true);
		}
	}
}