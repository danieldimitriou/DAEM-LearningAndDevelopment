package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Position position;
	private User manager;
	private Department department;
	private List<Certification> certifications;
	private List<Course> pendingCourses;
	private List<Course> completedCourses;
}
