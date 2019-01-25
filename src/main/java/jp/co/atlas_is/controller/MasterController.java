package jp.co.atlas_is.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("master")
public class MasterController {	

	/**
	 * マスタ管理画面遷移処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "master", method = RequestMethod.POST)
	ModelAndView master() {
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master");
		return mav;
	}	
	
	/**
	 * 出席者追加画面遷移処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "addEmp", method = RequestMethod.POST)
	ModelAndView list() {
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("addEmp");
		return mav;
	}	
}