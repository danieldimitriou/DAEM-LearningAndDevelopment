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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable(name = "id") Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("Element not found");
		}
		final UserDTO userDTO = userConverter.convertToDTO(userOptional.get());
		return ResponseEntity.ok(ApiResponse.<UserDTO>builder().data(userDTO).build());
	}

	@GetMapping(params = "email")
	public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail(@Email @RequestParam String email) {
		User user = userService.findByEmail(email);
		if (user == null) {
			throw new NoSuchElementException("Element not found");
		}
		UserDTO userDTO = userConverter.convertToDTO(user);
		return ResponseEntity.ok(ApiResponse.<UserDTO>builder().data(userDTO).build());
	}



}

