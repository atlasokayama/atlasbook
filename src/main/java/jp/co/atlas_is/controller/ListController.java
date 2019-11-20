package jp.co.atlas_is.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.ListService;

@Controller
@RequestMapping("list")
public class ListController {

	/**
	 * マスタ管理画面遷移処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "master", method = RequestMethod.POST)
	ModelAndView master() {
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

	/**
	 * 出欠入力画面遷移処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "edit", method = RequestMethod.POST)
	ModelAndView edit() {
		// 出欠入力画面に表示する情報
		EditForm form = new EditForm();

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("edit", "form", form);
		return mav;
	}

}