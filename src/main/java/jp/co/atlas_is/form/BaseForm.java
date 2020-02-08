package jp.co.atlas_is.form;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class BaseForm {
	private YearMonth targetMonth;

	@SuppressWarnings("unused")
	private String dispYearMonth;

	public String getDispYearMonth() {
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月");
		return targetMonth.format(dtf1);
	}
	
}
