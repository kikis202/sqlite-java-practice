package net.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private Connection conn = null;
    public void createConnection(String db) {
        conn = null;
        try {
            String url = String.format("jdbc:sqlite:%s", db);
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAlbum(int id, String title, int artistID) {
        String sql = "UPDATE albums SET Title = ? , "
                + "ArtistId = ? "
                + "WHERE AlbumId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, artistID);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed!\n");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        Update app = new Update();
        app.createConnection("C:/Users/krist/git/sqlitetutorial/db/chinook.db");
        app.updateAlbum(2, "Balls to the Wall!", 1);
        app.close();
    }


}
