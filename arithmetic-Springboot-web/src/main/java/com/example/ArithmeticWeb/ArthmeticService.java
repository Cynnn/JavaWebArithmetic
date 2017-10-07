package com.example.ArithmeticWeb;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import question.Question;
@Service
public class ArthmeticService {
	
	public ArrayList<Question> getQuestionList(int size){
		ArrayList<Question> Expression = new ArrayList<>();
		int operators_num = 2;
        for(int i=0;i<size;i++){
        	Expression.add(new Question(operators_num));
        }
        return Expression;
	}

}
