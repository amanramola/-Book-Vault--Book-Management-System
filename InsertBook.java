import java.sql.*;
import java.util.Scanner;

public class InsertBook {
    public static void main(String[] args) {
        final String URL = "jdbc:mysql://localhost:3306/library";
        final String USER = "root";
        final String PASSWORD = "@M@Nr@m0";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            int choice;

            do {
                System.out.println("\n===== Book Manager Menu =====");
                System.out.println("1. Insert Book");
                System.out.println("2. View All Books");
                System.out.println("3. Update Book");
                System.out.println("4. Delete Book");
                System.out.println("5. Search Book"); // ✅ Added this
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Insert Book
                        System.out.print("Enter Book ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Book Author: ");
                        String author = scanner.nextLine();

                        System.out.print("Enter Book Price: ");
                        double price = scanner.nextDouble();

                        String insertSql = "INSERT INTO Books (id, title, author, price) VALUES (?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(insertSql);
                        preparedStatement.setInt(1, id);
                        preparedStatement.setString(2, title);
                        preparedStatement.setString(3, author);
                        preparedStatement.setDouble(4, price);

                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("✅ Book inserted successfully!");
                        }
                        break;

                    case 2:
                        // View All Books
                        String viewSql = "SELECT * FROM Books";
                        Statement stmt = connection.createStatement();
                        ResultSet resultSet = stmt.executeQuery(viewSql);

                        System.out.println("\n--- Book List ---");
                        while (resultSet.next()) {
                            System.out.println("ID: " + resultSet.getInt("id"));
                            System.out.println("Title: " + resultSet.getString("title"));
                            System.out.println("Author: " + resultSet.getString("author"));
                            System.out.println("Price: " + resultSet.getDouble("price"));
                            System.out.println("-----------------------");
                        }
                        resultSet.close();
                        stmt.close();
                        break;

                    case 3:
                        // Update Book
                        System.out.print("Enter Book ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter New Title: ");
                        String newTitle = scanner.nextLine();

                        System.out.print("Enter New Author: ");
                        String newAuthor = scanner.nextLine();

                        System.out.print("Enter New Price: ");
                        double newPrice = scanner.nextDouble();

                        String updateSql = "UPDATE Books SET title = ?, author = ?, price = ? WHERE id = ?";
                        preparedStatement = connection.prepareStatement(updateSql);
                        preparedStatement.setString(1, newTitle);
                        preparedStatement.setString(2, newAuthor);
                        preparedStatement.setDouble(3, newPrice);
                        preparedStatement.setInt(4, updateId);

                        int rowsUpdated = preparedStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("✅ Book updated successfully!");
                        } else {
                            System.out.println("❌ Book with given ID not found.");
                        }
                        break;

                    case 4:
                        // Delete Book
                        System.out.print("Enter Book ID to delete: ");
                        int deleteId = scanner.nextInt();

                        String deleteSql = "DELETE FROM Books WHERE id = ?";
                        preparedStatement = connection.prepareStatement(deleteSql);
                        preparedStatement.setInt(1, deleteId);

                        int rowsDeleted = preparedStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("✅ Book deleted successfully!");
                        } else {
                            System.out.println("❌ Book with given ID not found.");
                        }
                        break;

                    case 5:
                        // 🔍 Search Book
                        System.out.println("Search by:\n1. Book ID\n2. Book Title");
                        int searchChoice = scanner.nextInt();
                        scanner.nextLine();

                        String searchSql = "";
                        preparedStatement = null;

                        if (searchChoice == 1) {
                            System.out.print("Enter Book ID to search: ");
                            int searchId = scanner.nextInt();

                            searchSql = "SELECT * FROM Books WHERE id = ?";
                            preparedStatement = connection.prepareStatement(searchSql);
                            preparedStatement.setInt(1, searchId);

                        } else if (searchChoice == 2) {
                            System.out.print("Enter Book Title to search: ");
                            String searchTitle = scanner.nextLine();

                            searchSql = "SELECT * FROM Books WHERE title LIKE ?";
                            preparedStatement = connection.prepareStatement(searchSql);
                            preparedStatement.setString(1, "%" + searchTitle + "%");
                        } else {
                            System.out.println("❌ Invalid search option.");
                            break;
                        }

                        ResultSet searchResult = preparedStatement.executeQuery();
                        boolean found = false;

                        System.out.println("\n--- Search Results ---");
                        while (searchResult.next()) {
                            found = true;
                            System.out.println("ID: " + searchResult.getInt("id"));
                            System.out.println("Title: " + searchResult.getString("title"));
                            System.out.println("Author: " + searchResult.getString("author"));
                            System.out.println("Price: " + searchResult.getDouble("price"));
                            System.out.println("-----------------------");
                        }
                        if (!found) {
                            System.out.println("❌ No matching book found.");
                        }

                        searchResult.close();
                        preparedStatement.close();
                        break;

                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        break;

                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                }

            } while (choice != 6);

        } catch (Exception e) {
            System.out.println("⚠️ Database connection or query error.");
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
