### STAGE 1: Build ###

# We label our stage as 'builder'
FROM node:9-alpine as builder

WORKDIR /app
COPY package*.json /app/
RUN npm install
COPY ./ /app/
RUN npm run build -- --configuration production

### STAGE 2: nginx ###

FROM nginx
COPY nginx.conf /etc/nginx/
COPY --from=builder /app/dist/families /usr/share/nginx/html