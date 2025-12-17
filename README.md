# 📚 Library Management System (Java)

A console-based Library Management System built using **core Java**, focusing on clean object-oriented design and correct system behavior.

---

## 🔹 Features

- Preloaded catalog of books with multiple physical copies  
- Member registration with session-based member switching  
- Issue and return books with strict validation  
- Prevents invalid actions such as:
  - Issuing unavailable copies  
  - Returning books not issued to the member  
- Dynamically displays **only available book copies**

---

## 🔹 System Design

The system models real-world library concepts using clear abstractions:

- **Book** – Represents book metadata  
- **BookCopy** – Represents a physical copy with availability state  
- **Member** – Represents a library member  
- **Loan** – Represents an active borrowing relationship  
- **Library** – Service layer enforcing all business rules  
- **Main** – Console-based driver for user interaction  

The core logic is **input-agnostic** and separated from the UI layer.

---

## 🔹 Console Flow

1. Program starts with no registered members  
2. User enters a Member ID  
   - Existing member → welcomed back  
   - New member → prompted for name and registered  
3. Menu options:
   - Issue Book  
   - Return Book  
   - Switch Member  
   - Exit  
4. Available books list updates dynamically after each issue or return

---

## 🔹 Error Handling

- Invalid copy IDs and member IDs are handled gracefully  
- Members cannot return books they did not borrow  
- System never enters an invalid state  

---

## 🔹 Technologies Used

- Java (Core)
- Collections Framework
- `java.time` API
- Console-based input/output

---

## 🔹 Future Improvements

- Database persistence (JDBC / MySQL)  
- Unit testing with JUnit  
- REST API layer using Spring Boot  
- Fine calculation for overdue books  

---

## 🔹 Author

Built as a learning project to practice **object-oriented design, system modeling, and clean Java architecture**.
