package jp.co.atlas_is.form;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 一覧画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListForm extends BaseForm {
	/** 出欠情報 */
	private List<EditForm> attendanceInfoList;

	@SuppressWarnings("unused")
	private int totalEmployees;
	
	public int getTotalEmployees() {
		if (attendanceInfoList == null || attendanceInfoList.isEmpty()) {
			return 0;
		} else {
			return attendanceInfoList.size();
		}
	}


	@SuppressWarnings("unused")
	private int totalAmAttendanceCount;

	public int getTotalAmAttendanceCount() {
		if (attendanceInfoList == null || attendanceInfoList.isEmpty()) {
			return 0;
		} else {
			return (int) attendanceInfoList.stream().filter(r -> r.isAm_attend()).count();
		}
	}

	@SuppressWarnings("unused")
	private int totalPmAttendanceCount;
	
	public int getTotalPmAttendanceCount() {
		if (attendanceInfoList == null || attendanceInfoList.isEmpty()) {
			return 0;
		} else {
			return (int) attendanceInfoList.stream().filter(r -> r.isPm_attend()).count();
		}
	}
}