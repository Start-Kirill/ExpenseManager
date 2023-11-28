FROM postgres:15.3-alpine3.18
MAINTAINER Kiryl

ADD /expense-manager/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /expense-manager/dml/*.sql /docker-entrypoint-initdb.d/
