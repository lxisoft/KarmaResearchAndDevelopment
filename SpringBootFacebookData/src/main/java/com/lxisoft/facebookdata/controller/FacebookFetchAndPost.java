package com.lxisoft.facebookdata.controller;

import java.util.List;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Permission;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacebookFetchAndPost {

	private Facebook facebook;
	private ConnectionRepository connectionRepository;

	public FacebookFetchAndPost(Facebook facebook, ConnectionRepository connectionRepository) {
		this.facebook = facebook;
		this.connectionRepository = connectionRepository;
	}

	@GetMapping("/")
	public String getFacebookFeed(Model model) {
		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
			return "redirect:/connect/facebook";
		}
		PagedList<Post> feed = facebook.feedOperations().getFeed();
		List<Permission> permisions = facebook.userOperations().getUserPermissions();

		for (Permission p : permisions) {
			System.out.println("***************" + p.getName());
		}

		facebook.feedOperations().updateStatus("helooooooooooooooooo friends");
		model.addAttribute("feed", feed);
		return "hello";
	}

}
