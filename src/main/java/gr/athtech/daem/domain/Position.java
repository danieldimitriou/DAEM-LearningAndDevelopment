package gr.athtech.daem.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POSITION")
public class Position extends BaseModel {

	@Column(length = 50, nullable = false, unique = true)
	@NotEmpty
	@NotNull
	private String name;

	@Column(length = 20, nullable = false)
	@NotEmpty
	@NotNull
	private String level;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "position")
	@JoinColumn(name = "user_id")
	private User user;

}
