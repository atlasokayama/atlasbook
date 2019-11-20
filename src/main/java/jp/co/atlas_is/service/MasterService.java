package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

public class MasterService {
	
	public List<EditForm> addEmployee(String name) {
		List<EditForm> list = new ArrayList<EditForm>();

		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {
			String sql = "insert into employee (emp_no, emp_name) select MAX(emp_no)+1, ? from employee;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, name);

			// SQLを実行
			stmt.executeUpdate();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		return list;
	}
	
	public static void delEmp() {
		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {			
			
			String sql = "delete from employee where emp_no = 1;";
			PreparedStatement stmt = con.prepareStatement(sql);

			// SQLを実行
			stmt.executeUpdate();

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
