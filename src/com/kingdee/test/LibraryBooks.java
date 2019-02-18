package com.kingdee.test;

import java.awt.*;
import java.util.*;
import java.text.*;

public class LibraryBooks {
	LibraryBooks(int num, String name, String author, int remainder, int sum) {
		Books b = new Books(num, name, author, remainder, sum);
		BTree bt = new BTree();
		bt.createBTree(b);
		bt.dispBTree(bt.getHead(), 0);
	}
	public static void main(String[] args) {
		new LibraryBooks(0, null, null, 0, 0);
	}
}

class Student {
	String lendBookID;
	Date lendDate;
	Date returnDate;
	Student nextStu;
}

class StuBook {
	String bookID;
	Date bookDate;
	StuBook nextStuBook;
}

class Books {
  int num;
	String name;
	String author;
	int remainder;
	int sum;
	int lendBook = 0;
	Student user;
	StuBook booker;

	Books(int num, String name, String author, int remainder, int sum) {
		this.num = num;
		this.name = name;
		this.author = author;
		this.remainder = remainder;
		this.sum = sum;
	}
}

class Result {
	BTNode location;
	int index;
	boolean tag;
}

class BTNode {
	int keynum;
	BTNode parent;
	Books[] bookey = new Books[4];
	BTNode[] ptr = new BTNode[4];
}

class BTree {
	private static int num = 0;
	private static BTNode head = null;
	private static final int m = 3;
	private static final int min = 1;
	private static final int bookTimeUp = 15;//棰勭害鏈熼檺璁剧疆涓�15澶�.
	private static final int lendTimeUp = 2;//鍊熼槄鏈熼檺璁剧疆涓�2涓湀.

	public void createBTree(Books book) {
		if(head == null) {
			head = new BTNode();
			head.keynum = 1;
			head.parent = null;
			head.bookey[1] = book;
			head.ptr[0] = head.ptr[1] = null;
			for(int i=2; i<=3; i++) {
				head.bookey[i] = null;
				head.ptr[i] = null;
			}
		} else {
			Result su = new Result();
			su = searchBTree(book.num);
			if(su.tag) {
				su.location.bookey[su.index].remainder += book.remainder;
				su.location.bookey[su.index].sum += book.sum;
			} else {
				insertBTree(book, su.location, su.index);
			}
		}
	}

	public Result searchBTree (int bookNum) {
		int i = 0;
		boolean found = false;
		BTNode p = head, q = null;
		Result sult = new Result();
		while(p!=null && !found) {
			i = search(p, bookNum);
			if(i>0 && p.bookey[i].num==bookNum) {
				found = true;
			} else {
				q = p;
				p = p.ptr[i];
			}
		}
		if(found) {
			sult.location = p;
			sult.index = i;
			sult.tag = true;
		} else {
			sult.location = q;
			sult.index = i;
			sult.tag = false;
		}
		return sult;
	}

	public int search(BTNode b, int n) {
		int i;
		for(i=1; i<=b.keynum; i++) {
			if(b.bookey[i].num == n) {
				return i;
			}
			if(b.bookey[i].num>n) {
				return i-1;
			}
		}
		return i-1;
	}

	public void insertBTree(Books b, BTNode bt, int index) {
		int s = 0;
		boolean finished = false;
		Books x = b;
		BTNode ap = null;
		while(bt!=null && !finished) {
			insert(bt, index, x, ap);
			if(bt.keynum<m) {
				finished = true;
			} else {
				s = (m+1)/2;
				x = bt.bookey[s];
				ap = split(bt);
				bt = bt.parent;
				if(bt != null) {
					index = search(bt, x.num);
				}
			}
		}
		if(!finished) {
			newRoot(x, ap);
		}
	}

	public void insert(BTNode lo, int index0, Books book, BTNode bp) {
		for(int n=lo.keynum; n>=index0+1; n--) {
			lo.ptr[n+1] = lo.ptr[n];
			lo.ptr[n] = lo.ptr[n-1];
			lo.bookey[n+1] = lo.bookey[n];
		}
		lo.bookey[index0+1] = book;
		lo.keynum++;
		lo.ptr[index0+1] = bp;
	}

	public BTNode split(BTNode p) {
		int s, i;
		s = (m+1) / 2;
		BTNode bp = new BTNode();
		bp.ptr[0] = p.ptr[s];
		for(i=s+1; i<=m; i++) {
			bp.bookey[i-s] = p.bookey[i];
			bp.ptr[i-s] = p.ptr[i];
			if(bp.ptr[i-s] != null) {
				bp.ptr[i-s].parent = bp;
			}
		}
		bp.keynum = p.keynum - s;
		bp.parent = p.parent;
		for(i=0; i<=p.keynum; i++) {
			if(bp.ptr[i] != null) {
				bp.ptr[i].parent = bp;
			}
		}
		p.keynum = s-1;
		return bp;
	}

