package jp.co.atlas_is.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSONユーティリティ
 */
public class JsonUtil {

	/** コンストラクタ （インスタンス化抑止用） */
	private JsonUtil() {}

	//
	// パブリックメソッド
	//
	
	/**
	 * JSON文字列への変換
	 * @param object オブジェクト
	 * @param useEscape HTMLエスケープを行うか（true:行う、false:行わない）
	 * @return JSON文字列
	 * @throws JsonGenerationException JSON生成失敗
	 * @throws JsonMappingException JSONマッピング失敗
	 * @throws IOException IO例外捕捉時
	 */
	public static String stringify(final Object object, boolean useEscape) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = getMapper(useEscape);
		return mapper.writeValueAsString(object);
	}

	/**
	 * JSON文字列への変換<br/>
	 * ※常にHTMLエスケープを行います。
	 * @param object オブジェクト
	 * @return JSON文字列
	 * @throws JsonGenerationException JSON生成失敗
	 * @throws JsonMappingException JSONマッピング失敗
	 * @throws IOException IO例外捕捉時
	 */
	public static String stringify(final Object object) throws JsonGenerationException, JsonMappingException, IOException {
		return stringify(object, true);
	}
	
	/**
	 * オブジェクトへのパース<br/>
	 * パース対象がオブジェクトの場合このメソッドを使用してください。
	 * @param jsonString JSON文字列
	 * @param useEscape HTMLエスケープを行うか（true:行う、false:行わない）
	 * @param clazz クラス
	 * @return パースされたオブジェクト
	 * @throws JsonGenerationException JSON生成失敗
	 * @throws JsonMappingException JSONマッピング失敗
	 * @throws IOException IO例外捕捉時
	 */
	public static <T> T parse(final String jsonString, final Class<T> clazz, boolean useEscape) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = getMapper(useEscape);
		T object = null;
		object = mapper.readValue(jsonString, clazz);
		return object;
	}

	/**
	 * オブジェクトへのパース<br/>
	 * パース対象がオブジェクトの場合このメソッドを使用してください。<br/>
	 * ※常にHTMLエスケープを行います。
	 * @param jsonString JSON文字列
	 * @param clazz クラス
	 * @return パースされたオブジェクト
	 * @throws JsonGenerationException JSON生成失敗
	 * @throws JsonMappingException JSONマッピング失敗
	 * @throws IOException IO例外捕捉時
	 */
	public static <T> T parse(final String jsonString, final Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return parse(jsonString, clazz, true);
	}
	
	/**
	 * オブジェクトへのパース<br/>
	 * パース対象がリスト、マップの場合このメソッドを使用してください。
	 * @param jsonString JSON文字列
	 * @param useEscape HTMLエスケープを行うか（true:行う、false:行わない）
	 * @param valueTypeRef 型情報
	 * @return パースされたオブジェクト
	 * @throws JsonGenerationException JSON生成失敗
	 * @throws JsonMappingException JSONマッピング失敗
	 * @throws IOException IO例外捕捉時
	 */
	public static <T> T parse(String jsonString, TypeReference<T> valueTypeRef, boolean useEscape) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = getMapper(useEscape);
		T object = null;
		object = mapper.readValue(jsonString, valueTypeRef);
		return object;
	}

	/**
	 * オブジェクトへのパース<br/>
	 * パース対象がリスト、マップの場合このメソッドを使用してください。<br/>
	 * ※常にHTMLエスケープを行います。
	 * @param jsonString JSON文字列
	 * @param valueTypeRef 型情報
	 * @return パースされたオブジェクト
	 * @throws JsonGenerationException JSON生成失敗
	 * @throws JsonMappingException JSONマッピング失敗
	 * @throws IOException IO例外捕捉時
	 */
	public static <T> T parse(String jsonString, TypeReference<T> valueTypeRef) throws JsonParseException, JsonMappingException, IOException {
		return parse(jsonString, valueTypeRef, true);
	}

	//
	// プライベートメソッド
	//

	/**
	 * Jacksonオブジェクトマッパーを返す
	 * @param useEscape HTMLエスケープを行うか（true:する、false:しない）
	 * @return　オブジェクトマッパー
	 */
	private static ObjectMapper getMapper(boolean useEscape) {
		ObjectMapper mapper = new ObjectMapper();
		if (useEscape) {
			mapper.configure(Feature.ESCAPE_NON_ASCII, true);
			mapper.getFactory().setCharacterEscapes(new CustomCharacterEscapes());
		}
		return mapper;
	}

	/**
	 * JSON変換エスケープ定義
	 * @author yakubo_yutaka
	 */
	@SuppressWarnings("serial")
	private static class CustomCharacterEscapes extends CharacterEscapes {
	    private final int[] asciiEscapes;
	    public CustomCharacterEscapes() {
	        int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
	        esc['"']  = CharacterEscapes.ESCAPE_STANDARD;
	        esc['\''] = CharacterEscapes.ESCAPE_STANDARD;
	        esc['/']  = CharacterEscapes.ESCAPE_STANDARD;
	        esc['\n'] = CharacterEscapes.ESCAPE_STANDARD;
	        asciiEscapes = esc;
	    }
	    @Override
	    public int[] getEscapeCodesForAscii() {
	        return asciiEscapes;
	    }
	    @Override
	    public SerializableString getEscapeSequence(int ch) {
	        return null;
	    }
	}

}
