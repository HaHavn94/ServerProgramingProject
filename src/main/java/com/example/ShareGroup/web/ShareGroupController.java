package com.example.ShareGroup.web;


import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ShareGroup.domain.ItemRepository;



@Controller
public class ShareGroupController {
	@Autowired
    private ItemRepository itemRepo;
    
	// Show login page
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	@RequestMapping(value = { "/", "/afterLogin" })
	public String ItemList(Model model) {
		model.addAttribute("items", itemRepo.findAll());	
		return "afterLogin";
	}
	
 
}
	    
