package com.fajarmf.restapp.ticketmanagement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fajarmf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fajarmf.service.SecurityService;
import com.fajarmf.service.UserService;
import com.fajarmf.util.Util;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userSevice;
	
	@Autowired
	SecurityService securityService;

	@ResponseBody
	@RequestMapping("")
	public List<User> getAllUsers() {
		return userSevice.getAllUsers();
	}
	
	@ResponseBody
	@RequestMapping(value = "/register/customer", method = RequestMethod.POST)
	public Map<String, Object> registerCustomer(
	@RequestParam(value = "username") String username,
	@RequestParam(value = "password") String password
	) {
		userSevice.createUser(username, password, 1);
		return Util.getSuccessResult();
	}
	
	@ResponseBody
	@RequestMapping(value = "/register/admin", method = RequestMethod.POST)
	public Map<String, Object> registerAdmin(
	@RequestParam(value = "username") String username,
	@RequestParam(value = "password") String password
	) {
		Map<String, Object> map = new LinkedHashMap<>();
		userSevice.createUser(username, password, 3); // 3 - admin (usertype)
		map.put("result", "added");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/register/csr", method = RequestMethod.POST)
	public Map<String, Object> registerCSR(
	@RequestParam(value = "username") String username,
	@RequestParam(value = "password") String password
	) {
		userSevice.createUser(username, password, 2);
		return Util.getSuccessResult();
	}

	@ResponseBody
	@RequestMapping("/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		return userSevice.getUser(id);
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> createUser(
		@RequestParam(value = "userid") Integer userid,
		@RequestParam(value = "username") String username){
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			userSevice.createUser(userid, username);
			map.put("result", "Berhasil ditambahkan");
			return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/customer", method = RequestMethod.POST)
	public Map<String, Object> loginCustomer(
	@RequestParam(value = "username") String username,
	@RequestParam(value = "password") String password
	) {
		User user = userSevice.getUser(username, password, 1);
		if(user == null){
			return Util.getUserNotAvailableError();
		}
		String subject = user.getUserid()+"="+user.getUserType();
		String token = securityService.createToken(subject, (15 * 1000 * 60));
		return Util.getSuccessResult(token);
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/admin", method = RequestMethod.POST)
	public Map<String, Object> loginAdmin(
	@RequestParam(value = "username") String username,
	@RequestParam(value = "password") String password
	) {
		Map<String, Object> map = new LinkedHashMap<>();
		User user = userSevice.getUser(username, password, 3);
		if(user == null){
			return Util.getUserNotAvailableError();
		}
		String subject = user.getUserid()+"="+user.getUserType();
		String token = securityService.createToken(subject, (15 * 1000 * 60));
		map.put("result_code", 0);
		map.put("result", "success");
		map.put("token", token);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login/csr", method = RequestMethod.POST)
	public Map<String, Object> loginCSR(
	@RequestParam(value = "username") String username,
	@RequestParam(value = "password") String password
	) {
		User user = userSevice.getUser(username, password, 2);
		if(user == null){
			return Util.getUserNotAvailableError();
		}
		String subject = user.getUserid()+"="+user.getUserType();
		String token = securityService.createToken(subject, (15 * 1000 * 60));
		return Util.getSuccessResult(token);
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Map<String, Object> updateUser(
			@RequestParam(value = "userid") Integer userid,
			@RequestParam(value = "username") String username){
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				userSevice.updateUser(userid, username);
				map.put("result", "Berhasil diupdate");
				return map;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteUser(
			@PathVariable("id") Integer userid){
				Map<String, Object> map = new LinkedHashMap<String, Object>();   
				userSevice.deleteUser(userid);    
				map.put("result", "Berhasil dihapus");
				return map;
	}

}