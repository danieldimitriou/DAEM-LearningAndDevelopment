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

	List<Course> findByUsersPendingIn(List<User> usersPending);

	List<Course> findByUsersCompletedIn(List<User> usersCompleted);

	List<Course> findByAreasOfStudyIn(Long [] areaOfStudyIds);

	Course findByCertificationId(Long id);

	List<Course> findByName(String name);
}
