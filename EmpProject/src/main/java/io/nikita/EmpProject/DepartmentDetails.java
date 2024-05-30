package io.nikita.EmpProject;

public class DepartmentDetails {
    private Long deptId;
    private String name;

    public DepartmentDetails(Long deptId, String deptName) {
        this.deptId=deptId;
        this.name=deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}