# Department and Employee Service API
A **Spring Boot REST API** for managing **Departments** and **Employees**. This service exposes CRUD endpoints for both modules.

## Tech Stack 
* Java 17+ 
* Spring Boot
* Spring Web (REST) 
* Spring Data JPA
* Lombok
* Mapstruct
* Gradle

## Getting Started

### Prerequisites

* Java 17 or higher
* Gradle
* Database (MySQL)

### Run the Application

```bash
./gradlew bootrun
```

Application will start on:

```
http://localhost:8080
```

---

## API Base Paths

* **Departments:** `/departments`
* **Employees:** `/employees`

---

## Department API Endpoints

### Create Department

```
POST /departments
```

**Request Body:**

```json
{
  "name": "IT",
  "code": "IT001"
}
```

**Response:** `201 CREATED`

```json
1
```

### Get All Departments

```
GET /departments
```

**Response:** `200 OK`

```json
[
  {"id": 1, "name": "IT", "code": "IT001"}
]
```

### Get Department By ID

```
GET /departments/{id}
```

**Response:** `200 OK`

```json
{"id": 1, "name": "IT", "code": "IT001"}
```

### Update Department

```
PUT /departments/{id}
```

**Request Body:**

```json
{"name": "HR", "code": "HR001"}
```

**Response:** `200 OK`

```json
{"id": 1, "name": "HR", "code": "HR001"}
```

### Delete Department

```
DELETE /departments/{id}
```

**Response:** `204 NO CONTENT`

---

## Employee API Endpoints

### Create Employee

```
POST /employees
```

**Request:** `multipart/form-data`

* `employee` (JSON)
* `image` (file)

**Response:** `201 CREATED`

```json
1
```

### Update Employee

```
PUT /employees/{id}
```

**Request:** `multipart/form-data`

* `employee` (JSON)
* `image` (file)

**Response:** `200 OK`

```json
1
```

### Get Employee By ID

```
GET /employees/{id}
```

**Response:** `200 OK`

```json
{
  "id": 1,
  "name": "John Doe",
  "code": "EMP001",
  "departmentId": 1,
  "email": "john@example.com"
}
```

### Get All Employees (Paginated)

```
GET /employees?size=10&page=0
```

**Response:** `200 OK`

```json
{
  "content": [
    {"id": 1, "name": "John Doe", "code": "EMP001"}
  ],
  "totalElements": 50,
  "totalPages": 5,
  "size": 10,
  "number": 0
}
```

### Delete Employee

```
DELETE /employees/{id}
```

**Response:** `204 NO CONTENT`

---

## Error Handling

* `404 NOT FOUND` – Entity not found
* `400 BAD REQUEST` – Validation errors
* `500 INTERNAL SERVER ERROR` – Unexpected server error

---

## Testing

Test APIs using:
* Postman
* curl

Example:

```bash
curl -X GET http://localhost:8080/employees
```

---

## License

MIT License

---

## Author

**Hany Elrebby**
