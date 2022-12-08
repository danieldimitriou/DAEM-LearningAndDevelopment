package gr.athtech.daem.controller;

import gr.athtech.daem.domain.User;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController extends BaseController<User> {

	private final UserService userService;

	@Override
	protected BaseService<User> getBaseService() {
		return userService;
	}
}
