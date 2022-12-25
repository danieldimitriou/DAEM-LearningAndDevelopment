package gr.athtech.daem.dto;

import gr.athtech.daem.domain.AreaOfStudy;
import gr.athtech.daem.domain.Certification;
import gr.athtech.daem.domain.TypeOfCourse;
import gr.athtech.daem.domain.User;
import lombok.Data;
import java.util.List;

@Data
public class CourseDTO {
	private String name;
	private TypeOfCourse type;
	private List<AreaOfStudy> areasOfStudy;
	private Certification certification;
	private List<User> usersPending;
	private List<User> usersCompleted;
	private boolean pending;
	private boolean completed;
}
