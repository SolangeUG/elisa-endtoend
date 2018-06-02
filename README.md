# Product Ordering Interface

[![GNU General Public License v3.0](https://img.shields.io/badge/license-GPL%20v3.0-green.svg)][1]
[![Java Back-End](https://img.shields.io/badge/back--end-Java-blue.svg)][2]
[![JavaScript Front-End](https://img.shields.io/badge/front--end-JavaScript-F4D03F.svg)][3]

Create a **web page for ordering products**:
- a user can select	a product from the provided product	catalog	and	submit an order
- the product catalog must also	display	prices of products (in addition	to the product information).

A product order	is sent	to API and stored to the database. Orders are anonymous; no authentication or authorization of 
a user is required. Basic information about	a customer should be saved with	the	order.

**The final project should include:**
- a HTML5/JS/CSS web interface for ordering	products: controls for displaying available	products with prices 
and an action for submitting the order	
- API implemented using Java 8 and provided	frameworks
- tests


## Languages - Libraries - Tools

- [Java SE 8][4]
- [Maven 3.3+][5]
- Any IDE/editor that supports JDK 8

## Setup and Run Instructions

- Download and unzip this `elisa-endtoend` project source code.
- From a command line window, navigate to the (newly unzipped) project root folder, then run the `mvn clean install` 
command, (make sure you have sufficient user permissions to install the application).
- To launch the application, run `java -jar target/endtoend-1.0-SNAPSHOT.jar`.
- The web application will start and be available at `http://localhost:8080/`.


[1]: https://github.com/SolangeUG/elisa-endtoend/blob/master/LICENSE
[2]: http://java.com/en/
[3]: https://www.javascript.com/
[4]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[5]: https://maven.apache.org/download.cgi
