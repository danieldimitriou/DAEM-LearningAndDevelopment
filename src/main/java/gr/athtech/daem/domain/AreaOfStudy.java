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
import jakarta.persistence.ManyToMany;
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
public class AreaOfStudy extends BaseModel {

	@Column(nullable = false)
	@NotEmpty
	@NotNull
	private String name;

	@Column(nullable = false)
	@NotEmpty
	@NotNull
	private String description;

	@JsonIgnore
	@ManyToMany(mappedBy = "areasOfStudy", cascade = CascadeType.PERSIST)
	private List<Course> courses;
}
