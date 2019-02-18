package com.kingdee.test2;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class InputFile {
	FileInputStream in=null;
	ObjectInputStream inn=null;
	public void read(){
		try {
			 in = new FileInputStream("D:\\data.txt");
			inn = new ObjectInputStream(in);
			List<?> list = (List<?>)inn.readObject();
			Goods go;
			Iterator<?> it = list.iterator();
			while(it.hasNext())
			{
				go = (Goods) it.next();
				System.out.println("编号		名称		价格		数量");
				System.out.println(go.getId()+"		"+go.getName()+"		"+go.getPrice()+"		"+go.getNumber());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				inn.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
 
