package com.kingdee.test2;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
 
public class OutputFile {
	FileOutputStream in =null;
	ObjectOutputStream out = null;
	public void write(List<?> list){
		
		try {
			in = new FileOutputStream("D:\\data.txt");
			out = new ObjectOutputStream(in);
			out.writeObject(list);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}