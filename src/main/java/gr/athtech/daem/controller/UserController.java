package gr.athtech.daem.controller;

import gr.athtech.daem.converter.UserConverter;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.UserDTO;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.UserService;
import gr.athtech.daem.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserConverter userConverter;

	protected BaseService<User> getBaseService() {
		return userService;
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
		final List<UserDTO> userDTOList = userConverter.convertToDTO(getBaseService().findAll());
		return ResponseEntity.ok(ApiResponse.<List<UserDTO>>builder().data(userDTOList).build());
	}
}
