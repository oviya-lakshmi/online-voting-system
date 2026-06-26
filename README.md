# Online Voting System

A secure and user-friendly web-based Online Voting System developed using Spring Boot. This application allows users to register, log in, cast votes electronically, and view live voting results in real time.

---

## Features

- User Registration and Login
- Secure Authentication System
- Vote Casting Functionality
- Live Result Display
- Responsive User Interface
- Real-Time Vote Updates
- Session Management
- Prevents Multiple Voting

---

## Technologies Used

### Backend
- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate

### Frontend
- HTML
- CSS
- JavaScript
- Thymeleaf

### Database
- MySQL

### Tools
- Maven
- Git
- GitHub

---

## Project Structure

```text
online-voting-system/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   ├── templates/
│   │   │   └── application.properties
│
├── pom.xml
├── README.md
└── .gitignore


## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/oviya-lakshmi/online-voting-system.git
```

### 2. Navigate to Project Folder

```bash
cd online-voting-system
```

### 3. Configure Database

Open:

```text
src/main/resources/application.properties
```

Update MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_password
```

### 4. Run the Project

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main class:

```text
OnlineVotingApplication.java
```

---

## Application Screens

- Home Page
- Registration Page
- Login Page
- Voting Page
- Live Results Page

---

## Future Enhancements

- Admin Dashboard
- Email Verification
- OTP Authentication
- Election Scheduling
- Candidate Management
- Graphical Analytics
- Deployment on Cloud

---

## Author

### Oviya Lakshmi

GitHub:  
https://github.com/oviya-lakshmi

---

## License

This project is developed for educational and learning purposes.
