# POCr
Generate, build and deploy on a weblogic server simple JSF applications

# Prerequisites

Download and install:

- Oracle JDK (>= 8)
- Maven (3.x)
- MySQL
- Wildfly server.

# Configurations

- Download POCr sources and ``mvn clean install`` in the parent directory.
- Start the MySQL server. Create a MySQL database. 
- Update the ``persistence.xml`` file in ``pocr-install`` with the correct database connection details. 
Run standalone Java program ``eu.micul01.pocr.entity.CreateSchema`` from the same project.
- Start the Wildfly application server. 
Create a datasource in Wildfly pointing to the database created earlier. 
Make sure the JNDI matches the one in the ``persistence.xml`` file in ``pocr-ejb`` project.
- ``mvn clean install -P deploy``

# What to expect

The application presents a basic GUI allowing to create an application, 
containing a form which can have one or more fields.
At first the home page is empty.
The user can choose to create a new application.
Then he/she can add a form to it, and some fields.
After submitting, the application goes back to the home page, containing the newly created application.
From here, he/she can choose to deploy the application.
Once the action is complete, pressing "Run application" opens a new browser tab of the JSF application 
which was generated, compiled, packaged and deployed, containing the form and fields created at the previous steps.


