package jp.co.atlas_is.controller;

import java.security.Principal;
import java.time.YearMonth;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.LoginService;

@Controller
public class LoginController {

	/**
	 * ルート画面遷移処理
	 * 
	 * @return ビュー
	 */
	@GetMapping("/")
	public String rootForm(Model model) {
		return "login";
	}

	/**
	 * ログイン画面遷移処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@RequestParam Optional<String> error) {
		return new ModelAndView("login", "error", error);
	}

	/**
	 * トップ画面遷移処理
	 * 
	 * @return ビュー
	 */
	@GetMapping("/top")
	public ModelAndView topForm(Principal principal, Model model) {
		Authentication authentication = (Authentication) principal;
		String username = authentication.getName();

		// トップ画面アクセス時は一覧画面へ遷移
		// formを作成
		ListForm form = new ListForm();

		form.setTargetMonth(YearMonth.now());

		// 出欠一覧フォーム
		// 一覧情報を検索
		form.setAttendanceInfoList(LoginService.getLoginList(form.getTargetMonth()));

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		mav.addObject("username", username);
		return mav;

	}
}