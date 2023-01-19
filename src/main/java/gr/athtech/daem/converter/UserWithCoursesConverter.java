package gr.athtech.daem.converter;

import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.UserWithCoursesDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class UserWithCoursesConverter {

	private ModelMapper modelMapper;

	public UserWithCoursesDTO convertToDTO(User user) {
		return modelMapper.map(user, UserWithCoursesDTO.class);
	}

	public List<UserWithCoursesDTO> convertToDTO(List<User> users) {
		return users.stream().map(this::convertToDTO).toList();
	}

	public User convertToEntity(UserWithCoursesDTO userWithCoursesDTO) {
		return modelMapper.map(userWithCoursesDTO, User.class);
	}

	public List<User> convertToEntity(List<UserWithCoursesDTO> userWithCoursesDTOList) {
		return userWithCoursesDTOList.stream().map(this::convertToEntity).toList();
	}
}
