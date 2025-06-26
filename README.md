# Book-Management-System 📚

The BookVault 📚 Library Manager 🧑‍💼 offers a set of essential features designed to efficiently manage a library’s book records. These features are accessible through a menu-driven console interface, making it simple and intuitive for users to perform various operations. Each feature interacts with a MySQL database using JDBC, ensuring that data is stored and retrieved reliably.
Below is a detailed explanation of each system feature:
 1. Insert New Book
•	Allows the user to add a new book record to the library’s database.
•	The user provides Book ID, Title, Author, and Price.
•	The system uses an SQL INSERT statement via PreparedStatement to store the new book details in the Books table.
•	Displays a success message upon successful insertion.

![Screenshot 2025-04-17 211243](https://github.com/user-attachments/assets/23805eb9-4ed5-445b-a632-9d13046ec137)

2. View All Books
•	Retrieves and displays all the book records stored in the database.
•	Uses an SQL SELECT query executed via Statement.
•	Displays details such as ID, Title, Author, and Price for each book in a formatted list.
•	Helps users quickly review the complete list of books.
Example Output:

![Screenshot 2025-04-17 211335](https://github.com/user-attachments/assets/9dbd1165-16b6-417d-901c-c4f894e95888)

3. Update Existing Book
•	Enables the user to modify the details of an existing book by providing the Book ID.
•	The user can update the Title, Author, and Price of the selected book.
•	Uses an SQL UPDATE statement to modify the existing record in the database.
•	Displays a confirmation message if the update is successful or a warning if the book is not found.
Example:

![Screenshot 2025-04-17 211436](https://github.com/user-attachments/assets/c7a4254d-213e-4c89-af30-a04029798bb9)

 
4. Delete Book by ID
•	Allows the user to remove a book record from the database by specifying the Book ID.
•	Uses an SQL DELETE statement through PreparedStatement.
•	Confirms successful deletion or notifies the user if the book was not found.
Example:

![Screenshot 2025-04-17 211512](https://github.com/user-attachments/assets/5d905554-b332-4c96-ab11-032b421db627)

 
5. Search Book
•	Provides two ways to search for specific books in the library:
1.	Search by Book ID — Finds a book by its unique ID.
2.	Search by Book Title — Finds books by partial or full title using an SQL LIKE query.
•	Displays detailed information about the matching book(s) or informs the user if no match is found.
Example:

![Screenshot 2025-04-17 211945](https://github.com/user-attachments/assets/d2c70037-4603-46ea-8174-b8a95194eec4)


6. Exit Application
•	Allows the user to safely exit the application.
•	Closes the database connection and scanner object properly.
•	Displays a friendly goodbye message before terminating the program.
Example:

![Screenshot 2025-04-17 212240](https://github.com/user-attachments/assets/8f6e72b2-299a-4167-b67f-b387bcab4ded)


**Preview** ⤵️

![Screenshot 2025-04-17 213336](https://github.com/user-attachments/assets/a0157efa-a5d2-4f2a-8a1a-8c491c7da5e4)


 
