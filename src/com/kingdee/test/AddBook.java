package com.kingdee.test;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddBook extends Frame
  implements ActionListener
{
  private Frame f = this;
  private Frame f1 = null;
  private Label l = new Label(" 采编入库 ", 1);
  private Label l1 = new Label("书号:        ", 2);
  private Label l2 = new Label("书名:        ", 2);
  private Label l3 = new Label("著者:        ", 2);
  private Label l4 = new Label("现存量:      ", 2);
  private Label l5 = new Label("图书总量:    ", 2);
  private TextField t1 = new TextField();
  private TextField t2 = new TextField();
  private TextField t3 = new TextField();
  private TextField t4 = new TextField();
  private TextField t5 = new TextField();
  private Panel p1 = new Panel();
  private Panel p2 = new Panel();
  private Button b1 = new Button("增加");
  private Button b2 = new Button("取消");

  AddBook(String name, Frame f1) { super(name);
    this.f1 = f1;
    setLayout(null);
    setLocation(400, 200);
    setSize(500, 500);
    this.l.setBounds(0, 50, 500, 50);
    this.l.setFont(new Font(null, 1, 20));
    this.l1.setFont(new Font(null, 1, 15));
    this.l2.setFont(new Font(null, 1, 15));
    this.l3.setFont(new Font(null, 1, 15));
    this.l4.setFont(new Font(null, 1, 15));
    this.l5.setFont(new Font(null, 1, 15));
    this.t1.setFont(new Font(null, 0, 20));
    this.t2.setFont(new Font(null, 0, 20));
    this.t3.setFont(new Font(null, 0, 20));
    this.t4.setFont(new Font(null, 0, 20));
    this.t5.setFont(new Font(null, 0, 20));
    this.t1.setBackground(new Color(190, 240, 200));
    this.t2.setBackground(new Color(190, 240, 200));
    this.t3.setBackground(new Color(190, 240, 200));
    this.t4.setBackground(new Color(190, 240, 200));
    this.t5.setBackground(new Color(190, 240, 200));
    this.t1.addActionListener(this);
    this.t2.addActionListener(this);
    this.t3.addActionListener(this);
    this.t4.addActionListener(this);
    this.t5.addActionListener(this);
    this.b1.setFont(new Font(null, 1, 20));
    this.b2.setFont(new Font(null, 1, 20));
    this.b1.addActionListener(this);
    this.b2.addActionListener(this);
    this.p1.setBounds(0, 120, 350, 280);
    this.p1.setLayout(new GridLayout(5, 2, 0, 30));
    this.p1.add(this.l1);
    this.p1.add(this.t1);
    this.p1.add(this.l2);
    this.p1.add(this.t2);
    this.p1.add(this.l3);
    this.p1.add(this.t3);
    this.p1.add(this.l4);
    this.p1.add(this.t4);
    this.p1.add(this.l5);
    this.p1.add(this.t5);
    this.p2.setBounds(0, 420, 500, 50);
    this.p2.setLayout(new FlowLayout());
    this.p2.add(this.b1);
    this.p2.add(this.b2);
    add(this.l);
    add(this.p1);
    add(this.p2);
    setResizable(false);
    setVisible(true);
    addWindowListener(new WindowCloserAdapter()); }

  public void actionPerformed(ActionEvent e)
  {
    if ((e.getSource() == this.b1) || (e.getSource() == this.t1) || (e.getSource() == this.t2) || (e.getSource() == this.t3) || (e.getSource() == this.t4) || (e.getSource() == this.t5))
    {
      try {
        int bookNum = Integer.parseInt(this.t1.getText());
        String bookName = this.t2.getText();
        String bookAuthor = this.t3.getText();
        int bookRemainder = Integer.parseInt(this.t4.getText());
        int bookSum = Integer.parseInt(this.t5.getText());
        if (bookRemainder > bookSum) {
          new ConfirmDialog(this.f, "添加书号失败!原因: 库存量应该大于或等于现存量!");
        } else {
          new LibraryBooks(bookNum, bookName, bookAuthor, bookRemainder, bookSum);
          new ConfirmDialog(this.f, "恭喜! 书号添加成功!");
        }
      } catch (NumberFormatException e1) {
        new ConfirmDialog(this.f, "添加书号失败!原因: 输入数据格式错误!");
      }
    } else if (e.getSource() == this.b2) {
      this.t1.setText("");
      this.t2.setText("");
      this.t3.setText("");
      this.t4.setText("");
      this.t5.setText("");
    }
  }
  public class WindowCloserAdapter extends WindowAdapter {
    public WindowCloserAdapter() {
    }
    public void windowClosing(WindowEvent e) {
      AddBook.this.f.setVisible(false);
      AddBook.this.f1.setEnabled(true);
      AddBook.this.f1.setVisible(true);
    }
  }
}