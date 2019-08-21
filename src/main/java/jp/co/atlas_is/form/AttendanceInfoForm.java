package jp.co.atlas_is.form;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 出欠情報
 */
@Data
public class AttendanceInfoForm {

	/** 社員NO */
	private int emp_no;

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
}
