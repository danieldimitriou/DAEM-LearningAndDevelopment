package gr.athtech.daem.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserWithCoursesDTO {

	private String firstName;
	private String lastName;
	private List<CourseDTO> pendingCoursesDTO;
	private List<CourseDTO> completedCoursesDTO;

}
