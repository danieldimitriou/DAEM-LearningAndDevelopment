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

	public void addMember(User member) {
		member.setDepartment(this);
		members.add(member);
	}
}
