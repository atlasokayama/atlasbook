package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

import java.sql.Connection;

public class AddEmpService {
	public List<EditForm> addEmployee(String name) {
		List<EditForm> list = new ArrayList<EditForm>();

		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {
			String sql = "insert into employee (emp_no, emp_name) select MAX(emp_no)+1, ? from employee;";
            
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, name);

			// SQLを実行
			stmt.executeUpdate();
			
//			con.commit();

			// 結果を取得
//			while (rs.next()) {
//				// 項目名指定で結果を取得してFormに格納
//				EditForm edit = new EditForm();
//				edit.setEmp_no(rs.getInt("emp_no"));
//				edit.setEmp_name(rs.getString("emp_name"));
//				list.add(edit);
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		return list;
	}
}
