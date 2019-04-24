package jp.co.atlas_is.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.atlas_is.dto.AttendanceInfoDto;
import jp.co.atlas_is.dto.EmployeeInfoDto;
import jp.co.atlas_is.form.EditForm;
import jp.co.atlas_is.form.ListForm;

@Controller
public class LoginController {
	
	/**
	 * ルート画面遷移処理
	 * @return ビュー
	 */
	@GetMapping("/")
	public String rootForm(Model model) {
		return "login";
	}
	
	/**
	 * ログイン画面遷移処理
	 * @return モデル／ビュー
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@RequestParam Optional<String> error) {
		return new ModelAndView("login", "error", error);
	}

	/**
	 * トップ画面遷移処理
	 * @return ビュー
	 */
	@GetMapping("/top")
	public ModelAndView topForm(Principal principal, Model model) {
		Authentication authentication = (Authentication)principal;
		String username = authentication.getName();
		
		// トップ画面アクセス時は一覧画面へ遷移
		// formを作成
		ListForm form = new ListForm();
		
		// 職員情報を作成
		EmployeeInfoDto dto = new EmployeeInfoDto();
		// 出欠情報を作成
		AttendanceInfoDto attendDto = new AttendanceInfoDto();
		// 出欠一覧フォーム
		List<EditForm> list = new ArrayList<EditForm>();
		EditForm edit = new EditForm();

		// 設定ファイルの読み込み
		Resource resource = new ClassPathResource("/top.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
			// 職員情報を格納
			dto.setEmployeeNo(props.getProperty("Employee1.No"));
			dto.setName(props.getProperty("Employee1.Name"));
			// 出欠情報を格納
			attendDto.setAttendanceAm(props.getProperty("Attend1.AttendanceAM"));
			attendDto.setReasonAm(props.getProperty("Attend1.ReasonAm"));
			attendDto.setAttendancePm(props.getProperty("Attend1.AttendancePM"));
			attendDto.setReasonPm(props.getProperty("Attend1.ReasonPm"));						
			// 出欠情報をformに格納
			edit.setEmployeeInfo(dto);
			edit.setAttendanceInfo(attendDto);
			list.add(edit);
	
			// 2件目
			dto = new EmployeeInfoDto();
			dto.setEmployeeNo(props.getProperty("Employee2.No"));
			dto.setName(props.getProperty("Employee2.Name"));
			// 出欠情報を格納
			attendDto = new AttendanceInfoDto();
			attendDto.setAttendanceAm(props.getProperty("Attend2.AttendanceAM"));
			attendDto.setReasonAm(props.getProperty("Attend2.ReasonAm"));
			attendDto.setAttendancePm(props.getProperty("Attend2.AttendancePM"));
			attendDto.setReasonPm(props.getProperty("Attend2.ReasonPm"));			
			// 出欠情報をformに格納
			edit = new EditForm();
			edit.setAttendanceInfo(attendDto);
			edit.setEmployeeInfo(dto);		
			list.add(edit);

			// 3件目
			dto = new EmployeeInfoDto();
			dto.setEmployeeNo(props.getProperty("Employee3.No"));
			dto.setName(props.getProperty("Employee3.Name"));
			// 出欠情報を格納
			attendDto = new AttendanceInfoDto();
			attendDto.setAttendanceAm(props.getProperty("Attend3.AttendanceAM"));
			attendDto.setReasonAm(props.getProperty("Attend3.ReasonAm"));
			attendDto.setAttendancePm(props.getProperty("Attend3.AttendancePM"));
			attendDto.setReasonPm(props.getProperty("Attend3.ReasonPm"));			
			// 出欠情報をformに格納
			edit = new EditForm();
			edit.setAttendanceInfo(attendDto);
			edit.setEmployeeInfo(dto);		
			list.add(edit);			
			
			form.setAttendanceInfoList(list);

		} catch (IOException e) {
		}		
		
		// 遷移先情報を設定
		ModelAndView mav = new ModelAndView("list", "form", form);
		mav.addObject("username", username);
		return mav;

	}
}