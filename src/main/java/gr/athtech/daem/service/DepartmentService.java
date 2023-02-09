package gr.athtech.daem.service;

import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.User;

import java.util.Optional;

public interface DepartmentService extends BaseService<Department> {

	Optional<Department> findById(Long id);

	Department findDepartmentByHeadOfDepartment(User headOfDepartment);

	Department findDepartmentByMembers(User[] members);

	Department findDepartmentByNameLike(String name);

	Department replaceDepartmentHead(Department department, User newHeadOfDepartment);

	Department renameDepartment(Department department, String name);

	Department addMember(Department department, User memberToBeAdded);

	Department removeMember(Department department, User memberToBeRemoved);
}
