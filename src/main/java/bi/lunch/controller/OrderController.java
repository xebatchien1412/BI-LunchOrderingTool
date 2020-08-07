package bi.lunch.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import bi.lunch.constant.OrderCRUDConstant;
import bi.lunch.constant.ResponseConstants;
import bi.lunch.entity.Order;
import bi.lunch.entity.User;
import bi.lunch.service.IOrderService;
import bi.lunch.service.IUserService;
import bi.lunch.utils.CRUDUtils;

@Controller
public class OrderController {
	private static final Logger log = Logger.getLogger(OrderController.class.getName());

	@Autowired
	IOrderService orderService;

	@Autowired
	IUserService userService;

	@PostMapping(value = "/order/create_order", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Object, Object>> createOrder(@RequestBody Map<Object, Object> data) {
		Map<Object, Object> result = new HashMap<>();
		String[] requireFields = { "user_id", "order_detail" };
		if (CRUDUtils.validateRequireFields(requireFields, data)) {
			User user = userService.getUserById(Integer.parseInt(data.get("user_id").toString()));
			if (user != null) {
				Order order = new Order();
				order.setUserEntity(user);
				order.setOrderDetails(data.get("order_detail").toString());
				if (orderService.insert(order)) {
					CRUDUtils.insertMessageToClient(result, OrderCRUDConstant.CREATE_ORDER_SUCCESS);
					return ResponseEntity.ok(result);
				} else {
					CRUDUtils.insertMessageToClient(result, OrderCRUDConstant.CREATE_ORDER_FAIL);
					return ResponseEntity.badRequest().body(result);
				}
			} else {
				CRUDUtils.insertMessageToClient(result, ResponseConstants.INVALID_INPUT);
				return ResponseEntity.badRequest().body(result);
			}
		} else {
			CRUDUtils.insertMessageToClient(result, ResponseConstants.INVALID_INPUT);
			return ResponseEntity.badRequest().body(result);
		}
	}
}
