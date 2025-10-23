# React (Next.js) + Java Spring Boot + Docker + Postgres + Nginx Boilerplate

A tested working boilerplate template for modern full-stack web applications with React frontend, Java Spring Boot backend, Docker containerization, and Nginx reverse proxy for singular frontend + backend port/domain access.

**🔗 Original Repository:** https://github.com/iairu/react-springboot-docker-boilerplate

## 📌 Service Versions

- **React**: 19.x
- **Next.js**: 15.x
- **Node.js**: 22.x (Alpine LTS)
- **Java**: 17+
- **Spring Boot**: 3.5.6
- **PostgreSQL**: 18.0 (Alpine)
- **Nginx**: 1.28.0 (Alpine Stable)

## 🚀 What's Included

This boilerplate provides a foundation with:

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
git clone https://github.com/iairu/react-springboot-docker-boilerplate.git
cd react-springboot-docker-boilerplate
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
├── .env.local.example       # Environment variables template
└── README.md                # This file
```

## 🔧 Development Workflow

### Local Development

### Quick Restart

Often used after minor config changes, inconsistencies, or computer reboot:

```bash
# Turn off
docker compose down

# Stop and remove all remaining Docker containers and networks (adjust prefix if needed)
docker stop $(docker ps -a -q -f name=react-springboot-) ; docker rm -f $(docker ps -a -q -f name=react-springboot-) ; docker network rm $(docker network ls -q -f name=react-springboot_)

# Turn on again
docker compose up -d
```

**One-line restart command** (recommended to add to .bashrc as an alias):
```bash
docker compose down ; docker stop $(docker ps -a -q -f name=react-springboot-) ; docker rm -f $(docker ps -a -q -f name=react-springboot-) ; docker network rm $(docker network ls -q -f name=react-springboot_) ; docker compose up -d
```

### Full Rebuild

Use after major changes (especially if package.json or pom.xml changed):

```bash
# Turn off
docker compose down

# Stop and remove all remaining Docker containers and networks
docker stop $(docker ps -a -q -f name=react-springboot-) ; docker rm -f $(docker ps -a -q -f name=react-springboot-) ; docker network rm $(docker network ls -q -f name=react-springboot_)

# Rebuild without cache (important!)
docker compose build --no-cache

# Turn on
docker compose up -d
```

**One-line rebuild command** (recommended to add to .bashrc as an alias):
```bash
docker compose down ; docker stop $(docker ps -a -q -f name=react-springboot-) ; docker rm -f $(docker ps -a -q -f name=react-springboot-) ; docker network rm $(docker network ls -q -f name=react-springboot_) ; docker compose build --no-cache ; docker compose up -d
```

### Selective Service Rebuild

```bash
# Rebuild specific service
docker compose build --no-cache backend
docker compose build --no-cache frontend
docker compose build --no-cache nginx

# Restart only nginx after config changes
docker compose stop nginx && docker compose build nginx && docker compose up -d nginx
```

### View Logs

```bash
# View all logs
docker compose logs -f

# View logs for specific service
docker compose logs -f backend
docker compose logs -f frontend
docker compose logs -f postgres

# View logs for specific container by ID
docker logs <container_id>
```

### Stop Services

```bash
# Stop all services (keeps containers)
docker compose stop

# Stop and remove all services
docker compose down
```

### Nuclear Option - Complete Docker Reset

**⚠️ WARNING**: This will remove **ALL** containers and images on your machine, not just this project!

**Consider backing up other containers first!**

```bash
# Stop and remove all Docker containers, then remove all images
docker stop $(docker ps -a -q) ; docker rm -f $(docker ps -a -q) ; docker rmi $(docker images -q)

# Remove all volumes and networks
docker system prune -a --volumes --force

# Start fresh
docker compose up -d
```

### Debugging Commands

```bash
# List all containers (running and stopped)
docker container ls -a

# Get bash shell in a container
docker exec -it <container-name> /bin/bash

# If bash is not available, try sh
docker exec -it <container-name> /bin/sh

# Inspect container details
docker inspect <container-name>

# Check Docker disk usage
docker system df
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
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
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

The boilerplate works perfectly without any environment configuration!

## 📦 Technology Stack

- **Frontend**: React, Next.js, Axios, CSS Modules
- **Backend**: Spring Boot, Maven, Java 17+, JPA/Hibernate
- **Database**: PostgreSQL 15 (Alpine) with automatic schema management
- **Containerization**: Docker, Docker Compose
- **Web Server**: Nginx (reverse proxy)
- **CI/CD**: GitHub Actions
- **Hosting**: GitHub Pages, Docker-compatible platforms

## 🗄️ Database Features

- **PostgreSQL Integration**: Fully configured with connection pooling
- **JPA/Hibernate**: Automatic schema generation and updates
- **Sample Entity**: User entity with CRUD operations
- **Health Checks**: Database connectivity monitoring
- **Security**: Database only accessible within Docker network
- **Persistence**: Data persisted in Docker volumes

### Available API Endpoints

- `GET /api/users` - List all users
- `POST /api/users` - Create new user
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/count` - Get user count
- `GET /api/health` - Database health check

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

**Database connection issues:**
```bash
# Check PostgreSQL logs
docker compose logs postgres
# Check backend connection logs
docker compose logs backend
# Verify database health
curl http://localhost/api/health
```

**API calls fail:**
- Check Nginx configuration in `nginx-reverse-proxy/nginx.conf`
- Verify backend is running: `docker compose logs backend`
- Ensure CORS is configured in Spring Boot
- Test database connectivity: `curl http://localhost/api/health`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Make your changes
4. Test with `docker compose up`
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Happy coding!** 🚀 If you find this boilerplate helpful, please give it a star ⭐