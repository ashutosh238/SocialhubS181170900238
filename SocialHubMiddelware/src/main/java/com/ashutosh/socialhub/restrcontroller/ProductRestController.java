package com.ashutosh.socialhub.restrcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.socialhub.dao.BlogDAO;
import com.ashutosh.socialhub.domain.Blog;

@RestController
public class ProductRestController 
	{ 
		@Autowired
		BlogDAO blogDAO;
	
		@GetMapping("/listBlogs")
		public ResponseEntity<List<Blog>> listBlogs()
		{
			List<Blog> listBlogs=blogDAO.list();
			if(listBlogs.size()>0)
			{
				return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
			}
		
		}
		
		@PostMapping(value="/addBlog")
		public ResponseEntity<String> insertBlog(@RequestBody Blog blog)
		{
			blog.setDislikes(0);
			blog.setLikes(0);
			blog.setStatus("NA");
			if(blogDAO.save(blog)) 
			{
				return new ResponseEntity<String>("Success", HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
				
			}
			
		}

	}
