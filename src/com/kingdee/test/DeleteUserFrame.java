package com.kingdee.test;
import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteUserFrame extends Frame
  implements ActionListener
{
  Frame f = this;
  Frame f1 = null;
  Panel p = new Panel();
  Label l1 = new Label("删除用户", 1);
  Label l2 = new Label("请输入您要删除的用户的图书证号: ");
  TextField tf = new TextField(20);
  Button b1 = new Button("删除");
  Button b2 = new Button("取消");

  DeleteUserFrame(String title, Frame f1) { super(title);
    this.f1 = f1;
    this.l1.setFont(new Font(null, 1, 22));
    this.l2.setFont(new Font(null, 1, 20));
    this.tf.setFont(new Font(null, 1, 18));
    this.b1.setFont(new Font(null, 1, 20));
    this.b2.setFont(new Font(null, 1, 20));
    this.tf.setBackground(new Color(190, 240, 200));
    this.p.setLayout(new FlowLayout(2));
    this.b1.addActionListener(this);
    this.b2.addActionListener(this);
    this.p.add(this.b1);
    this.p.add(this.b2);
    setLocation(450, 300);
    setLayout(new BorderLayout());
    add(this.l1, "North");
    add(this.l2, "West");
    add(this.tf, "Center");
    add(this.p, "South");
    pack();
    addWindowListener(new DeleteUserWindowCloser());
    setVisible(true); }

  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getSource() == this.b1)
      if (!this.tf.getText().equals("")) {
        LibrarySumUsers lsu = new LibrarySumUsers();
        int sign = lsu.LibraryDeleteUsers(this.tf.getText());
        if (sign == 1)
          new ConfirmDialog(this.f, "该用户已经成功从用户资料库中删除!");
        else if (sign == 0)
          new ConfirmDialog(this.f, "删除失败! 原因: 用户资料库为空!");
        else if (sign == -1)
          new ConfirmDialog(this.f, "删除失败! 原因: 不存在该图书证号!");
      }
      else {
        new ConfirmDialog(this.f, "请先输入您要删除用户的图书证号!");
      }
    else if (ae.getSource() == this.b2)
      this.tf.setText(""); 
  }
  public class DeleteUserWindowCloser extends WindowAdapter {
    public DeleteUserWindowCloser() {
    }

    public void windowClosing(WindowEvent e) {
      DeleteUserFrame.this.f1.setEnabled(true);
      DeleteUserFrame.this.f1.setVisible(true);
      DeleteUserFrame.this.f.setVisible(false);
    }
  }
}