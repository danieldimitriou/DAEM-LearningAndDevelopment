package gr.athtech.daem.service;

import gr.athtech.daem.converter.UserConverter;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.Position;
import gr.athtech.daem.domain.Role;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.AuthenticationResponse;
import gr.athtech.daem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserConverter userConverter;

	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return userRepository;
	}

	@Override
	public Optional<User> findById(final Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAllByDepartmentId(final Long departmentId) {
		return userRepository.findAllByDepartmentId(departmentId);
	}

	@Override
	public List<User> findUsersByManagerId(final Long managerId) {
		return userRepository.findUsersByManagerId(managerId);
	}

	@Override
	public List<User> findUsersByFirstNameOrLastNameLikeIgnoreCase(final String firstName, final String lastName) {
		return userRepository.findUsersByFirstNameOrLastNameLikeIgnoreCase(firstName, lastName);
	}

	@Override
	public User findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findUsersByPositionId(final Long positionId) {
		return userRepository.findUsersByPositionId(positionId);
	}

	@Override
	public List<User> findUsersByCertifications(final List<Certification> certifications) {
		return userRepository.findUsersByCertificationsIn(certifications);
	}

	@Override
	public List<User> findUsersByPendingCourses(final List<Course> pendingCourses) {
		return userRepository.findUsersByPendingCoursesIn(pendingCourses);
	}

	@Override
	public List<User> findUsersByCompletedCourses(final List<Course> completedCourses) {
		return userRepository.findUsersByCompletedCoursesIn(completedCourses);
	}

	@Override
	public User updateUserFirstName(final User userToBeUpdated, final String firstName) {
		userToBeUpdated.setFirstName(firstName);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User updateUserLastName(final User userToBeUpdated, final String lastName) {
		userToBeUpdated.setLastName(lastName);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User updateUserEmail(final User userToBeUpdated, final String email) {
		userToBeUpdated.setEmail(email);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User updateUserPosition(final User userToBeUpdated, final Position position) {
		userToBeUpdated.setPosition(position);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User updateUserManager(final User userToBeUpdated, final User manager) {
		userToBeUpdated.setManager(manager);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User updateUserDepartment(final User userToBeUpdated, final Department department) {
		userToBeUpdated.setDepartment(department);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User addCertificationToUser(final User userToBeUpdated, final Certification certification) {
		userToBeUpdated.addCertification(certification);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User addPendingCourseToUser(final User userToBeUpdated, final Course pendingCourse) {
		userToBeUpdated.addPendingCourse(pendingCourse);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User addCompletedCourseToUser(final User userToBeUpdated, final Course completedCourse) {
		userToBeUpdated.addCompletedCourse(completedCourse);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User completePendingCourse(final User userToBeUpdated, final Course completedCourse) {
		userToBeUpdated.removePendingCourse(completedCourse);
		userToBeUpdated.addCompletedCourse(completedCourse);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User deleteCertificationFromUser(final User userToBeUpdated, final Certification certification) {
		userToBeUpdated.getCertifications().remove(certification);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User deletePendingCourseFromUser(final User userToBeUpdated, final Course pendingCourseToBeDeleted) {
		userToBeUpdated.getPendingCourses().remove(pendingCourseToBeDeleted);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User deleteCompletedCourseFromUser(final User userToBeUpdated, final Course completedCourseToBeDeleted) {
		userToBeUpdated.getCompletedCourses().remove(completedCourseToBeDeleted);
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public AuthenticationResponse register(String firstName, String lastName, String email, String password) {
		if (userRepository.findByEmail(email.trim()) == null) {
			User user = new User();
			user.setFirstName(firstName.trim());
			user.setLastName(lastName.trim());
			user.setEmail(email.trim());
			user.setPassword(passwordEncoder.encode(password.trim()));
			user.setRole(Role.ROLE_USER);
			user.setCertifications(null);
			user.setCompletedCourses(null);
			user.setPendingCourses(null);
			userRepository.save(user);
			var jwtToken = jwtService.generateToken(user);
			return AuthenticationResponse.builder().token(jwtToken).id(user.getId()).role(user.getRole()).build();
		}
		return null;
	}

	@Override
	public AuthenticationResponse login(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		var user = userRepository.findByEmail(email.trim());
		if (user == null) {
			throw new NoSuchElementException("This username does not exist");
		}
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).id(user.getId()).role(user.getRole()).build();
	}

	@Override
	public AuthenticationResponse changePassword(Long userId, String currentPassword, String newPassword,
												 String newPasswordConfirmed) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			throw new NoSuchElementException("Invalid user id");
		}
		User user = userOptional.get();
		if (passwordEncoder.matches(currentPassword.trim(), user.getPassword())) {
			if (Objects.equals(newPassword.trim(), newPasswordConfirmed.trim())) {
				user.setPassword(passwordEncoder.encode(newPassword.trim()));
				userRepository.save(user);
				logger.info("Changed password for user with ID {}", userId);
				var jwtToken = jwtService.generateToken(user);
				return AuthenticationResponse.builder().token(jwtToken).id(user.getId()).role(user.getRole()).build();
			}
		}
		return null;
	}

}
