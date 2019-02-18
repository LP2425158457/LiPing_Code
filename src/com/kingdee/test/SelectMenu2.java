package com.kingdee.test;

import java.awt.*;
import java.awt.event.*;

public class SelectMenu2 extends Frame implements ActionListener {
	private Frame f = this;
	private Frame ft = null;
	Panel p1 = new Panel();
	Panel p3 = new Panel();
	Label l = new Label(">>>>>>>>>> 娆㈣繋杩涘叆鐢ㄦ埛鐣岄潰 <<<<<<<<<<");
	Button b1 = new Button("鍊熼槄鍥句功");
	Button b2 = new Button("褰掕繕鍥句功");
	Button b3 = new Button("鏄剧ず搴撳瓨涔﹀彿");
	Button b4 = new Button("鏌ョ湅涔︾睄淇℃伅");
	Button b5 = new Button("钁楄�呰憲浣滃垪琛�");
	Button b6 = new Button("棰勭害鍊熶功");
	private Image backGroundPicture = Toolkit.getDefaultToolkit().getImage("backpicture2.jpg");

	SelectMenu2(Frame ft) {
		this.ft = ft;
		l.setFont(new Font(null, Font.BOLD, 20));
		setLayout(null);//蹇呰.
		setBounds(250, 120, 900, 600);
		setResizable(false);
		p1.setBounds(400, 50, 500, 70);
		p1.add(l);
		p3.setBounds(450, 130, 400, 350);
		p3.setLayout(new GridLayout(3, 2, 100, 50));
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b1.setBackground(new Color(150, 200, 140));
		b2.setBackground(new Color(150, 200, 140));
		b3.setBackground(new Color(150, 200, 140));
		b4.setBackground(new Color(150, 200, 140));
		b5.setBackground(new Color(150, 200, 140));
		b6.setBackground(new Color(150, 200, 140));
		b1.setFont(new Font(null, Font.BOLD, 15));
		b2.setFont(new Font(null, Font.BOLD, 15));
		b3.setFont(new Font(null, Font.BOLD, 15));
		b4.setFont(new Font(null, Font.BOLD, 15));
		b5.setFont(new Font(null, Font.BOLD, 15));
		b6.setFont(new Font(null, Font.BOLD, 15));
		p3.add(b1);
		p3.add(b2);
		p3.add(b3);
		p3.add(b4);
		p3.add(b5);
		p3.add(b6);
		add(p1);
		add(p3);
		addWindowListener(new WindowCloserAdapter());
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(backGroundPicture, 0, 0, 400, 600, this);
	}

	public void actionPerformed(ActionEvent menu2E) {
		if(menu2E.getSource() == b1) {
			new LendBookFrame("鍊熼槄鍥句功", f);
			f.setEnabled(false);
		} else if(menu2E.getSource() == b2) {
			new ReturnBookFrame("褰掕繕鍥句功", f);
			f.setEnabled(false);
		} else if(menu2E.getSource() == b3) {
			new DispBookNums("鏄剧ず搴撳瓨涔﹀彿", f);
			f.setEnabled(false);
		} else if(menu2E.getSource() == b4) {
			new SearchInfo("鏌ユ壘鎸囧畾涔︾睄淇℃伅", f);
			f.setEnabled(false);
		} else if(menu2E.getSource() == b5) {
			new AuthorBookList("钁楄�呰憲浣滃垪琛�", f);
			f.setEnabled(false);
		} else if(menu2E.getSource() == b6) {
			new BookBookFrame("棰勭害鍥句功", f);
			f.setEnabled(false);
		}
	}

	public class WindowCloserAdapter  extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			f.setVisible(false);
			ft.setVisible(true);
		}
	}
	/*public static void main(String[] args) {
		new SelectMenu2();
	}*/
}