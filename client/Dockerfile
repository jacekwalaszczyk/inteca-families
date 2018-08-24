FROM node:8-alpine as builder

WORKDIR /app
COPY package*.json /app/
RUN npm install

COPY ./ /app/
RUN npm run build -- --configuration production

FROM nginx
COPY nginx.conf /etc/nginx/
COPY --from=builder /app/dist/families /usr/share/nginx/html