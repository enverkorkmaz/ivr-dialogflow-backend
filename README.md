# IVR AI Platform

## Project Description

This project is a voice-driven appointment system developed with Java and Spring Boot. It allows users to create appointments through natural language using Dialogflow. The system is designed with SOLID principles and includes monitoring via Prometheus and Grafana. All components run in Docker containers, making the setup easily reproducible.

## Technologies Used

- Java 21
- Spring Boot 3
- PostgreSQL
- Dialogflow (with webhook integration)
- Prometheus
- Grafana
- Docker & Docker Compose
- Ngrok (for local webhook testing)

## Project Structure

```
├── controller
│   ├── AppointmentController.java
│   └── DialogflowWebhookController.java
├── service
│   ├── AppointmentService.java
│   ├── impl
│   │   └── AppointmentServiceImpl.java
│   └── DialogflowService.java
├── model
├── dto
├── repository
├── config
├── resources
│   └── application.properties
```

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/ivr-ai-platform.git
   cd ivr-ai-platform
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

3. Run with Docker:
   ```bash
   docker-compose up --build
   ```

4. Access services:
   - Backend: `http://localhost:8080`
   - PostgreSQL: `localhost:5432`
   - Prometheus: `http://localhost:9090`
   - Grafana: `http://localhost:3000`

## Dialogflow Webhook Setup (Local Testing)

To test Dialogflow with your local Spring Boot app:

1. Install [Ngrok](https://ngrok.com/)
2. Run:
   ```bash
   ngrok http 8080
   ```
3. Copy the HTTPS URL and set it in Dialogflow as the webhook endpoint:
   ```
   https://your-ngrok-url/api/webhook/dialogflow
   ```

## Prometheus Metrics

Make sure `/actuator/prometheus` is enabled in `application.properties`:
```
management.endpoints.web.exposure.include=*
management.endpoint.prometheus.enabled=true
management.metrics.web.server.request.autotime.enabled=true
```

Prometheus and Grafana dashboards are preconfigured to monitor HTTP requests and database metrics.

## Notes

- Rollback is supported with `@Transactional` in service layer.
- Custom metrics are implemented in service classes using `Counter` from Micrometer.
- The system is modular and adheres strictly to SOLID principles.
