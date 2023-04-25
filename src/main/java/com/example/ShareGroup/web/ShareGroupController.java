package com.example.ShareGroup.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.ShareGroup.domain.AppUser;
import com.example.ShareGroup.domain.AppUserRepository;
import com.example.ShareGroup.domain.Item;
import com.example.ShareGroup.domain.ItemRepository;

@Controller
public class ShareGroupController {

	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private AppUserRepository userRepo;

	private String name;
	private AppUser currentUser;
	private AppUser admin;
	private List<Item> listItem;
	private List<Item> listBorrow;
	private String status;
	private AppUser borr;

	// RESTful service to get all items ONLY SHOW AFTER LOGIN, USER JSONIGNORE to
	// hide information that link to user object for example password
	

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public @ResponseBody List<Item> itemListtRest() {
		return (List<Item>) itemRepo.findAll();
	}

	// RESTful service to get all items ONLY SHOW AFTER LOGIN BY ADMIN ACCOUNT

	@RequestMapping(value = "/appusers", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('adminGroup')")
	public @ResponseBody List<AppUser> itemUsertRest() {
		return (List<AppUser>) userRepo.findAll();
	}

	// RESTful Get item by Id after login,USER JSONIGNORE to hide information that
	// link to user object for example password
	
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('adminGroup')")
	public @ResponseBody Optional<Item> findItemRest(@PathVariable("id") Long itemId) {
		return itemRepo.findById(itemId);
	}

	// Show login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
     
	// Keep information about current user and show available items

	@RequestMapping(value = { "/", "/afterLogin" })
	public String ItemList(Model model) {
		
		name = SecurityContextHolder.getContext().getAuthentication().getName();
		currentUser = userRepo.findByUsername(name);
		admin = userRepo.findByUsername("admin");
		model.addAttribute("items", itemRepo.findByStatus("available"));
		return "afterLogin";
	}

	// Show add item form
	 
	@RequestMapping(value = "/AddItem")
	@PreAuthorize("hasAuthority('userGroup')")
	public String addItem(Model model) {
		model.addAttribute("item", new Item());
		return "AddItem";
	}
	 
	
	// Save the new item to itemRepository
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('userGroup')")
	public String saveNewItem(Item item) {
		item.setStatus("available");
		item.setAppuser(currentUser);
		item.setBorrower(admin);
		itemRepo.save(item);
		return "redirect:afterLogin";
	}

	// Get id from html and delete by id

	@RequestMapping(value = "/deleteInMyCart/{id}", method = RequestMethod.GET)
	public String deleteItemInMyCart(@PathVariable("id") Long id) {
		itemRepo.deleteById(id);
		return "redirect:../MyCart";
	}

	// Show current user's own items
	

	@RequestMapping(value = "/MyCart")
	@PreAuthorize("hasAuthority('userGroup')")
	public String MyCartList(Model model) {
		listItem = itemRepo.findAllByAppuser(currentUser);
		listBorrow = itemRepo.findAllByBorrower(currentUser);
		model.addAttribute("items", listItem);
		model.addAttribute("borrow", listBorrow);
		return "MyCart";

	}
	
	// Get id of chosen item and set to unavailable and change borrower

	@RequestMapping(value = "/borrow/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('userGroup')")
	public String borrow(@PathVariable("id") Long id) {
		Item currentItem = itemRepo.findById(id).get();
		currentItem.setStatus("unavailable");
		currentItem.setBorrower(currentUser);
		itemRepo.save(currentItem);
		return "redirect:../afterLogin";
	}

	// Return borrow item to admin
	
	@RequestMapping(value = "/deleteInBorrow/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('userGroup')")
	public String deleteInBorrow(@PathVariable("id") Long id) {
		Item currentItem = itemRepo.findById(id).get();

		currentItem.setStatus("available");
		currentItem.setBorrower(admin);
		itemRepo.save(currentItem);
		return "redirect:../MyCart";
	}

	// user can only edit their own item because sec:authorize in html

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("item", itemRepo.findById(id).get());
		status = itemRepo.findById(id).get().getStatus();
		borr = itemRepo.findById(id).get().getBorrower();
		return "edit";
	}

	// save the updating

	@PostMapping("/update/{id}")
	public String updateItem(Item item) {
		item.setStatus(status);
		item.setAppuser(currentUser);
		item.setBorrower(borr);
		itemRepo.save(item);
		return "redirect:../MyCart";
	}

	// show all account detail, only visible for admin

	@RequestMapping(value = { "/userAccountAdminSide" })
	@PreAuthorize("hasAuthority('adminGroup')")
	public String userAccountList(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userAccountAdminSide";
	}

	// Show all item detail and who is keeping what in admin side
	@RequestMapping(value = { "/adminPanel" })
	@PreAuthorize("hasAuthority('adminGroup')")
	public String adminPanel(Model model) {
		admin = userRepo.findByUsername("admin");
		model.addAttribute("items", itemRepo.findByStatus("available"));
		return "adminview";
	}

	// Delete user by admin

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('adminGroup')")
	public String deleteUser(@PathVariable("userId") Long userId) {
		userRepo.deleteById(userId);
		return "redirect:../userAccountAdminSide";
	}

}