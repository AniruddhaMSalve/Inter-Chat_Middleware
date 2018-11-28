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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.dao.ForumDAO;
import com.inter_chat.Inter_Chat_Backend.model.Forum;
import com.inter_chat.Inter_Chat_Backend.model.Forum;
import com.inter_chat.Inter_Chat_Backend.model.Forum;
import com.inter_chat.Inter_Chat_Backend.model.Forum;

@RestController
public class ForumRESTController {
	@Autowired
	ForumDAO forumDAO;

	// working
	@GetMapping("/showAllForum")
	public ResponseEntity<List<Forum>> listForum() {
		List<Forum> listForum = forumDAO.listForum();
		if (listForum.size() > 0)
			return new ResponseEntity<List<Forum>>(listForum, HttpStatus.OK);
		else
			return new ResponseEntity<List<Forum>>(listForum, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// working
			@GetMapping("/showMyForum/{loginName}")
			public ResponseEntity<List<Forum>> showMyForum(@PathVariable("loginName")String loginName) {
				List<Forum> listMyForum = forumDAO.listUserForum(loginName);
				if (listMyForum.size() > 0)
					return new ResponseEntity<List<Forum>>(listMyForum, HttpStatus.OK);
				else
					return new ResponseEntity<List<Forum>>(listMyForum, HttpStatus.INTERNAL_SERVER_ERROR);
			}

	// working
	@PostMapping("/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum) {
		forum.setCreateDate(new Date());

		if (forumDAO.addForum(forum))
			return new ResponseEntity<String>("Forum Added", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Forum Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PutMapping("/updateForum/{forumId}")
	public ResponseEntity<String> updateForum(@PathVariable("forumId") int forumId, @RequestBody Forum forum) {
		Forum forum1 = forumDAO.getForum(forumId);
		forum1.setCreateDate(new Date());
		forum1.setForumContent(forum.getForumContent());
		forum1.setForumName(forum.getForumName());
		forum1.setLoginName(forum.getLoginName());
		forum1.setStatus(forum.getStatus());

		if (forumDAO.updateForum(forum1))
			return new ResponseEntity<String>("Forum Updated", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Forum Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/deleteForum/{forumId}")
	public ResponseEntity<String> deleteForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);

		if (forumDAO.deleteForum(forum))
			return new ResponseEntity<String>("Forum Deleted", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Forum Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/approveForum/{forumId}")
	public ResponseEntity<String> approveForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);

		if (forumDAO.approveForum(forum))
			return new ResponseEntity<String>("Forum Approved", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Forum Not Approved", HttpStatus.OK);
	}

	// working
	@GetMapping("/rejectForum/{forumId}")
	public ResponseEntity<String> rejectForum(@PathVariable("forumId") int forumId) {
		Forum forum = forumDAO.getForum(forumId);

		if (forumDAO.rejectForum(forum))
			return new ResponseEntity<String>("Forum rejected", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Forum not rejected", HttpStatus.OK);
	}

	// Working
	@GetMapping("getForum/{forumId}")
	public ResponseEntity<Forum> getAForum(@PathVariable("forumId") int forumId) {
		Forum forum = (Forum) forumDAO.getForum(forumId);
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
}