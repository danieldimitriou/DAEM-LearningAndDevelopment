package gr.athtech.daem.bootstrap;

import gr.athtech.daem.base.BaseComponent;
import gr.athtech.daem.domain.AreaOfStudy;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.TypeOfCourse;
import gr.athtech.daem.service.CourseService;
import gr.athtech.daem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("generate-courses")
@Order(1)
public class CourseSampleContentCreator extends BaseComponent implements CommandLineRunner {

	private final CourseService courseService;


	@Override
	public void run(final String... args) throws Exception {
		List<Course> courses = List.of(Course.builder().name("Object Oriented Programming").type(
				TypeOfCourse.builder().name("Programming").build()).areasOfStudy(
				(List<AreaOfStudy>) AreaOfStudy.builder().name("Software Development").build()).certification(
				Certification.builder().name("Certification of Object Oriented Programming").build()).build());
		courseService.createAll(courses);
	}
}
