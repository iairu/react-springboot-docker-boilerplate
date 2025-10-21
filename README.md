# React Springboot Docker Demo

## Bootstrap Changelog

1. touch README.md docker-compose.yml .gitignore && mkdir frontend-react && mkdir backend-java-springboot
1. Gemini CLI bootstrap of docker-compose + frontend Dockerfile + backend Dockerfile
2. Frontend: React bootstrap to make Dockerfile work: npx create-next-app@latest (npx create-react-app is deprecated so as Vercel fan I went with nextjs)
3. Backend: Springboot Initializr with Maven from https://start.spring.io/
4. Backend: Hello World Backend Api Controller
5. Reverse Proxy: Nginx Basic setup to expose backend on :80/api and frontend on :80/*
6. Frontend: Added axios get to hello world 
7. Backend: Updated with -XX:-UseContainerSupport now works
8. Backend: Added .json to api endpoints so Github Pages static API has identical file format extension

## Local Development - Docker Management

```bash
# Rebuild backend, frontend, launch services
docker compose build --no-cache backend
docker compose build --no-cache frontend
docker compose up
```

## GitHub Workflow

On push to `main` repository:

1. Loads `docker compose up -d` in Github CI/CD server
2. Uses `wget` to obtain static pages for Github Pages publishing
3. Publishes to Github Pages on http://react-springboot-docker-static.iairu.com

## Faster Springboot rebuilds
todo