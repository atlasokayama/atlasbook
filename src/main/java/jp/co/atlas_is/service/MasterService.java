package jp.co.atlas_is.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.co.atlas_is.util.DbUtil;

/**
 * マスタ管理画面のサービスクラス
 */
@Service
public class MasterService {

	/**
	 * マスタ情報登録処理
	 * 
	 * @param name
	 * @return 登録可否(true:成功、false:失敗)
	 */
	public boolean addEmployee(String name) {
		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {
			String sql = "insert into employee (emp_no, emp_name) select MAX(emp_no)+1, ? from employee;";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, name);

			// SQLを実行
			stmt.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	/**
	 * マスタ情報削除処理
	 * 
	 * @param listEmpNo 削除対象のEmp_No一覧
	 * @return 登録可否(true:成功、false:失敗)
	 */
	public boolean delEmp(List<Integer> listEmpNo) {
		// DBコネクションを取得
		try (Connection con = DbUtil.getConnection()) {

			String sql = "delete from employee where emp_no = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			for (Integer empNo : listEmpNo) {
				stmt.setInt(1, empNo);
				// SQLを実行
				stmt.executeUpdate();
			}
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}
}
