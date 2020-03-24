package jp.co.atlas_is.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.EditService;
import jp.co.atlas_is.service.LoginService;

/**
 * 出欠入力画面のコントローラクラス
 */
@Controller
@RequestMapping("edit")
public class EditController {

	// サービスクラスをDI
	@Autowired
	LoginService loginSv;
	@Autowired
	EditService editSv;

	/**
	 * 登録ボタン処理
	 * 
	 * @param input
	 * @param errors
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "attendanceEdit", method = RequestMethod.POST)
	ModelAndView edit(@Validated @ModelAttribute EditForm input, Errors errors) {
		// 入力エラーチェック
		if (errors.hasErrors()) {
			// エラーがある場合は自画面へ
			ModelAndView mav = new ModelAndView("edit", "form", input);
			mav.addObject("errors", errors.getAllErrors());
			return mav;
		}
		
		// 出欠情報を登録
		editSv.attendanceEdit(input);

		// 成功時は出欠一覧画面へ遷移
		ListForm form = new ListForm();
		form.setTargetMonth(input.getTargetMonth());

		// 出欠一覧を再検索して格納
		form.setAttendanceInfoList(loginSv.getLoginList(input.getTargetMonth()));
		form.setTargetMonth(input.getTargetMonth());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}
	
	/**
	 * 戻るボタン処理
	 * 
	 * @param input
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "modoru", method = RequestMethod.POST)
	ModelAndView modoru(@ModelAttribute EditForm input) {
		//  戻るボタン押下時は出欠一覧画面へ遷移
		ListForm form = new ListForm();

		// 出欠一覧フォームを作成
		// 出欠一覧情報を検索して格納
		form.setAttendanceInfoList(loginSv.getLoginList(input.getTargetMonth()));
		form.setTargetMonth(input.getTargetMonth());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}
}