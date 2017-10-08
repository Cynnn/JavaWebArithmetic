package com.example.ArithmeticWeb;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import question.Question;

@Controller
public class ArithmeticController {	
	
	@Autowired
	private ArthmeticService arthmeticService;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){  
        return "login";  
    }
	
	@RequestMapping(value ="/number",method = RequestMethod.GET)
    public String getNumber(Model model,@RequestParam(value="Action",required=true) String action,
    		@RequestParam(value="Number",required=true)String number) {
		int num = Integer.parseInt(number);
        ArrayList<Question> Expression = arthmeticService.getQuestionList(num);
        model.addAttribute("expression", Expression);
        int right=0;
        int wrong=0;
        String str =arthmeticService.readTxtFile();
		if(str!=null&&!str.equals("")){
		String[] splits = str.split("\t");
		right = Integer.parseInt(splits[0]);
		wrong = Integer.parseInt(splits[1]);
		}
		model.addAttribute("right", right);
		model.addAttribute("wrong", wrong);
        return "index";
    }
	@RequestMapping(value ="/Arithmetic")
    public String index(Model model) {
		ArrayList<Question> Expression = arthmeticService.getQuestionList(10);
        model.addAttribute("expression", Expression);
        int right=0;
        int wrong=0;
        String str =arthmeticService.readTxtFile();
		if(str!=null&&!str.equals("")){
		String[] splits = str.split("\t");
		right = Integer.parseInt(splits[0]);
		wrong = Integer.parseInt(splits[1]);
		}
		model.addAttribute("right", right);
		model.addAttribute("wrong", wrong);
        return "index";
    }
	@RequestMapping(value ="/index",method = RequestMethod.POST)
    public String chkform(@RequestParam(value="Action",required=true)String action,
    		@RequestParam(value="Right",required=true)int right,
    		@RequestParam(value="Wrong",required=true)int wrong) {
		String str =arthmeticService.readTxtFile();
		if(str!=null&&!str.equals("")){
		String[] splits = str.split("\t");
		int r = Integer.parseInt(splits[0]);
		int w = Integer.parseInt(splits[1]);
		right+=r;
		wrong+=w;
		}
		arthmeticService.writeTxtFile(right, wrong);
        return "login";
    }

}
