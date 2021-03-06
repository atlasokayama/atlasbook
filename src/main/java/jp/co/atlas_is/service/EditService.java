package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Service;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

/**
 * 出席者追加画面のサービスクラス
 */
@Service
public class EditService {
	
	/**
	 * 出欠入力情報登録処理
	 * 
	 * @param model
	 * @return 登録可否(true:成功、false:失敗)
	 */
	public boolean attendanceEdit(EditForm model) {

		try (Connection con = DbUtil.getConnection()) {

			String sql = 
					"insert into attendance "
					+ "(emp_no, am_attend, am_reason, pm_attend, pm_reason, attend_yearmonth) " 
					+ "values (?," + "?,"
					+ "?," + "?," + "?," + "? ) " 
					+ "on conflict on constraint attendance_pkey " 
					+ "do update "
					+ "set am_attend=?, am_reason=?," 
					+ "pm_attend=?, pm_reason=?";

			PreparedStatement stmt = con.prepareStatement(sql);

			// Insert
			stmt.setInt(1, model.getEmp_no());
			stmt.setBoolean(2, model.isAm_attend());
			stmt.setString(3, model.getAm_reason());
			stmt.setBoolean(4, model.isPm_attend());
			stmt.setString(5, model.getPm_reason());
			stmt.setDate(6, Date.valueOf(model.getTargetMonth() + "-01"));

			// Update
			stmt.setBoolean(7, model.isAm_attend());
			stmt.setString(8, model.getAm_reason());
			stmt.setBoolean(9, model.isPm_attend());
			stmt.setString(10, model.getPm_reason());

			// SQLを実行
			stmt.execute();
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return false;
	}
}