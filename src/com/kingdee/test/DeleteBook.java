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

public class DeleteBook extends Frame
  implements ActionListener
{
  private Frame f = this;
  private Frame f1 = null;
  Panel p1 = new Panel();
  Panel p2 = new Panel();
  Label l = new Label("请输入您要删除的书号: ");
  Label l1 = new Label(">>>>>>>> 清除库存 <<<<<<<<", 1);
  Label l2 = new Label("您要删除的库存书号信息如下: ");
  Button b1 = new Button("删除");
  Button b2 = new Button("取消");
  TextField tf = new TextField(15);
  TextArea ta = new TextArea();

  DeleteBook(String name, Frame f1) { super(name);
    this.f1 = f1;
    this.p1.setLayout(new BorderLayout());
    this.l.setFont(new Font(null, 1, 16));
    this.l1.setFont(new Font(null, 1, 22));
    this.l2.setFont(new Font(null, 1, 18));
    this.tf.setFont(new Font(null, 0, 16));
    this.ta.setFont(new Font(null, 0, 18));
    this.tf.setBackground(new Color(190, 240, 200));
    this.ta.setBackground(new Color(190, 240, 200));
    this.ta.setEditable(false);
    this.tf.addActionListener(this);
    this.b1.addActionListener(this);
    this.b2.addActionListener(this);
    this.l1.setBackground(Color.lightGray);
    this.l2.setBackground(Color.lightGray);
    this.p1.add(this.l1, "North");
    this.p1.add(this.l, "West");
    this.p1.add(this.tf, "Center");
    this.p1.add(this.l2, "South");
    this.p2.setLayout(new FlowLayout(2));
    this.b1.setFont(new Font(null, 1, 20));
    this.b2.setFont(new Font(null, 1, 20));
    this.p2.add(this.b1);
    this.p2.add(this.b2);
    setLayout(new BorderLayout());
    add(this.p1, "North");
    add(this.ta, "Center");
    add(this.p2, "South");
    pack();
    setLocation(400, 200);
    setVisible(true);
    addWindowListener(new WindowCloserAdapter()); }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.tf) {
      Result r = new Result();
      BTree bt = new BTree();
      try {
        r = bt.searchBTree(Integer.parseInt(this.tf.getText()));
        if (!r.tag) {
          new ConfirmDialog(this.f, "库存中找不到指定的书号!");
        } else if (r.tag) {
          this.ta.append("书号:  " + r.location.bookey[r.index].num + "\n");
          this.ta.append("书名:  " + r.location.bookey[r.index].name + "\n");
          this.ta.append("作者:  " + r.location.bookey[r.index].author + "\n");
          this.ta.append("现存量: " + r.location.bookey[r.index].remainder + "\n");
          this.ta.append("总量:  " + r.location.bookey[r.index].sum + "\n\n");
        }
      } catch (NumberFormatException nfe1) {
        new ConfirmDialog(this.f, "读取书籍相关信息失败! 原因: 输入数据格式错误!");
      }
    } else if (e.getSource() == this.b1) {
      BTree bt = new BTree();
      if (bt.getHead() != null) {
        Result r = new Result();
        r = bt.searchBTree(Integer.parseInt(this.tf.getText()));
        if (!r.tag) {
          this.ta.setText("");
          new ConfirmDialog(this.f, "库存中找不到指定的书号!");
        } else if (r.tag) {
          bt.deleteBTree(r.location.bookey[r.index].num);
          bt.dispBTree(bt.getHead(), 0);
          this.tf.setText("0");
          this.ta.setText("");
          this.ta.append("书号:  " + r.location.bookey[r.index].num + "\n");
          this.ta.append("书名:  " + r.location.bookey[r.index].name + "\n");
          this.ta.append("作者:  " + r.location.bookey[r.index].author + "\n");
          this.ta.append("现存量: " + r.location.bookey[r.index].remainder + "\n");
          this.ta.append("总量:  " + r.location.bookey[r.index].sum + "\n\n");
          new ConfirmDialog(this.f, "书号删除成功!");
        }
      } else {
        this.ta.setText("");
        new ConfirmDialog(this.f, "库存为空!");
      }
    } else if (e.getSource() == this.b2) {
      this.tf.setText("");
      this.ta.setText("");
    }
  }
  public class WindowCloserAdapter extends WindowAdapter {
    public WindowCloserAdapter() {
    }
    public void windowClosing(WindowEvent e) {
      DeleteBook.this.f.setVisible(false);
      DeleteBook.this.f1.setEnabled(true);
      DeleteBook.this.f1.setVisible(true);
    }
  }
}