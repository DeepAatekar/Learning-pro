package com.luv2code.springbootlibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springbootlibrary.config.SecurityConfiguration;
import com.luv2code.springbootlibrary.entity.Book;
import com.luv2code.springbootlibrary.service.BookService;
import com.luv2code.springbootlibrary.utils.ExtractJWT;

@CrossOrigin("http://localhost:3000")	
@RestController
@RequestMapping("/api/books")
public class BookController 
{
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	
	private BookService bookService;
	
	
	@Autowired
	public BookController(BookService bookService) 
	{
		this.bookService = bookService;
	}
	
	
	@GetMapping("/secure/currentloans/count")
	public int currentLoansCount(@RequestHeader(value = "Authorization") String token) 
	{
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.currentLoansCount(userEmail);
	}
	
	
	@GetMapping("/secure/ischeckedout/byuser")
	public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token,  @RequestParam Long bookId) 
	{
		logger.info("Token details:{}",token);
		
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		logger.info("UserEmail details:{}",userEmail);
		return bookService.checkoutBookByUser(userEmail, bookId);
	}
	
	
	@PutMapping("/secure/checkout") 
	public Book checkoutBook(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId) throws Exception
	{
		
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.checkoutBook(userEmail, bookId);
	}
}
