package gr.athtech.daem.controller;

import gr.athtech.daem.converter.AuthorityConverter;
import gr.athtech.daem.domain.Authority;
import gr.athtech.daem.dto.AuthorityDTO;
import gr.athtech.daem.service.AuthorityService;
import gr.athtech.daem.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("authorities")
@RequiredArgsConstructor
@CrossOrigin
public class AuthorityController{

	private final AuthorityService authorityService;
	private final AuthorityConverter authorityConverter;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Authority>>> getAllAuthorities() {
		final List<Authority> authorities = authorityService.findAll();
		return ResponseEntity.ok(ApiResponse.<List<Authority>>builder().data(authorities).build());
	}

	@Transactional
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<AuthorityDTO>> getAuthorityById(@PathVariable(name = "id") Long id) {
		Optional<Authority> optionalAuthority = authorityService.findById(id);
		if (optionalAuthority.isEmpty()) {
			throw new NoSuchElementException("Element not found");
		}
		final AuthorityDTO authorityDTO = authorityConverter.convertToDTO(optionalAuthority.get());
		return ResponseEntity.ok(ApiResponse.<AuthorityDTO>builder().data(authorityDTO).build());
	}

}
