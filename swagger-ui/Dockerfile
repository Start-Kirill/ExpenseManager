FROM swaggerapi/swagger-ui:latest
MAINTAINER Kiryl

ADD /expense-manager/spec/expense-manager.yaml /usr/share/nginx/html/doc/expense-manager.yaml

ENV URLS="[{ url: 'doc/expense-manager.yaml', name: 'ExpenseManager'}]"
ENV URLS_PRIMARY_NAME="ExpenseManager"