package bi.lunch.controller;

import bi.lunch.service.IOrderService;
import bi.lunch.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunchMenuController {
    private static final Logger log = Logger.getLogger(OrderController.class.getName());

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserService userService;
}
