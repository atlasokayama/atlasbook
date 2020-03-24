package jp.co.atlas_is.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.AddEmpForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.ListService;
import jp.co.atlas_is.service.LoginService;
import jp.co.atlas_is.service.MasterService;

/**
 * マスタ管理画面のコントローラクラス
 */
@Controller
@RequestMapping("master")
public class MasterController {

	// サービスクラスをDI
	@Autowired
	LoginService loginSv;
	@Autowired
	MasterService masterSv;
	@Autowired
	ListService listSv;

	/**
	 * 出席者追加画面遷移処理
	 * 
	 * @param input
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "addEmp", method = RequestMethod.POST)
	ModelAndView addEmp(@ModelAttribute ListForm input) {
		// 出席者追加画面へ遷移
		AddEmpForm form = new AddEmpForm();
		form.setTargetMonth(input.getTargetMonth());
		ModelAndView mav = new ModelAndView("addEmp", "form", form);
		return mav;
	}

	/**
	 * 戻るボタン処理
	 * 
	 * @param input
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "modoru", method = RequestMethod.POST)
	ModelAndView modoru(@ModelAttribute ListForm input) {
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

	/**
	 * 削除ボタン処理
	 * 
	 * @param input
	 * @param checks
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "delEmp", method = RequestMethod.POST)
	ModelAndView dellist(@ModelAttribute ListForm input, @RequestParam(required = false) List<Integer> checks) {

		// 削除対象のチェックボックスが選択されていた場合は削除を実施
		if (checks != null) {
			// マスタ削除実行
			masterSv.delEmp(checks);
		}

		// マスタ管理画面へ遷移
		// マスタ一覧情報を検索して格納
		ListForm form = new ListForm();
		form.setAttendanceInfoList(listSv.getEmployeeList());
		form.setTargetMonth(input.getTargetMonth());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master", "form", form);
		return mav;
	}
}