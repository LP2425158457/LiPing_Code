package com.kingdee.test;

import java.awt.*;
import java.awt.event.*;

public class SearchInfo extends Frame implements ActionListener {
	private Frame f = this;
	private Frame f1 = null;
	private Panel p1 = new Panel();
	private Panel p2 = new Panel();
	private Panel p3 = new Panel();
	private Label l1 = new Label("鏌ョ湅鎸囧畾涔︾睄淇℃伅", Label.CENTER);
	private Label l2 = new Label("璇疯緭鍏ユ偍瑕佹煡鎵剧殑涔︾睄涔﹀彿: ");
	private Label l3 = new Label("鎮ㄨ鏌ユ壘鐨勪功绫嶇浉鍏崇殑淇℃伅濡備笅: ");
	private TextField tf = new TextField();
	private Button b1 = new Button("纭畾");
	private Button b2 = new Button("鍙栨秷");
	private TextArea ta = new TextArea();
	SearchInfo(String name, Frame f1) {
		super(name);
		this.f1 = f1;
		p1.setLayout(new BorderLayout());
		p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.setLayout(new BorderLayout());
		l1.setFont(new Font(null, Font.BOLD, 22));
		l2.setFont(new Font(null, Font.BOLD, 18));
		l3.setFont(new Font(null, Font.BOLD, 18));
		tf.setFont(new Font(null, Font.BOLD, 18));
		b1.setFont(new Font(null, Font.BOLD, 20));
		b2.setFont(new Font(null, Font.BOLD, 20));
		ta.setFont(new Font(null, Font.BOLD, 18));
		ta.setBackground(new Color(190, 240, 200));
		tf.setBackground(new Color(190, 240, 200));
		ta.setEditable(false);
		tf.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		p2.add(b1);
		p2.add(b2);
		p1.add(l1, BorderLayout.NORTH);
		p1.add(l2, BorderLayout.WEST);
		p1.add(tf, BorderLayout.CENTER);
		p1.add(p2, BorderLayout.SOUTH);
		p3.add(l3, BorderLayout.NORTH);
		p3.add(ta, BorderLayout.SOUTH);
		setLayout(new BorderLayout());
		add(p1, BorderLayout.NORTH);
		add(p3, BorderLayout.SOUTH);
		pack();
		setLocation(400, 250);
		setVisible(true);
		addWindowListener(new SearchWindow());
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == tf) {
			Result r = new Result();
			BTree bt = new BTree();
			r = bt.searchBTree(Integer.parseInt(tf.getText()));
			if(!r.tag) {
				ta.append("搴撳瓨涓壘涓嶅埌鎸囧畾鐨勪功鍙�!\n");
			} else if(r.tag) {
				ta.append("涔﹀彿:  " + r.location.bookey[r.index].num + "\n");
				ta.append("涔﹀悕:  " + r.location.bookey[r.index].name + "\n");
				ta.append("浣滆��:  " + r.location.bookey[r.index].author + "\n");
				ta.append("鐜板瓨閲�: " + r.location.bookey[r.index].remainder + "\n");
				ta.append("鎬婚噺:  " + r.location.bookey[r.index].sum + "\n\n");
			}
		} else if(ae.getSource() == b1) {
			BTree bt = new BTree();
			if(bt.getHead()!=null) {
				Result r = new Result();
				r = bt.searchBTree(Integer.parseInt(tf.getText()));
				if(!r.tag) {
					ta.setText("");
					ta.append("搴撳瓨涓壘涓嶅埌鎸囧畾鐨勪功鍙�!\n");
					bt.dispBTree(bt.getHead(), 0);
				} else if(r.tag) {
					ta.setText("");
					ta.append("涔﹀彿:  " + r.location.bookey[r.index].num + "\n");
					ta.append("涔﹀悕:  " + r.location.bookey[r.index].name + "\n");
					ta.append("浣滆��:  " + r.location.bookey[r.index].author + "\n");
					ta.append("鐜板瓨閲�: " + r.location.bookey[r.index].remainder + "\n");
					ta.append("鎬婚噺:  " + r.location.bookey[r.index].sum + "\n\n");
				}
			} else {
				ta.append("搴撳瓨涓虹┖!\n");
			}
		} else if(ae.getSource() == b2) {
			tf.setText("");
		}
	}

	public class SearchWindow extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			f.setVisible(false);
			f1.setEnabled(true);
			f1.setVisible(true);
		}
	}

	/*public static void main(String[] args) {
		new SearchInfo("鏌ョ湅鎸囧畾涔︾睄淇℃伅");
	}*/
}