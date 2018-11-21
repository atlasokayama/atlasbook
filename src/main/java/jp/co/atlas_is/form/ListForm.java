package jp.co.atlas_is.form;

import jp.co.atlas_is.dto.EmployeeInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 一覧画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ListForm{

	/** 職員情報 */
	private EmployeeInfoDto employeeInfo;
}