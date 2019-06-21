package jp.co.atlas_is.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.form.AttendanceInfoForm;
import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.EmployeeInfoForm;
import jp.co.atlas_is.form.ListForm;

@Controller
@RequestMapping("edit")
public class EditController {	

	/**
	 * 出欠入力画面登録処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(params = "edit", method = RequestMethod.POST)
	ModelAndView master(@Validated @ModelAttribute EditForm input, Errors errors) {
		// エラーチェック
		if (errors.hasErrors()) {
			// エラーがある場合は自画面へ
			// 遷移先情報を設定
			ModelAndView mav = new ModelAndView("edit", "form", input);
			mav.addObject("errors", errors.getAllErrors());
			return mav;
		}
		// 登録成功時は一覧画面へ遷移
		// formを作成
		ListForm form = new ListForm();
		
		// 職員情報を作成
		EmployeeInfoForm employeeInfo = new EmployeeInfoForm();
		// 出欠情報を作成
		AttendanceInfoForm attendInfo = new AttendanceInfoForm();
		// 出欠一覧フォーム
		List<EditForm> list = new ArrayList<EditForm>();
		EditForm edit = new EditForm();

		// 設定ファイルの読み込み
		Resource resource = new ClassPathResource("/top.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
			// 職員情報を格納
			employeeInfo.setEmployeeNo(props.getProperty("Employee1.No"));
			employeeInfo.setName(props.getProperty("Employee1.Name"));
			// 出欠情報を格納
			attendInfo.setAttendanceAm(props.getProperty("Attend1.AttendanceAM"));
			attendInfo.setReasonAm(props.getProperty("Attend1.ReasonAm"));
			attendInfo.setAttendancePm(props.getProperty("Attend1.AttendancePM"));
			attendInfo.setReasonPm(props.getProperty("Attend1.ReasonPm"));						
			// 出欠情報をformに格納
			edit.setEmployeeInfo(employeeInfo);
			edit.setAttendanceInfo(attendInfo);
			list.add(edit);
	
			// 2件目
			employeeInfo = new EmployeeInfoForm();
			employeeInfo.setEmployeeNo(props.getProperty("Employee2.No"));
			employeeInfo.setName(props.getProperty("Employee2.Name"));
			// 出欠情報を格納
			attendInfo = new AttendanceInfoForm();
			attendInfo.setAttendanceAm(props.getProperty("Attend2.AttendanceAM"));
			attendInfo.setReasonAm(props.getProperty("Attend2.ReasonAm"));
			attendInfo.setAttendancePm(props.getProperty("Attend2.AttendancePM"));
			attendInfo.setReasonPm(props.getProperty("Attend2.ReasonPm"));			
			// 出欠情報をformに格納
			edit = new EditForm();
			edit.setAttendanceInfo(attendInfo);
			edit.setEmployeeInfo(employeeInfo);		
			list.add(edit);

			// 3件目
			employeeInfo = new EmployeeInfoForm();
			employeeInfo.setEmployeeNo(props.getProperty("Employee3.No"));
			employeeInfo.setName(props.getProperty("Employee3.Name"));
			// 出欠情報を格納
			attendInfo = new AttendanceInfoForm();
			attendInfo.setAttendanceAm(props.getProperty("Attend3.AttendanceAM"));
			attendInfo.setReasonAm(props.getProperty("Attend3.ReasonAm"));
			attendInfo.setAttendancePm(props.getProperty("Attend3.AttendancePM"));
			attendInfo.setReasonPm(props.getProperty("Attend3.ReasonPm"));			
			// 出欠情報をformに格納
			edit = new EditForm();
			edit.setAttendanceInfo(attendInfo);
			edit.setEmployeeInfo(employeeInfo);		
			list.add(edit);			
			
			form.setAttendanceInfoList(list);

		} catch (IOException e) {
		}		
		
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		return mav;
	}	

}