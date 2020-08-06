package bi.lunch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bi.lunch.constant.ResponseConstants;
import bi.lunch.constant.UserCRUDConstants;
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

	private void insertMessageToClient(Map<Object, Object> result, Object[] message) {
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

	@PostMapping(value = "/user/get_user/{lkjj}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> getUserById(@RequestBody User user) {
		log.info(user.toString());
		return null;
	}

	@PostMapping(value = "/user/edit_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> editUser(@RequestBody User user) {
		Map<Object, Object> result = new HashMap<>();
		// We need to verify if user has permission to edit the user, if a super user is
		// trying to edit the user, let him do it, otherwise, check it
		// Let's assume that we are super user, the verification would be written later
		// by TienLV since he has lots of knowledge about that
		if (user.getPassword().equals(user.getRePassword())) {
			// Let's get userName from database
			User oldUser = userService.getUserById(user.getId());
			user.setUserName(oldUser.getUserName());
			if (userService.update(user)) {
				insertMessageToClient(result, UserCRUDConstants.USER_UPDATED_SUCCESSFULLY);
				return ResponseEntity.ok(result);
			} else {
				insertMessageToClient(result, UserCRUDConstants.USER_UPDATED_FAIL);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			insertMessageToClient(result, UserCRUDConstants.PASSWORD_DOES_NOT_MATCH);
			return ResponseEntity.badRequest().body(result);
		}
	}

	@PostMapping(value = "/user/add_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> createUser(@RequestBody User user) {
		Map<Object, Object> result = new HashMap<>();
		if (user.getPassword().equals(user.getRePassword())) {
			if (userService.insert(user)) {
				insertMessageToClient(result, UserCRUDConstants.REGISTERED_SUCCESSFULLY);
				return ResponseEntity.ok(result);
			} else {
				insertMessageToClient(result, UserCRUDConstants.ERROR_HAPPENED);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			insertMessageToClient(result, UserCRUDConstants.PASSWORD_DOES_NOT_MATCH);
			return ResponseEntity.badRequest().body(result);
		}
	}
}
