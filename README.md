

### ✅ **Final Complete README.md** File (Everything Combined & Organized)

````markdown
# 📌 Web-Based Complaint Management System using JSP

## 🧑‍💻 Project Title

**Advanced API Development – Individual Assignment**  
**Project:** Web-Based Complaint Management System using JSP

---

## 📖 Project Overview

This is a standalone web-based complaint management system developed for an academic assignment using Java technologies.

The system consists of three main roles:

- **Employee:**
  - Can log into the system.
  - Can add, update, delete, and view their own complaints.
  - Cannot view complaints submitted by other employees.

- **Admin:**
  - Can access and manage all complaints in the system.
  - Can update the **status** of each complaint and add **remarks**.

- **System:**
  - Handles user login, session tracking, form submissions, and database interactions through a servlet-based MVC structure.

---

## 🛠️ Technologies Used

- **Frontend:**JSP, HTML, CSS, JavaScript 
- **Backend:** Jakarta EE (Servlets), Apache Commons DBCP
- **Database:** MySQL, accessed via DBCP connection pooling
- **Server:** Apache Tomcat (v11.0.7)
- **Connection Pooling:** Apache Commons DBCP via `context.xml`

---

## 🚀 How to Run the Project (Setup Guide)

### 1. Clone the Repository

```bash
git clone https://github.com/Visun517/Visun517--Complaint-Management-System.git
cd Visun517--Complaint-Management-System
````

---

### 2. Set Up the Database

1. Open **MySQL**.
2. Create a database named:

```sql
CREATE DATABASE cms_db;
```

3. Import the SQL schema:

* Navigate to the project directory.
* Locate the SQL file at: `db/schema.sql`
* Import it using MySQL CLI or Workbench:

```bash
-- If using MySQL CLI:
SOURCE path/to/db/schema.sql;
```

---

### 3. Configure Database Connection (DBCP)

This project uses **Apache DBCP connection pooling** via `context.xml`. Add the following configuration to your Tomcat's `context.xml`:

```xml
<Resource
    name="jdbc/pool"
    type="javax.sql.DataSource"
    driverClassName="com.mysql.cj.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/assignment_cms"
    username="root"
    password="Ijse@1234"
    maxTotal="100"
    maxIdle="50"
    factory="org.apache.commons.dbcp2.BasicDataSourceFactory"
/>
```

> 🔐 Update the `username` and `password` values to match your local MySQL credentials if different.

---

### 4. Deploy the Application

1. Open **Apache Tomcat** (Version 11.0.7 or compatible).
2. Copy the full project folder or its `.war` file into the `webapps/` directory.
3. Start the Tomcat server.

---

### 5. Access the Application

Once the server is running, visit:

```
http://localhost:8080/Visun517--Complaint-Management-System/
```

---

### 🔐 Default Login Credentials

| Username | Password | Role     |
| -------- | -------- | -------- |
| emp1     | pass123  | employee |
| emp2     | pass123  | employee |
| admin1   | admin123 | admin    |

---

## 📁 Project Structure (Key Files & Folders)

```
Visun517--Complaint-Management-System/
│
├── db/
│   └── schema.sql              # SQL dump for database setup
│
├── src/
│   └── lk/ijse/gdse71/         # Java packages: Servlets, Models, DAO
│
├── web/
│   ├── jsp/                    # JSP views
│   ├── css/                    # CSS stylesheets
│   ├── js/                     # JavaScript files (if any)
│   └── WEB-INF/
│       └── web.xml             # Deployment descriptor
│
└── README.md                   # Project documentation
```

---

## 🙋‍♂️ Author

* **Name:** Visun
* **Course:** GDSE71
* **Institute:** IJSE
* **Assignment:** Advanced API Development – Individual Assignment

---

## 📄 License

This project is developed as part of an academic coursework assignment and is intended for educational use only.

---

````

---

✅ **Instructions for Use:**

1. Copy the above content into a new file named `README.md`.
2. Place it in the root directory of your GitHub repository.
3. Commit and push it:

```bash
git add README.md
git commit -m "Add complete README with setup guide and project details"
git push origin main
````
