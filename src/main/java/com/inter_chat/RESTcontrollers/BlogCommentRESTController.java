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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.dao.BlogCommentDAO;
import com.inter_chat.Inter_Chat_Backend.model.BlogComment;

@RestController
public class BlogCommentRESTController {
	@Autowired
	BlogCommentDAO blogCommentDAO;

	// working
	@GetMapping("/showAllBlogComment/{blogId}")
	public ResponseEntity<List<BlogComment>> showAllBlogComment(@PathVariable("blogId") int blogId) {
		List<BlogComment> listBlogComment = blogCommentDAO.listBlogComment(blogId);

		if (listBlogComment.size() > 0)
			return new ResponseEntity(listBlogComment, HttpStatus.OK);
		else
			return new ResponseEntity(listBlogComment, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/addBlogComment")
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment) {
		blogComment.setCommentDate(new Date());

		if (blogCommentDAO.addBlogComment(blogComment))
			return new ResponseEntity<String>("Comment Added to Blog", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Comment Not Added to Blog", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/deleteBlogComment/{commentId}")
	public ResponseEntity<String> deleteBlogComment(@PathVariable("commentId") int commentId) {
		BlogComment blogComment = blogCommentDAO.getBlogComment(commentId);

		if (blogCommentDAO.deleteBlogComment(blogComment))
			return new ResponseEntity("Comment Deleted from Blog", HttpStatus.OK);
		else
			return new ResponseEntity("Comment Deleted from Blog", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}