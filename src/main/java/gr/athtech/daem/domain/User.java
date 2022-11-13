package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@SequenceGenerator(name = "idGenerator")
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

	@Column(length = 50, nullable = false, unique = false)
	@NotNull
	@NotEmpty
	private Position position;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User manager;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Certification> certifications;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Course> pendingCourses;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Course> completedCourses;
}
