package gr.athtech.daem.dto;

import gr.athtech.daem.domain.Course;
import lombok.Data;

import java.util.List;

@Data
public class UserWithCoursesDTO {

	private String firstName;
	private String lastName;
	private List<Course> pendingCourses;
	private List<Course> completedCourses;

}
