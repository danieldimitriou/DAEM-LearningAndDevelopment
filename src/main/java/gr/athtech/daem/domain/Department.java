package gr.athtech.daem.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table
@SequenceGenerator(name = "idGenerator", allocationSize = 1)
public class Department extends BaseModel {

	@Column(length = 50, nullable = false, unique = true)
	@NotEmpty
	@NotNull
	private String name;

	@JsonBackReference
	@OneToOne
	private User headOfDepartment;

	@JsonIgnore
	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
	private List<User> members;

	public void addMember(User newMember) {
		if (this.members == null) {
			this.members = new ArrayList<>();
		}
		// If the user is not in any Department add them to this Department
		if (newMember.getDepartment() == null) {
			newMember.setDepartment(this);
			members.add(newMember);
		}
		// Else if the user is a member of another Department remove them from that Department and add them on this one
		else if (!newMember.getDepartment().getId().equals(this.getId())) {
			// If the user was the head of their previous department make the head of the previous department null
			if (newMember.getDepartment().getHeadOfDepartment() != null &&
					newMember.getDepartment().getHeadOfDepartment().getId().equals(newMember.getId())) {
				newMember.getDepartment().setHeadOfDepartment(null);
			}
			// Remove user from previous Department and add them on this one
			for (int i = 0; i < newMember.getDepartment().getMembers().size(); i++) {
				if (newMember.getDepartment().getMembers().get(i).getId().equals(newMember.getId())) {
					newMember.getDepartment().getMembers().remove(i);
					newMember.setDepartment(this);
					members.add(newMember);
					break;
				}
			}

		}
	}

}
