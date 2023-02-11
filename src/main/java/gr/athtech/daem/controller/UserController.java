package gr.athtech.daem.controller;

import gr.athtech.daem.converter.PositionConverter;
import gr.athtech.daem.converter.UserConverter;
import gr.athtech.daem.converter.UserWithCertificationsConverter;
import gr.athtech.daem.converter.UserWithCoursesConverter;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.Position;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.*;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.CourseService;
import gr.athtech.daem.service.DepartmentService;
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
import java.util.Map;
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

	private final UserWithCertificationsConverter userWithCertificationsConverter;

	private final PositionConverter positionConverter;

	private final CourseController courseController;

	private final CertificationController certificationController;

	private final CourseService courseService;

	private final DepartmentService departmentService;

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
	public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@RequestBody LoginRequest loginRequest) {
		AuthenticationResponse authenticationResponse = userService.login(loginRequest.getEmail(),
																		  loginRequest.getPassword());
		return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder().data(authenticationResponse).build());
	}

	@Transactional
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthenticationResponse>> register(
			@RequestBody @NotNull RegisterRequest registerRequest) {
		AuthenticationResponse authenticationResponse = userService.register(registerRequest.getFirstName(),
																			 registerRequest.getLastName(),
																			 registerRequest.getEmail(),
																			 registerRequest.getPassword());
		return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder().data(authenticationResponse).build());
	}

	@Transactional
	@PutMapping("/{id}/change-password")
	public ResponseEntity<ApiResponse<AuthenticationResponse>> changePassword(@PathVariable(name = "id") Long id,
																			  @RequestBody @NotNull ResetPasswordRequest resetPasswordRequest) {
		AuthenticationResponse authenticationResponse = userService.changePassword(id,
																				   resetPasswordRequest.getCurrentPassword(),
																				   resetPasswordRequest.getNewPassword(),
																				   resetPasswordRequest.getNewPasswordConfirmed());
		if (authenticationResponse == null) {
			return new ResponseEntity<>(
					ApiResponse.<AuthenticationResponse>builder().data(authenticationResponse).build(),
					HttpStatus.UNAUTHORIZED);
		}
		return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder().data(authenticationResponse).build());
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

	@Transactional
	@PostMapping("/{userId}/completePendingCourse/{courseId}")
	public ResponseEntity<ApiResponse<UserWithCoursesDTO>> completePendingCourse(
			@PathVariable(name = "userId") Long userId, @PathVariable(name = "courseId") Long courseId) {
		Optional<User> userOptional = userService.findById(userId);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		User user = userOptional.get();
		Optional<Course> courseOptional = courseService.findById(courseId);
		if (courseOptional.isEmpty()) {
			throw new NoSuchElementException("Course not found");
		}
		Course course = courseOptional.get();
		userService.completePendingCourse(user, course);
		final UserWithCoursesDTO userWithCoursesDTO = userWithCoursesConverter.convertToDTO(user);
		return new ResponseEntity<>(ApiResponse.<UserWithCoursesDTO>builder().data(userWithCoursesDTO).build(),
									HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping("/{id}/addCertification")
	public ResponseEntity<ApiResponse<UserWithCertificationsDTO>> addCertification(@PathVariable(name = "id") Long id,
																				   @RequestBody CertificationDTO certificationDTO) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		final User user = userOptional.get();
		final Certification newCertification = Objects.requireNonNull(
				certificationController.createCertification(certificationDTO).getBody()).getData();
		userService.addCertificationToUser(user, newCertification);
		final UserWithCertificationsDTO userWithCertificationsDTO = userWithCertificationsConverter.convertToDTO(user);
		return new ResponseEntity<>(
				ApiResponse.<UserWithCertificationsDTO>builder().data(userWithCertificationsDTO).build(),
				HttpStatus.CREATED);
	}

	@Transactional
	@GetMapping("/{id}/certifications")
	public ResponseEntity<ApiResponse<UserWithCertificationsDTO>> getUserWithCertificationsByUserId(
			@PathVariable(name = "id") Long id) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		final UserWithCertificationsDTO userWithCertificationsDTO = userWithCertificationsConverter.convertToDTO(
				userOptional.get());
		return ResponseEntity.ok(
				ApiResponse.<UserWithCertificationsDTO>builder().data(userWithCertificationsDTO).build());
	}

	@Transactional
	@PutMapping("/{id}/setPosition")
	public ResponseEntity<ApiResponse<UserDTO>> setPosition(@PathVariable(name = "id") Long id,
															@RequestBody PositionDTO positionDTO) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		final User user = userOptional.get();
		final Position position = positionConverter.convertToEntity(positionDTO);
		userService.updateUserPosition(user, position);
		final UserDTO userDTO = userConverter.convertToDTO(user);
		return new ResponseEntity<>(ApiResponse.<UserDTO>builder().data(userDTO).build(), HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping("/{userId}/setDepartment/{departmentId}")
	public ResponseEntity<ApiResponse<UserDTO>> setDepartment(@PathVariable(name = "userId") Long userId,
															  @PathVariable(name = "departmentId") Long departmentId) {
		Optional<User> userOptional = userService.findById(userId);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		User user = userOptional.get();
		Optional<Department> departmentOptional = departmentService.findById(departmentId);
		if (departmentOptional.isEmpty()) {
			throw new NoSuchElementException("Department not found");
		}
		Department department = departmentOptional.get();
		departmentService.addMember(department, user);
		final UserDTO userDTO = userConverter.convertToDTO(user);
		return new ResponseEntity<>(ApiResponse.<UserDTO>builder().data(userDTO).build(), HttpStatus.CREATED);

	}

	@Transactional
	@PutMapping("/{id}/setManager")
	public ResponseEntity<ApiResponse<UserDTO>> setManager(@PathVariable(name = "id") Long id,
														   @RequestBody @Email Map<String, String> managerEmail) {
		Optional<User> userOptional = userService.findById(id);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		User user = userOptional.get();
		User manager = userService.findByEmail(managerEmail.get("managerEmail"));
		if (manager == null) {
			throw new NoSuchElementException("Wrong email");
		}
		if (!manager.getPosition().getName().equalsIgnoreCase("Manager")) {
			throw new IllegalArgumentException("Email does not belong to a manager");
		}
		userService.updateUserManager(user, manager);
		final UserDTO userDTO = userConverter.convertToDTO(user);
		return new ResponseEntity<>(ApiResponse.<UserDTO>builder().data(userDTO).build(), HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/{id}/updateEmail", consumes = "application/json")
	public ResponseEntity<ApiResponse<UserDTO>> updateEmail(@PathVariable(name = "id") Long id,
															@RequestBody @Email Map<String, String> newEmail) {
		Optional<User> userOptional = userService.findById(id);

		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("User not found");
		}
		User user = userOptional.get();
		userService.updateUserEmail(user, newEmail.get("email"));
		final UserDTO userDTO = userConverter.convertToDTO(user);
		return new ResponseEntity<>(ApiResponse.<UserDTO>builder().data(userDTO).build(), HttpStatus.CREATED);
	}

}

