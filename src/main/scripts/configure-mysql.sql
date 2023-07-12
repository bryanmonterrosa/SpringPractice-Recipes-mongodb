## Use to run mysql db docker image.
# docker run --name quazar-mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=1 -p 3306:3306 -d mysql

# conect to mysql and run as root user
# Create Databases

CREATE DATABASE aq_dev;
CREATE DATABASE aq_prod;

CREATE USER 'aq_dev_user'@'localhost' IDENTIFIED BY 'quazar';
CREATE USER 'aq_prod_user'@'localhost' IDENTIFIED BY 'quazar';
CREATE USER 'aq_dev_user'@'%' IDENTIFIED BY 'quazar';
CREATE USER 'aq_prod_user'@'%' IDENTIFIED BY 'quazar';

# Database grants
GRANT SELECT ON aq_dev.* to 'aq_dev_user'@'localhost';
GRANT INSERT ON aq_dev.* to 'aq_dev_user'@'localhost';
GRANT DELETE ON aq_dev.* to 'aq_dev_user'@'localhost';
GRANT UPDATE ON aq_dev.* to 'aq_dev_user'@'localhost';
GRANT SELECT ON aq_prod.* to 'aq_prod_user'@'localhost';
GRANT INSERT ON aq_prod.* to 'aq_prod_user'@'localhost';
GRANT DELETE ON aq_prod.* to 'aq_prod_user'@'localhost';
GRANT UPDATE ON aq_prod.* to 'aq_prod_user'@'localhost';

GRANT SELECT ON aq_dev.* to 'aq_dev_user'@'%';
GRANT INSERT ON aq_dev.* to 'aq_dev_user'@'%';
GRANT DELETE ON aq_dev.* to 'aq_dev_user'@'%';
GRANT UPDATE ON aq_dev.* to 'aq_dev_user'@'%';
GRANT SELECT ON aq_prod.* to 'aq_prod_user'@'%';
GRANT INSERT ON aq_prod.* to 'aq_prod_user'@'%';
GRANT DELETE ON aq_prod.* to 'aq_prod_user'@'%';
GRANT UPDATE ON aq_prod.* to 'aq_prod_user'@'%';