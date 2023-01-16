package gr.athtech.daem.converter;

import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.dto.AuthorityDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthorityConverter {

	private ModelMapper modelMapper;

	public AuthorityDTO convertToDTO(Authority authority) {
		return modelMapper.map(authority, AuthorityDTO.class);
	}
}
