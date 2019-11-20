package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

public class EditService {
	/**
	 * 出欠入力情報取得処理
	 * 
	 * @param empNo
	 * @return List<EditForm>
	 */
	public boolean attendanceEdit(EditForm model) {

		try (Connection con = DbUtil.getConnection()) {

			String sql = "insert into attendance " + 
					"(emp_no, am_attend, am_reason, pm_attend, pm_reason, attend_yearmonth) " + 
					"values (?," + 
					"?," + 
					"?," + 
					"?," + 
					"?," + 
					"? ) " + 
					"on conflict on constraint attendance_pkey " + 
					"do update " + 
					"set am_attend=?, am_reason=?," + 
					"pm_attend=?, pm_reason=?";

			PreparedStatement stmt = con.prepareStatement(sql);
			
			// Insert
			// TODO：画面から情報を受け取っていないので要修正
//			stmt.setInt(1, model.getEmp_no());  
			stmt.setInt(1, 1);
			stmt.setBoolean(2, model.isAm_attend());
			stmt.setString(3, model.getAm_reason());
			stmt.setBoolean(4, model.isPm_attend());
			stmt.setString(5, model.getPm_reason());
			// TODO：画面から情報を受け取っていないので要修正
			stmt.setDate(6, Date.valueOf("2019-12-01"));

			// Update
			stmt.setBoolean(7, model.isAm_attend());
			stmt.setString(8, model.getAm_reason());
			stmt.setBoolean(9, model.isPm_attend());
			stmt.setString(10, model.getPm_reason());  

			// SQLを実行
			stmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}