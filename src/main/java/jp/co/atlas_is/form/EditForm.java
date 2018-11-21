package jp.co.atlas_is.form;

import jp.co.atlas_is.dto.AttendanceInfoDto;
import jp.co.atlas_is.dto.EmployeeInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出欠入力画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EditForm{

	/** 職員情報 */
	private EmployeeInfoDto employeeInfo;

	/** 出欠情報 */
	private AttendanceInfoDto attendanceInfo;
}