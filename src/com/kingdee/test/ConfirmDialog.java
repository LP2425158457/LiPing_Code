package com.kingdee.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfirmDialog extends Dialog
  implements ActionListener
{
  Label l = new Label(null, 1);
  Panel p = new Panel();
  Button okay = new Button();
  Button cancel = new Button();

  ConfirmDialog(Frame parent, String strLabel) {
    super(parent, "温馨提示!", true);
    this.cancel.setFont(new Font(null, 1, 20));
    this.cancel.setLabel("确定");
    this.cancel.addActionListener(this);
    this.p.add(this.cancel);
    setUp(strLabel);
  }

  ConfirmDialog(Frame parent, String title, String strLabel) {
    super(parent, title, true);
    this.okay.setLabel("确定");
    this.cancel.setLabel("取消");
    this.cancel.setFont(new Font(null, 1, 20));
    this.cancel.addActionListener(this);
    this.okay.setFont(new Font(null, 1, 20));
    this.okay.addActionListener(this);
    this.p.add(this.okay);
    this.p.add(this.cancel);
    setUp(strLabel);
  }

  public void setUp(String name) {
    this.p.setLayout(new FlowLayout(1));
    this.l.setFont(new Font(null, 1, 14));
    this.l.setSize(20, 15);
    this.l.setText(name);
    setLayout(new BorderLayout());
    add(this.l, "North");
    add(this.p, "Center");
    pack();
    setLocation(400, 400);
    addWindowListener(new ConfirmWindowCloser());
    setResizable(false);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.okay)
      System.exit(0);
    else if (e.getSource() == this.cancel)
      setVisible(false); 
  }
  public class ConfirmWindowCloser extends WindowAdapter {
    public ConfirmWindowCloser() {
    }

    public void windowClosing(WindowEvent e) {
      ConfirmDialog.this.setVisible(false);
    }
  }
}