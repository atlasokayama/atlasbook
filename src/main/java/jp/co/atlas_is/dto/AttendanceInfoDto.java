package jp.co.atlas_is.dto;

import java.sql.Date;

/**
 * 出欠情報DTO
 */
public class AttendanceInfoDto {

	/** 社員NO */
	private int emp_no;

	/** 年 */
	private Date attend_yearmonth;

	/** AM出欠 */
	private boolean am_attend;

	/** AM理由 */
	private String am_reason;

	/** PM出欠 */
	private boolean pm_attend;

	/** PM理由 */
	private String pm_reason;

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public Date getAttend_yearmonth() {
		return attend_yearmonth;
	}

	public void setAttend_yearmonth(Date attend_yearmonth) {
		this.attend_yearmonth = attend_yearmonth;
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

}
