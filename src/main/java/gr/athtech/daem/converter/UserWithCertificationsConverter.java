package gr.athtech.daem.converter;

import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.UserWithCertificationsDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserWithCertificationsConverter {

	private final ModelMapper modelMapper;

	public UserWithCertificationsDTO convertToDTO(User user) {
		return modelMapper.map(user, UserWithCertificationsDTO.class);
	}

	public List<UserWithCertificationsDTO> convertToDTO(List<User> users) {
		return users.stream().map(this::convertToDTO).toList();
	}

	public User convertToEntity(UserWithCertificationsDTO userWithCertificationsDTO) {
		return modelMapper.map(userWithCertificationsDTO, User.class);
	}

	public List<User> convertToEntity(List<UserWithCertificationsDTO> userWithCertificationsDTOList) {
		return userWithCertificationsDTOList.stream().map(this::convertToEntity).toList();
	}
}
