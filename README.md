Junior Java Developer Test Task
Objective: The candidate is asked to write a microservice that consists of two APIs, one client to
access an external API, and uses at least one database to store business entities and exchange
rates.
This task implies checking the writing of effective algorithms, the ability to build an application
architecture, the ability to work with SQL (within the framework of the task, you need to use a
JOIN with a subquery and an aggregation function), use WEB frameworks, if desired, you can
use the tools of the java.util.concurrent package to parallelize transaction calculations in several
currencies, and get up-to-date exchange rates. A full list of evaluation criteria is given below.
Java:
• BigDecimal, Date types, enums
• Collections, equals and hashCode, Stream API, Lambdas
• Exception Handling
• Generics
• Code conventions
• Multithreading*, Reflection API*
Frameworks, Libraries and Build Tools:
• Spring WEB, WebFlux: bins, components, configurations, Jackson lib
• ORM (Spring Data, JOOQ): Entities, Transactions, Relations (ManyToOne,
OneToMany), JPQL, native queries, data mappings
• HTTP clients: Feign, Reactive Feign, WebClient
• DTO-Entity mappers: MapStruct, JMapper
• Configurations, @ComponentScan, Spring Boot auto-configuration with exceptions*
• Lombok
• Maven/Gradle (Bill of Materials, dependency management, build plugins, модули)
DB:
• SQL, Normal Forms, Table Relationships, Foreign Keys, Subqueries
• NoSQL and its appropriate uses (Mongo, Cassandra, Clickhouse, external caches) *
• Migration tools (Flyway, Liquibase)
General Engineering Skills:
• Understanding the HTTP Protocol
• Application architecture (Controller-Service-Repository and packages, separation of Utils
methods into separate classes)
• Development of algorithms (polynomial, preferably more efficient than O(n2))
• Using Patterns and Avoiding Anti-Patterns
• Working with documentation (external API), writing your own (JavaDoc, Swagger,
README.md)
• Code cleanliness (following the DRY principle, excluding hardcode, working with
environment variables and configurations for different environments (dev, test, prod)
• Following the principles of SOLID, DDD
• Logging: info, debug, warning, error; logback.xml;
• Working with a version control system
• Ability to estimate the time of completion and meet deadlines
QA:
• Unit Tests of Bean Methods and Utils Classes
• Integration tests, use of mocks*
• Test configuration and test-stage in CI pipeline *
Infrastructure and Linux:
• Docker, Docker Compose
• CI/CD pipelines, Bash*, Deploy code to a public server optional*
Task description:
You work as a Junior Java developer in a bank. The following task is received from the manager:
It is necessary to develop a prototype of a microservice, without API access restrictions, which
will be integrated into the existing banking system. A microservice should:
1. Receive information about each expense transaction in tenge (KZT), rubles (RUB) and
   other currencies in real time and save it in your own database (DB);
2. Keep the monthly spending limit in U.S. dollars (USD) separate for two expense
   categories: goods and services. If not set, accept the limit of 1000 USD;
3. Request data on exchange rates of KZT/USD, RUB/USD currency pairs on a daily
   interval (1day/daily) and store them in your own database. When calculating rates, use
   close data. If they are not available for the current day (weekend or holiday), then use the
   data of the last close (previous_close);
4. Mark transactions that have exceeded the monthly transaction limit (technical flag
   limit_exceeded);
5. Allow the client to set a new limit. When a new limit is set, the microservice
   automatically sets the current date, not allowing it to be set in the past or future tense. It
   is forbidden to update existing limits;
6. At the client's request, return a list of transactions that have exceeded the limit, indicating
   the limit that has been exceeded (date of setting, limit amount, currency (USD)).
   To fulfill step 3:
   You need to calculate the amount of expenses in USD at the exchange rate on the day of
   expenditure or at the last closing rate.
   Receive exchange trading data from an external data source (twelvedata.com, alphavantage.co,
   openexchangerates.org or from another at your discretion).
   You have to pay for each external data request, and it takes extra time to execute the external
   query. In this regard, the obtained exchange rates should be stored in your database and mainly
   used.
   To fulfill step 4:
   The last limit should not affect the flag limit_exceeded transactions made before the last limit
   was set. In other words, if the limit set on 1.01.2022 in the amount of 1000 USD is exceeded by
   two transactions in the amount of 500 and 600 USD, then the second transaction must be flagged
   limit_exceeded = true. If the user set a new limit on 11.01.2022, and executed a third transaction
   on 12.01.2022 in the amount of 100 USD, it should have the flag limit_exceeded = false.
   The following table illustrates the logic of flagging transactions, but is not a solution that should
   be implemented as part of database model development:
   To fulfill p.6:
   At the user's request, based on the table above, the microservice should return a list of two
   transactions performed on January 3 and 13 in the first case, and two transactions on January 11
   and 12 in the second case.
   Hint:
   When getting limits, use JOIN with a subquery, aggregation functions, and groupings in an SQL
   query.
   The structure of the transaction data at the input to the service:
   Limit Setting
   Date
   Limit, USD M o n t h l y
   l i m i t
   b a l a n c e ,
   USD
   Tr a n s a c t i o n
   Date
   Transaction
   Amount
   limit_exceeded
   1 case
   1.01.2022 1000 1000
   500 2.01.2022 500 false
   -100 3.01.2022 600 true
   10.01.2022 2000 900
   800 11.01.2022 100 false
   100 12.01.2022 700 false
   0 13.01.2022 100 false
   -100 13.01.2022 100 true
   Case 2
   1.02.2022 1000 1000
   500 2.01.2022 500 false
   400 3.01.2022 100 false
   10.02.2022 400 -200
   -300 11.01.2022 100 true
   -400 12.01.2022 100 true
   p/n Parameter name Parameter Name Data type Example
   1 Customer's bank
   account
   account_from Integer, 10 digits 0000000123
   2 Counterparty's bank
   account
   account_to Integer, 10 digits 9999999999
   The response data structure in claim 6 is similar to the above structure, with one exception: the
   following three parameters must be added to each transaction:
   Requirements
1. The service must contain two optional REST/SOAP/GraphQL APIs:
   A) to accept transactions (conditionally integration with banking services);
   B) Client, for external requests from the client: receiving a list of transactions that have
   exceeded the limit, setting a new limit, receiving all limits.
