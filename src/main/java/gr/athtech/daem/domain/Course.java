package gr.athtech.daem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
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

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "COURSE_AREA_OF_STUDY", joinColumns = @JoinColumn(name = "COURSE_ID"), inverseJoinColumns = @JoinColumn(name = "AREA_OF_STUDY_ID"))
	private List<AreaOfStudy> areasOfStudy;

	@JsonManagedReference(value = "course-certification")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "certification_id")
	private Certification certification;

	@JsonIgnore
	@ManyToMany(mappedBy = "pendingCourses", cascade = CascadeType.MERGE)
	private List<User> usersPending = new ArrayList<>();

	@JsonIgnore
	@ManyToMany(mappedBy = "completedCourses", cascade = CascadeType.MERGE)
	private List<User> usersCompleted;

	public void setCertification(Certification certification) {
		if (certification != null) {
			certification.setCourse(this);
		}
		this.certification = certification;
	}

	public void setType(TypeOfCourse type) {
		if (type != null) {
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
