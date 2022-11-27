package gr.athtech.daem.service;

import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.Position;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	private final UserRepository userRepository;

	@Override
	public JpaRepository<User, Long> getRepository(){
		return userRepository;
	}

	@Override
	public Optional<User> findById(final Long id){
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
		return userRepository.findUsersByCertifications(certifications);
	}

	@Override
	public List<User> findUsersByPendingCourses(final List<Course> pendingCourses) {
		return userRepository.findUsersByPendingCourses(pendingCourses);
	}

	@Override
	public List<User> findUsersByCompletedCourses(final List<Course> completedCourses) {
		return userRepository.findUsersByCompletedCourses(completedCourses);
	}

	@Override
	public User updateUserFirstName(final User userToBeUpdated, final String firstName){
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
		List<Certification> certifications = userToBeUpdated.getCertifications();
		if (!certifications.contains(certification)) {
			certifications.add(certification);
		}
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User addPendingCourseToUser(final User userToBeUpdated, final Course pendingCourse) {
		List<Course> pendingCourses = userToBeUpdated.getPendingCourses();
		if (!pendingCourses.contains(pendingCourse)) {
			pendingCourses.add(pendingCourse);
		}
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User addCompletedCourseToUser(final User userToBeUpdated, final Course completedCourse) {
		List<Course> completedCourses = userToBeUpdated.getCompletedCourses();
		if (!completedCourses.contains(completedCourse)) {
			completedCourses.add(completedCourse);
		}
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User completePendingCourse(final User userToBeUpdated, final Course completedCourse) throws NoSuchElementException{
		 if (userToBeUpdated.getPendingCourses().remove(completedCourse)) {
			 // If the user is actually attending the course
			 userToBeUpdated.getCompletedCourses().add(completedCourse);
		 } else {
			 throw new NoSuchElementException("The user is not attending course " + completedCourse);
		 }
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User deletePendingCourseFromUser(final User userToBeUpdated, final Course pendingCourseToBeDeleted) throws NoSuchElementException{
		if (!userToBeUpdated.getPendingCourses().remove(pendingCourseToBeDeleted)) {
			// If the user is actually attending the course
			throw new NoSuchElementException("The user is not attending course " + pendingCourseToBeDeleted);
		}
		return userRepository.save(userToBeUpdated);
	}

	@Override
	public User deleteCompletedCourseFromUser(final User userToBeUpdated, final Course completedCourseToBeDeleted) throws NoSuchElementException{
		if (!userToBeUpdated.getCompletedCourses().remove(completedCourseToBeDeleted)) {
			throw new NoSuchElementException("The user has not completed course " + completedCourseToBeDeleted);
		}
		return userRepository.save(userToBeUpdated);
	}

}
