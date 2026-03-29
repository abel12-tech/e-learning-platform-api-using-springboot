# 📚 E-Learning Platform API

## 🚀 Overview

This project is a **Spring Boot REST API** for managing an E-Learning platform.

It allows:

* Managing students, courses, instructors, and profiles
* Enrolling and unenrolling students in courses
* Integrating external course ratings (mock API)
* Securing endpoints using Basic Authentication

---

## 🧱 Architecture

The project follows a layered architecture:

```
config       → Configuration (Swagger, Security)
controller   → REST Controllers (API endpoints)
dto          → Request & Response DTOs
entity       → JPA Entities
repository   → Data access layer
service      → Business logic
restclient   → External API integration
exception    → Global exception handling
```

---

## 🗂️ Entities & Relationships

### Entities

* Student
* Course
* Instructor
* Profile

### Relationships

* **One-to-One:** Student ↔ Profile
* **One-to-Many:** Instructor → Courses
* **Many-to-Many:** Students ↔ Courses

---

## 📌 API Endpoints

### 👨‍🎓 Students

```
GET    /api/students
GET    /api/students/{id}
POST   /api/students
```

---

### 📚 Courses

```
GET    /api/courses
POST   /api/courses
```

---

### 👨‍🏫 Instructors

```
GET    /api/instructors
```

---


#### Enroll Student

```
POST /api/enrollments
```

```json
{
  "studentId": 2,
  "courseId": 1
}
```

---

#### Unenroll Student

```
DELETE /api/enrollments
```

```json
{
  "studentId": 2,
  "courseId": 1
}
```

---

### 👤 Profile

```
GET /api/students/{studentId}/profile
PATCH /api/students/{studentId}/profile
DELETE /api/students/{studentId}/profile
```

---

## 🔐 Authentication

This API uses **Basic Authentication**.

### Roles:

* **ADMIN** → Manage courses, enrollments, instructors
* **STUDENT** → View data

### Example Header:

```
Authorization: Basic admin password
```

---

## 🌐 External API Integration

A mock external API is used to simulate course ratings:

```
GET /external-api/ratings/{courseId}
```

This is consumed internally using `RestTemplate`.

---

## ✅ Validation

Validation is implemented using annotations:

* `@NotBlank`
* `@Email`
* `@Size`
* `@NotNull`

---

## ⚠️ Exception Handling

Global exception handling is implemented using:

```
@ControllerAdvice
```

Handles:

* Validation errors
* Resource not found
* Business logic errors

---

## 📖 API Documentation (Swagger)

Swagger UI is available at:

```
http://localhost:8081/swagger-ui.html
```

Features:

* Interactive API testing
* Request/response documentation
* Authentication support

---

## 🧪 Sample Requests

### Create Student

```
POST /api/students

{
  "name": "Bona Bekele",
  "email": "bona@student.aau.edu.et",
  "bio": "Backend developer",
  "phone": "0912345678"
}
```

---

### Create Course

```
POST "/api/courses"

{
  "title": "React Js Basics",
  "maxEnrollment": 50,
  "instructorId": 1
}
```

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Spring Security
* OpenAPI (Swagger)
* Hibernate
* RestTemplate

---

## 🏁 How to Run

1. Clone the repository
2. Configure database in `application.yml`
3. Run the application:

```
./gradlew bootRun
```

4. Open Swagger:

```
http://localhost:8081/swagger-ui.html
```

---

## 💡 Design Decisions

* Used **Many-to-Many** for enrollment to match assignment requirements
* Used **DTO pattern** to avoid exposing entities
* Used **JSON-based enrollment API** for flexibility
* Added **mock external API** to simulate real-world integration

---

## 🏆 Conclusion

This project demonstrates:

* RESTful API design
* Layered architecture
* Data relationships with JPA
* Security and validation
* External API consumption

---

👨‍💻 Developed by Abel Kinfu
