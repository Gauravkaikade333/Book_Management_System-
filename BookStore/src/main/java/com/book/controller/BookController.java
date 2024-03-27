package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.book.entity.Book;
import com.book.entity.MyBookList;
import com.book.service.BookService;
import com.book.service.MyBookListService;

@Controller
public class BookController {
	@Autowired
	private BookService bService;
	
	@Autowired
	private MyBookListService myBookListService;
	@GetMapping("")
	public String Disply2() {
		System.out.println("main page");
		return "hello2";
	}
 @GetMapping("book_register")
 public String bookRegister() {
	 System.out.println("new bookRegister");
	 return "bookRegister";
 }
 @GetMapping("available_Book")
 public ModelAndView getAllBook() {
	 System.out.println("Available Book");
	List<Book> list = bService.getAllBook();
	
//	ModelAndView m= new ModelAndView();   
//	m.setViewName("myBook");
//	m.addObject("book", list);
// insted of above three line we can write in single line below by creating and passing the three of value in that
	return new ModelAndView("availableBook","book", list);
 }
 @GetMapping("my_book")
 public String getMyBooks(Model model) {
	 System.out.println("my book");
	 List<MyBookList> list=myBookListService.getAllMyBooks();
	 model.addAttribute("book", list);
	 return "myBook";
 }
 @PostMapping("/save")
public String addBook(@ModelAttribute Book b) {
	 bService.save(b);
	 return "redirect:/available_Book";
 } 
 @RequestMapping("/myList/{id}")
 public String getMyList(@PathVariable("id") int id) {
	 Book b=bService.getBookById(id);
	 MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
	 myBookListService.saveMyBooks(mb);
	return "redirect:/my_book";
 }
 @RequestMapping("/editBook/{id}")
 public String editBook(@PathVariable("id") int id, Model model) {
Book b=	bService.getBookById(id);
model.addAttribute("book",b);
	 return "bookEdit";
 }
}
