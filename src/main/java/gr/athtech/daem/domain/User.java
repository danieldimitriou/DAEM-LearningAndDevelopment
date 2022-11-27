package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

	@Column(length = 50, nullable = false, unique = false)
	@NotEmpty
	@NotNull
	private String firstName;

	@Column(length = 50, nullable = false, unique = false)
	@NotEmpty
	@NotNull
	private String lastName;

	@Column(length = 50, nullable = false, unique = true)
	@NotEmpty
	@NotNull
	@Email
	private String email;

	@Column(length = 255, nullable = false)
	@NotNull
	@NotEmpty
	private String password;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Position position;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User manager;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="USERS_CERTIFICATIONS",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="certification_id")
	)
	private List<Certification> certifications;

	@ManyToMany
	@JoinTable(
			name="PENDING_COURSES_USERS",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
	)
	private List<Course> pendingCourses;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="FINISHED_COURSES_USERS",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="course_id")
	)
	private List<Course> completedCourses;
}
