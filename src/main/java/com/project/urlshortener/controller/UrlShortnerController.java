package com.project.urlshortener.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.project.urlshortener.service.UrlShortenerService;

@Controller
public class UrlShortnerController {
	
	@Autowired
	UrlShortenerService urlShortenerService;
	
	@GetMapping("/urlShortener")
	public String showUrlShortenerForm(Model model) {
	    model.addAttribute("shortUrl", null); // Initialize shortUrl attribute
	    return "urlShortener"; // Return the name of the Thymeleaf template
	}
	
	@GetMapping("/urlShortener/getShortUrl")
	public String getShortUrl(@RequestParam(name = "longUrl", required = true) String longUrl, Model model){
		
		String shortUrl = urlShortenerService.getUrlShortnerService(longUrl);
	    model.addAttribute("shortUrl", shortUrl); // Add shortUrl to model
		 return "urlShortener";
	}
	
	@GetMapping("/{id}")
	public RedirectView redirectToOriginalUrl(@PathVariable String id){
		
		 String originalUrl = urlShortenerService.getOriginalUrl(id);
		Map<String, String> response = new HashMap<>();
		response.put("originalUrl", originalUrl);
		 return new RedirectView(originalUrl);
	}
}