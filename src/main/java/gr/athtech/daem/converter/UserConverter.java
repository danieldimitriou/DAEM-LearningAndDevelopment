package gr.athtech.daem.converter;

import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserConverter {

	private final ModelMapper modelMapper;

	public User convertToEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}

	public List<User> convertToEntity(List<UserDTO> userDTOList) {
		return userDTOList.stream().map(this::convertToEntity).toList();
	}

	public UserDTO convertToDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}

	public List<UserDTO> convertToDTO(List<User> users) {
		return users.stream().map(this::convertToDTO).toList();
	}

}
