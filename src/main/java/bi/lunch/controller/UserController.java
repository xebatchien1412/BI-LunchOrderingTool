package bi.lunch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.classfile.Module.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bi.lunch.constant.UserCRUDConstants;
import bi.lunch.constant.ResponseConstants;
import bi.lunch.constant.UserConstants;
import bi.lunch.entity.User;
import bi.lunch.service.IUserService;

@Controller
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class.getName());

	@Autowired
	IUserService userService;

	private boolean validateInput(String[] requiredFields, Map<Object, Object> data) {
		for (String required : requiredFields) {
			if (!data.containsKey(required)) {
				log.info(String.format("User input is missing %s", required));
				return false;
			}
		}
		return true;
	}

	private void insertMessageToClient(Map<Object, Object> result, String[] message) {
		result.put("status", message[0]);
		result.put("message", message[1]);
	}

	@PostMapping(value = "/user/get_users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> getUsers() {
		Map<Object, Object> result = new HashMap<>();
		List<User> users = userService.getAll();
		result.put("users", users);
		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/user/get_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> getUserById(@RequestBody Map<Object, Object> data) {
		Map<Object, Object> result = new HashMap<>();
		String[] requriedFields = new String[] { "userId" };
		User user = null;
		if (validateInput(requriedFields, data)) {
			user = userService.getUserById(Integer.parseInt(data.get("userId").toString()));
			if (user != null) {
				result.put("user", user);
			} else {
				insertMessageToClient(result, UserCRUDConstants.USER_NOT_FOUND);
			}
			return ResponseEntity.ok(result);
		} else {
			insertMessageToClient(result, ResponseConstants.INVALID_INPUT);
			return ResponseEntity.badRequest().body(result);
		}
	}

	@PostMapping(value = "/user/edit_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> editUser(@RequestBody Map<Object, Object> data) {
		Map<Object, Object> result = new HashMap<>();
		// We need to verify if user has permission to edit the user, if a super user is
		// trying to edit the user, let him do it, otherwise, check it
		// Let's assume that we are super user, the verification would be written later
		// by TienLV since he has lots of knowledge about that

		User user = null;
		String[] requiredFields = new String[] { "userId", "password", "rePassword", "email", "userType", "status" };
		if (validateInput(requiredFields, data)) {
			if (data.get("password").toString().equals(data.get("rePassword").toString())) {
				user = userService.getUserById(Integer.parseInt(data.get("userId").toString()));
				user.setEmail(data.get("email").toString());
				user.setPassword(data.get("password").toString());
				user.setUserType(Integer.parseInt(data.get("userType").toString()));
				user.setStatus((byte) Integer.parseInt(data.get("status").toString()));
				if (userService.update(user)) {
					insertMessageToClient(result, UserCRUDConstants.USER_UPDATED_SUCCESSFULLY);
					result.put("user", user);
					return ResponseEntity.ok(result);
				} else {
					insertMessageToClient(result, UserCRUDConstants.USER_UPDATED_FAIL);
					return ResponseEntity.badRequest().body(result);
				}
			} else {
				insertMessageToClient(result, UserCRUDConstants.PASSWORD_DOES_NOT_MATCH);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			insertMessageToClient(result, ResponseConstants.INVALID_INPUT);
			return ResponseEntity.badRequest().body(result);
		}
	}

	@PostMapping(value = "/user/add_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> createUser(@RequestBody Map<Object, Object> data) {
		log.info("User request " + data);
		Map<Object, Object> result = new HashMap<>();
		String[] requiredFields = new String[] { "userName", "password", "rePassword", "email" };
		if (validateInput(requiredFields, data)) {
			if (data.get("password").toString().equals(data.get("rePassword").toString())) {
				User user = new User();
				user.setUserName(data.get("userName").toString());
				user.setPassword(data.get("password").toString());
				user.setEmail(data.get("email").toString());
				if (userService.insert(user)) {
					log.info(String.format("User %s added successfully", user.toString()));
					insertMessageToClient(result, UserCRUDConstants.REGISTERED_SUCCESSFULLY);
					return ResponseEntity.ok(result);
				} else {
					log.info(String.format("User %s added failed", user.toString()));
					insertMessageToClient(result, UserCRUDConstants.ERROR_HAPPENED);
					return ResponseEntity.unprocessableEntity().body(result);
				}
			} else {
				insertMessageToClient(result, UserCRUDConstants.PASSWORD_DOES_NOT_MATCH);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			insertMessageToClient(result, ResponseConstants.INVALID_INPUT);
			return ResponseEntity.badRequest().body(result);
		}
	}
}
