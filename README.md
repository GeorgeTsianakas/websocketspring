# WebSocket Spring Boot Demo

A minimal yet complete Spring Boot application showcasing WebSocket messaging over STOMP with a simple in-memory message broker and a browser-based client.

![](https://img.shields.io/badge/Code-Java%208-informational?style=flat&logo=java)
![](https://img.shields.io/badge/Build-Maven-informational?style=flat&logo=apache-maven)
![](https://img.shields.io/badge/Framework-Spring%20Boot%202.1.7.RELEASE-success?style=flat&logo=spring)
![](https://img.shields.io/badge/WebSocket-STOMP%20%2B%20SockJS-blue?style=flat)

## Table of Contents
- Overview
- Features
- Architecture & Endpoints
- Tech Stack
- Getting Started
  - Prerequisites
  - Run (Windows / macOS & Linux)
  - Build a package
- Try it out (UI)
- Message Contracts
- Project Structure
- Configuration
- Testing
- Troubleshooting
- Acknowledgments

## Overview
This project is a small demo inspired by the Baeldung guide on Spring WebSockets. It exposes a STOMP-over-WebSocket endpoint and a simple web UI that lets you connect, send your name, and receive a greeting broadcast via the broker topic.

## Features
- STOMP-over-WebSocket endpoint with SockJS fallback
- Simple in-memory message broker
- Browser client with Connect/Disconnect and Send actions
- Clean separation of app destinations vs. broker topics

## Architecture & Endpoints
- STOMP endpoint (SockJS enabled): `/gs-guide-websocket`
- Application destination prefix: `/app`
- Controller mapping: `/hello`
- Broker topic used for broadcasting: `/topic/greetings`

Flow:
1. Client connects to `/gs-guide-websocket` using SockJS + STOMP.
2. Client sends a JSON payload to `/app/hello`.
3. Server processes and publishes to `/topic/greetings`.
4. Subscribed clients receive the greeting message.

## Tech Stack
- Java 8
- Spring Boot 2.1.7.RELEASE
- spring-boot-starter-websocket, spring-messaging
- WebJars for front-end assets (SockJS, STOMP, jQuery, Bootstrap)
- Maven Wrapper

## Getting Started
### Prerequisites
- Java 8 (JDK 1.8)
- Internet access for Maven dependencies

### Run (Windows)
From the project root, start the app using the Maven Wrapper in the `websocketdemo` module:

- PowerShell / CMD:
  1. `cd websocketdemo`
  2. `mvnw.cmd spring-boot:run`

The application starts on http://localhost:8080 by default.

### Run (macOS & Linux)
- Terminal:
  1. `cd websocketdemo`
  2. `./mvnw spring-boot:run`

### Build a package
Create an executable WAR:
- Windows: `cd websocketdemo && mvnw.cmd clean package`
- macOS/Linux: `cd websocketdemo && ./mvnw clean package`

Then run it:
- `java -jar target/websocketdemo-0.0.1-SNAPSHOT.war`

## Try it out (UI)
After the app starts, open the demo page:
- http://localhost:8080/

Steps:
- Click Connect
- Type your name
- Click Send
- Observe the greeting appearing in the table; all connected clients see broadcasts from `/topic/greetings`.

## Message Contracts
- Client → Server (send to `/app/hello`):
  - Content-Type: application/json
  - Body: `{ "name": "Alice" }`

- Server → Client (subscribe to `/topic/greetings`):
  - Body: `{ "content": "Hello, Alice!" }`

## Project Structure
Key files and directories:
- `websocketdemo/src/main/java/com/websocketdemo/config/WebSocketConfig.java` — STOMP endpoint and broker setup
- `websocketdemo/src/main/java/com/websocketdemo/config/GlobalConstants.java` — endpoint, prefixes, and destinations
- `websocketdemo/src/main/java/com/websocketdemo/controller/GreetingController.java` — message mapping and broadcast
- `websocketdemo/src/main/webapp/index.html` — demo page (UI)
- `websocketdemo/src/main/resources/static/app.js` — client logic (SockJS + STOMP)
- `websocketdemo/pom.xml` — build configuration

## Configuration
- Default server port: 8080 (no custom `application.properties` set)
- Static assets are provided via WebJars and the `/static` classpath
- SockJS is enabled at the endpoint for better browser compatibility

## Testing
- Unit tests live under `websocketdemo/src/test/java`
- Run tests: 
  - Windows: `cd websocketdemo && mvnw.cmd test`
  - macOS/Linux: `cd websocketdemo && ./mvnw test`

## Troubleshooting
- If the UI doesn’t load WebJars:
  - Ensure the application is running and the console shows no 404s for `/webjars/*`.
- If WebSocket fails:
  - Check the browser console; SockJS/STOMP logs help identify handshake issues.
  - Make sure you connect to `/gs-guide-websocket` and subscribe to `/topic/greetings`.
- Port conflicts:
  - Stop other apps on port 8080 or set `server.port` in `application.properties`.

## Acknowledgments
- Based on the Baeldung guide to Spring WebSockets: https://www.baeldung.com/spring-websockets
- Spring Guides: https://spring.io/guides
