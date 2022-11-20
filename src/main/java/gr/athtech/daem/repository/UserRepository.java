package gr.athtech.daem.repository;

import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

	List<User> findAllByDepartmentId(Long id);

	List<User> findUsersByManagerId(Long id);

	List<User> findUsersByFirstNameOrLastNameLikeIgnoreCase(String name);

	User findByEmail(String email);

	List<User> findUsersByPositionId(Long id);

	List<User> findUsersByCertifications(List<Certification> certifications);

	List<User> findUsersByPendingCourses(List<Course> pendingCourses);

	List<User> findUsersByCompletedCourses(List<Course> completedCourses);

	void deleteUserById(Long id);
}
