package gr.athtech.daem.controller;

import gr.athtech.daem.converter.CourseConverter;
import gr.athtech.daem.domain.AreaOfStudy;
import gr.athtech.daem.domain.Course;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.dto.CourseDTO;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.service.CourseService;
import gr.athtech.daem.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("courses")
@CrossOrigin
public class CourseController extends BaseController<Course> {

	private final CourseService courseService;
	private final CourseConverter courseConverter;
	@Override
	protected BaseService<Course> getBaseService() {
		return courseService;
	}

	@GetMapping("id")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(courseService.get(id));
	}

//	@GetMapping
//	public ResponseEntity<List<Course>> getAllCourses() {
//		return ResponseEntity.ok(courseService.findAll());
//	}

	@PostMapping("create")
	public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseDTO courseDto){
		Course course = courseConverter.dtoToEntity(courseDto);
		Course newCourse = courseService.create(course);
		return new ResponseEntity<>(ApiResponse.<Course>builder().data(newCourse).build(),HttpStatus.CREATED );
	}

	@PostMapping("update")
	public ResponseEntity<ApiResponse<Course>> updateCourse(@RequestBody Course course) {
		return new ResponseEntity<>(ApiResponse.<Course>builder().data(course).build(), HttpStatus.NO_CONTENT);
	}

	@GetMapping("name")
	public ResponseEntity<List<Course>> findByName(@PathVariable("name") final String name) {
		return ResponseEntity.ok(courseService.findByName(name));
	}

	//	@GetMapping
	//	public ResponseEntity<List<Course>> findByTypeId(@PathVariable ("type") final Long typeId){
	//		return ResponseEntity.ok(courseService.findByTypeId(typeId));
	//	}
//	@GetMapping
//	public ResponseEntity<List<Course>> findByUsersPending(
//			@PathVariable("usersPending") final List<User> usersPending) {
//		return ResponseEntity.ok(courseService.findByUsersPending(usersPending));
//	}
//
//	@GetMapping
//	public ResponseEntity<List<Course>> findByUsersCompletedIn(
//			@PathVariable("usersCompleted") final List<User> usersCompleted) {
//		return ResponseEntity.ok(courseService.findByUsersCompletedIn(usersCompleted));
//	}
//
//	@GetMapping
//	public ResponseEntity<List<Course>> findCoursesByAreasOfStudy(
//			@PathVariable("areasOfStudy") final List<AreaOfStudy> areasOfStudy) {
//		return ResponseEntity.ok(courseService.findCoursesByAreasOfStudy(areasOfStudy));
//	}
//
//	@GetMapping
//	public ResponseEntity<Course> findByCertificationId(
//			@PathVariable("certificationIds") final Long[] certificationIds) {
//		return ResponseEntity.ok(courseService.findByCertificationId(certificationIds));
//	}

}
