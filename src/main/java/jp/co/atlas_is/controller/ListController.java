package jp.co.atlas_is.controller;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.form.MasterForm;
import jp.co.atlas_is.service.ListService;
import jp.co.atlas_is.service.LoginService;

/**
 * 出欠一覧画面のコントローラクラス
 */
@Controller
@RequestMapping("list")
public class ListController {

	// サービスクラスをDI
	@Autowired
	ListService listSv;

	@Autowired
	LoginService loginSv;

	/**
	 * マスタ管理画面遷移処理
	 * 
	 * @param input
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "master", method = RequestMethod.POST)
	ModelAndView master(@ModelAttribute MasterForm input) {
		// マスタ管理画面へ遷移
		// マスタ一覧情報を検索して格納
		ListForm form = new ListForm();
		form.setAttendanceInfoList(listSv.getEmployeeList());
		form.setTargetMonth(input.getTargetMonth());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master", "form", form);
		return mav;
	}

	/**
	 * 出欠入力画面遷移処理
	 * 
	 * @param targetValue
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "edit", method = RequestMethod.POST)
	ModelAndView edit(@ModelAttribute("edit") String targetValue) {
		// 出欠入力画面へ遷移
		// 引数を","で分割して対象名、対象年月に分解
		String[] values = targetValue.split(",", -1);
		YearMonth targetMonth = YearMonth.now();
		targetMonth = targetMonth.withYear(Integer.valueOf(values[1].substring(0, 4)));
		targetMonth = targetMonth.withMonth(Integer.valueOf(values[1].substring(5, 7)));
		
		// 出欠入力画面に表示する情報を取得
		EditForm form = listSv.getAttendanceInfo(targetMonth, values[0]);

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("edit", "form", form);
		return mav;
	}
	/**
	 * 次月/前月ボタン処理
	 * 
	 * @param targetValue
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "move", method = RequestMethod.POST)
	ModelAndView move(@ModelAttribute("move") String targetValue) {
		// 次月/前月ボタン押下時は出欠一覧画面へ遷移
		ListForm form = new ListForm();

		// 出欠一覧フォームを作成
		// 出欠一覧情報を検索して格納
		// 引数を","で分割して対象名、対象年月に分解
		String[] values = targetValue.split(",", -1);
		YearMonth target = YearMonth.of(Integer.parseInt(values[1].substring(0, 4)), 
				Integer.parseInt(values[1].substring(5, 7)));
		// 次月・前月を算出
		target = target.plusMonths(Long.valueOf(values[0]));
		form.setAttendanceInfoList(loginSv.getLoginList(target));
		form.setTargetMonth(target);

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}
}