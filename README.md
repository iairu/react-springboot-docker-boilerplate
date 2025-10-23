# React + Spring Boot + Docker Bootstrap

A tested working bootstrap template for modern full-stack web applications with React frontend, Java Spring Boot backend, Docker containerization, and Nginx reverse proxy for singular frontend + backend port/domain access.

**🔗 Original Repository:** https://github.com/iairu/react-springboot-docker-bootstrap

## 🚀 What's Included

This bootstrap provides a foundation with:

- **Frontend**: React (Next.js) with Axios for API communication with demo Hello World GET
- **Backend**: Spring Boot (Java) with a demo Hello World REST API endpoint
- **Containerization**: Docker with Docker Compose that includes all services (frontend, backend, reverse proxy)
- **Reverse Proxy**: Nginx for routing and load balancing
- **CI/CD Demo**: GitHub Actions workflow example for automated Github Pages deployment of a static API export
- **Static Hosting**: GitHub Pages integration for static builds

## 📋 Prerequisites

- [Docker](https://www.docker.com/get-started) and Docker Compose
- [Git](https://git-scm.com/)

## 🛠 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/iairu/react-springboot-docker-bootstrap.git
cd react-springboot-docker-bootstrap
```

### 2. Launch with Docker

```bash
# Start all services
docker compose up

# Or rebuild and start (useful after code changes)
docker compose build --no-cache
docker compose up
```

### 3. Access Your Application

- **Frontend**: http://localhost (React app routed through Nginx)
- **Backend API**: http://localhost/api (Spring Boot REST endpoints routed through Nginx)
- **Direct Backend**: http://localhost:8080 (Spring Boot server, must uncomment ports in docker-compose.yml)

## 🏗 Project Structure

```
├── frontend-react/          # React/Next.js frontend application
├── backend-java-springboot/  # Spring Boot backend application  
├── nginx-reverse-proxy/      # Nginx configuration for reverse proxy
├── .github/workflows/        # GitHub Actions CI/CD pipeline
├── docker-compose.yml        # Multi-container orchestration
└── README.md                # This file
```

## 🔧 Development Workflow

### Local Development

```bash
# Rebuild specific service
docker compose build --no-cache backend
docker compose build --no-cache frontend

# View logs
docker compose logs -f backend
docker compose logs -f frontend

# Stop all services
docker compose down
```

### Making Changes

1. **Frontend changes**: Edit files in `frontend-react/`
2. **Backend changes**: Edit files in `backend-java-springboot/`
3. **Nginx config**: Edit `nginx-reverse-proxy/nginx.conf`
4. Rebuild affected containers and restart

## 🚀 Deployment

### GitHub Pages (Automatic)

The repository includes GitHub Actions that automatically:

1. Builds the application on push to `main`
2. Generates static files using Docker
3. Deploys to GitHub Pages

### Manual Deployment

```bash
# Production build
docker compose -f docker-compose.prod.yml up --build

# Or deploy to your preferred hosting service
```

## 🔌 API Integration

The frontend uses Axios to communicate with the Spring Boot backend:

```javascript
// Example API call (already implemented)
import axios from 'axios';

const response = await axios.get('/api/hello.json');
console.log(response.data);
```

Backend provides REST endpoints:

```java
// Example controller (already implemented)
@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
```

## 🔧 Customization

### Adding New API Endpoints

1. Add controller methods in `backend-java-springboot/src/main/java/`
2. Rebuild backend: `docker compose build --no-cache backend`
3. Call from frontend using Axios

### Adding Frontend Pages

1. Add React components in `frontend-react/src/`
2. Update routing as needed
3. Rebuild frontend: `docker compose build --no-cache frontend`

### Environment Configuration

Environment variables are **optional** but available for customization:

1. **Copy the example file**:
   ```bash
   cp .env.local.example .env.local
   ```

2. **Edit your settings** in `.env.local`:
   ```bash
   # .env.local
   REACT_APP_API_URL=http://localhost/api
   SPRING_PROFILES_ACTIVE=development
   ```

3. **Enable in Docker** by uncommenting the `env_file` and `environment` lines in `docker-compose.yml`

The bootstrap works perfectly without any environment configuration!

## 📦 Technology Stack

- **Frontend**: React, Next.js, Axios, CSS Modules
- **Backend**: Spring Boot, Maven, Java 11+
- **Database**: Ready for integration (PostgreSQL/MySQL recommended)
- **Containerization**: Docker, Docker Compose
- **Web Server**: Nginx (reverse proxy)
- **CI/CD**: GitHub Actions
- **Hosting**: GitHub Pages, Docker-compatible platforms

## 🐛 Troubleshooting

### Common Issues

**Port conflicts:**
```bash
# Check what's using port 80
sudo lsof -i :80
# Kill conflicting processes or change ports in docker-compose.yml
```

**Container build fails:**
```bash
# Clean Docker cache
docker system prune -a
# Rebuild from scratch
docker compose build --no-cache --pull
```

**API calls fail:**
- Check Nginx configuration in `nginx-reverse-proxy/nginx.conf`
- Verify backend is running: `docker compose logs backend`
- Ensure CORS is configured in Spring Boot

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Make your changes
4. Test with `docker compose up`
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Happy coding!** 🚀 If you find this bootstrap helpful, please give it a star ⭐