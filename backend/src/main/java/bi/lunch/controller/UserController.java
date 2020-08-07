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

import bi.lunch.constant.UserCRUDConstants;
import bi.lunch.entity.User;
import bi.lunch.service.IUserService;
import bi.lunch.utils.CRUDUtils;

@Controller
public class UserController {
	private static final Logger log = Logger.getLogger(UserController.class.getName());

	@Autowired
	IUserService userService;

	@PostMapping(value = "/user/delete_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> deleteUser(@RequestBody User user) {
		Map<Object, Object> result = new HashMap<>();
		User oldUser = userService.getUserById(user.getId());
		if (oldUser != null) {
			if (userService.delete(oldUser)) {
				CRUDUtils.insertMessageToClient(result, UserCRUDConstants.USER_DELETED_SUCCESSFULLY);
				return ResponseEntity.ok(result);
			} else {
				CRUDUtils.insertMessageToClient(result, UserCRUDConstants.USER_DELETED_FAIL);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			CRUDUtils.insertMessageToClient(result, UserCRUDConstants.USER_NOT_FOUND);
			return ResponseEntity.badRequest().body(result);
		}
	}

	@PostMapping(value = "/user/get_users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> getUsers() {
		Map<Object, Object> result = new HashMap<>();
		List<User> users = userService.getAll();
		result.put("users", users);
		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/user/get_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> getUserById(@RequestBody User user) {
		Map<Object, Object> result = new HashMap<>();
		user = userService.getUserById(user.getId());
		if (user != null) {
			result.put("user", user);
			return ResponseEntity.ok(result);
		} else {
			CRUDUtils.insertMessageToClient(result, UserCRUDConstants.USER_NOT_FOUND);
			return ResponseEntity.badRequest().body(result);
		}

	}

	@PostMapping(value = "/user/edit_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> editUser(@RequestBody User user) {
		Map<Object, Object> result = new HashMap<>();
		// We need to verify if user has permission to edit the user, if a super user is
		// trying to edit the user, let him do it, otherwise, check it
		// Let's assume that we are super user, the verification would be written later
		// by TienLV since he has lots of knowledge about that
		User oldUser = userService.getUserById(user.getId());
		if (oldUser != null) {
			if (user.getPassword() != null && user.getPassword().equals(user.getRePassword())) {
				// Let's get userName from database
			} else {
				CRUDUtils.insertMessageToClient(result, UserCRUDConstants.PASSWORD_DOES_NOT_MATCH);
				return ResponseEntity.badRequest().body(result);
			}
			user.setUserName(oldUser.getUserName());
			if (userService.update(user)) {
				CRUDUtils.insertMessageToClient(result, UserCRUDConstants.USER_UPDATED_SUCCESSFULLY);
				return ResponseEntity.ok(result);
			} else {
				CRUDUtils.insertMessageToClient(result, UserCRUDConstants.USER_UPDATED_FAIL);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			CRUDUtils.insertMessageToClient(result, UserCRUDConstants.USER_NOT_FOUND);
			return ResponseEntity.badRequest().body(result);
		}
	}

	@PostMapping(value = "/user/add_user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> createUser(@RequestBody User user) {
		Map<Object, Object> result = new HashMap<>();
		if (user.getPassword().equals(user.getRePassword())) {
			if (userService.insert(user)) {
				CRUDUtils.insertMessageToClient(result, UserCRUDConstants.REGISTERED_SUCCESSFULLY);
				return ResponseEntity.ok(result);
			} else {
				CRUDUtils.insertMessageToClient(result, UserCRUDConstants.ERROR_HAPPENED);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			CRUDUtils.insertMessageToClient(result, UserCRUDConstants.PASSWORD_DOES_NOT_MATCH);
			return ResponseEntity.badRequest().body(result);
		}
	}
}
