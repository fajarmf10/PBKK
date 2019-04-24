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

import com.fajarmf.service.UserService;
import com.fajarmf.util.Util;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userSevice;

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