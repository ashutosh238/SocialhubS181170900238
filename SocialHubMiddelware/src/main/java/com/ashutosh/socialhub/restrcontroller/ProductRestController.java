package com.ashutosh.socialhub.restrcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		@GetMapping("/getBlog/{blogid}")
		public ResponseEntity<Blog> getBlog(@PathVariable("blogid")int blogid)
		{
			Blog blog=blogDAO.get(blogid);
			if(blog!=null)
			{
				return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
			}
		}
		@RequestMapping("/approvedList")
		public ResponseEntity<List<Blog>> approvedBlogList()
		{
			List<Blog> approvedBlogs = blogDAO.approvedBlogsList();
			if(approvedBlogs.isEmpty())
			{
				return new ResponseEntity<List<Blog>>(approvedBlogs, HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<List<Blog>>(approvedBlogs, HttpStatus.OK);
			}
	}
		
		@RequestMapping("/blog/approve/{blogid}")
		public ResponseEntity<Blog> approveBlog(@PathVariable int blogid)
		{
			if(blogDAO.approveBlog(blogid))
			{
				Blog blog = blogDAO.get(blogid);
				blog.setStatus("Approved");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
				Blog blog = blogDAO.get(blogid);
				blog.setStatus("Could not Approved, Error Occurred");
				return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@RequestMapping("/blog/reject/{blogid}")
		public ResponseEntity<Blog> rejectBlog(@PathVariable int blogid)
		{
			if(blogDAO.rejectBlog(blogid))
			{
				Blog blog = blogDAO.get(blogid);
				blog.setStatus("Rejected");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
				Blog blog = blogDAO.get(blogid);
				blog.setStatus("Could not performed operation successfully");
				return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@RequestMapping("blog/incLike/{blogid}")
		public ResponseEntity<Blog> incLike(@PathVariable int blogid)
		{
			if(blogDAO.incLikes(blogid))
			{
				Blog blog = blogDAO.get(blogid);
				blog.setStatus("Like incremented successfully");
				return new ResponseEntity<Blog>(blog, HttpStatus.OK);
			}
			else
			{
				Blog blog = blogDAO.get(blogid);
				blog.setStatus("Error Occurred");
				return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		

		

	}
