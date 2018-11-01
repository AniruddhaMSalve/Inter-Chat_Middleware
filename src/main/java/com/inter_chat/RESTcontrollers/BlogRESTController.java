//Complete and Working

package com.inter_chat.RESTcontrollers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.dao.BlogDAO;
import com.inter_chat.Inter_Chat_Backend.model.Blog;

@RestController
public class BlogRESTController {
	@Autowired
	BlogDAO blogDAO;

	// working
	@GetMapping("/showAllBlog")
	public ResponseEntity<List<Blog>> showAllBlog() {
		List<Blog> listBlog = blogDAO.listBlog();
		if (listBlog.size() > 0)
			return new ResponseEntity<List<Blog>>(listBlog, HttpStatus.OK);
		else
			return new ResponseEntity<List<Blog>>(listBlog, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog) {
		blog.setCreateDate(new Date());

		if (blogDAO.addBlog(blog))
			return new ResponseEntity<String>("Blog Added", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Blog Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PutMapping("/updateBlog/{blogId}")
	public ResponseEntity<String> updateBlog(@PathVariable("blogId") int blogId, @RequestBody Blog blog) {
		Blog blog1 = blogDAO.getBlog(blogId);
		blog1.setCreateDate(new Date());
		blog1.setBlogName(blog.getBlogName());
		blog1.setBlogDesc(blog.getBlogDesc());
		blog1.setLoginName(blog.getLoginName());
		blog1.setStatus(blog.getStatus());
		blog1.setLikes(blog.getLikes());
		blog1.setDislikes(blog.getDislikes());

		if (blogDAO.updateBlog(blog1))
			return new ResponseEntity<String>("Blog Updated", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Blog Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);

		if (blogDAO.approveBlog(blog))
			return new ResponseEntity<String>("Blog Approved", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Blog Not Approved", HttpStatus.OK);
	}

	// working
	@GetMapping("/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);

		if (blogDAO.rejectBlog(blog))
			return new ResponseEntity<String>("Blog rejected", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Blog not rejected", HttpStatus.OK);
	}

	// working
	@GetMapping("/incrementLikes/{blogId}")
	public ResponseEntity<String> incrementLikes(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);

		if (blogDAO.incrementLikes(blogId))
			return new ResponseEntity<String>("Likes Incremented", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Likes Not Incremented", HttpStatus.OK);
	}

	// working
	@GetMapping("/incrementDislikes/{blogId}")
	public ResponseEntity<String> incrementDislikes(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);

		if (blogDAO.incrementDislikes(blogId))
			return new ResponseEntity<String>("Likes Decremented", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Likes Decremented", HttpStatus.OK);
	}

	// working
	@GetMapping("/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId") int blogId) {
		Blog blog = blogDAO.getBlog(blogId);

		if (blogDAO.deleteBlog(blog))
			return new ResponseEntity<String>("Blog Deleted", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Blog Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}