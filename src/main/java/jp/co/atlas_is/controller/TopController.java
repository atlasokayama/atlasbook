package jp.co.atlas_is.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.dto.EmployeeInfoDto;
import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;

@Controller
@RequestMapping("top")
public class TopController {
	
	/**
	 * 一覧画面遷移処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "list", method = RequestMethod.POST)
	ModelAndView list() {
		ModelAndView mav = new ModelAndView("list");
		return mav;
	}
	
	/**
	 * 出欠入力画面遷移処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "edit", method = RequestMethod.POST)
	ModelAndView edit() {
		ModelAndView mav = new ModelAndView("edit");
		return mav;
	}
	
	/**
	 * マスタ管理画面遷移処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "master", method = RequestMethod.POST)
	ModelAndView master() {
		// formを作成
		ListForm form = new ListForm();
		List<EditForm> list = new ArrayList<EditForm>();
		EditForm edit = new EditForm();
		
		// 職員情報を作成
		EmployeeInfoDto dto = new EmployeeInfoDto();
		// 職員情報を格納
		dto.setEmployeeNo("123456");
		dto.setName("岡山　太郎");
		// 職員情報をformに格納
		edit.setEmployeeInfo(dto);
		list.add(edit);

		// 2件目
		dto = new EmployeeInfoDto();
		dto.setEmployeeNo("999999");
		dto.setName("岡山　次郎");
		edit = new EditForm();
		edit.setEmployeeInfo(dto);		
		list.add(edit);
		
		form.setAttendanceInfoList(list);

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master", "form", form);
		return mav;
	}

	/**
	 * 演習用サンプル
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "sample", method = RequestMethod.POST)
	ModelAndView sample() {
		// formを作成
		ListForm form = new ListForm();
		List<EditForm> list = new ArrayList<EditForm>();
		EditForm edit = new EditForm();
		
		// 職員情報を作成
		EmployeeInfoDto dto = new EmployeeInfoDto();
		// 職員情報を格納
		dto.setEmployeeNo("123456");
		dto.setName("岡山　太郎");
		// 職員情報をformに格納
		edit.setEmployeeInfo(dto);
		list.add(edit);

		// 2件目
		dto = new EmployeeInfoDto();
		dto.setEmployeeNo("999999");
		dto.setName("岡山　次郎");
		edit = new EditForm();
		edit.setEmployeeInfo(dto);		
		list.add(edit);
		
		form.setAttendanceInfoList(list);

		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("sample", "form", form);
		return mav;
	}

}