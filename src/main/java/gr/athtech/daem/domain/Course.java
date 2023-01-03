package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "type_of_course_id")
	private TypeOfCourse type;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "COURSE_AREA_OF_STUDY", joinColumns = @JoinColumn(name = "COURSE_ID"), inverseJoinColumns = @JoinColumn(name = "AREA_OF_STUDY_ID"))
	private List<AreaOfStudy> areasOfStudy;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "certification_id")
	private Certification certification;

	@ManyToMany(mappedBy = "pendingCourses")
	private List<User> usersPending;

	@ManyToMany(mappedBy = "completedCourses")
	private List<User> usersCompleted;

//	@NotEmpty
//	@NotNull
//	private boolean pending;
//
//	@NotEmpty
//	@NotNull
//	private boolean completed;

	public void setCertification(Certification certification) {
		if (certification != null) {
			certification.setCourse(this);
		}
		this.certification = certification;
	}

	public void setType(TypeOfCourse type){
		if (type != null)  {
			type.getCourses().add(this);
		}
		this.type = type;
	}

	public void addAreaOfStudy(AreaOfStudy areaOfStudy) {
		if (areaOfStudy != null) {
			areaOfStudy.getCourses().add(this);
		}
		this.areasOfStudy.add(areaOfStudy);
	}

}
