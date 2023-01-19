package gr.athtech.daem.controller;

import gr.athtech.daem.base.BaseComponent;
import gr.athtech.daem.domain.BaseModel;
import gr.athtech.daem.service.BaseService;
import gr.athtech.daem.transfer.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
public abstract class BaseController<T extends BaseModel> extends BaseComponent {

	protected abstract BaseService<T> getBaseService();

	@GetMapping
	public ResponseEntity<ApiResponse<List<T>>> getAll() {
		return ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getBaseService().findAll()).build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<T>> get(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(ApiResponse.<T>builder().data(getBaseService().get(id)).build());
	}

	@PostMapping
	public ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody final T entity) {
		return new ResponseEntity<>(ApiResponse.<T>builder().data(getBaseService().create(entity)).build(),
									HttpStatus.CREATED);
	}

	@PostMapping(headers = "content=List")
	public ResponseEntity<ApiResponse<List<T>>> createAll(@RequestBody List<T> entities) {
		return new ResponseEntity<>(ApiResponse.<List<T>>builder().data(getBaseService().createAll(entities)).build(),
									HttpStatus.CREATED);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final T entity) {
		getBaseService().update(entity);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") final Long id) {
		getBaseService().deleteById(id);
	}

}
