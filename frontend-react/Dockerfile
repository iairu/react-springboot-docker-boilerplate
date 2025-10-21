# Use an official Node.js runtime as a parent image
FROM node:18-alpine

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json from the next-js-app subdirectory
COPY next-js-app/package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application's source code from the next-js-app subdirectory
COPY next-js-app/ .

# Build the React app for production
RUN npm run build

# Expose the port the app runs on
EXPOSE 3000

# Command to run the app using the start script
CMD ["npm", "start"]
