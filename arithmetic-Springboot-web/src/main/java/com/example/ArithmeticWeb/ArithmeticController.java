package com.example.ArithmeticWeb;
import java.util.Stack;

public class ArithmeticController {	
	//调度场算法，通过入栈出栈操作来计算结果
		public static String evaluateAlgorithm(String s) {
			Stack<Character> ops = new Stack<Character>();  //运算符栈
			Stack<Fraction> vals = new Stack<Fraction>();	//操作数栈
			for(int i=0;i<s.length();i++) {
				char s1 = s.charAt(i);
			//左括号入栈 
			if (s1 == '(') 
				ops.push(s1);
			//右括号把之前的数和运算符出栈进行运算
			else if(s1 == ')') {
				while(ops.peek()!= '(') {
					int result[] = new int[2];
					Fraction a = vals.pop();
					Fraction b = vals.pop();
					result = caculate(ops.pop(),a.getNumerator(),a.getDenominator(),b.getNumerator(),b.getDenominator());
					vals.push(new Fraction(result[0],result[1]));
				}

				ops.pop();
			}
			else if(s1 == '+' || s1 == '-' || s1 == '*' || s1 == '÷') {  //遇到运算符的情况
				while(!ops.empty() && JudgePriority(s1,ops.peek())) {    //判断运算符的优先级
					int result[] = new int[2];
					Fraction a = vals.pop();
					Fraction b = vals.pop();
					//当前运算符如果比栈顶运算符的优先级高，将之前的运算符和数字出栈进行运算
					result = caculate(ops.pop(),a.getNumerator(),a.getDenominator(),b.getNumerator(),b.getDenominator()); 
					vals.push(new Fraction(result[0],result[1]));  //将计算出的数字入栈				
				}

				ops.push(s1);
			}
			else {
				if (s1 >= '0' && s1 <= '9') {     //操作数入栈
					StringBuffer buf = new StringBuffer();
					while (i < s.length() && ((s.charAt(i) >='0' && s.charAt(i) <= '9') || s.charAt(i) == '/')) 
						buf.append(s.charAt(i++));								
				i--;
				String s2 = buf.toString();
				int flag = s2.length();
				for(int j=0;j<s2.length();j++) {  //寻找分号的位置
					if(s2.charAt(j) == '/') 
						flag = j;
					}
				StringBuffer buf1 = new StringBuffer();
				StringBuffer buf2 = new StringBuffer();
				for(int k=0;k<flag;k++) {        //分号之前的是分子
					buf1.append(s2.charAt(k));
				}
				if(flag != s2.length() ) {       //分号后面是分母
					for(int k=flag+1;k<s2.length();k++)
						buf2.append(s2.charAt(k));			
					
				}
				//整数的分母是1
				else buf2.append('1');
	            //入栈
				vals.push(new Fraction(Integer.parseInt(buf1.toString()),Integer.parseInt(buf2.toString())));
				}
			} 
			}
			while(!ops.empty()) {
				int result[] = new int[2];
				Fraction a = vals.pop();
				Fraction b = vals.pop();
				result = caculate(ops.pop(),a.getNumerator(),a.getDenominator(),b.getNumerator(),b.getDenominator());
				vals.push(new Fraction(result[0],result[1]));
			}
			
	         Fraction result = vals.pop();
	         //最大公约数
	         int k = GCD(result.numerator,result.denominator);
	         //如果分母为1，只输出分子
	         String rightResult;
	         if(result.denominator/k == 1) {
	        	 //System.out.println(result.numerator/k);
	             rightResult = result.numerator/k + "";
	         }
	         else { //输出分数
	        	 //System.out.println(result.numerator/k+"/"+result.denominator/k);
	        	 rightResult = result.numerator/k+"/"+result.denominator/k + "";
	         }
	         return rightResult;
			
		}
		
	    //判断运算符的优先级
		public static boolean JudgePriority(char op1, char op2) {
			if (op2 == '(' || op2 == ')')
				return false;
			if ((op1 == '*' || op1 == '÷') && (op2 == '+' || op2 == '-')) //op1比op2的优先级高
				return false;
			else
				return true;
		}
		
		//对numerator1/denominator1和numerator2/denominator2两个操作数进行运算
		public  static int[] caculate(char op, int numerator1, int denominator1, int numerator2, int denominator2) { 
			int[] result = new int[2];
			switch (op) {
			case '+': 
				result[0] = numerator1*denominator2 + numerator2*denominator1; result[1]= denominator1*denominator2;
				return result;
			case '-':
				result[0] = numerator2*denominator1 - numerator1*denominator2; result[1]= denominator1*denominator2;
				return result;
			case '*':
				result[0] = numerator1*numerator2; result[1] = denominator1*denominator2;
				return result;
			case '÷':
				result[0] = numerator2*denominator1; result[1] = numerator1*denominator2;
				return result;
			}
			return result;
		}
		
		//求两个数的最大公约数
		public static int GCD(int a,int b) {
			//取绝对值，避免是负数
			a = Math.abs(a); 
			b = Math.abs(b);
			while(b!=0) {
				int temp = a%b;
				a = b;
				b = temp;
			}
			return a;
		}

}
