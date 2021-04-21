package jp.co.atlas_is.form;

import javax.validation.constraints.Size;

/**
 * 出欠入力画面フォーム
 */
public class EditForm extends BaseForm {

	/** 社員NO */
	private int emp_no;

	/** 氏名 */
	private String emp_name;

	/** AM出欠 */
	private boolean am_attend;

	/** AM欠席理由 */
	@Size(min = 0, max = 20, message = "AM欠席理由は２０文字以内で入力してください")
	private String am_reason;

	/** PM出欠 */
	private boolean pm_attend;

	/** PM欠席理由 */
	@Size(min = 0, max = 20, message = "PM欠席理由は２０文字以内で入力してください")
	private String pm_reason;

	/** 出欠未登録 */
	private boolean attendance;
	
	private boolean chk;

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

	public boolean isAm_attend() {
		return am_attend;
	}

	public void setAm_attend(boolean am_attend) {
		this.am_attend = am_attend;
	}

	public String getAm_reason() {
		return am_reason;
	}

	public void setAm_reason(String am_reason) {
		this.am_reason = am_reason;
	}

	public boolean isPm_attend() {
		return pm_attend;
	}

	public void setPm_attend(boolean pm_attend) {
		this.pm_attend = pm_attend;
	}

	public String getPm_reason() {
		return pm_reason;
	}

	public void setPm_reason(String pm_reason) {
		this.pm_reason = pm_reason;
	}

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	public boolean isChk() {
		return chk;
	}

	public void setChk(boolean chk) {
		this.chk = chk;
	}
}