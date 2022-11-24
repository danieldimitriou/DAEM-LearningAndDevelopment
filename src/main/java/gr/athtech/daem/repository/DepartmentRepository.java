package gr.athtech.daem.repository;

import gr.athtech.daem.domain.Department;
import gr.athtech.daem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findDepartmentByHeadOfDepartment(User headOfDepartment);

	Department findDepartmentByMembers(List<User> members);

	Department findDepartmentByNameLike(String name);
}
