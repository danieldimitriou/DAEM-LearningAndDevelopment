package gr.athtech.daem.bootstrap;

import gr.athtech.daem.base.BaseComponent;
import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.Position;
import gr.athtech.daem.domain.Role;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.service.CourseService;
import gr.athtech.daem.service.DepartmentService;
import gr.athtech.daem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("generate-users")
@Order(2)
public class UserSampleContentCreator extends BaseComponent implements CommandLineRunner {

	private final UserService userService;
	private final DepartmentService departmentService;

	private final CourseService courseService;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void run(final String... args) throws Exception {
		List<User> users = List.of(User.builder().firstName("Stefan").lastName("Bordea").email(
										   "stefan@stefanbordea.com").password(passwordEncoder.encode("pass12345!")).role(Role.ROLE_ADMIN).build(),

								   User.builder().firstName("Dionysis").lastName("Petrotos").email("dpetr@email.gr")
									   .password(passwordEncoder.encode("o)(JD!2djjd109jd")).role(Role.ROLE_USER)
									   //						.department(Department.builder().name("C-level").build())
									   .build());

		List<Department> departments = List.of(Department.builder().name("C-Level").members(new ArrayList<>()).build(),
											   Department.builder().name("A-Level").build(),
											   Department.builder().name("B-Level").build());
		departments.get(0).setHeadOfDepartment(users.get(0));
		users.forEach(user -> departments.get(0).addMember(user));

		List<Position> positions = List.of(Position.builder().name("Manager").level("2").users(new ArrayList<>())
												   .build(), Position.builder().name("Subordinate").level("3")
																	 .users(new ArrayList<>()).build());

		users.get(0).setPosition(positions.get(0));
		users.get(1).setPosition(positions.get(1));

		users.get(0).addPendingCourse(courseService.findById(7L).get());
		users.get(0).addCompletedCourse(courseService.findById(10L).get());

		departmentService.createAll(departments);
		userService.createAll(users);
	}
}
