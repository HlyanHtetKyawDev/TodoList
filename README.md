# Todo List Android App Assignment

### Overview

This project is a simple TODO list application that fetches data from an external API, stores it
locally for offline access, and displays it in a user-friendly interface. The app is designed to
demonstrate offline capabilities using Room DB and fetch/update operations with an API. It also include some test cases for testing.

### Used Technologies

- UI Design
    - Jetpack Compose

- Architecture
    - MVVM (Model-View-ViewModel) clean architecture pattern

- Technologies
    - Kotlin programming language
    - Retrofit for API integration
    - Hilt for dependency injection
    - Room database for local storage
    - JUnit, Mockito for testing
    - Additional third-party libraries as needed

### Features

- Fetches TODO list from the external API: https://jsonplaceholder.typicode.com/todos
- Stores the TODO list in the local Room database for offline accessibility
- Displays each TODO item with title, status and status icon (Completed or Not Completed)
- Automatically updates the list when the device is online and shows cached data when offline.