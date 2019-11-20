package jp.co.atlas_is.controller;

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
import jp.co.atlas_is.service.LoginService;
import jp.co.atlas_is.service.MasterService;

@Controller
@RequestMapping("addEmp")
public class AddEmpController {

	@RequestMapping(params = "add", method = RequestMethod.POST)
	ModelAndView entry(@Validated @ModelAttribute AddEmpForm input, Errors errors) {

		String name = input.getAddNamae();

		// エラーチェック
//  	if (errors.hasErrors()) {
		if (name.isEmpty()) {
			// エラーがある場合は自画面へ
			// 遷移先情報を設定
			ModelAndView mav = new ModelAndView("addEmp", "form", input);
			mav.addObject("errors", errors.getAllErrors());
			return mav;
		}

		MasterService service = new MasterService();

		service.addEmployee(name);

		// 登録成功時は一覧画面へ遷移
		// formを作成
		ListForm form = new ListForm();

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master", "form", form);
		return mav;
	}

	@RequestMapping(params = "modoru", method = RequestMethod.POST)
	ModelAndView modoru(@Validated @ModelAttribute EditForm input, Errors errors) {
		// 登録成功時は一覧画面へ遷移
		ListForm form = new ListForm();

		// 出欠一覧フォーム
		// 一覧情報を検索
		form.setAttendanceInfoList(LoginService.getLoginList());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}

}