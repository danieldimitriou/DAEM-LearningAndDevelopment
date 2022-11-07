package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseModel {

	private String name;
	private TypeOfCourse type;
	private AreaOfStudy areaOfStudy;
	private Certification certification;
	private User user;
}
