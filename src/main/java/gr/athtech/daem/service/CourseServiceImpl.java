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
		return courseRepository.findByUsersPending(usersPending);
	}

	@Override
	public List<Course> findByUsersCompleted(final List<User> usersCompleted) {
		return courseRepository.findByUsersCompleted(usersCompleted);
	}

	@Override
	public List<Course> findCoursesByAreasOfStudy(final List<AreaOfStudy> areasOfStudy) {
		return courseRepository.findCoursesByAreasOfStudy(areasOfStudy);
	}

	@Override
	public Course findByCertificationId(final Long certificationId) {
		return courseRepository.findByCertificationId(certificationId);
	}

	@Override
	public void updateCourse(final Course course) {
		courseRepository.updateCourse(course);
	}

	@Override
	public Course createCourse(final Course course) {
		return courseRepository.createCourse(course);
	}

	@Override
	public void updateStatus(final Course course, boolean status) {
		courseRepository.updateStatus(course, status);
	}

	@Override
	public void addCertification(final Course course) {
		courseRepository.addCertification(course);
	}

	@Override
	public void deleteCourse(final Course course) {
		courseRepository.deleteCourse(course);

	}

}
