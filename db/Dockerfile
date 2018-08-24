FROM mysql:8

ENV MYSQL_DATABASE=families \
    MYSQL_ROOT_PASSWORD=root \
    MYSQL_USER=springuser \
    MYSQL_PASSWORD=springpassword

COPY schema.sql /docker-entrypoint-initdb.d/schema.sql