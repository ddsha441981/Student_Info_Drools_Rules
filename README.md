# Student Management System

## Overview

The Student Management System is a Spring Boot application that manages student data, including their marks. It integrates with Drools for business rule management to evaluate student performance and sort student data based on their marks.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete student records.
- **Rule-Based Evaluation**: Use Drools to evaluate student performance based on their marks.
- **Database Integration**: Store and manage student data in a relational database.
- **Sorting**: Retrieve and sort student data based on their marks.

## Technologies

- **Spring Boot**: Framework for building the application.
- **Drools**: Business rule management system.
- **MySQL**: Database for storing student data.
- **JPA**: Java Persistence API for database interactions.

## Setup

### Prerequisites

- Java 17 or later
- Maven 3.6 or later
- MySQL Server
- IDE like IntelliJ IDEA or Eclipse (optional)

### Installation

1. **Clone the Repository**

    ```bash
    git clone https://github.com/your-username/student-management-system.git
    cd student-management-system
    ```

2. **Configure the Database**

    Ensure MySQL is running and create a database named `student_db`. Update the `application.properties` file with your MySQL database credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/student_db
    spring.datasource.username=root
    spring.datasource.password=your-password
    ```

3. **Build the Project**

    Use Maven to build the project:

    ```bash
    mvn clean install
    ```

4. **Run the Application**

    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Add Students

- **Endpoint**: `/students`
- **Method**: POST
- **Request Body**:
    ```json
    [
        {
            "id": 1,
            "name": "John Doe",
            "address": "123 Elm Street",
            "marks": 85
        },
        ...
    ]
    ```

### Get Students

- **Endpoint**: `/students`
- **Method**: GET
- **Response**:
    ```json
    [
        {
            "id": 1,
            "name": "John Doe",
            "address": "123 Elm Street",
            "marks": 85
        },
        ...
    ]
    ```

### Update Student

- **Endpoint**: `/students/{id}`
- **Method**: PUT
- **Request Body**:
    ```json
    {
        "name": "John Doe",
        "address": "123 Elm Street",
        "marks": 90
    }
    ```

### Delete Student

- **Endpoint**: `/students/{id}`
- **Method**: DELETE

## Drools Rules

The Drools rules are defined to evaluate student performance based on their marks. The rules are stored in the `students.drl` file.

### Rule Definitions

#### Rule: Mark Evaluation

- **Purpose**: Evaluate student marks and categorize their performance.
- **Criteria**:
    - Marks less than 40: Fail
    - Marks between 40 and 50: Too Low Score
    - Marks between 50 and 60: Medium Score
    - Marks between 60 and 80: Average Score
    - Marks between 80 and 90: Good Score
    - Marks above 90: Excellent Score

### Sample Drools Rule (students.drl)

```java
package com.cwc.student.rules;

import com.cwc.student.model.Student;

rule "Mark Evaluation"
    when
        $student : Student(marks < 40)
    then
        System.out.println($student.getName() + " has failed.");
end

rule "Mark Evaluation"
    when
        $student : Student(marks >= 40 && marks < 50)
    then
        System.out.println($student.getName() + " has too low score.");
end

rule "Mark Evaluation"
    when
        $student : Student(marks >= 50 && marks < 60)
    then
        System.out.println($student.getName() + " has medium score.");
end

rule "Mark Evaluation"
    when
        $student : Student(marks >= 60 && marks < 80)
    then
        System.out.println($student.getName() + " has average score.");
end

rule "Mark Evaluation"
    when
        $student : Student(marks >= 80 && marks < 90)
    then
        System.out.println($student.getName() + " has good score.");
end

rule "Mark Evaluation"
    when
        $student : Student(marks >= 90)
    then
        System.out.println($student.getName() + " has excellent score.");
end
