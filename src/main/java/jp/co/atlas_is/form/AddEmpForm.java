package jp.co.atlas_is.form;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AddEmpForm {
	/** 氏名 */
	@Size(min = 0, max = 10, message = "名前は１０文字以内で入力してください")
	private String addNamae;
}
