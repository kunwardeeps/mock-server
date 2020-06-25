package com.mockapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mockapi.dto.MockResponseDTO;

@Controller
public class MockController {
	
	@RequestMapping(value = "/welcome")
    public String home(Model model) {
        model.addAttribute("mockReq", new MockResponseDTO());
        return "welcome";
    }


}