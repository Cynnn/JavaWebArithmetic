package com.example.ArithmeticWeb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import question.Question;
@Service
public class ArthmeticService {
	
	public ArrayList<Question> getQuestionList(int size){
		ArrayList<Question> Expression = new ArrayList<>();
		Random random = new Random();
		int operators_num = random.nextInt(6)+1;
        for(int i=0;i<size;i++){
        	Expression.add(new Question(operators_num));
        }
        return Expression;
	}
	
	public boolean writeTxtFile(int right,int wrong){
		File fileName = new File("e:/record.txt");
		String content = right +"\t"+wrong;
		try{
			if(!fileName.exists()){  
			    fileName.createNewFile();
			}
			FileOutputStream o=new FileOutputStream(fileName);
			o.write(content.getBytes("GBK"));  
		    o.close();  
		    return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public String readTxtFile(){  
		  String result=null;  
		  FileReader fileReader=null;  
		  BufferedReader bufferedReader=null;  
		  File fileName = new File("e:/record.txt");
		  System.out.println(fileName.getAbsolutePath());
		  try{  
			  if(!fileName.exists()){  
				    fileName.createNewFile();
				    return "";
				}		   
		   fileReader=new FileReader(fileName);  
		   bufferedReader=new BufferedReader(fileReader);  
		   String read=null;  
		    while((read=bufferedReader.readLine())!=null){  
		     result=read; 
		    }
		    if(bufferedReader!=null){  
			    bufferedReader.close();  
			   }  
			   if(fileReader!=null){  
			    fileReader.close();  
			   }  
		  }catch(Exception e){  
		   e.printStackTrace();  
		  } 
		  System.out.println("璇诲彇鍑烘潵鐨勬枃浠跺唴瀹规槸锛�"+"\r\n"+result);  
		  return result;  
		 }  

}
