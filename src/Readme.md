# E-Learning Platform API

## Overview

This project is a **Spring Boot REST API** for managing an E-Learning platform.

It allows:

* Managing students, courses, instructors, and profiles
* Enrolling and unenrolling students in courses
* Consuming external api
* Securing endpoints using Basic Authentication

---

## Architecture

The project follows a layered architecture:

```
config       → Configuration (Swagger, Security)
controller   → REST Controllers (API endpoints)
dto          → Request & Response DTOs
entity       → JPA Entities
repository   → Data access layer
service      → Business logic
restclient   → External API consuming
exception    → Global exception handling
```

---

##  Entities & Relationships

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

##  API Endpoints

### Students

```
GET    /api/students
GET    /api/students/{id}
POST   /api/students
PATCH /api/students/{id}
DELETE /api/student/{id}
```

---

###  Courses

```
GET    /api/courses
POST   /api/courses
PATCH /api/courses/{id}
DELETE /api/courses/{id}

```

---

### Instructors

```
GET    /api/instructors
POST /api/instructors
PATCH /api/instructors/{id}
DELETE /api/instructors/{id}
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

###  Profile

```
GET /api/students/{studentId}/profile
PATCH /api/students/{studentId}/profile
DELETE /api/students/{studentId}/profile
```

---

## Authentication

This API uses **Basic Authentication**.

### Roles:

* **ADMIN** → Manage courses, enrollments, instructors
* **STUDENT** → View data

### Example Header:

```
Authorization: Basic admin password
```

## Sample Requests

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

## 🏁 How to Run

1. Clone the repository
2. Configure database in `application.yml`
3. Run the application:



