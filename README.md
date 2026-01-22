**(Cognivanta Job Portal – Backend API)**

A production-ready backend system for a job portal where users can apply for jobs by uploading resumes, and admins can manage job postings and application statuses using JWT-based authentication.

*******************************************************************************************************************************************************
**(Project Overview):**

This backend powers a job application platform for Cognivanta, enabling:

Public users to view active jobs

Candidates to apply for jobs with PDF resume uploads

Admins to post jobs and manage applications

Secure admin authentication using JWT

The project follows clean architecture, RESTful API design, and best security practices using Spring Boot and Spring Security.
*******************************************************************************************************************************************************

**(Tech Stack)**

Java 

Spring Boot

Spring Security (JWT Authentication)

Spring Data JPA

Hibernate

MySQL / PostgreSQL (configurable)

Multipart File Upload

BCrypt Password Encoding
**************************************************************************************************************************************************************

**(Project Architecture)**
 
controller   → REST APIs
service      → Business logic
repository   → Database access (JPA)
entity       → Database models
dto          → Request/Response validation
security     → JWT authentication & authorization
exception    → Global exception handling
config       → File storage configuration
*************************************************************************************************************************************************************
**(Authentication & Security)**

Admin authentication via JWT

Stateless session management

Role-based access (ROLE_ADMIN)

Passwords stored using BCrypt hashing

Custom JWT filter for request validation
*************************************************************************************************************************************************************
**(User Roles)**

1) Public User

View active job listings

a) Apply for jobs

Upload resume (PDF only)

2) Admin

a) Login using username & password

b) Create and manage job postings

c) View applications per job

3) Admin can Update application status:

* APPLIED

* SHORTLISTED

* REJECTED

 **Core Features**
 
A. Job Management

Create jobs (Admin only)

Fetch active jobs (Public)

B. Job Application

Apply for a job with resume upload

Resume stored securely on server

Resume path saved in database

C. Application Tracking

View all applications for a job

Update application status

D. Robust Validation

DTO-based request validation

Centralized error handling

Meaningful HTTP status codes
*******************************************************************************************************************************************************************
**API Endpoints Summary**
Public APIs:-
Method	Endpoint	Description:

1) GET	/api/jobs	          (Get all active jobs)

2) POST	/api/applications/{jobId}/upload	          (Apply for job with resume)

3) POST	/api/auth/login	                             (Admin login)

Admin APIs (JWT Required):
Method	Endpoint	Description

POST	/api/jobs	                                     (Create a job)
GET	/api/admin/jobs/{jobId}/applications	           (View applicants)
PUT	/api/admin/applications/{id}/status	              (Update application status)
****************************************************************************************************************************************************************
Resume Upload Handling
Only PDF files allowed

Files saved on server with UUID-based unique names

Directory auto-created if missing

Secure file path stored in database
*****************************************************************************************************************************************************************
 **Exception Handling**

Centralized error handling using @RestControllerAdvice:

400 → Validation errors

401 → Unauthorized access

404 → Resource not found

500 → Internal server errors
******************************************************************************************************************************************************************
 **What This Project Demonstrates**

✔ Real-world backend architecture
✔ Secure authentication using JWT
✔ File upload handling
✔ Clean separation of concerns
✔ REST API best practices
✔ Admin vs Public access control
*****************************************************************************************************************************************************************
 **Future Enhancements**

Resume download API (admin)

Pagination & filtering

Email notifications

Cloud storage (AWS S3)

Frontend integration (React)
****************************************************************************************************************************************************************
**Author**

Yash Saini
Backend Developer | Java | Spring Boot
GitHub: github.com/sainiyash24
