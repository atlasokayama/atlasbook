package jp.co.atlas_is.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.ListService;
import jp.co.atlas_is.service.LoginService;
import jp.co.atlas_is.service.MasterService;

@Controller
@RequestMapping("master")
public class MasterController {
	/**
	 * 出席者追加画面遷移処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "addEmp", method = RequestMethod.POST)
	ModelAndView list() {
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("addEmp");
		return mav;
	}

	@RequestMapping(params = "modoru", method = RequestMethod.POST)
	ModelAndView modoru(@ModelAttribute ListForm input) {
		// 登録成功時は一覧画面へ遷移
		ListForm form = new ListForm();

		// 出欠一覧フォーム
		// 一覧情報を検索
		form.setAttendanceInfoList(LoginService.getLoginList(input.getTargetMonth()));

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}

	@RequestMapping(params = "delEmp", method = RequestMethod.POST)
	ModelAndView dellist(@ModelAttribute ListForm input) {
		// 遷移先情報を設定
		MasterService.delEmp();

		// formを作成
		ListForm form = new ListForm();
		List<EditForm> list = new ArrayList<EditForm>();
		ListService service = new ListService();

		list = service.getEmployeeList();

		form.setAttendanceInfoList(list);

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master", "form", form);
		return mav;
	}
}