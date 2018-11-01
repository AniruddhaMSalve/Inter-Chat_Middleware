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

import com.inter_chat.Inter_Chat_Backend.dao.ForumCommentDAO;
import com.inter_chat.Inter_Chat_Backend.model.ForumComment;

@RestController
public class ForumCommentRESTController {
	@Autowired
	ForumCommentDAO forumCommentDAO;

	// working
	@GetMapping("/showAllForumComment/{forumId}")
	public ResponseEntity<List<ForumComment>> showAllForumComment(@PathVariable("forumId") int forumId) {
		List<ForumComment> listForumComment = forumCommentDAO.listForumComment(forumId);

		if (listForumComment.size() > 0)
			return new ResponseEntity(listForumComment, HttpStatus.OK);
		else
			return new ResponseEntity(listForumComment, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/addForumComment")
	public ResponseEntity<String> addForumComment(@RequestBody ForumComment forumComment) {
		forumComment.setCommentDate(new Date());

		if (forumCommentDAO.addForumComment(forumComment))
			return new ResponseEntity("Comment Added to Forum", HttpStatus.OK);
		else
			return new ResponseEntity("Comment Added to Forum", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/deleteForumComment/{commentId}")
	public ResponseEntity<String> deleteForumComment(@PathVariable("commentId") int commentId) {
		ForumComment forumComment = forumCommentDAO.getForumComment(commentId);

		if (forumCommentDAO.deleteForumComment(forumComment))
			return new ResponseEntity("Comment Deleted from Forum", HttpStatus.OK);
		else
			return new ResponseEntity("Comment Deleted from Forum", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}