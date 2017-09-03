package com.mockapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mockapi.dto.MockRequestDTO;

@Controller
public class MockController {
	
	@RequestMapping(value = "/welcome")
    public String home(Model model) {
        model.addAttribute("mockReq", new MockRequestDTO());
        return "welcome";
    }
	

}