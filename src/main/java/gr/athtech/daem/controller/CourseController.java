package gr.athtech.daem.controller;

import gr.athtech.daem.converter.CourseConverter;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.dto.CourseDTO;
import gr.athtech.daem.service.AuthorityService;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.CourseService;
import gr.athtech.daem.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("courses")
@CrossOrigin
public class CourseController {

	private final CourseService courseService;

	private final AuthorityService authorityService;
	private final CourseConverter courseConverter;

	protected BaseService<Course> getBaseService() {
		return courseService;
	}

	@Transactional
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CourseDTO>> getCourseById(@PathVariable("id") final Long id) {
		Optional<Course> courseOptional = courseService.findById(id);
		if (courseOptional.isEmpty()) {
			throw new NoSuchElementException("Element not found");
		}
		final CourseDTO courseDTO = courseConverter.entityToDto(courseOptional.get());
		return ResponseEntity.ok(ApiResponse.<CourseDTO>builder().data(courseDTO).build());
	}

	@Transactional
	@GetMapping
	public ResponseEntity<ApiResponse<List<CourseDTO>>> getAllCourses() {
		final List<CourseDTO> courseDTOList = courseConverter.entityToDto(courseService.findAll());
		return ResponseEntity.ok(ApiResponse.<List<CourseDTO>>builder().data(courseDTOList).build());
	}

	@Transactional
	@PostMapping("create")
	public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseDTO courseDto) {
		final Course newCourse = courseConverter.dtoToEntity(courseDto);
		//		final Authority authority = newCourse.getCertification();
		authorityService.create(newCourse.getCertification().getCertificationAuthority());
		courseService.create(newCourse);
		return new ResponseEntity<>(ApiResponse.<Course>builder().data(newCourse).build(), HttpStatus.CREATED);
	}

	@GetMapping(params = "name")
	public ResponseEntity<ApiResponse<List<CourseDTO>>> findByName(@RequestParam String name) {
		final List<CourseDTO> courseDTOList = courseConverter.entityToDto(courseService.findByName(name));
		return ResponseEntity.ok(ApiResponse.<List<CourseDTO>>builder().data(courseDTOList).build());
	}

	@Transactional
	@GetMapping("/findBy/certification/{id}")
	public ResponseEntity<ApiResponse<CourseDTO>> findByCertification(@PathVariable("id") final Long id) {

		final CourseDTO courseDTO = courseConverter.entityToDto(courseService.findByCertificationId(id));
		return ResponseEntity.ok(ApiResponse.<CourseDTO>builder().data(courseDTO).build());
	}

	@Transactional
	@GetMapping("findType/{typeId}")
	public ResponseEntity<ApiResponse<List<CourseDTO>>> findByTypeId(@PathVariable("typeId") final Long typeId) {
		final List<CourseDTO> courseDTOList = courseConverter.entityToDto(courseService.findByTypeId(typeId));
		return ResponseEntity.ok(ApiResponse.<List<CourseDTO>>builder().data(courseDTOList).build());
	}

}