	public void newRoot(Books book, BTNode bt) {
		BTNode t = new BTNode();
		t.keynum = 1;
		t.bookey[1] = book;
		for(int i=2; i<m+1; i++) {
			t.bookey[i] = null;
			t.ptr[i] = null;
		}
		t.ptr[0] = head;
		t.ptr[1] = bt;
		t.parent = null;
		bt.parent = head.parent = t;
		head = t;
	}

	public void dispBTree(BTNode t, int begin) {
		int i, j, k;
		for(k=0; k<begin; k++) {
			System.out.print(" ");
		}
		if(t != null) {
			for(i=1; i<t.keynum; i++) {
				System.out.print(t.bookey[i].num + ",");
			}
			System.out.println(t.bookey[t.keynum].num);
			for(j=0; t.ptr[j]!=null && j<=t.keynum; j++) {
				dispBTree(t.ptr[j], begin + 2);
			}
		}
	}

	public void dispBooks(BTNode t, int begin, TextArea ta) {
		int i, j, k;
		for(k=0; k<begin; k++) {
			ta.append("  ");
		}
		if(t != null) {
			for(i=1; i<t.keynum; i++) {
				ta.append("" + t.bookey[i].num + ", ");
			}
			ta.append("" + t.bookey[i].num + "\n");
			for(j=0; t.ptr[j]!=null && j<=t.keynum; j++) {
				dispBooks(t.ptr[j], begin + 2, ta);
			}
		}
	}

	public void deleteBTree(int k) {
		boolean b = recDelete(k, head);
		if(head.keynum == 0) {
			head = head.ptr[0];
		}
	}

	public boolean recDelete(int k, BTNode p) {
		boolean found = false;
		if(p == null) {
			return false;
		}
		int i = search(p, k);
		if(i>0 && p.bookey[i].num==k) {
			found = true;
		} else {
			found = false;
		}
		if(found) {
			if(p.ptr[i-1] != null) {
				successor(p, i);
				found = recDelete(p.bookey[i].num, p.ptr[i]);
			} else {
				remove(p, i);
			}
		} else {
			found = recDelete(k, p.ptr[i]);
		}
		if(p.ptr[i]!=null && p.ptr[i].keynum<min) {
			restore(p, i);
		}
		return found;
	}

	public void remove(BTNode p, int i) {
		for(int j=i+1; j<=p.keynum; j++) {
			p.bookey[j-1] = p.bookey[j];
			p.ptr[j-1] = p.ptr[j];
		}
		p.keynum--;
	}

	public void successor(BTNode p, int i) {
		BTNode q;
		for(q=p.ptr[i]; q.ptr[0]!=null; q=q.ptr[0]);
		p.bookey[i] = q.bookey[1];
	}

	public void restore(BTNode p, int i) {
		if(i==0) {
			if(p.ptr[1].keynum>min) {
				moveLeft(p, 1);
			} else {
				combine(p, 1);
			}
		} else if(i==p.keynum) {
			if(p.ptr[i-1].keynum>min) {
				moveRight(p, i);
			} else {
				combine(p, i);
			}
		} else if(p.ptr[i-1].keynum>min) {
			moveRight(p, i);
		} else if(p.ptr[i+1].keynum>min) {
			moveLeft(p, i+1);
		} else {
			combine(p, i);
		}
	}

	public void moveRight(BTNode p, int i) {
		BTNode t;
		t = p.ptr[i];
		for(int c=t.keynum; c>0; c--) {
			t.bookey[c+1] = t.bookey[c];
			t.ptr[c+1] = t.ptr[c];
		}
		t.ptr[1] = t.ptr[0];
		t.keynum++;
		t.bookey[1] = p.bookey[i];
		t = p.ptr[i-1];
		p.bookey[i] = t.bookey[t.keynum];
		p.ptr[i].ptr[0] = t.ptr[t.keynum];
		t.keynum--;
	}

