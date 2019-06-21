package jp.co.atlas_is.form;

import javax.validation.Valid;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出欠入力画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class EditForm{

	/** 職員情報 */
	private EmployeeInfoForm employeeInfo;

	/** 出欠情報 */
	@Valid
	private AttendanceInfoForm attendanceInfo;
}