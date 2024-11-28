package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		PreparedStatement statement = null;
        
		try {
			con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Gran6de."
			);
			
			System.out.println("データベース接続成功：" + con);
			System.out.println("レコード追加を実行します");
			
			String[][] postList = {
	            { "1003", "2023-02-08", "昨日の夜は徹夜でした・・", "13" },
	            { "1002", "2023-02-08", "お疲れ様です！", "12" },
	            { "1003", "2023-02-09", "今日も頑張ります！", "18" },
	            { "1001", "2023-02-09", "無理は禁物ですよ！", "17" },
	            { "1002", "2023-02-10", "明日から連休ですね！", "20" }
	        };
			
			String insertSql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?)";
			statement = con.prepareStatement(insertSql);
			
			for (String[] post : postList) {
				statement.setInt(1, Integer.parseInt(post[0]));
				statement.setString(2, post[1]);
				statement.setString(3, post[2]);
				statement.setInt(4, Integer.parseInt(post[3]));
				statement.executeUpdate();
			}
			
			System.out.println("5件のレコードが追加されました");
			
			String searchSql = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002";
			statement = con.prepareStatement(searchSql);
			ResultSet result = statement.executeQuery();
			
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			int rowNumber = 0;
			while (result.next()) {
				rowNumber++;
				System.out.printf("%d件目：投稿日時=%s／投稿内容=%s／いいね数=%d%n",
								  rowNumber,
								  result.getDate("posted_at"),
								  result.getString("post_content"),
								  result.getInt("likes"));
			}
			
		} catch(SQLException e) {
            System.out.println("データベース接続失敗：" + e.getMessage());
        
		} finally {

			try {
				if (statement != null) statement.close();
				if (con != null) con.close();
			} catch (SQLException ignore) {}
		}
	}

}
