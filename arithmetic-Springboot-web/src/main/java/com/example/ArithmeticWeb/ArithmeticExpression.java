package com.example.ArithmeticWeb;

import java.util.Random;

public class ArithmeticExpression {
	private String expression;
	private String result;
	public ArithmeticExpression(String expression, String result) {
		super();
		this.expression = expression;
		this.result = result;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public static String random() {
		Random oprandom = new Random();
		Random fraction1 = new Random();
		Random fraction2 = new Random();
		Random bracket1 = new Random();
		Random bracket2 = new Random();
		//运算符个数
		int opnumber = oprandom.nextInt(5)%5+1;
		//操作数个数
		int valnumber = opnumber + 1;
		String[] op = new String[opnumber];
		String[] val = new String[valnumber];
		
		//生成运算符数组
		for (int i=0;i<opnumber;i++) {
			int oprange = oprandom.nextInt(4)%4 + 1;
			switch(oprange) {
			case 1: op[i]="+";break;
			case 2: op[i]="-";break;
			case 3: op[i]="*";break;
			case 4: op[i]="÷";
			} 
		}
		//生成操作数数组
		for (int j=0;j<valnumber;j++) {
			int b = (int)(Math.random()*10) % 2; 
			//整数
			if(b == 0)
				val[j] = (fraction1.nextInt(10)%10 +1) + "";
			//分数
			else {
				int num = fraction1.nextInt(10)%10 +1;
				int deno = fraction2.nextInt(10)%9 + 2;
				while(num >= deno) {
					num = fraction1.nextInt(10)%10 +1;
					deno = fraction2.nextInt(10)%9 + 2;
				}
				val[j] = num + "/" + deno + "";										
			}		
			
		}
		
		int m = (int)(Math.random()*10) % 2;
		if(m == 0) { //产生带括号的运算式
			int o = (int)(Math.random()*10) % 2;
			if (o == 0) { //先插入左括号，再插入右括号
				int[] lval1 = new int[valnumber]; //左括号标记数组
				int[] rval1 = new int[valnumber]; //右括号标记数组
				for (int k=0;k<valnumber-1;k++) {					
					int n = (int)(Math.random()*10) % 2;
					if(n == 0 && rval1[k] != 1) {
						lval1[k] = 1;           //标记为有左括号
						val[k] = "(" + val[k];  //操作数之前加上左括号
						int c = valnumber - 1;
						//找右括号的位置，必须在左括号的后面
						int d = bracket1.nextInt(c)%(c-k) + (k+1);
						//如果当前操作数之前有左括号，需要重新生成运算式
						while (lval1[d] == 1) 
							d = bracket1.nextInt(c)%(c-k) + (k+1);
						val[d] = val[d] +")";
						rval1[d] = 1;          //标记为有右括号
					} 
				}
			} 
				  
			else { //先插入右括号，再插入左括号
				int[] lval2 = new int[valnumber]; //左括号标记数组
				int[] rval2 = new int[valnumber]; //右括号标记数组
				//System.out.println("B");
				for (int k=valnumber-1;k>0;k--) {
					
					int n = (int)(Math.random()*10) % 2;
					if(n == 0 && lval2[k] != 1) {
						rval2[k] = 1;             //标记为有右括号
						val[k] = val[k] +")" ;    //操作数之后加上右括号
						int d = bracket2.nextInt(valnumber-1)%valnumber;
						//左括号的位置必须在右括号之前
						while (rval2[d] == 1 || d>=k)  
							d = bracket2.nextInt(valnumber-1)%valnumber;
						val[d] = "(" + val[d];
						lval2[d] = 1;
					}
				}
			}			 
		}
		
		//将随机生成的运算符和操作数连成一个字符串
		String s1 = val[0];
		String s2 = "";
		for(int k=0;k<opnumber;k++) {
			s2 = s2+op[k]+val[k+1];
		}
		String s = s1+s2;
		while(s.charAt(0) == '(' && s.charAt(s.length()-1) ==')') {
			s = random();
		}
		
		return s;
	}	

}
