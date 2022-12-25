package gr.athtech.daem.bootstrap;

import gr.athtech.daem.base.BaseComponent;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("generate-users")
@Order(2)
public class UserSampleContentCreator extends BaseComponent implements CommandLineRunner {

	private final UserService userService;

	@Override
	public void run(final String... args) throws Exception {
		List<User> users = List.of(User.builder().firstName("Stefan").lastName("Bordea").email(
											   "stefan@stefanbordea.com").password("oogAbooga12345!")
									   //						.department(Department.builder().name("C-level").build())
									   .build(),

								   User.builder().firstName("Oogaman").lastName("Debest").email("ooga@booga.com")
									   .password("o)(JD!2djjd109jd")
									   //						.department(Department.builder().name("C-level").build())
									   .build());

		userService.createAll(users);
	}
}
