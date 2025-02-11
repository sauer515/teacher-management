
# Teacher Management System

This project is a Teacher Management System built with Java, Spring Boot, and Maven. It allows for managing teachers and their groups, including adding, removing, and searching for teachers, as well as managing group capacities and conditions.

## Features

- Add, remove, and search for teachers
- Manage teacher groups
- Calculate group fill percentages
- Export teacher data to CSV
- RESTful API endpoints for managing teachers and groups

## Technologies Used

- Java
- Spring Boot
- Maven
- JPA (Jakarta Persistence API)
- H2 Database (for testing)
- JUnit 5 (for testing)
- Mockito (for mocking in tests)

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/sauer515/teacher-management-system.git
    cd teacher-management-system
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

### Running Tests

To run the tests, use the following command:
```sh
mvn test
```

## API Endpoints

### Teacher Endpoints

- `POST /api/teacher` - Add a new teacher
- `DELETE /api/teacher/{id}` - Remove a teacher by ID
- `GET /api/teacher/csv` - Get all teachers in CSV format

### Group Endpoints

- `GET /api/group` - Get all groups
- `POST /api/group` - Add a new group
- `DELETE /api/group/{name}` - Remove a group by name
- `GET /api/group/{name}/teacher` - Get all teachers in a group
- `GET /api/group/{name}/fill` - Get the fill percentage of a group

## Project Structure

- `src/main/java/com/example/demo` - Main application code
- `src/main/java/com/example/demo/model` - Entity classes
- `src/main/java/com/example/demo/controller` - REST controllers
- `src/main/java/com/example/demo/service` - Service classes
- `src/test/java/com/example/demo` - Test classes

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Spring Boot documentation
- JPA documentation
- JUnit 5 documentation
- Mockito documentation
