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
	public Course findByCertificationId(final Long[] certificationIds) {
		return courseRepository.findByCertificationId(certificationIds);
	}

	@Override
	public void updateIsPending(final List<Course> coursesPending) {
		for (Course course : coursesPending) {
			course.setPending(!course.isPending());
		}

	}

	@Override
	public void updateIsCompleted(List<Course> coursesCompleted) {
		for (Course course : coursesCompleted) {
			course.setCompleted(!course.isCompleted());
		}

	}

	@Override
	public void addCertifications(final List<Course> courses) {
		courseRepository.addCertifications(courses);
	}

}
