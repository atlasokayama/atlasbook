package jp.co.atlas_is.controller;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;
import jp.co.atlas_is.service.SampleService;
import jp.co.atlas_is.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SampleController {
    
	/**
	 * ajax通信処理
	 * 
	 * @param empNo
	 * @return 検索結果
	 */
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	@ResponseBody
	public String getJsonData(@PathParam("empNo") int empNo) {
		String ret = "";
		try {
			SampleService service = new SampleService();
			ret = JsonUtil.stringify(service.getSampleRow(empNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * サンプル画面遷移処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@RequestParam Optional<String> error) {
		// 出欠入力画面に表示する情報
		EditForm form = new EditForm();
		log.trace("trace message");
		log.debug("debug message");
		log.info("info message");
		log.warn("warn message");
		log.error("error message");
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("sample", "form", form);
		return mav;
	}

	/**
	 * 出欠入力画面登録処理
	 * 
	 * @return モデル／ビュー
	 */
	@RequestMapping(value = "/sample", params = "edit", method = RequestMethod.POST)
	ModelAndView master(@Validated @ModelAttribute EditForm input, Errors errors) {
		// エラーチェック
		if (errors.hasErrors()) {
			// エラーがある場合は自画面へ
			// 遷移先情報を設定
			ModelAndView mav = new ModelAndView("sample", "form", input);
			mav.addObject("errors", errors.getAllErrors());
			return mav;
		}
		// 登録成功時は一覧画面へ遷移
		SampleService service = new SampleService();

		// formを作成
		ListForm form = new ListForm();
		// 一覧情報を検索
		form.setAttendanceInfoList(service.getSampleList());

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}
}