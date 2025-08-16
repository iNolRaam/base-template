# base-template
It's a base template for my projects using Springboot 3


Developer profile: Clean, package and re-create database:
mvn clean package -DsqlMode=always -Dprofile=dev

Production profile: Clean, package and re-create database:
mvn clean package -DsqlMode=always -Dprofile=prod
