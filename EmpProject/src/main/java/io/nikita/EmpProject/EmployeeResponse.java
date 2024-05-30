package io.nikita.EmpProject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @NoArgsConstructor @Setter
public class EmployeeResponse {


    DepartmentDetails departmentDetails;
    private Long empId;
    private String name;
    private String email;
    private Long deptId;

    public EmployeeResponse(Long empId, String name, String email, Long deptId, String deptName) {
        this.empId=empId;
        this.name=name;
        this.deptId=deptId;
        this.email=email;
        this.departmentDetails= new DepartmentDetails(deptId,deptName);
    }
}