package com.kingdee.test;

import java.awt.*;
import java.awt.event.*;

public class TestEcho extends Frame {
	private TextField tf;
	private Frame fe = null;
	private Frame fthis = this;
	private Frame errorf = null;

	TestEcho(Frame f) {
		fe = f;
		Label l = new Label("璇疯緭鍏ョ櫥闄嗗瘑鐮�: ");
		l.setFont(new Font(null, Font.PLAIN, 20));
		tf = new TextField(20);
		tf.addActionListener(new EchoMonitor());
		tf.setBackground(new Color(190, 240, 200));
		tf.setEchoChar('*');
		setLocation(500, 200);
		setLayout(new FlowLayout());
		add(l);
		add(tf);
		pack();
		setVisible(true);
		addWindowListener(new WindowCloserAdapter());
	}

	/*public static void main(String[] args) {
		new TestEcho();
	}*/

	private class WindowCloserAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			fe.setEnabled(true);
			fthis.setVisible(false);
		}
	}

	class EchoMonitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tf = (TextField) e.getSource();
			if(tf.getText().equals("123456")) {
				fe.setEnabled(true);
				fe.setVisible(false);
			  tf.setText("");
			  fthis.setVisible(false);
				new SelectMenu1(fe);
			} else {
				new ErrorTip();
			}
		}
	}

	class ErrorTip extends Frame {
		ErrorTip() {
			errorf = this;
			fthis.setEnabled(false);
			Label l = new Label("瀵嗙爜閿欒!", Label.CENTER);
			Button b = new Button("纭");
			l.setFont(new Font(null, Font.BOLD, 13));
			b.setFont(new Font(null, Font.BOLD, 15));
			b.addActionListener(new BesureMonitor());
			setLayout(new BorderLayout());
			setLocation(260, 250);
			add(l, BorderLayout.NORTH);
      add(b, BorderLayout.CENTER);
      pack();
			setVisible(true);
			addWindowListener(new EchoWindowCloser());
		}
	}

	private class EchoWindowCloser extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			fe.setEnabled(true);
			errorf.setVisible(false);
			fthis.setVisible(false);
		}
	}

	class BesureMonitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fe.setEnabled(true);
			errorf.setVisible(false);
			fthis.setVisible(false);
		}
	}
}

