package jp.co.atlas_is.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.AttendanceInfoForm;
import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.EmployeeInfoForm;
import jp.co.atlas_is.form.ListForm;

@Controller
@RequestMapping("list")
public class ListController {	

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
		EmployeeInfoForm employeeInfo = new EmployeeInfoForm();
		
		//プロパティ読み込み
		Resource resource = new ClassPathResource("/top.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
			// 職員情報を格納
			employeeInfo.setEmp_no(Integer.parseInt(props.getProperty("Employee1.No")));
			employeeInfo.setName(props.getProperty("Employee1.Name"));
			// 職員情報をformに格納
			edit.setEmployeeInfo(employeeInfo);
			list.add(edit);

			// 2件目
			employeeInfo = new EmployeeInfoForm();
			employeeInfo.setEmp_no(Integer.parseInt(props.getProperty("Employee2.No")));
			employeeInfo.setName(props.getProperty("Employee2.Name"));
			edit = new EditForm();
			edit.setEmployeeInfo(employeeInfo);		
			list.add(edit);
		
			form.setAttendanceInfoList(list);
		} catch (IOException e) {
		}
		
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("master", "form", form);
		return mav;
	}	

	/**
	 * 出欠入力画面遷移処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "edit", method = RequestMethod.POST)
	ModelAndView edit() {
		// 出欠入力画面に表示する情報
		EditForm form = new EditForm();
		EmployeeInfoForm employeeInfo = new EmployeeInfoForm();
		AttendanceInfoForm attendanceInfo = new AttendanceInfoForm();
		form.setEmployeeInfo(employeeInfo);
		form.setAttendanceInfo(attendanceInfo);
		
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("edit", "form", form);
		return mav;
	}	

}