	public void moveLeft(BTNode p, int i) {
		BTNode t;
		t = p.ptr[i-1];
		t.keynum++;
		t.bookey[t.keynum] = p.bookey[i];
		t.ptr[t.keynum] = p.ptr[i].ptr[0];
		t = p.ptr[i];
		p.bookey[i] = t.bookey[1];
		t.ptr[0] = t.ptr[1];
		t.keynum--;
		for(int c=1; c<=t.keynum; c++) {
			t.bookey[c] = t.bookey[c+1];
			t.ptr[c] = t.ptr[c+1];
		}
	}

	public void combine(BTNode p, int i) {
		BTNode q, l;
		q = p.ptr[i];
		l = p.ptr[i-1];
		l.keynum++;
		l.bookey[l.keynum] = p.bookey[i];
		l.ptr[l.keynum] = q.ptr[0];
		for(int c=1; c<=q.keynum; c++) {
			l.keynum++;
			l.bookey[l.keynum] = q.bookey[c];
			l.ptr[l.keynum] = q.ptr[c];
		}
		for(int c=i; c<p.keynum; c++) {
			p.bookey[c] = p.bookey[c+1];
			p.ptr[c] = p.ptr[c+1];
		}
		p.keynum--;
	}

	public void authorBooksSearch(BTNode t, String sAuthor, java.awt.List listBook) {
		int i;
		for(i=1; i<=t.keynum; i++) {
			if(sAuthor.equals(t.bookey[i].author)) {
				listBook.add("钁椾綔: " + "<<" + t.bookey[i].name + ">>" + "\n");
			}
		}
		for(int j=0; t.ptr[j]!=null && j<=t.keynum; j++) {
			authorBooksSearch(t.ptr[j], sAuthor, listBook);
		}
	}

	public void SearchInfoByName(BTNode t, String sName, TextArea ta) {
		int i;
		for(i=1; i<=t.keynum; i++) {
			if(sName.equals(t.bookey[i].name)) {
				ta.append("涔﹀彿: " + t.bookey[i].num + "\n" +
				          "涔﹀悕: " + t.bookey[i].name + "\n" +
				          "浣滆��: " + t.bookey[i].author + "\n" +
				          "鐜板瓨閲�: " + t.bookey[i].remainder + "\n" +
				          "鎬诲瓨閲�: " + t.bookey[i].sum + "\n\n");
			}
		}
		for(int j=0; t.ptr[j]!=null && j<=t.keynum; j++) {
			SearchInfoByName(t.ptr[j], sName, ta);
		}
	}

	//***********************************************************************************************************
	public void LendBookByNum(Frame f, Result sult, String lenderNumber) {
		boolean isBooker = false;
		boolean flag = true;
		StuBook pr, ps;
		pr = ps = sult.location.bookey[sult.index].booker;
		if(sult.location.bookey[sult.index].lendBook!=0) {
			IsLendBook(sult);
		}
		if(sult.location.bookey[sult.index].lendBook!=0) {//鍒ゆ柇鍊熼槄鑰呮槸鍚︿负璇ヤ功鐨勯绾﹁��
			while(ps!=null && flag) {
				if(ps.bookID.equals(lenderNumber)) {
					isBooker = true;
					flag = false;
				} else {
					pr = ps;
					ps = ps.nextStuBook;
				}
			}
		}
		if(isBooker || sult.location.bookey[sult.index].remainder>sult.location.bookey[sult.index].lendBook) {
			Calendar cn = Calendar.getInstance();
			Student p = new Student();
			p.nextStu = null;
			p.lendBookID = lenderNumber;
			p.lendDate = cn.getTime();
			cn.set(Calendar.MONTH, cn.get(Calendar.MONTH)+lendTimeUp);
			p.returnDate = cn.getTime();
			if(isBooker) {
				if(pr == sult.location.bookey[sult.index].booker) {//鍒犻櫎鎴愬姛鍊熼槄鐨勯绾﹁�呰褰�.
					sult.location.bookey[sult.index].booker = ps.nextStuBook;
				} else {
					pr.nextStuBook = ps.nextStuBook;
				}
				sult.location.bookey[sult.index].lendBook--;
			}

			if(sult.location.bookey[sult.index].user == null) {//娣诲姞鍊熼槄鑰呬俊鎭�
				sult.location.bookey[sult.index].user = p;
			} else {
				Student q = sult.location.bookey[sult.index].user;
				while(q.nextStu!=null) {
					q = q.nextStu;
				}
				q.nextStu = p;
			}
			sult.location.bookey[sult.index].remainder--;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃� HH:mm:ss");
			new ConfirmDialog(f, "鎭枩!鍊熼槄鎴愬姛!鍊熼槄鏃堕棿涓�: " + sdf.format(p.lendDate) + "鍒版湡鏃堕棿涓�: " + sdf.format(p.returnDate));
		} else {
			new ConfirmDialog(f, "鎶辨瓑!鍊熼槄澶辫触!\n鍘熷洜:涔︾睄<<" + sult.location.bookey[sult.index].name + ">>宸茬粡琚汉棰勭害浜�!");
		}
	}



