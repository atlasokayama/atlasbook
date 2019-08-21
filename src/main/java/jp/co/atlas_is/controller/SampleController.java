package jp.co.atlas_is.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.AttendanceInfoForm;
import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.EmployeeInfoForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.util.DbUtil;

@Controller
public class SampleController {

	/**
	 * サンプル画面遷移処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@RequestParam Optional<String> error) {
		// 出欠入力画面に表示する情報
		EditForm form = new EditForm();
		EmployeeInfoForm employeeInfo = new EmployeeInfoForm();
		AttendanceInfoForm attendanceInfo = new AttendanceInfoForm();
		form.setEmployeeInfo(employeeInfo);
		form.setAttendanceInfo(attendanceInfo);

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("sample", "form", form);
		return mav;
	}

	/**
	 * 出欠入力画面登録処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(value = "/sample", params = "edit", method = RequestMethod.POST)
	ModelAndView master(@Validated @ModelAttribute EditForm input, Errors errors) {
		// エラーチェック
		if (errors.hasErrors()) {
			// エラーがある場合は自画面へ
			// 遷移先情報を設定
			ModelAndView mav = new ModelAndView("sample", "form", input);
			mav.addObject("errors", errors.getAllErrors());
			return mav;
		}
		// 登録成功時は一覧画面へ遷移
		// formを作成
		ListForm form = new ListForm();
		List<EditForm> list = new ArrayList<EditForm>();
		
		try {
			// DBコネクションを取得
			Connection con = DbUtil.getConnection();
			
			String sql = "select * from attendance a left join employee b on a.emp_no = b.emp_no;";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			// SQLを実行
			ResultSet rs = stmt.executeQuery();
			
			// 結果を取得
			while (rs.next()) {
				// 項目名指定で結果を取得してFormに格納
				EditForm edit = new EditForm();
				AttendanceInfoForm attend = new AttendanceInfoForm();
				attend.setEmp_no(rs.getInt("emp_no"));
				attend.setAm_attend(rs.getBoolean("am_attend"));
				attend.setAm_reason(rs.getString("am_reason"));
				attend.setPm_attend(rs.getBoolean("pm_attend"));
				attend.setPm_reason(rs.getString("pm_reason"));
				edit.setAttendanceInfo(attend);
				EmployeeInfoForm emp = new EmployeeInfoForm();
				emp.setName(rs.getString("emp_name"));
				edit.setEmployeeInfo(emp);
				list.add(edit);
			}
			// 取得結果を格納
			form.setAttendanceInfoList(list);
		} catch (Exception e) {
			// エラー画面へ遷移
			ModelAndView mav = new ModelAndView("error");
			return mav;
		}

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}
}