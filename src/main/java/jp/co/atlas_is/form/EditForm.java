package jp.co.atlas_is.form;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出欠入力画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EditForm {

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
	
	private boolean chk;
}