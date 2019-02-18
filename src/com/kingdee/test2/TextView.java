package com.kingdee.test2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
 
 
public class TextView {
	String s;
	List<Goods> list = new ArrayList<Goods>();
	public void login(){
		while(true){
		System.out.println("|----------------------------|");
		System.out.println("|-------欢迎进入商品信息管理系统------|");
		System.out.println("|----------------------------|");
		System.out.println("|---1.添加商品      2.查询商品       3.退出--|");
		System.out.println("|----------------------------|");
		System.out.println("请选择：");
		
		Scanner sc = new Scanner(System.in);
		s=sc.next();
		System.out.println("|-----------------------------|");
		System.out.println("您选择的是："+s);
		System.out.println("|-----------------------------|");
		int i;
		i=Integer.parseInt(s);
		if(i==1){
			update();
		}else if(i==2){
			check();
		}else if(i==3){
			
			
			break;
		}
		}
		System.out.println("退出成功！！！");
		exit();
	}
	
	public void update(){
		Goods go = new Goods();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入商品的编号：");
		int i;
		i=Integer.parseInt(sc.next());
		go.setId(i);
		System.out.println("请输入商品的名称：");
		go.setName(sc.next());
		System.out.println("请输入商品的价格：");
		i=Integer.parseInt(sc.next());
		go.setPrice(i);
		System.out.println("请输入商品的数量：");
		i=Integer.parseInt(sc.next());
		go.setNumber(i);
		System.out.println("是否保存（y/n）？");
		String s;
		s=sc.next();
		if(s.equals("y")||s.equals("Y"))
		{
			list.add(go);
			new OutputFile().write(list);
			System.out.println("保存成功！！！");
		}else{
			System.out.println("商品未保存！！！");
		}
	}
	
	public void check(){
		new InputFile().read();
	}
	
	public void exit(){
		System.exit(0);
	}
}