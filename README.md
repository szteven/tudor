# Tudor
Draft Spring Boot app

## DB details
- database: tudorDB
- user: demouser
- pass: demopass

mysql> create database tudorDB;
mysql> CREATE USER 'demouser'@'localhost' IDENTIFIED BY 'demopass';
mysql> GRANT ALL PRIVILEGES ON tudorDB . * TO 'demouser'@'localhost';
mysql> FLUSH PRIVILEGES;

## Provisioning

Run http://{project_root}/provision to load some sample data (users).
