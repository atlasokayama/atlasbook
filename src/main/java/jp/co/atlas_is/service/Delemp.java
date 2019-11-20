/**
 * 
 */
package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.util.DbUtil;

/**
 * @author atlas-okayama
 *
 */
public class Delemp {
	public static void delEmp() {

		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {			
			
			String sql = "delete from employee where emp_no = 1;";
			PreparedStatement stmt = con.prepareStatement(sql);

			// SQLを実行
			stmt.executeUpdate();

		} catch (Exception e) {
		}
	}
}
