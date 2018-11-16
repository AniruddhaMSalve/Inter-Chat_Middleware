//Complete and Working

package com.inter_chat.RESTcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.dao.FriendDAO;
import com.inter_chat.Inter_Chat_Backend.model.Friend;
import com.inter_chat.Inter_Chat_Backend.model.UserDetail;

@RestController
public class FriendRESTController 
{

	@Autowired
	FriendDAO friendDAO;

	@PostMapping(value="/sendFriendRequest")
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		if(friendDAO.sendFriendRequest(friend))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
			
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	//Working
	@GetMapping(value="/showFriendList/{loginName}")
	public ResponseEntity<List<Friend>> showFriendList(@PathVariable("loginName")String loginName)
	{
		List<Friend> listFriend=friendDAO.listFriend(loginName);
		if(listFriend.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listFriend,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listFriend,HttpStatus.NOT_FOUND);
			
		}
	}
	
	//Working
	@GetMapping(value="/showPendingFriendRequest/{loginName}")
	public ResponseEntity<List<Friend>> showPendingFriendList(@PathVariable("loginName")String loginName)
	{
		List<Friend> listPendingFriendRequest=friendDAO.pendingFriend(loginName);
		if(listPendingFriendRequest.size()>0)
		{
			return new ResponseEntity<List<Friend>>(listPendingFriendRequest,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(listPendingFriendRequest,HttpStatus.NOT_FOUND);
		}
	}
	
	//Working
	@GetMapping(value="/showSuggestedFriendList/{loginName}")
	public ResponseEntity<List<UserDetail>> showSuggestedFriendList(@PathVariable("loginName")String loginName)
	{
		List<UserDetail> listSuggestedFriendList=friendDAO.suggestedFriend(loginName);
		if(listSuggestedFriendList.size()>0)
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriendList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(listSuggestedFriendList,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/acceptFriendRequest/{friendId}")
	public ResponseEntity<String> acceptFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/deleteFriendRequest/{friendId}")
	public ResponseEntity<String> deleteFriendRequest(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
}