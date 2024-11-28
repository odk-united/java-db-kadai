package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection con = null;
        
		try {
			con = DriverManager.getConnection(
                "jdbc:mysql://localhost/challenge_java",
                "root",
                "Gran6de."
			);
			
			System.out.println("データベース接続成功：" + con);

            Statement stmt = con.createStatement();
            
            System.out.println("レコード更新を実行します");

            String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE name = '武者小路勇気'";
            stmt.executeUpdate(updateSql);
            System.out.println("1件のレコードが更新されました");

            System.out.println("数学・英語の点数が高い順に並べ替えました");

            String querySql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";
            ResultSet rs = stmt.executeQuery(querySql);

            int recordNumber = 1;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int mathScore = rs.getInt("score_math");
                int englishScore = rs.getInt("score_english");

                System.out.printf("%d件目：生徒ID=%d／氏名=%s／数学=%d／英語=%d%n",
                        recordNumber, id, name, mathScore, englishScore);
                recordNumber++;
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}