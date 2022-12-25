package gr.athtech.daem.converter;

import gr.athtech.daem.domain.Course;
import gr.athtech.daem.dto.CourseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;


@AllArgsConstructor
@Component
public class CourseConverter {

	 final ModelMapper mapper;

	public Course dtoToEntity(CourseDTO courseDTO) {
		Course course = mapper.map(courseDTO, Course.class);
		return course;

	}

	public CourseDTO entityToDto(Course course) {
		CourseDTO courseDTO = mapper.map(course, CourseDTO.class);
		return courseDTO;
	}

	public List<CourseDTO> entityToDto(List<Course> courses) {
		return courses.stream().map(this:: entityToDto).toList();
	}

}
