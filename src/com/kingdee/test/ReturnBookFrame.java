package com.kingdee.test;

import java.awt.*;
import java.awt.event.*;

public class ReturnBookFrame extends Frame implements ActionListener {
	private Frame f = this;
	private Frame f2 = null;
	private Panel p1 = new Panel();
	private Panel p2 = new Panel();
	private Panel p3 = new Panel();
	private Panel p4 = new Panel();
	private Label l1 = new Label("褰掕繕鍥句功", Label.CENTER);
	private Label l2 = new Label("璇疯緭鍏ユ偍瑕佸綊杩樼殑鍥句功涔﹀彿: ");
	private Label l3 = new Label("鎮ㄨ褰掕繕鐨勫浘涔︾殑璇︾粏淇℃伅濡備笅: ");
	private TextField tf = new TextField(15);
	private TextArea ta = new TextArea(8, 10);
	private Button b1 = new Button("纭畾");
	private Button b2 = new Button("鍙栨秷");
	private Button b3 = new Button("纭畾褰掕繕");

	private Frame fInfo = new Frame();
	private Panel p5 = new Panel();
	private Label l4 = new Label("涓汉淇℃伅", Label.CENTER);
	private Label l5 = new Label("璇疯緭鍏ユ偍鐨勫浘涔﹁瘉鍙�: ");
	private TextField tf1 = new TextField(15);
	private Button b4 = new Button("纭畾");
	private Button b5 = new Button("鍙栨秷");

	private Result r = new Result();
	private BTree bt = new BTree();

	ReturnBookFrame(String title, Frame f2) {
		super(title);
		this.f2 = f2;
		l1.setFont(new Font(null, Font.BOLD, 22));
		l2.setFont(new Font(null, Font.BOLD, 20));
		l3.setFont(new Font(null, Font.BOLD, 20));
		tf.setFont(new Font(null, Font.BOLD, 18));
		ta.setFont(new Font(null, Font.BOLD, 18));
		b1.setFont(new Font(null, Font.BOLD, 20));
		b2.setFont(new Font(null, Font.BOLD, 20));
		b3.setFont(new Font(null, Font.BOLD, 20));
		tf.setBackground(new Color(190, 240, 200));
		tf.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b3.setEnabled(false);
		p4.setBackground(Color.lightGray);
		l2.setBackground(Color.lightGray);
		l3.setBackground(Color.lightGray);
		ta.setBackground(new Color(190, 240, 200));
		ta.setEditable(false);
		p1.setLayout(new BorderLayout());
		p2.setLayout(new BorderLayout());
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		setLayout(new BorderLayout());
		p3.add(b1);
		p3.add(b2);
		p4.add(b3);
		p1.add(l1, BorderLayout.NORTH);
		p1.add(l2, BorderLayout.WEST);
		p1.add(tf, BorderLayout.CENTER);
		p1.add(p3, BorderLayout.SOUTH);
		p2.add(l3, BorderLayout.NORTH);
		p2.add(ta, BorderLayout.CENTER);
		p2.add(p4, BorderLayout.SOUTH);
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		pack();
		setLocation(350, 200);
		addWindowListener(new LendWindowCloser());
		setVisible(true);

		fInfo.setTitle(title);
		l4.setFont(new Font(null, Font.BOLD, 22));
		l5.setFont(new Font(null, Font.BOLD, 20));
		tf1.setFont(new Font(null, Font.BOLD, 18));
		b4.setFont(new Font(null, Font.BOLD, 20));
		b5.setFont(new Font(null, Font.BOLD, 20));
		tf1.setBackground(new Color(190, 240, 200));
		p5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		b4.addActionListener(this);
		b5.addActionListener(this);
		tf1.addActionListener(this);
		fInfo.addWindowListener(new LendWindowCloser());
		p5.add(b4);
		p5.add(b5);
		fInfo.setLayout(new BorderLayout());
		fInfo.add(l4, BorderLayout.NORTH);
		fInfo.add(l5, BorderLayout.WEST);
		fInfo.add(tf1, BorderLayout.CENTER);
		fInfo.add(p5, BorderLayout.SOUTH);
		fInfo.pack();
		fInfo.setLocation(400, 300);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==tf || e.getSource()==b1) {
			b3.setEnabled(false);
			if(bt.getHead()!=null) {
				ta.setText("");
				try {
					r = bt.searchBTree(Integer.parseInt(tf.getText()));
					if(!r.tag) {
						new ConfirmDialog(f, "搴撳瓨涓壘涓嶅埌鎸囧畾鐨勪功鍙�!");
					} else if(r.tag) {
						b3.setEnabled(true);
						ta.append("涔﹀彿:  " + r.location.bookey[r.index].num + "\n");
						ta.append("涔﹀悕:  " + r.location.bookey[r.index].name + "\n");
						ta.append("浣滆��:  " + r.location.bookey[r.index].author + "\n");
						ta.append("鐜板瓨閲�: " + r.location.bookey[r.index].remainder + "\n");
						ta.append("鎬婚噺:  " + r.location.bookey[r.index].sum + "\n\n");
					}
				} catch(NumberFormatException nfe1) {
					new ConfirmDialog(f, "杈撳叆鐨勪功鍙锋牸寮忛敊璇�!");
				}
			} else {
				new ConfirmDialog(f, "鎶辨瓑!褰掕繕澶辫触!鍘熷洜: 搴撳瓨涓虹┖!");
			}
		} else if(e.getSource() == b2) {
			tf.setText("");
			ta.setText("");
		} else if(e.getSource() == b3) {
			f.setEnabled(false);
			fInfo.setVisible(true);
		} else if(e.getSource() == b4) {
			if(!tf1.getText().equals("")) {
				LibrarySumUsers lsu = new LibrarySumUsers();
				if(lsu.SearchUser(tf1.getText())) {
					bt.ReturnBookByNum(fInfo, r, tf1.getText());
					fInfo.setVisible(false);
					f.setEnabled(true);
					f.setVisible(true);
					//tf.setText("");
					//ta.setText("");
					tf1.setText("");
					b3.setEnabled(false);
				} else {
					new ConfirmDialog(f, "涓嶅瓨鍦ㄨ鐢ㄦ埛淇℃伅! 濡傛湁鐤戦棶,璇蜂笌绠＄悊鍛樿仈绯�!");
				}
			} else {
				new ConfirmDialog(f, "璇峰厛杈撳叆鎮ㄧ殑鍥句功璇佸彿!");
			}
		} else if(e.getSource() == b5) {
			fInfo.setVisible(false);
			f.setEnabled(true);
			f.setVisible(true);
		}
	}

	private class LendWindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			if(we.getSource() == f) {
				f.setVisible(false);
				f2.setEnabled(true);
				f2.setVisible(true);
			} else if(we.getSource() == fInfo) {
				fInfo.setVisible(false);
				f.setEnabled(true);
				f.setVisible(true);
			}
		}
	}

	/*public static void main(String[] args) {
		new LendBookFrame("鍊熼槄鍥句功");
	}*/
}