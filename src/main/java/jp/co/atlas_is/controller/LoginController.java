package jp.co.atlas_is.controller;

import java.security.Principal;
import java.time.YearMonth;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.LoginService;

/**
 * ログイン画面のコントローラクラス
 */
@Controller
public class LoginController {

	// サービスクラスをDI
	@Autowired
	LoginService loginSv;

	/**
	 * ルート画面遷移処理
	 * 
	 * @param model
	 * @return ビュー名
	 */
	@GetMapping("/")
	public String rootForm(Model model) {
		return "login";
	}

	/**
	 * ログイン画面遷移処理
	 * 
	 * @param error
	 * @return モデル／ビュー
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@RequestParam Optional<String> error) {
		// ログイン画面へ遷移
		return new ModelAndView("login", "error", error);
	}

	/**
	 * トップ画面遷移処理
	 * 
	 * @param principal
	 * @param model
	 * @return モデル／ビュー
	 */
	@GetMapping("/top")
	public ModelAndView topForm(Principal principal, Model model) {
		// トップ画面アクセス時は出欠一覧画面へ遷移
		// トップ画面アクセス時は現在年月を表示対象とする
		ListForm form = new ListForm();
		form.setTargetMonth(YearMonth.now());

		// 出欠一覧フォーム
		// 出欠一覧情報を検索して格納
		form.setAttendanceInfoList(loginSv.getLoginList(form.getTargetMonth()));

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;

	}
}