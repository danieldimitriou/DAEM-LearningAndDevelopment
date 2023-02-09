package gr.athtech.daem.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "USERS_CERTIFICATIONS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "certification_id"))
	private List<Certification> certifications;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "PENDING_COURSES_USERS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> pendingCourses;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "FINISHED_COURSES_USERS", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> completedCourses;

	public void setPosition(final Position position) {
		if (position.getUsers() == null) {
			position.setUsers(new ArrayList<>());
		}
		position.getUsers().add(this);
		this.position = position;
	}

	public void addPendingCourse(final Course pendingCourse) {
		if (this.pendingCourses == null) {
			this.pendingCourses = new ArrayList<>();
		}
		boolean exists = false;
		for (Course course : pendingCourses) {
			if (course.getId().equals(pendingCourse.getId())) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			if (completedCourses == null || !completedCourses.contains(pendingCourse)) {
				pendingCourse.getUsersPending().add(this);
				this.pendingCourses.add(pendingCourse);
			} else {
				throw new IllegalArgumentException(
						"Cannot add pending course to user, the course is a completed course");
			}
		}
	}

	public void addCompletedCourse(final Course completedCourse) {
		if (this.completedCourses == null) {
			this.completedCourses = new ArrayList<>();
		}
		if (completedCourse.getUsersCompleted() == null) {
			completedCourse.setUsersCompleted(new ArrayList<>());
		}
		boolean exists = false;
		if (pendingCourses == null || !pendingCourses.contains(completedCourse)) {
			for (Course course : this.completedCourses) {
				if (completedCourse.getId().equals(course.getId())) {
					exists = true;
					break;
				}
			}
			if (!exists) {
				completedCourse.getUsersCompleted().add(this);
				this.completedCourses.add(completedCourse);
				this.addCertification(completedCourse.getCertification());
			}
		} else {
			throw new IllegalArgumentException("Cannot add completed course to user, the course is a pending course");
		}

	}

	public void removePendingCourse(Course course) {
		if (this.pendingCourses == null) {
			throw new NullPointerException("User is not attending any courses");
		}
		if (course.getUsersPending() == null) {
			throw new NullPointerException("Course does not have any pending users");
		}
		// Remove course from user's pendingCourses
		for (int i = 0; i < this.pendingCourses.size(); i++) {
			if (this.pendingCourses.get(i).getId().equals(course.getId())) {
				// Remove user from course's usersPending
				for (int j = 0; j < course.getUsersPending().size(); j++) {
					if (course.getUsersPending().get(j).getId().equals(this.getId())) {
						course.getUsersPending().remove(j);
						break;
					}

				}
				this.pendingCourses.remove(i);
				break;
			}
		}
	}

	public void addCertification(final Certification newCertification) {
		if (this.certifications == null) {
			this.certifications = new ArrayList<>();
		}
		if (newCertification.getHolders() == null) {
			newCertification.setHolders(new ArrayList<>());
		}
		boolean exists = false;
		for (Certification certification : certifications) {
			if (newCertification.getId().equals(certification.getId())) {
				exists = true;
				break;
			}
		}
		if (!exists) {
			newCertification.getHolders().add(this);
			this.certifications.add(newCertification);
		}
	}

}
