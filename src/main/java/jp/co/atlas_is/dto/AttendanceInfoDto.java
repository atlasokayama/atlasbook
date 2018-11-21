package jp.co.atlas_is.dto;

import lombok.Data;

/**
 * 出欠情報DTO
 */
@Data
public class AttendanceInfoDto {

	/** 職員番号 */
	private String employeeNo;

	/** AM出欠 */
	private String attendanceAm;

	/** AM欠席理由 */
	private String reasonAm;

	/** PM出欠 */
	private String attendancePm;

	/** PM欠席理由 */
	private String reasonPm;

}
