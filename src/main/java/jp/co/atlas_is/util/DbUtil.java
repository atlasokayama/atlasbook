package jp.co.atlas_is.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class DbUtil {

	public static Connection getConnection() throws SQLException, IOException {

		// 設定ファイルの読み込み
		Resource resource = new ClassPathResource("/application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		// 接続情報を取得
		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String password = props.getProperty("db.password");

		Connection con = null;
		con = DriverManager.getConnection(url, user, password);
		return con;
	}
}
