package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

/**
 * 出欠一覧画面のサービスクラス
 */
@Service
public class ListService {
	
	/**
	 * 出欠一覧情報取得処理
	 * 
	 * @return 出欠一覧
	 */
	public List<EditForm> getEmployeeList() {
		List<EditForm> list = new ArrayList<EditForm>();

		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {
			String sql = "select * from employee order by emp_no asc;";
			PreparedStatement stmt = con.prepareStatement(sql);

			// SQLを実行
			ResultSet rs = stmt.executeQuery();

			// 結果を取得
			while (rs.next()) {
				// 項目名指定で結果を取得してFormに格納
				EditForm edit = new EditForm();
				edit.setEmp_no(rs.getInt("emp_no"));
				edit.setEmp_name(rs.getString("emp_name"));
				list.add(edit);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		return list;
	}

	/**
	 * 出欠情報取得処理
	 * 
	 * @return 出欠情報
	 */
	public EditForm getAttendanceInfo(YearMonth targetMonth, String targetName) {
		EditForm edit = new EditForm();
		edit.setTargetMonth(targetMonth);

		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {
			String sql = "select E.emp_no, E.emp_name, A.am_attend, A.am_reason, A.pm_attend, A.pm_reason, COALESCE(A.emp_no, -1) AS \"attendance\" from employee E LEFT JOIN attendance A ON E.emp_no = A.emp_no AND A.attend_yearmonth = CAST(? as date) WHERE E.emp_name = ?;";

			PreparedStatement stmt = con.prepareStatement(sql);

			String hoge = String.format("%s-01", targetMonth.toString());
			stmt.setString(1, hoge);
			stmt.setString(2, targetName);

			// SQLを実行
			ResultSet rs = stmt.executeQuery();

			// 結果を取得
			while (rs.next()) {
				// 項目名指定で結果を取得してFormに格納
				edit.setEmp_no(rs.getInt("emp_no"));
				edit.setAm_attend(rs.getBoolean("am_attend"));
				edit.setAm_reason(rs.getString("am_reason"));
				edit.setPm_attend(rs.getBoolean("pm_attend"));
				edit.setPm_reason(rs.getString("pm_reason"));
				edit.setEmp_name(rs.getString("emp_name"));
				edit.setAttendance(rs.getInt("attendance") == -1 ? false : true);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		return edit;
	}

}
