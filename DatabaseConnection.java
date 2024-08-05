import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DatabaseConnection implements AutoCloseable {
    private static final String URL = "jdbc:mysql://localhost:3306/details";
    private static final String USER = "root";
    private static final String PASSWORD = "rakesh";

    private Connection connection;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBooking(int pnr, char src, char dest, String name, int noOfSeats, String seatNumbers) {
        String query = "INSERT INTO bookings1 (pnr, source, destination, name, no_of_seats, seat_numbers) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, pnr);
            pstmt.setString(2, String.valueOf(src));
            pstmt.setString(3, String.valueOf(dest));
            pstmt.setString(4, name);
            pstmt.setInt(5, noOfSeats);
            pstmt.setString(6, seatNumbers);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
