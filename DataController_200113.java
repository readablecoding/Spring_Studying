package com.springlegacy.ex1;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springlegacy.ex1.vo.TestData;
import com.springlegacy.ex1.vo.TestDataLB;

@Controller
public class DataController {
	
	@RequestMapping(value="formData", method = RequestMethod.POST)
	public String formData(String str, String password, String[] hobby) {
		System.out.println(str);
		System.out.println(password);
		for(String s : hobby) {
			System.out.println(s);
		}	
		return "formData1";
	}
	
	//Lombok 사용
	@RequestMapping(value = "formDataVo", method = RequestMethod.POST)
	public String formDataVo(TestDataLB td) {
		System.out.println(td);
		return "formData1";		
	}
		
}
