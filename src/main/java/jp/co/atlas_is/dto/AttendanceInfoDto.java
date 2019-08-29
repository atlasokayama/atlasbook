package jp.co.atlas_is.dto;

import lombok.Data;

/**
 * 出欠情報DTO
 */
@Data
public class AttendanceInfoDto {

	/** 社員NO */
	private int emp_no;

	/** 年 */
	private int year;

	/** 月 */
	private int month;

	/** AM出欠 */
	private boolean am_attend;

	/** AM理由 */
	private String am_reason;

	/** PM出欠 */
	private boolean pm_attend;

	/** PM理由 */
	private String pm_reason;

}
