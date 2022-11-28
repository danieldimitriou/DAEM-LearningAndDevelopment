package gr.athtech.daem.service;

import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.User;
import gr.athtech.daem.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Override
	public JpaRepository<Department, Long> getRepository() {
		return departmentRepository;
	}

	@Override
	public Department findDepartmentByHeadOfDepartment(final User headOfDepartment) {
		return departmentRepository.findDepartmentByHeadOfDepartment(headOfDepartment);
	}

	@Override
	public Department findDepartmentByMembers(final User[] members) {
		return departmentRepository.findDepartmentByMembers(members);
	}

	@Override
	public Department findDepartmentByNameLike(final String name) {
		return departmentRepository.findDepartmentByNameLike(name);
	}

	@Override
	public Department replaceDepartmentHead(final Department department, final User newHeadOfDepartment) {
		department.setHeadOfDepartment(newHeadOfDepartment);
		return departmentRepository.save(department);
	}

	@Override
	public Department renameDepartment(final Department department, final String name) {
		department.setName(name);
		return departmentRepository.save(department);
	}

	@Override
	public Department addMember(final Department department, final User memberToBeAdded) {
		department.getMembers().add(memberToBeAdded);
		return departmentRepository.save(department);
	}

	@Override
	public Department removeMember(final Department department, final User memberToBeRemoved) {
		department.getMembers().remove(memberToBeRemoved);
		return departmentRepository.save(department);
	}
}
