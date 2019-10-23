package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

public class ListService {
	public List<EditForm> getEmployeeList() {
		List<EditForm> list = new ArrayList<EditForm>();
		
		// DBコネクションを取得
		try(Connection con = DbUtil.getConnection()) {
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
		}
		
		return list;
	}

}
