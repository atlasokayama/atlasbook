package jp.co.atlas_is.form;

import jp.co.atlas_is.dto.EmployeeInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * マスタ管理画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MasterForm{

	/** 職員情報 */
	private EmployeeInfoDto employeeInfo;
}