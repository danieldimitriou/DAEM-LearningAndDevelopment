package gr.athtech.daem.service;

import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.Position;
import gr.athtech.daem.domain.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserService extends BaseService<User>{



	Optional<User> findById(Long id);

	List<User> findAllByDepartmentId(Long departmentId);

	List<User> findUsersByManagerId(Long managerId);

	List<User> findUsersByFirstNameOrLastNameLikeIgnoreCase(String firstName, String lastName);

	User findByEmail(String email);

	List<User> findUsersByPositionId(Long positionId);

	List<User> findUsersByCertifications(List<Certification> certifications);

	List<User> findUsersByPendingCourses(List<Course> pendingCourses);

	List<User> findUsersByCompletedCourses(List<Course> completedCourses);

	User updateUserFirstName(User userToBeUpdated, String firstName);

	User updateUserLastName(User userToBeUpdated, String lastName);

	User updateUserEmail(User userToBeUpdated, String email);

	User updateUserPosition(User userToBeUpdated, Position position);

	User updateUserManager(User userToBeUpdated, User manager);

	User updateUserDepartment(User userToBeUpdated, Department department);

	User addCertificationToUser(User userToBeUpdated, Certification certification);

	User addPendingCourseToUser(User userToBeUpdated, Course pendingCourse);

	User addCompletedCourseToUser(User userToBeUpdated, Course completedCourse);

	User completePendingCourse(User userToBeUpdated, Course completedCourse);

	User deletePendingCourseFromUser(User userToBeUpdated, Course pendingCourseToBeDeleted);

	User deleteCompletedCourseFromUser(User userToBeUpdated, Course completedCourseToBeDeleted);

}
