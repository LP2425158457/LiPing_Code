package com.kingdee.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AuthorBookList extends Frame
  implements ActionListener
{
  private Frame f = this;
  private Frame f1 = null;
  private Panel p1 = new Panel();
  private Panel p2 = new Panel();
  private Panel p3 = new Panel();
  private Panel p4 = new Panel();
  private Panel p5 = new Panel();
  private Label l1 = new Label("著者著作列表", 1);
  private Label l2 = new Label("请输入您要查找的著者姓名: ");
  private Label l3 = new Label("您要查找的著者的相关书籍列表如下: ");
  private Label l4 = new Label("查找指定书名的详细信息");
  private TextField tf = new TextField(10);
  private Button b1 = new Button("确定");
  private Button b2 = new Button("取消");
  private Button b3 = new Button("确定");
  private Button b4 = new Button("上一条");
  private Button b5 = new Button("下一条");
  private TextArea ta = new TextArea(6, 20);
  private List bookList = new List(8);
  private String str = null;
  private String str0 = null;

  AuthorBookList(String name, Frame f1) { super(name);
    this.f1 = f1;
    this.p1.setLayout(new BorderLayout());
    this.p2.setLayout(new FlowLayout(2));
    this.p3.setLayout(new BorderLayout());
    this.p4.setLayout(new BorderLayout());
    this.p5.setLayout(new GridLayout(3, 1, 5, 10));
    this.l1.setFont(new Font(null, 1, 22));
    this.l2.setFont(new Font(null, 1, 18));
    this.l3.setFont(new Font(null, 1, 18));
    this.l4.setFont(new Font(null, 1, 18));
    this.tf.setFont(new Font(null, 1, 18));
    this.ta.setFont(new Font(null, 1, 18));
    this.b1.setFont(new Font(null, 1, 20));
    this.b2.setFont(new Font(null, 1, 20));
    this.b3.setFont(new Font(null, 1, 20));
    this.b4.setFont(new Font(null, 1, 20));
    this.b5.setFont(new Font(null, 1, 20));
    this.l2.setBackground(Color.lightGray);
    this.l3.setBackground(Color.lightGray);
    this.l4.setBackground(Color.lightGray);
    this.bookList.setFont(new Font(null, 1, 18));
    this.bookList.setBackground(new Color(190, 240, 200));
    this.tf.setBackground(new Color(190, 240, 200));
    this.ta.setBackground(new Color(190, 240, 200));
    this.ta.setEditable(false);
    this.tf.addActionListener(this);
    this.b1.addActionListener(this);
    this.b2.addActionListener(this);
    this.b3.addActionListener(this);
    this.b4.addActionListener(this);
    this.b5.addActionListener(this);
    this.p2.add(this.b1);
    this.p2.add(this.b2);
    this.p1.add(this.l1, "North");
    this.p1.add(this.l2, "West");
    this.p1.add(this.tf, "Center");
    this.p1.add(this.p2, "South");
    this.p5.add(this.b3);
    this.p5.add(this.b4);
    this.p5.add(this.b5);
    this.p4.add(this.l4, "North");
    this.p4.add(this.p5, "West");
    this.p4.add(this.ta, "Center");
    this.p3.add(this.l3, "North");
    this.p3.add(this.bookList, "Center");
    this.p3.add(this.p4, "South");
    setLayout(new BorderLayout());
    add(this.p1, "North");
    add(this.p3, "Center");
    pack();
    setLocation(400, 150);
    setVisible(true);
    addWindowListener(new AuthorBooksWindow()); }

  public void actionPerformed(ActionEvent ae)
  {
    if ((ae.getSource() == this.tf) || (ae.getSource() == this.b1)) {
      BTree bt = new BTree();
      if (bt.getHead() != null) {
        this.bookList.removeAll();
        this.ta.setText("");
        bt.authorBooksSearch(bt.getHead(), this.tf.getText(), this.bookList);
        if (this.bookList.getItemCount() == 0)
          new ConfirmDialog(this.f, "库存中不存在该著者著作!");
      }
      else {
        new ConfirmDialog(this.f, "库存为空!");
      }
    } else if (ae.getSource() == this.b2) {
      this.tf.setText("");
      this.bookList.removeAll();
    } else if (ae.getSource() == this.b3) {
      if (this.bookList.getSelectedItem() != null) {
        BTree bt = new BTree();
        this.str = this.bookList.getSelectedItem().substring(6, this.bookList.getSelectedItem().length() - 3);

        bt.SearchInfoByName(bt.getHead(), this.str, this.ta);
      } else {
        new ConfirmDialog(this.f, "尚未在列表中指定要查找的书名!");
      }
    } else if (ae.getSource() == this.b4) {
      if (this.bookList.getSelectedItem() != null)
        try {
          this.str0 = this.bookList.getItem(this.bookList.getSelectedIndex() - 1);
          BTree bt = new BTree();
          this.str = this.str0.substring(6, this.str0.length() - 3);
          bt.SearchInfoByName(bt.getHead(), this.str, this.ta);
          this.bookList.select(this.bookList.getSelectedIndex() - 1);
        } catch (ArrayIndexOutOfBoundsException abe) {
          new ConfirmDialog(this.f, "该记录已经是第一条记录!");
        }
      else
        new ConfirmDialog(this.f, "尚未在列表中指定要查找的书名!");
    }
    else if (ae.getSource() == this.b5) {
      if (this.bookList.getSelectedItem() != null)
        try {
          this.str0 = this.bookList.getItem(this.bookList.getSelectedIndex() + 1);
          BTree bt = new BTree();
          this.str = this.str0.substring(6, this.str0.length() - 3);
          bt.SearchInfoByName(bt.getHead(), this.str, this.ta);
          this.bookList.select(this.bookList.getSelectedIndex() + 1);
        } catch (ArrayIndexOutOfBoundsException abe1) {
          new ConfirmDialog(this.f, "该记录已经是最后一条记录!");
        }
      else
        new ConfirmDialog(this.f, "尚未在列表中指定要查找的书名!"); 
    }
  }

  public class AuthorBooksWindow extends WindowAdapter {
    public AuthorBooksWindow() {
    }

    public void windowClosing(WindowEvent e) {
      AuthorBookList.this.f.setVisible(false);
      AuthorBookList.this.f1.setEnabled(true);
      AuthorBookList.this.f1.setVisible(true);
    }
  }
}