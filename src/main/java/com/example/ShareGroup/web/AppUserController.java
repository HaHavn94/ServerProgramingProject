package com.example.ShareGroup.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ShareGroup.domain.AppUser;
import com.example.ShareGroup.domain.AppUserRepository;
import com.example.ShareGroup.domain.SignupForm;
import com.example.ShareGroup.domain.UserGroupRepository;

import jakarta.validation.*;


@Controller
public class AppUserController {
	@Autowired
	private AppUserRepository repository;

	@Autowired
	private UserGroupRepository grRepo;

	@RequestMapping(value = "signup")
	public String addStudent(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}

	/**
	 * Create new user Check if user already exists & form validation
	 * 
	 * @param signupForm
	 * @param bindingResult
	 * @return
	 */
 
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);
				AppUser newUser = new AppUser();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setUsergroup(grRepo.findById((long) 2).get());
				if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}

}
