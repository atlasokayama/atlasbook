package jp.co.atlas_is.form;

import javax.validation.constraints.Size;

/**
 * サンプル画面Form
 */
public class SampleForm  extends BaseForm{

	/** 職員番号 */
	private String employeeNo;

	/** AM出欠 */
	private String attendanceAm;

	/** AM欠席理由 */
	private String reasonAm;

	/** PM出欠 */
	private String attendancePm;

	/** PM欠席理由 */
	@Size(min = 0, max = 10, message = "PM欠席理由は１０文字以内で入力してください")
	private String reasonPm;

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getAttendanceAm() {
		return attendanceAm;
	}

	public void setAttendanceAm(String attendanceAm) {
		this.attendanceAm = attendanceAm;
	}

	public String getReasonAm() {
		return reasonAm;
	}

	public void setReasonAm(String reasonAm) {
		this.reasonAm = reasonAm;
	}

	public String getAttendancePm() {
		return attendancePm;
	}

	public void setAttendancePm(String attendancePm) {
		this.attendancePm = attendancePm;
	}

	public String getReasonPm() {
		return reasonPm;
	}

	public void setReasonPm(String reasonPm) {
		this.reasonPm = reasonPm;
	}
	
}
