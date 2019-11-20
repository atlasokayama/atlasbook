package jp.co.atlas_is.controller;

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

@Controller
@RequestMapping("edit")
public class EditController {

	/**
	 * 出欠入力画面登録処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "edit", method = RequestMethod.POST)
	ModelAndView master(@Validated @ModelAttribute EditForm input, Errors errors) {
		// エラーチェック
		if (errors.hasErrors()) {
			// エラーがある場合は自画面へ
			// formを作成
			// EditForm form = new EditForm();
			// 遷移先情報を設定
			ModelAndView mav = new ModelAndView("edit", "form", input);
			mav.addObject("errors", errors.getAllErrors());
			return mav;
		}
		// 登録成功時は一覧画面へ遷移
		ListForm form = new ListForm();

		// 出欠一覧フォーム
		// 一覧情報を検索
		form.setAttendanceInfoList(LoginService.getLoginList());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
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

	@RequestMapping(params = "attendanceEdit", method = RequestMethod.POST)
	ModelAndView edit(@Validated @ModelAttribute EditForm input, Errors errors) {
		// TODO：バリデーションの判定とエラーメッセージの返却

		// 戻る時は一覧画面へ遷移
		// formを作成
		ListForm form = new ListForm();

		EditService service = new EditService();

		service.attendanceEdit(input);

		// TODO：更新後の再検索処理

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}
}