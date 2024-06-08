package com.project.urlshortener.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.urlshortener.ShortURLRepository;
import com.project.urlshortener.model.UrlShortnerModel;

@Service
public class UrlShortenerService {
	
	@Value("${urlshortener.baseUrl}")
	String baseUrl;
	
	@Autowired
	ShortURLRepository shortURLRepository;
	
	public String getUrlShortnerService(String longUrl) {
		return createShortUrl(longUrl);
	}
	
	public String createShortUrl(String longUrl) {
		String hashCode = Integer.toString(longUrl.hashCode() & Integer.MAX_VALUE);
		UrlShortnerModel urlShortner = new UrlShortnerModel();
		urlShortner.setLongUrl(longUrl);
		urlShortner.setHashKey(hashCode);
		shortURLRepository.save(urlShortner);
		return baseUrl +"/" + hashCode;	
	}
	
	public String getOriginalUrl(String id) {
		UrlShortnerModel urlShortModel =  shortURLRepository.findById(id).orElse(null);
		return urlShortModel.getLongUrl();
	}
	
}
