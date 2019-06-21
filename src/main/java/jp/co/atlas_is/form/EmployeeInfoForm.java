package jp.co.atlas_is.form;

import lombok.Data;

/**
 * 社員DTO
 */
@Data
public class EmployeeInfoForm {

	/** 職員番号 */
	private String employeeNo;

	/** 氏名カナ */
	private String kanaName;

	/** 氏名 */
	private String name;

}
