package com.greatlearning.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.library.entity.Book;
import com.greatlearning.library.service.BookService;


@Controller
@RequestMapping("/books")
public class BooksController {
	

	@Autowired
	private BookService bookService;



	// add mapping for "/list"

	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		

		// get Books from db
		List<Book> theBooks = bookService.findAll();

		// add to the spring model
		theModel.addAttribute("books", theBooks);
		return "Books";
	}

	@RequestMapping("/addBook")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Book theBook = new Book();

		theModel.addAttribute("book", theBook);

		return "BookForm";
	}

	
	@RequestMapping("/updateBook")
	public String showFormForUpdate(@RequestParam("bookId") Integer theId,
			Model theModel) {

		// get the Book from the service
		Book theBook = bookService.findById(theId);


		// set Book as a model attribute to pre-populate the form
		theModel.addAttribute("book", theBook);

		// send over to our form
		return "BookForm";			
	}


	@PostMapping("/save")
	public String saveBook(@RequestParam("id") Integer id,
			@RequestParam("name") String name,@RequestParam("category") String category,@RequestParam("author") String author) {

		System.out.println(id);
		Book theBook;
		if(id!=0)
		{
			theBook=bookService.findById(id);
			theBook.setName(name);
			theBook.setCategory(category);
			theBook.setAuthor(author);
		}
		else
			theBook=new Book(name, category, author);
		// save the Book
		bookService.save(theBook);


		// use a redirect to prevent duplicate submissions
		return "redirect:/books/list";

	}

	

	@RequestMapping("/deleteBook")
	public String delete(@RequestParam("bookId") Integer theId) {

		if(theId!=0)
		{
			Book theBook=bookService.findById(theId);
			bookService.delete(theBook);
		}
		
		// redirect to /Books/list
		return "redirect:/books/list";

	}


	@RequestMapping("/search")
	public String search(@RequestParam("name") String name,
			@RequestParam("author") String author,
			Model theModel) {

		// check names, if both are empty then just give list of all Books
		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			return "redirect:/books/list";
		}
		else {
			// else, search by name and last name
			List<Book> theBooks =bookService.findByName_Author(name, author);

			// add to the spring model
			theModel.addAttribute("books", theBooks);

			// send to list-Books
			return "Books";
		}
	}
	
	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user)
	{
		ModelAndView mv=new ModelAndView();
		
		if(user!=null)
		{
			mv.addObject("msg", "Hi "+user.getName()+", you are not allowed to access this page");			
		}
		else
		{
			mv.addObject("msg", "Hi, you are not allowed to access this page");	
		}
		mv.setViewName("403");
		return mv;
	}

}
