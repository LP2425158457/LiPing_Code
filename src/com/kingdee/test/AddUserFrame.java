package com.kingdee.test;

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

public class AddUserFrame extends Frame
  implements ActionListener
{
  private Frame f = this;
  private Frame f1 = null;
  private Panel p1 = new Panel();
  private Panel p2 = new Panel();
  private Label l1 = new Label("注册新用户", 1);
  private Label l2 = new Label("图书证号: ");
  private Label l3 = new Label("姓名:        ");
  private TextField tf1 = new TextField(18);
  private TextField tf2 = new TextField(18);
  private Button b1 = new Button("增加");
  private Button b2 = new Button("取消");

  AddUserFrame(String name, Frame f1) {
    super(name);
    this.f1 = f1;
    setLayout(new BorderLayout());
    this.p1.setLayout(new BorderLayout());
    this.p2.setLayout(new FlowLayout(2));
    this.l1.setFont(new Font(null, 1, 22));
    this.l2.setFont(new Font(null, 1, 20));
    this.l3.setFont(new Font(null, 1, 20));
    this.tf1.setFont(new Font(null, 1, 18));
    this.tf2.setFont(new Font(null, 1, 18));
    this.b1.setFont(new Font(null, 1, 20));
    this.b2.setFont(new Font(null, 1, 20));
    this.tf1.setBackground(new Color(190, 240, 200));
    this.tf2.setBackground(new Color(190, 240, 200));
    this.b1.addActionListener(this);
    this.b2.addActionListener(this);
    this.p2.add(this.b1);
    this.p2.add(this.b2);
    this.p1.add(this.l3, "West");
    this.p1.add(this.tf2, "Center");
    this.p1.add(this.p2, "South");
    add(this.l1, "North");
    add(this.l2, "West");
    add(this.tf1, "Center");
    add(this.p1, "South");
    pack();
    setLocation(500, 300);
    addWindowListener(new AddUserWindowCloser());
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == this.b2) {
      this.tf1.setText("");
      this.tf2.setText("");
    } else if (ae.getSource() == this.b1) {
      if ((!this.tf1.getText().equals("")) && (!this.tf2.getText().equals(""))) {
        LibrarySumUsers lsu = new LibrarySumUsers();
        if (!lsu.SearchUser(this.tf1.getText())) {
          new LibrarySumUsers(new LibraryUserInfo(this.tf1.getText(), this.tf2.getText()));
          new ConfirmDialog(this.f, "恭喜! 添加新用户成功!");
          this.tf1.setText("");
          this.tf2.setText("");
        } else {
          new ConfirmDialog(this.f, "添加失败!原因: 该图书证号的用户已经存在了!");
        }
      } else {
        new ConfirmDialog(this.f, "请先输入新用户的相关必要信息!");
      }
    }
  }
  public class AddUserWindowCloser extends WindowAdapter {
    public AddUserWindowCloser() {
    }

    public void windowClosing(WindowEvent e) {
      AddUserFrame.this.f.setVisible(false);
      AddUserFrame.this.f1.setEnabled(true);
      AddUserFrame.this.f1.setVisible(true);
    }
  }
}