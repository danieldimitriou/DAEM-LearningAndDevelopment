package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COURSES")
@SequenceGenerator(name = "idGenerator", allocationSize = 1)
public class Course extends BaseModel {

	@Column(length = 100, nullable = false)
	@NotEmpty
	@NotNull
	private String name;

	@ManyToOne
	@JoinColumn(name = "type_of_course_id")
	private TypeOfCourse type;

	@ManyToMany
	@JoinTable(name = "COURSE_AREA_OF_STUDY", joinColumns = @JoinColumn(name = "COURSE_ID"), inverseJoinColumns = @JoinColumn(name = "AREA_OF_STUDY_ID"))
	private List<AreaOfStudy> areasOfStudy;

	@OneToOne
	@JoinColumn(name = "certification_id")
	private Certification certification;

	@ManyToMany(mappedBy = "pendingCourses")
	private List<User> usersPending;

	@ManyToMany(mappedBy = "completedCourses")
	private List<User> usersCompleted;

	@NotEmpty
	@NotNull
	private boolean pending;

	@NotEmpty
	@NotNull
	private boolean completed;

}
