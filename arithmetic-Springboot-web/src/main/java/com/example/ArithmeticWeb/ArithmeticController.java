package com.example.ArithmeticWeb;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import question.Question;

@Controller
public class ArithmeticController {	
	
	@Autowired
	private ArthmeticService arthmeticService;
	
	@RequestMapping(value = {"","/login"},method = RequestMethod.GET)
    public String login(){  
        return "login";  
    }
	
	@RequestMapping("/")
    public String index(Model model) {
        ArrayList<Question> Expression = arthmeticService.getQuestionList(10);
        model.addAttribute("expression", Expression);
        return "index";
    }

}
