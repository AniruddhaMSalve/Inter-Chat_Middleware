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

	// working
	@PostMapping("/addUserDetail")
	public ResponseEntity<String> addUserDetail(@RequestBody UserDetail userDetail) {
		if (userDetailDAO.addUser(userDetail))
			return new ResponseEntity("User Detail Added", HttpStatus.OK);
		else
			return new ResponseEntity("User Detail Not Added", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@PutMapping("/updateUserDetail/{username}")
	public ResponseEntity<String> updateUserDetail(@PathVariable("username") String username,
			@RequestBody UserDetail userDetail) {
		UserDetail userDetail1 = userDetailDAO.getUserDetail(username);
		userDetail1.setAddress(userDetail.getAddress());
		userDetail1.setEmailId(userDetail.getEmailId());
		userDetail1.setMobileNo(userDetail.getMobileNo());
		userDetail1.setRole(userDetail.getRole());
		userDetail1.setLoginName(userDetail.getLoginName());
		userDetail1.setPassword(userDetail.getPassword());

		if (userDetailDAO.updateUser(userDetail1))
			return new ResponseEntity("User Detail Updated", HttpStatus.OK);
		else
			return new ResponseEntity("User Detail Not Updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// working
	@GetMapping("/deleteUserDetail/{username}")
	public ResponseEntity<String> deleteUserDetail(@PathVariable("username") String username) {
		UserDetail userDetail = userDetailDAO.getUserDetail(username);

		if (userDetailDAO.deleteUser(userDetail))
			return new ResponseEntity("User Detail Deleted", HttpStatus.OK);
		else
			return new ResponseEntity("User Detail Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/checkUserDetail")
	public ResponseEntity<UserDetail> checkUser(@RequestBody UserDetail userDetail, HttpSession session) {
		UserDetail userDetail1 = userDetailDAO.checkUserValidation(userDetail.getUsername(), userDetail.getPassword());
		if (userDetail1 != null) {
			session.setAttribute("userDetail", userDetail1);
			return new ResponseEntity<UserDetail>(userDetail1, HttpStatus.OK);

		} else {
			return new ResponseEntity<UserDetail>(userDetail1, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}