	public void IsLendBook(Result su) {
		boolean flag = true;
		Calendar cNow = Calendar.getInstance();
		Date dNow = cNow.getTime();
		StuBook p = su.location.bookey[su.index].booker;
		while(p!=null && flag) {
			if(p.bookDate.compareTo(dNow)<0) {
				flag = false;
				su.location.bookey[su.index].lendBook--;
			}
			p = p.nextStuBook;
		}
	}

	public void ReturnBookByNum(Frame f, Result sult, String lenderNumber) {
		Student s1, s2;
		s1 = s2 = sult.location.bookey[sult.index].user;
		boolean flag = true;
		while(s2!=null && flag) {
			if(s2.lendBookID.equals(lenderNumber)) {
				if(s2 == sult.location.bookey[sult.index].user) {
					sult.location.bookey[sult.index].user = s1.nextStu;
				} else {
					s1.nextStu = s2.nextStu;
				}
				flag = false;
			} else {
				s1 = s2;
				s2 = s2.nextStu;
			}
		}
		if(flag) {
			new ConfirmDialog(f, "鎿嶄綔澶辫触! 鍘熷洜: 璇ュ浘涔︿腑娌℃湁鎮ㄧ殑鍊熼槄淇℃伅!");
		} else {
			sult.location.bookey[sult.index].remainder++;
			Calendar cNow = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃� HH:mm:ss");
			if(s1.returnDate.compareTo(cNow.getTime())<0) {
				new ConfirmDialog(f, "鍥句功褰掕繕鎴愬姛! 鎮ㄥ�熺殑鍥句功瓒呮湡浜�! 鍒版湡鏃堕棿涓�: " + sdf.format(s1.returnDate));
			} else {
				new ConfirmDialog(f, "鎭枩! 鍥句功褰掕繕鎴愬姛!");
			}
		}
	}

	public void BookBookByNum(Frame f, Result sult, String lenderNumber) {
		boolean flag = true, sign = true;
		Student s1 = sult.location.bookey[sult.index].user;
		StuBook pr, ps;
	  pr = ps = sult.location.bookey[sult.index].booker;
		while(s1!=null && flag) {
			if(s1.lendBookID.equals(lenderNumber)) {
				new ConfirmDialog(f, "棰勭害澶辫触! 鍘熷洜: 鎮ㄥ凡缁忓�熼槄浜嗚鏈功!");
				flag = false;
			} else {
				s1 = s1.nextStu;
			}
		}
		while(pr!=null && sign) {
			if(pr.bookID.equals(lenderNumber)) {
				new ConfirmDialog(f, "棰勭害澶辫触! 鍘熷洜: 鎮ㄥ凡缁忛绾︿簡璇ユ湰涔�!");
				sign = false;
			} else {
				ps = pr;
				pr = pr.nextStuBook;
			}
		}
		if(flag && sign) {
			Calendar cN = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃� HH:mm:ss");
			StuBook sb = new StuBook();
			sb.bookID = lenderNumber;
			cN.set(Calendar.MONTH, cN.get(Calendar.DAY_OF_MONTH)+bookTimeUp);
			sb.bookDate = cN.getTime();
			sb.nextStuBook = null;
			if(pr == sult.location.bookey[sult.index].booker) {
				sult.location.bookey[sult.index].booker = sb;
			} else {
				ps.nextStuBook = sb;
			}
			sult.location.bookey[sult.index].lendBook++;
			new ConfirmDialog(f, "鎭枩! 棰勭害鎴愬姛! " +
			                  "涔︾睄<<" + sult.location.bookey[sult.index].name +
			                  ">>鏈�鏃╁埌鏈熸椂闂翠负: " + sdf.format(sult.location.bookey[sult.index].user.returnDate) +
			                  "! 鎮ㄥ彲浠ュ湪杩樹功鏃ユ湡寮�濮�" + bookTimeUp + "澶╁唴鍓嶆潵鍔炵悊鍊熼槄鎵嬬画! 鍦ㄨ繖" + bookTimeUp +
			                  "鍐呮垜浠皢浼氫负鎮ㄤ繚鐣欒涔︾殑鍊熼槄鏉�!");
		}
	}

	public BTNode getHead() {
		return head;
	}
}