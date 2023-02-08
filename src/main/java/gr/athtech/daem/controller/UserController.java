package gr.athtech.daem.controller;

import gr.athtech.daem.converter.UserConverter;
import gr.athtech.daem.converter.UserWithCoursesConverter;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.CourseDTO;
import gr.athtech.daem.dto.LoginRequest;
import gr.athtech.daem.dto.RegisterRequest;
import gr.athtech.daem.dto.ResetPasswordRequest;
import gr.athtech.daem.dto.UserDTO;
import gr.athtech.daem.dto.UserWithCoursesDTO;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.UserService;
import gr.athtech.daem.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

	private final UserService userService;
	private final UserConverter userConverter;

	private final UserWithCoursesConverter userWithCoursesConverter;

	private final CourseController courseController;

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
		final UserDTO userDTO = userConverter.convertToDTO(user);
		return ResponseEntity.ok(ApiResponse.<UserDTO>builder().data(userDTO).build());
	}

	@Transactional
	@GetMapping("/{id}/courses")
	public ResponseEntity<ApiResponse<UserWithCoursesDTO>> getUserWithCoursesByUserId(
			@PathVariable(name = "id") Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		final UserWithCoursesDTO userWithCoursesDTO = userWithCoursesConverter.convertToDTO(userOptional.get());
		return ResponseEntity.ok(ApiResponse.<UserWithCoursesDTO>builder().data(userWithCoursesDTO).build());
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserDTO>> login(@RequestBody LoginRequest loginRequest) {
		UserDTO body = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
		if (body == null) {
			return new ResponseEntity<>(ApiResponse.<UserDTO>builder().data(body).build(), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(ApiResponse.<UserDTO>builder().data(body).build());
	}

	@Transactional
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<User>> register(@RequestBody @NotNull RegisterRequest registerRequest) {
		User body = userService.register(registerRequest.getFirstName(), registerRequest.getLastName(),
										 registerRequest.getEmail(), registerRequest.getPassword());
		if (body == null) {
			return new ResponseEntity<>(ApiResponse.<User>builder().data(body).build(),
										HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return ResponseEntity.ok(ApiResponse.<User>builder().data(body).build());
	}

	@Transactional
	@PutMapping("/{id}/change-password")
	public ResponseEntity<ApiResponse<UserDTO>> changePassword(@PathVariable(name = "id") Long id,
															   @RequestBody @NotNull ResetPasswordRequest resetPasswordRequest) {
		UserDTO body = userService.changePassword(id, resetPasswordRequest.getCurrentPassword(),
												  resetPasswordRequest.getNewPassword(),
												  resetPasswordRequest.getNewPasswordConfirmed());
		if (body == null) {
			return new ResponseEntity<>(ApiResponse.<UserDTO>builder().data(body).build(), HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(ApiResponse.<UserDTO>builder().data(body).build());
	}

	@Transactional
	@PostMapping("/{id}/addPendingCourse")
	public ResponseEntity<ApiResponse<UserWithCoursesDTO>> addPendingCourse(@PathVariable(name = "id") Long id,
																			@RequestBody CourseDTO courseDTO) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		final User user = userOptional.get();
		final Course newCourse = Objects.requireNonNull(courseController.createCourse(courseDTO).getBody()).getData();
		userService.addPendingCourseToUser(user, newCourse);
		final UserWithCoursesDTO userWithCoursesDTO = userWithCoursesConverter.convertToDTO(user);
		return new ResponseEntity<>(ApiResponse.<UserWithCoursesDTO>builder().data(userWithCoursesDTO).build(),
									HttpStatus.CREATED);

	}

}

