package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

public class LoginService {

	/**
	 * 一覧情報取得処理
	 * 
	 * @return List<EditForm>
	 */
	public static List<EditForm> getLoginList(YearMonth target) {
		List<EditForm> list = new ArrayList<EditForm>();

		try (Connection con = DbUtil.getConnection()) {

			String sql = "select E.emp_no, E.emp_name, A.am_attend, A.am_reason, A.pm_attend, A.pm_reason, COALESCE(A.emp_no, -1) AS \"attendance\" from employee E LEFT JOIN attendance A ON E.emp_no = A.emp_no AND A.attend_yearmonth = CAST(? as date);";

			PreparedStatement stmt = con.prepareStatement(sql);

			String hoge = String.format("%s-01", target.toString());
			stmt.setString(1, hoge);

			// SQLを実行
			ResultSet rs = stmt.executeQuery();

			// 結果を取得
			while (rs.next()) {
				// 項目名指定で結果を取得してFormに格納
				EditForm edit = new EditForm();
				edit.setEmp_no(rs.getInt("emp_no"));
				edit.setAm_attend(rs.getBoolean("am_attend"));
				edit.setAm_reason(rs.getString("am_reason"));
				edit.setPm_attend(rs.getBoolean("pm_attend"));
				edit.setPm_reason(rs.getString("pm_reason"));
				edit.setEmp_name(rs.getString("emp_name"));
				edit.setAttendance(rs.getInt("attendance") == -1 ? false : true);
				list.add(edit);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return list;
	}

}
