//Complete and Working

package com.inter_chat.RESTcontrollers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inter_chat.Inter_Chat_Backend.dao.UserDetailDAO;
import com.inter_chat.Inter_Chat_Backend.model.UserDetail;

@RestController
public class UserDetailRESTController {
	@Autowired
	UserDetailDAO userDetailDAO;

	// working
	@GetMapping("/showAllUserDetail")
	public ResponseEntity<List<UserDetail>> showAllUserDetail() {
		List<UserDetail> listUserDetail = userDetailDAO.listUserDetail();

		if (listUserDetail.size() > 0)
			return new ResponseEntity(listUserDetail, HttpStatus.OK);
		else
			return new ResponseEntity(listUserDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getUserDetail/{loginName}")
	public ResponseEntity<List<UserDetail>> getUserDetail(@PathVariable("loginName") String loginName) {

		UserDetail userDetail = userDetail = userDetailDAO.getUserDetail(loginName);

		if (userDetail != null)
			return new ResponseEntity(userDetail, HttpStatus.OK);
		else
			return new ResponseEntity(userDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/addUserDetail")
	public ResponseEntity<String> addUserDetail(@RequestBody UserDetail userDetail) {
		if (userDetailDAO.addUser(userDetail))
			return new ResponseEntity("User Detail Added", HttpStatus.OK);
		else
			return new ResponseEntity("User Detail Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Working
	@PutMapping(value = "/updateUserDetail/{loginName}")
	public ResponseEntity<String> updateUserDetail(@PathVariable("loginName") String loginName,
			@RequestBody UserDetail userDetail) {
		System.out.println("In updating user " + loginName);
		UserDetail mUser = userDetailDAO.getUserDetail(loginName);
		if (mUser == null) {
			System.out.println("No user found with loginName " + loginName);
			return new ResponseEntity<String>("No user found", HttpStatus.NOT_FOUND);
		}

		mUser.setUsername(userDetail.getUsername());
		mUser.setEmailId(userDetail.getEmailId());
		mUser.setMobileNo(userDetail.getMobileNo());
		mUser.setPassword(userDetail.getPassword());
		mUser.setAddress(userDetail.getAddress());
		userDetailDAO.updateUser(mUser);
		return new ResponseEntity<String>("User updated successfully", HttpStatus.OK);
	}

	// working
	@GetMapping("/deleteUserDetail/{loginName}")
	public ResponseEntity<String> deleteUserDetail(@PathVariable("loginName") String loginName) {
		UserDetail userDetail = userDetailDAO.getUserDetail(loginName);

		if (userDetailDAO.deleteUser(userDetail))
			return new ResponseEntity("User Detail Deleted", HttpStatus.OK);
		else
			return new ResponseEntity("User Detail Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PostMapping("/checkUserDetail")
	public ResponseEntity<UserDetail> checkUserDetail(@RequestBody UserDetail userDetail, HttpSession session) {
		UserDetail userDetail1 = userDetailDAO.checkUserValidation(userDetail.getLoginName(), userDetail.getPassword());
		if (userDetail1 != null) {
			session.setAttribute("userDetail", userDetail1);
			return new ResponseEntity<UserDetail>(userDetail1, HttpStatus.OK);

		} else {
			return new ResponseEntity<UserDetail>(userDetail1, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}