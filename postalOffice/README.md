Post Office Management System
A simple Spring Boot REST API that simulates a Post Office system.
This project helped me understand how backend systems are structured using layered architecture, REST principles, and database integration.

Overview
This application manages:
•	Post Offices
•	Parcels
•	Parcel History
It follows a clean layered structure:
•	Controller layer
•	Service layer
•	Repository layer
•	Entity layer
Built using:
•	Java
•	Spring Boot
•	Maven
•	JPA
•	REST APIs
 
Project Structure
src/main/java/org/example/postaloffice
•	History.java
•	HistoryController.java
•	HistoryRepo.java
•	HistoryServices.java
•	Parcel.java
•	ParcelController.java
•	ParcelRepo.java
•	ParcelServices.java
•	PostOffice.java
•	PostOfficeController.java
•	PostOfficeRepo.java
•	PostOfficeServices.java
 
What I Learned
1. Layered Architecture
I separated the application into:
Controller
Handles HTTP requests and responses.
Service
Contains business logic.
Repository
Handles database communication using JPA.
Entity
Represents database tables using annotations.
This improved code organization and scalability.
 
2. REST API Development
I learned how to:
•	Create GET, POST, PUT, DELETE endpoints
•	Use @RestController
•	Use @RequestMapping
•	Handle path variables and request bodies
•	Return proper HTTP responses
Example endpoints:
•	GET /postoffices
•	POST /parcels
•	PUT /parcels/{id}
•	DELETE /history/{id}
 
3. Spring Boot Fundamentals
I understood:
•	How Spring Boot auto configuration works
•	Dependency injection using @Autowired
•	How to structure a Maven project
•	How application.properties connects to a database
 
4. JPA and Database Mapping
I worked with:
•	@Entity
•	@Id
•	@GeneratedValue
•	@OneToMany and @ManyToOne relationships
This helped me understand how Java objects map to database tables.
 
5. CRUD Operations
I implemented:
•	Create records
•	Read records
•	Update records
•	Delete records
This gave me hands on experience building full backend workflows.
 
6. Service Layer Logic
Instead of placing logic inside controllers, I moved business logic into service classes.
This made the code:
•	Cleaner
•	Easier to maintain
•	Easier to test
 
7. Maven and Project Management
I learned how to:
•	Manage dependencies in pom.xml
•	Use mvnw to run the project
•	Build the project using Maven lifecycle
 
How to Run
1.	Clone the repository
git clone https://github.com/Kei774/University-Projects.git
2.	Navigate to the project folder
cd postalOffice
3.	Run the application
./mvnw spring-boot:run
Or on Windows:
mvnw.cmd spring-boot:run
4.	Access API
http://localhost:8080
 
Future Improvements
•	Add validation using @Valid
•	Add exception handling with @ControllerAdvice
•	Implement authentication using Spring Security
•	Add unit tests
•	Add pagination and sorting
•	Dockerize the application
 
Why I Built This
I built this project to strengthen my backend development skills and understand how real world REST APIs are structured.
It helped me move from writing simple Java programs to building structured, production style backend systems.
 
Author
Senuka
Second Year Software Engineering Student
Focused on backend development and system design

