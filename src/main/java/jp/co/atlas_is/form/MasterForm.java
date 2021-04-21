package jp.co.atlas_is.form;

/**
 * マスタ管理画面フォーム
 */
public class MasterForm extends BaseForm{
	/** 社員NO */
	private int emp_no;

	/** 氏名 */
	private String name;

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}