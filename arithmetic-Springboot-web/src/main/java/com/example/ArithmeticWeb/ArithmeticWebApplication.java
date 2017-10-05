package com.example.ArithmeticWeb;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@SpringBootApplication
@EnableAutoConfiguration
public class ArithmeticWebApplication {
	@RequestMapping("/")
    public String index(Model model) {
        ArrayList<ArithmeticExpression> Expression = new ArrayList<>();
        String s1 = ArithmeticExpression.random();
        String s2 = ArithmeticExpression.random();
        ArithmeticExpression e1 = new ArithmeticExpression(s1,ArithmeticController.evaluateAlgorithm(s1));
        ArithmeticExpression e2 = new ArithmeticExpression(s2,ArithmeticController.evaluateAlgorithm(s2));
        Expression.add(e1);
        Expression.add(e2);
        model.addAttribute("expression", Expression);
        return "index";
    }
	public static void main(String[] args) {
		SpringApplication.run(ArithmeticWebApplication.class, args);
	}
}
