//Complete and Working

package com.inter_chat.RESTcontrollers;

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

import com.inter_chat.Inter_Chat_Backend.dao.FriendDAO;
import com.inter_chat.Inter_Chat_Backend.model.Friend;

@RestController
public class FriendRESTController {
	@Autowired
	FriendDAO friendDAO;

	// working
	@GetMapping("/showAllFriend")
	public ResponseEntity<List<Friend>> showAllFriend() {
		List<Friend> listFriend = friendDAO.listFriend();

		if (listFriend.size() > 0)
			return new ResponseEntity(listFriend, HttpStatus.OK);
		else
			return new ResponseEntity(listFriend, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/addFriend")
	public ResponseEntity<String> addFriend(@RequestBody Friend friend) {

		if (friendDAO.addFriend(friend))
			return new ResponseEntity("Friend Added", HttpStatus.OK);
		else
			return new ResponseEntity("Friend Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PutMapping("/updateFriend/{friendId}")
	public ResponseEntity<String> updateFriend(@PathVariable("friendId") int friendId, @RequestBody Friend friend) {
		Friend friend1 = friendDAO.getFriend(friendId);
		friend1.setFriendLoginName(friend.getFriendLoginName());
		friend1.setLoginName(friend.getLoginName());
		friend1.setStatus(friend.getStatus());

		if (friendDAO.updateFriend(friend1))
			return new ResponseEntity("Friend Updated", HttpStatus.OK);
		else
			return new ResponseEntity("Friend Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/deleteFriend/{friendId}")
	public ResponseEntity<String> deleteFriend(@PathVariable("friendId") int friendId) {
		Friend friend = friendDAO.getFriend(friendId);

		if (friendDAO.deleteFriend(friend))
			return new ResponseEntity("Friend Deleted", HttpStatus.OK);
		else
			return new ResponseEntity("Friend Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}