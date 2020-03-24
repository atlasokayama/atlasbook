package jp.co.atlas_is.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.AddEmpForm;
import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.ListService;
import jp.co.atlas_is.service.LoginService;
import jp.co.atlas_is.service.MasterService;

/**
 * 出席者追加画面のコントローラクラス
 */
@Controller
@RequestMapping("addEmp")
public class AddEmpController {

	// サービスクラスをDI
	@Autowired
	LoginService loginSv;
	@Autowired
	ListService listSv;
	@Autowired
	MasterService masterSv;

	/**
	 * 登録ボタン処理
	 * 
	 * @param input
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "add", method = RequestMethod.POST)
	ModelAndView entry(@Validated @ModelAttribute AddEmpForm input, Errors errors) {
		// 入力エラーチェック
		if (errors.hasErrors()) {
			// エラーがある場合は自画面へ
			ModelAndView mav = new ModelAndView("addEmp", "form", input);
			mav.addObject("errors", errors.getAllErrors());
			return mav;
		}

		// マスタ登録実行
		masterSv.addEmployee(input.getAddNamae());

		// 登録成功時はマスタ管理画面へ遷移
		// マスタ一覧情報を検索して格納
		ListForm form = new ListForm();
		form.setTargetMonth(input.getTargetMonth());
		form.setAttendanceInfoList(listSv.getEmployeeList());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master", "form", form);
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
		// 戻るボタン押下時は出欠一覧画面へ遷移
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