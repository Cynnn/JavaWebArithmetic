package com.example.ArithmeticWeb;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import question.Question;

public class ArthmeticServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetQuestionList() {
		Question question1 = new Question(3);		
		System.out.println(question1.getExpression());
		System.out.println(question1.printResult());
		
		Question question2 = new Question(5);		
		System.out.println(question2.getExpression());
		System.out.println(question2.printResult());
		
		Question question3 = new Question(8);		
		System.out.println(question3.getExpression());
		System.out.println(question3.printResult());
		
		fail("Not yet implemented");
	}

}
