package jp.co.atlas_is.form;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * BaseForm
 * 各Formクラスの基底クラス
 */
public class BaseForm {
	private YearMonth targetMonth;

	/** 表示年月 */
	@SuppressWarnings("unused")
	private String dispYearMonth;

	/**
	 * 表示年月取得処理
	 * 
	 * @return 表示用年月("yyyy年MM月"形式)
	 */
	public String getDispYearMonth() {
		// 表示年月がnullなら空文字、nullでないなら"yyyy年MM月"形式の文字列を返却
		if (targetMonth != null) {
			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月");
			return targetMonth.format(dtf1);			
		} else {
			return "";
		}
	}

	public YearMonth getTargetMonth() {
		return targetMonth;
	}

	public void setTargetMonth(YearMonth targetMonth) {
		this.targetMonth = targetMonth;
	}

	public void setDispYearMonth(String dispYearMonth) {
		this.dispYearMonth = dispYearMonth;
	}
	
}
