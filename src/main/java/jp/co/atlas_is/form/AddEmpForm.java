package jp.co.atlas_is.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出席者追加画面Form
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AddEmpForm extends BaseForm{
	/** 氏名 */
	@NotEmpty(message = "名前を入力してください")
	@Size(min = 0, max = 10, message = "名前は１０文字以内で入力してください")
	private String addNamae;
}
