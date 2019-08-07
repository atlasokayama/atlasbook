package jp.co.atlas_is.controller;

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
	@RequestMapping(value = "/sample",params = "edit", method = RequestMethod.POST)
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

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}
}