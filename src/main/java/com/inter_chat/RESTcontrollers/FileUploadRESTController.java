package com.inter_chat.RESTcontrollers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.inter_chat.Inter_Chat_Backend.dao.ProfilePictureDAO;
import com.inter_chat.Inter_Chat_Backend.model.ProfilePicture;
import com.inter_chat.Inter_Chat_Backend.model.UserDetail;

@RestController
public class FileUploadRESTController 
{
	@Autowired
	ProfilePictureDAO profilePictureDAO;
	
	@RequestMapping(value="/doUpload",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam(value="file") CommonsMultipartFile fileUpload, HttpSession session)
	{
		UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		System.out.println("File Byte Length :"+fileUpload.getBytes().length);
		if(userDetail==null)
		{
			return new ResponseEntity<String>("Unauthorized User",HttpStatus.NOT_FOUND);
		}
		else
		{
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setLoginName(userDetail.getLoginName());
			profilePicture.setImage(fileUpload.getBytes());
			profilePictureDAO.save(profilePicture);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImage/{loginName}",method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@PathVariable("loginName")String loginName,HttpSession session)
	{
		UserDetail userDetail=(UserDetail)session.getAttribute("userDetail");
		if(userDetail==null)
		{
			return null;
		}
		else
		{
			ProfilePicture profilePicture=profilePictureDAO.getProfilePicture(loginName);
			if(profilePicture!=null)
			{
				return profilePicture.getImage();
			}
			else
			{
				return null;
			}
		}
	}
}