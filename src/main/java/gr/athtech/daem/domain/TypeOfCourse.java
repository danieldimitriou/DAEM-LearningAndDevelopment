package gr.athtech.daem.domain;

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
public class TypeOfCourse extends BaseModel {

	@Column(length = 50, nullable = false)
	@NotEmpty
	@NotNull
	private String name;

	@Column(length = 50, nullable = false)
	@NotEmpty
	@NotNull
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
	private List<Course> courses = new ArrayList<>();
}
