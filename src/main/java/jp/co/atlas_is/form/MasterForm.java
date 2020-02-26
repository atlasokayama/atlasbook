package jp.co.atlas_is.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * マスタ管理画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MasterForm extends BaseForm{
	/** 社員NO */
	private int emp_no;

	/** 氏名 */
	private String name;
}