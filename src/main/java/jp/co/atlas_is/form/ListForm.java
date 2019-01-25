package jp.co.atlas_is.form;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 一覧画面フォーム
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ListForm{
	
	/** 出欠情報 */
	private List<EditForm> attendanceInfoList;
}