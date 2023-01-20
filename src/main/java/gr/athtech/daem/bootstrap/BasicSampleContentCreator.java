package gr.athtech.daem.bootstrap;

import gr.athtech.daem.base.BaseComponent;
import gr.athtech.daem.domain.AreaOfStudy;
import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.TypeOfCourse;
import gr.athtech.daem.domain.TypeOfInstitution;
import gr.athtech.daem.service.AuthorityService;
import gr.athtech.daem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("generate-users")
@Order(1)
public class BasicSampleContentCreator extends BaseComponent implements CommandLineRunner {

	private final AuthorityService authorityService;
	private final CourseService courseService;

	@Override
	public void run(final String... args) throws Exception {

		// Instantiate TypesOfInstitutions
		TypeOfInstitution universityInstitution = TypeOfInstitution.builder().description("University").build();
		TypeOfInstitution moocInstitution = TypeOfInstitution.builder().description("MOOC").build();


		// Instantiate Authorities
		List<Authority> universities = List.of(Authority.builder().name("University Of Sheffield").build(),
											   Authority.builder().name("University Of York").build());


		List<Authority> moocs = List.of(Authority.builder().name("Coursera").build(),
										Authority.builder().name("Udemy").build());


		// Bind authorities and TypesOfInstitutions
		universities.forEach(university -> university.setAwardingBody(universityInstitution));
		universityInstitution.setAuthorities(universities);

		moocs.forEach(mooc -> mooc.setAwardingBody(moocInstitution));
		moocInstitution.setAuthorities(moocs);


		// Instantiate Certifications and bind certifications with corresponding authorities
		List<Certification> courseraCertifications =
				List.of(Certification.builder().name("Spring Boot developer").build(),
							Certification.builder().name("Angular developer").build());

		courseraCertifications.forEach(
				courseraCertification -> courseraCertification.setCertificationAuthority(moocs.get(0))
									  );
		moocs.get(0).setCertifications(courseraCertifications);



		List<Certification> udemyCertifications =
				List.of(Certification.builder().name("Django developer").build(),
						Certification.builder().name("React developer").build());

		udemyCertifications.forEach(
				udemyCertification -> udemyCertification.setCertificationAuthority(moocs.get(1))
								   );
		moocs.get(1).setCertifications(udemyCertifications);



		List<Certification> sheffieldCertifications =
				List.of(Certification.builder().name("Java developer").build(),
						Certification.builder().name("Python developer").build());

		sheffieldCertifications.forEach(
				sheffieldCertification -> sheffieldCertification.setCertificationAuthority(universities.get(0))
									   );
		universities.get(0).setCertifications(sheffieldCertifications);



		List<Certification> yorkCertifications = List.of(Certification.builder().name("C# developer").build(),
														 Certification.builder().name("JavaScript developer").build());

		yorkCertifications.forEach(
				yorkCertification -> yorkCertification.setCertificationAuthority(universities.get(1))
								  );
		universities.get(1).setCertifications(yorkCertifications);


		// Create authorities
		universities.forEach(university -> authorityService.create(university));
		moocs.forEach(mooc -> authorityService.create(mooc));


		// Instantiate AreasOfStudy
		List<AreaOfStudy> areasOfStudy = List.of(
				AreaOfStudy.builder().name("Programming Languages").description("Programming Languages").courses(new ArrayList<>()).build(),
				AreaOfStudy.builder().name("Back-end Frameworks").description("Server-side development " +
																					  "frameworks").courses(new ArrayList<>()).build(),
				AreaOfStudy.builder().name("Front-end Frameworks").description("Client-side development" +
																					   " frameworks").courses(new ArrayList<>()).build());

		// Instantiate TypesOfCourses
		List<TypeOfCourse> typesOfCourses = List.of(
				TypeOfCourse.builder().name("Physical Course").description("Physical attendance required").courses(new ArrayList<>()).build(),
				TypeOfCourse.builder().name("Online Course").description("The course takes place online").courses(new ArrayList<>()).build()
												   );

		// Instantiate Courses
		List<Course> physicalCoursesSheffield = List.of(
				Course.builder().name("Java").areasOfStudy(new ArrayList<>()).usersPending(new ArrayList<>()).usersCompleted(new ArrayList<>()).build(),
				Course.builder().name("Python").areasOfStudy(new ArrayList<>()).certification(sheffieldCertifications.get(1)).build()
													   );

		physicalCoursesSheffield.get(0).setCertification(sheffieldCertifications.get(0));
		physicalCoursesSheffield.get(1).setCertification(sheffieldCertifications.get(1));

		for (Course course : physicalCoursesSheffield) {
			course.addAreaOfStudy(areasOfStudy.get(0));
			course.setType(typesOfCourses.get(0));
		}

		List<Course> physicalCoursesYork = List.of(
				Course.builder().name("C#").areasOfStudy(new ArrayList<>()).usersPending(new ArrayList<>())
						.usersCompleted(new ArrayList<>()).build(),
				Course.builder().name("JavaScript").areasOfStudy(new ArrayList<>()).build()
												  );

		physicalCoursesYork.get(0).setCertification(yorkCertifications.get(0));
		physicalCoursesYork.get(1).setCertification(yorkCertifications.get(1));

		for (Course course : physicalCoursesYork) {
			course.addAreaOfStudy(areasOfStudy.get(0));
			course.setType(typesOfCourses.get(0));
		}

		physicalCoursesYork.forEach(course -> courseService.create(course));
		physicalCoursesSheffield.forEach(course -> courseService.create(course));










	}
}
