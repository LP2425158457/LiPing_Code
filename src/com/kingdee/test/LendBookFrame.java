package com.kingdee.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LendBookFrame extends Frame
  implements ActionListener
{
  private Frame f = this;
  private Frame f2 = null;
  private Panel p1 = new Panel();
  private Panel p2 = new Panel();
  private Panel p3 = new Panel();
  private Panel p4 = new Panel();
  private Label l1 = new Label("借阅图书", 1);
  private Label l2 = new Label("请输入您要借阅的图书书号: ");
  private Label l3 = new Label("您要借阅的图书的详细信息如下: ");
  private TextField tf = new TextField(15);
  private TextArea ta = new TextArea(8, 10);
  private Button b1 = new Button("确定");
  private Button b2 = new Button("取消");
  private Button b3 = new Button("确定借阅");

  private Frame fInfo = new Frame();
  private Panel p5 = new Panel();
  private Label l4 = new Label("个人信息", 1);
  private Label l5 = new Label("请输入您的图书证号: ");
  private TextField tf1 = new TextField(15);
  private Button b4 = new Button("确定");
  private Button b5 = new Button("取消");

  private Result r = new Result();
  private BTree bt = new BTree();

  LendBookFrame(String title, Frame f2) {
    super(title);
    this.f2 = f2;
    this.l1.setFont(new Font(null, 1, 22));
    this.l2.setFont(new Font(null, 1, 20));
    this.l3.setFont(new Font(null, 1, 20));
    this.tf.setFont(new Font(null, 1, 18));
    this.ta.setFont(new Font(null, 1, 18));
    this.b1.setFont(new Font(null, 1, 20));
    this.b2.setFont(new Font(null, 1, 20));
    this.b3.setFont(new Font(null, 1, 20));
    this.tf.setBackground(new Color(190, 240, 200));
    this.tf.addActionListener(this);
    this.b1.addActionListener(this);
    this.b2.addActionListener(this);
    this.b3.addActionListener(this);
    this.b3.setEnabled(false);
    this.p4.setBackground(Color.lightGray);
    this.l2.setBackground(Color.lightGray);
    this.l3.setBackground(Color.lightGray);
    this.ta.setBackground(new Color(190, 240, 200));
    this.ta.setEditable(false);
    this.p1.setLayout(new BorderLayout());
    this.p2.setLayout(new BorderLayout());
    this.p3.setLayout(new FlowLayout(2));
    this.p4.setLayout(new FlowLayout(2));
    setLayout(new BorderLayout());
    this.p3.add(this.b1);
    this.p3.add(this.b2);
    this.p4.add(this.b3);
    this.p1.add(this.l1, "North");
    this.p1.add(this.l2, "West");
    this.p1.add(this.tf, "Center");
    this.p1.add(this.p3, "South");
    this.p2.add(this.l3, "North");
    this.p2.add(this.ta, "Center");
    this.p2.add(this.p4, "South");
    add(this.p1, "North");
    add(this.p2, "Center");
    pack();
    setLocation(350, 200);
    addWindowListener(new LendWindowCloser());
    setVisible(true);

    this.fInfo.setTitle(title);
    this.l4.setFont(new Font(null, 1, 22));
    this.l5.setFont(new Font(null, 1, 20));
    this.tf1.setFont(new Font(null, 1, 18));
    this.b4.setFont(new Font(null, 1, 20));
    this.b5.setFont(new Font(null, 1, 20));
    this.tf1.setBackground(new Color(190, 240, 200));
    this.p5.setLayout(new FlowLayout(2));

    this.b4.addActionListener(this);
    this.b5.addActionListener(this);
    this.tf1.addActionListener(this);
    this.fInfo.addWindowListener(new LendWindowCloser());
    this.p5.add(this.b4);
    this.p5.add(this.b5);
    this.fInfo.setLayout(new BorderLayout());
    this.fInfo.add(this.l4, "North");
    this.fInfo.add(this.l5, "West");
    this.fInfo.add(this.tf1, "Center");
    this.fInfo.add(this.p5, "South");
    this.fInfo.pack();
    this.fInfo.setLocation(400, 300);
  }

  public void actionPerformed(ActionEvent e) {
    if ((e.getSource() == this.tf) || (e.getSource() == this.b1)) {
      this.b3.setEnabled(false);
      if (this.bt.getHead() != null) {
        this.ta.setText("");
        try {
          this.r = this.bt.searchBTree(Integer.parseInt(this.tf.getText()));
          if (!this.r.tag) {
            new ConfirmDialog(this.f, "库存中找不到指定的书号!");
          } else if (this.r.tag) {
            this.b3.setEnabled(true);
            this.ta.append("书号:  " + this.r.location.bookey[this.r.index].num + "\n");
            this.ta.append("书名:  " + this.r.location.bookey[this.r.index].name + "\n");
            this.ta.append("作者:  " + this.r.location.bookey[this.r.index].author + "\n");
            this.ta.append("现存量: " + this.r.location.bookey[this.r.index].remainder + "\n");
            this.ta.append("总量:  " + this.r.location.bookey[this.r.index].sum + "\n\n");
          }
        } catch (NumberFormatException nfe1) {
          new ConfirmDialog(this.f, "输入的书号格式错误!");
        }
      } else {
        new ConfirmDialog(this.f, "抱歉!由于库存为空!暂时没有书籍可供借阅");
      }
    } else if (e.getSource() == this.b2) {
      this.tf.setText("");
      this.ta.setText("");
    } else if (e.getSource() == this.b3) {
      if (this.r.location.bookey[this.r.index].remainder == 0) {
        new ConfirmDialog(this.f, "抱歉!该书籍现存量不足,无法外借!");
      } else {
        this.tf1.setText("");
        this.f.setEnabled(false);
        this.fInfo.setVisible(true);
      }
    } else if (e.getSource() == this.b4) {
      if (!this.tf1.getText().equals("")) {
        LibrarySumUsers lsu = new LibrarySumUsers();
        if (lsu.SearchUser(this.tf1.getText())) {
          this.bt.LendBookByNum(this.fInfo, this.r, this.tf1.getText());
          this.fInfo.setVisible(false);
          this.f.setEnabled(true);
          this.f.setVisible(true);
          this.tf.setText("");
          this.ta.setText("");
          this.b3.setEnabled(false);
        } else {
          new ConfirmDialog(this.f, "不存在该用户信息! 如有疑问,请与管理员联系!");
          this.tf1.setText("");
        }
      } else {
        new ConfirmDialog(this.f, "请先输入您的图书证号!");
      }
    } else if (e.getSource() == this.b5) {
      this.fInfo.setVisible(false);
      this.f.setEnabled(true);
      this.f.setVisible(true);
    }
  }
  private class LendWindowCloser extends WindowAdapter {
    private LendWindowCloser() {
    }
    public void windowClosing(WindowEvent we) {
      if (we.getSource() == LendBookFrame.this.f) {
        LendBookFrame.this.f.setVisible(false);
        LendBookFrame.this.f2.setEnabled(true);
        LendBookFrame.this.f2.setVisible(true);
      } else if (we.getSource() == LendBookFrame.this.fInfo) {
        LendBookFrame.this.fInfo.setVisible(false);
        LendBookFrame.this.f.setEnabled(true);
        LendBookFrame.this.f.setVisible(true);
      }
    }
  }
}