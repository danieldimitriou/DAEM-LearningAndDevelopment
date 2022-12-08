package gr.athtech.daem.service;

import gr.athtech.daem.domain.AreaOfStudy;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {
	private final CourseRepository courseRepository;

	@Override
	public JpaRepository<Course, Long> getRepository() {
		return courseRepository;
	}

	@Override
	public List<Course> findByName(final String name) {
		return courseRepository.findByName(name);
	}

	@Override
	public Optional<Course> findById(final Long id) {
		return courseRepository.findById(id);
	}

	@Override
	public List<Course> findByTypeId(final Long id) {
		return courseRepository.findByTypeId(id);
	}

	@Override
	public List<Course> findByUsersPending(final List<User> usersPending) {
		return courseRepository.findByUsersPendingIn(usersPending);
	}

	@Override
	public List<Course> findByUsersCompletedIn(final List<User> usersCompleted) {
		return courseRepository.findByUsersCompletedIn(usersCompleted);
	}

	@Override
	public List<Course> findCoursesByAreasOfStudy(final List<AreaOfStudy> areasOfStudy) {
		return courseRepository.findCoursesByAreasOfStudyIn(areasOfStudy);
	}

	@Override
	public Course findByCertificationId(final Long[] certificationIds) {
		return courseRepository.findByCertificationId(certificationIds);
	}
}
