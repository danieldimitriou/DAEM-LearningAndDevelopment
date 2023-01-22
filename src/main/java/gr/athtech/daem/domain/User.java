package gr.athtech.daem.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
@SequenceGenerator(name = "idGenerator", allocationSize = 1)
public class User extends BaseModel {

	@Column(length = 50, nullable = false)
	@NotEmpty
	@NotNull
	private String firstName;

	@Column(length = 50, nullable = false)
	@NotEmpty
	@NotNull
	private String lastName;

	@Column(length = 50, nullable = false, unique = true)
	@NotEmpty
	@NotNull
	@Email
	private String email;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String password;

	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Position position;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User manager;

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany
	@JoinTable(name = "USERS_CERTIFICATIONS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "certification_id"))
	private List<Certification> certifications;


	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "PENDING_COURSES_USERS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> pendingCourses;


	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "FINISHED_COURSES_USERS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> completedCourses;

	public void setPosition(final Position position) {
		position.getUsers().add(this);
		this.position = position;
	}

	public void addPendingCourse(final Course pendingCourse) {
		if (!completedCourses.contains(pendingCourse)) {
			pendingCourse.getUsersPending().add(this);
			this.pendingCourses.add(pendingCourse);
		} else {
			throw new IllegalArgumentException("Cannot add pending course to user, the course is a completed course");
		}
	}

	public void addCompletedCourse(final Course completedCourse) {
		if (!pendingCourses.contains(completedCourse)) {
			completedCourse.getUsersPending().add(this);
			this.completedCourses.add(completedCourse);
		} else {
			throw new IllegalArgumentException("Cannot add completed course to user, the course is a pending course");
		}
	}

}
