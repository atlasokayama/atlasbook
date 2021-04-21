package jp.co.atlas_is.dto;

/**
 * 社員DTO
 */
public class EmployeeInfoDto {

	/** 職員番号 */
	private int emp_no;

	/** 名前 */
	private String emp_name;

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
}
