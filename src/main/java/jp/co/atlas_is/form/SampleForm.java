package jp.co.atlas_is.form;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 出欠情報DTO
 */
@Data
public class SampleForm {

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
}
