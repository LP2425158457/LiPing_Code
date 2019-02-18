package com.kingdee.test;

public class LibraryUserInfo  {
	String libraryBookNum;
	String name;
	LibraryUserInfo nextUser;

	LibraryUserInfo(String libraryBookNum, String name) {
		this.libraryBookNum = libraryBookNum;
		this.name = name;
		nextUser = null;
	}

	public static void main(String[] args) {
		new LibrarySumUsers(new LibraryUserInfo("1", "zeng"));
	}
}

class LibrarySumUsers {
	static int userNum = 0;
	static LibraryUserInfo firstUser = null;

	LibrarySumUsers() {}

	LibrarySumUsers(LibraryUserInfo newUser) {
		if(firstUser == null) {
			firstUser = newUser;
		} else {
			LibraryUserInfo pfind = firstUser;
			while(pfind.nextUser!=null) {
				pfind = pfind.nextUser;
			}
			pfind.nextUser = newUser;
		}
		userNum++;
	}

	public int LibraryDeleteUsers(String n) {
		if(firstUser == null) {
			return 0;
		} else {
			boolean flag = true;
			LibraryUserInfo p, q;
			p = q = firstUser;
			while(p!=null && flag) {
				if(p.libraryBookNum.equals(n)) {
					if(q==firstUser) {
						firstUser = p.nextUser;
					} else {
						q.nextUser = p.nextUser;
					}
					flag = false;
				} else {
					q = p;
					p = p.nextUser;
				}
			}
			if(flag) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public boolean SearchUser(String num) {
		LibraryUserInfo p = firstUser;
		boolean flag = false;
		while(p!=null && !flag) {
			if(p.libraryBookNum.equals(num)) {
				flag = true;
			} else {
				p = p.nextUser;
			}
		}
		return flag;
	}
}