# Task Service

This service is responsible for managing tasks.

## Features

-   **CRUD Operations:** Provides full Create, Read, Update, and Delete functionality for tasks.
-   **Task Management:** Allows users to create, view, update, and delete their tasks.

## Endpoints

-   `GET /api/tasks`: Retrieves a list of all tasks.
-   `GET /api/tasks/{id}`: Retrieves a single task by its ID.
-   `POST /api/tasks`: Creates a new task. Requires a JSON body with task details.
-   `PUT /api/tasks/{id}`: Updates an existing task. Requires a JSON body with the updated task information.
-   `DELETE /api/tasks/{id}`: Deletes a task by its ID.
