package jp.co.atlas_is.form;

import java.util.List;

/**
 * 出欠一覧画面フォーム
 */
public class ListForm extends BaseForm {
	/** 出欠情報 */
	private List<EditForm> attendanceInfoList;

	/** 社員数 */
	@SuppressWarnings("unused")
	private int totalEmployees;
	
	/**
	 * 社員数取得処理
	 * 
	 * @return 社員数
	 */
	public int getTotalEmployees() {
		// 出欠一覧リストが空なら0、そうでないなら件数を返却
		if (attendanceInfoList == null || attendanceInfoList.isEmpty()) {
			return 0;
		} else {
			return attendanceInfoList.size();
		}
	}


	/** AM出席者数 */
	@SuppressWarnings("unused")
	private int totalAmAttendanceCount;

	/**
	 * AM出席者数取得処理
	 * 
	 * @return AM出席者数
	 */
	public int getTotalAmAttendanceCount() {
		// 出欠一覧リストが空なら0、そうでないならAM出席件数を返却
		if (attendanceInfoList == null || attendanceInfoList.isEmpty()) {
			return 0;
		} else {
			return (int) attendanceInfoList.stream().filter(r -> r.isAm_attend()).count();
		}
	}

	/** PM出席者数 */
	@SuppressWarnings("unused")
	private int totalPmAttendanceCount;
	
	/**
	 * PM出席者数取得処理
	 * 
	 * @return PM出席者数
	 */
	public int getTotalPmAttendanceCount() {
		// 出欠一覧リストが空なら0、そうでないならPM出席件数を返却
		if (attendanceInfoList == null || attendanceInfoList.isEmpty()) {
			return 0;
		} else {
			return (int) attendanceInfoList.stream().filter(r -> r.isPm_attend()).count();
		}
	}

	public List<EditForm> getAttendanceInfoList() {
		return attendanceInfoList;
	}

	public void setAttendanceInfoList(List<EditForm> attendanceInfoList) {
		this.attendanceInfoList = attendanceInfoList;
	}

	public void setTotalEmployees(int totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	public void setTotalAmAttendanceCount(int totalAmAttendanceCount) {
		this.totalAmAttendanceCount = totalAmAttendanceCount;
	}

	public void setTotalPmAttendanceCount(int totalPmAttendanceCount) {
		this.totalPmAttendanceCount = totalPmAttendanceCount;
	}	
}