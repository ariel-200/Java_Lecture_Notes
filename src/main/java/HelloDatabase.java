import java.sql.*;

public class HelloDatabase {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:sqlite:hello.sqlite";
        Connection con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();

//        String createTable = "CREATE TABLE cats (name TEXT, age INTEGER)";
//        stmt.execute(createTable);

//        String insertData = "INSERT INTO cats VALUES ('Mary', 10)";
//        stmt.execute(insertData);

//        String insertData = "INSERT INTO cats VALUES ('Kitty', 25)";
//        stmt.execute(insertData);

        String getAll = "SELECT * FROM cats";
        ResultSet allCats = stmt.executeQuery(getAll);

        while (allCats.next()) {
            String name = allCats.getString("name");
            int age = allCats.getInt("age");
            System.out.println(name + " is " + age + " years old.");
        }

    }
}
