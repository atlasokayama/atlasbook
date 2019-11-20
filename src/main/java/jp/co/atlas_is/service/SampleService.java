package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

public class SampleService {

	/**
	 * 一覧情報取得処理
	 * 
	 * @return List<EditForm>
	 */
	public List<EditForm> getSampleList() {
		List<EditForm> list = new ArrayList<EditForm>();

		try (Connection con = DbUtil.getConnection()) {
			String sql = "select * from attendance a left join employee b on a.emp_no = b.emp_no;";
			PreparedStatement stmt = con.prepareStatement(sql);

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
				list.add(edit);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return list;
	}

	/**
	 * 出欠入力情報取得処理
	 * 
	 * @param empNo
	 * @return List<EditForm>
	 */
	public List<EditForm> getSampleRow(int empNo) {
		List<EditForm> list = new ArrayList<EditForm>();

		try (Connection con = DbUtil.getConnection()) {

			String sql = "select * from attendance a left join employee b on a.emp_no = b.emp_no where a.emp_no = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empNo);

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
				list.add(edit);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return list;
	}

}