2. At least cover the API with documentation, README.md describe the steps for
   launching the service. To describe the API, you can use Swagger or analogues.
3. Choose a database for storing entities: PostgreSQL, MySQL, or other. Develop your own
   data model.
4. Scripts for database migrations are listed in the same repository. It is highly
   recommended to use Liquibase/FlyWay and the schema first approach.
5. Cover the unit code and/or integration tests, be sure to cover the logic of flagging
   limit_exceeded with tests.
6. Publish to a public repository on GitHub/GitLab.
7. Familiarize yourself with the task and declare the required time to complete the task – in
   this way, the skill of estimating the complexity of the task is tested.
   Optional Requirements
   They are optional, but will be unequivocally regarded positively:
   3 Account Currency currency_shortname String KZT
   4 Transaction Amount sum Floating-point
   number, rounded to
   the nearest hundredth
   10000,45
   5 Expense Category expense_category String product / service
   6 Date & Time datetime Timestamp with time
   zone
   2022-01-30
   00:00:00+06
   p/n Parameter name Parameter Name Data type Example
   7 The amount of the set
   limit
   limit_sum Floating-point
   number, rounded to
   the nearest
   hundredth
   1000.00
   8 Date and time when
   the limit is set
   limit_datetime Timestamp with
   time zone
   2022-01-10
   00:00:00+06
   9 Limit Currency limit_currency_shortname String USD
   1*. Implement parallel execution of the algorithm for client transactions in different currencies.
   2*. To store exchange rates, it is preferable to use NoSQL DB Cassandra.
   3*. Prepare the service to run in Docker; A Docker Compose script is preferred.
   4*. Write a CI pipeline for the selected version control system.
   5*. Launch the service on a public server and attach a link to the API.