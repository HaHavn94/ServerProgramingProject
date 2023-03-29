package com.example.ShareGroup.web;


import java.util.List;






import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ShareGroup.domain.AppUser;
import com.example.ShareGroup.domain.AppUserRepository;

import com.example.ShareGroup.domain.Item;
import com.example.ShareGroup.domain.ItemRepository;
import com.example.ShareGroup.domain.SignupForm;

import jakarta.validation.Valid;



@Controller
public class ShareGroupController {
	@Autowired
    private ItemRepository itemRepo;
	
	@Autowired
    private AppUserRepository userRepo;

	
	private String name;
	private AppUser currentUser;
	private List<Item> listItem;
	private List<Item> listBorrow ;
			
	// Show login page
	
   @RequestMapping(value="/login")
   public String login(){
      return "login";
   }	
   	
	@RequestMapping(value = { "/", "/afterLogin" })
	public String ItemList(Model model) {
		 name=SecurityContextHolder.getContext().getAuthentication().getName();	
		 currentUser= userRepo.findByUsername(name);
		 model.addAttribute("items", itemRepo.findByStatus("available"));
		return "afterLogin";
	}
	
	
	  @RequestMapping(value="/AddItem")
	    public String addItem(Model model){
	    	model.addAttribute("item", new Item());
	        return "AddItem";
	    }
	  
	  @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveNewItem(Item item){	 		 
		    item.setStatus("available");
		    item.setAppuser(currentUser);
		    item.setBorrower(currentUser);
	        itemRepo.save(item);
	        return "redirect:afterLogin";
	    } 
	  
	  @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	  @PreAuthorize("hasAuthority('userGroup')")
	    public String deleteItem(@PathVariable("id") Long id){
		itemRepo.deleteById(id);
		        return "redirect:../afterLogin";
	    }
	  @RequestMapping(value="/deleteInMyCart/{id}", method = RequestMethod.GET)
	  @PreAuthorize("hasAuthority('userGroup')")
	    public String deleteItemInMyCart(@PathVariable("id") Long id){
		itemRepo.deleteById(id);
		        return "redirect:../MyCart";
	    }
	  
		@RequestMapping(value = "/MyCart" )
		public String MyCartList( Model model) {			
			 listItem = itemRepo.findAllByAppuser(currentUser);		
			listBorrow = itemRepo.findAllByBorrower(currentUser);					
			model.addAttribute("items", listItem);
			model.addAttribute("borrow",listBorrow);
			 return "MyCart";
		
		} 
	  @RequestMapping(value="/borrow/{id}", method = RequestMethod.GET)
	  @PreAuthorize("hasAuthority('userGroup')")
	    public String borrow(@PathVariable("id") Long id){	  
		 Item currentItem=  itemRepo.findById(id).get();
		 currentItem.setStatus("unavailable");	
		 currentItem.setBorrower(currentUser);	
		 itemRepo.save(currentItem);						 
		        return "redirect:../afterLogin";
	    }

	
 
	 
}
	    
