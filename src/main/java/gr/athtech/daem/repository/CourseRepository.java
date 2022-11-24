package gr.athtech.daem.repository;

import gr.athtech.daem.domain.AreaOfStudy;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findById(Long id);

	List<Course> findByTypeId(Long id);

	List<Course> findByUsersPending(List<User> usersPending);

	List<Course> findByUsersCompleted(List<User> usersCompleted);

	List<Course> findCoursesByAreasOfStudy(List<AreaOfStudy> areasOfStudy);

	Course findByCertificationId(Long certificationId);

	List<Course> findByName(String name);

	Course createCourse(Course course);

	void updateCourse(Course course);

	void updateStatus(Course course, boolean status);

	void addCertification(Course course);

	void deleteCourse(Course course);
}
