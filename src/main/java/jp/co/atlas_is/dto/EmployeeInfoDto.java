package jp.co.atlas_is.dto;

import lombok.Data;

/**
 * 社員情報DTO
 */
@Data
public class EmployeeInfoDto {

	/** 職員番号 */
	private String employeeNo;

	/** 氏名カナ */
	private String kanaName;

	/** 氏名 */
	private String name;

}
