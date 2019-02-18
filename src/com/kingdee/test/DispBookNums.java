package com.kingdee.test;
import java.awt.*;
import java.awt.event.*;

public class DispBookNums extends Frame {
	Frame f = this;
	Frame f1 = null;
	Panel p = new Panel();
	Label l1 = new Label("显示库存书号", Label.CENTER);
	Label l2 = new Label("现在库存中书号情况为(以凹入表形式表示): ");
	TextArea ta = new TextArea(null, 15, 10);
	DispBookNums(String name, Frame f1) {
		super(name);
		this.f1 = f1;
		p.setLayout(new BorderLayout());
		l1.setFont(new Font(null, Font.BOLD, 22));
		l2.setFont(new Font(null, Font.BOLD, 20));
		ta.setFont(new Font(null, Font.BOLD, 18));
		ta.setBackground(new Color(190, 240, 200));
		ta.setEditable(false);
		p.setBackground(Color.lightGray);
		p.add(l1, BorderLayout.NORTH);
		p.add(l2, BorderLayout.CENTER);
		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(ta, BorderLayout.CENTER);
		setLocation(400, 200);
		pack();
		setVisible(true);
		addWindowListener(new WindowCloserAdapter());
		BTree bt = new BTree();
		if(bt.getHead() != null) {
			bt.dispBooks(bt.getHead(), 0, ta);
		} else {
			ta.append("库存为空!");
		}
	}

  public class WindowCloserAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			f.setVisible(false);
			f1.setEnabled(true);
			f1.setVisible(true);
		}
	}


	/*public static void main(String[] args) {
		new DispBookNums();
	}*/
}