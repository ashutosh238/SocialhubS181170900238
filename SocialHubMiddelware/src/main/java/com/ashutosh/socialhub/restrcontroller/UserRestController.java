package com.ashutosh.socialhub.restrcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.socialhub.dao.UserDAO;
import com.ashutosh.socialhub.domain.UserDetail;

@RestController
public class UserRestController

	{	@Autowired
		UserDAO userDAO;
	
		@PostMapping("/registerUser")
		public ResponseEntity<String> registerUser(@RequestBody UserDetail userDetail)
		{
			userDetail.setRoles("ROLE_USER");
			if(userDAO.registerUser(userDetail))
			{
				return new ResponseEntity<String>("Success",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Faliure",HttpStatus.NOT_FOUND);
			}
		}
		/*@PostMapping("/checkLogin")
		public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail userDetail)
		{
			UserDetail tempUserDetail=userDAO.getUser(userDetail.getLoginname());
			
			if(tempUserDetail!=null)
				
			{
				session.setAttribute("userDetail",tempUserDetail);
				return new ResponseEntity<UserDetail>(tempUserDetail,HttpStatus.OK);
				
			}
			
		}*/
		
		
	}
