## About Book eShop
Book eShop is a simple and complete eCommerce web application developed with the following technologies :
 * Struts 2 for the presentation layer
 * Spring 3 for business layer
 * Hibernate 3 for data access layer

The application uses an in-memory HSQL database and runs inside a Tomcat web container.

## Build and run Book eShop
1.  Check out the project source code from github : `git clone https://github.com/benas/bookeshop.git`
2.  Open a terminal and run the following commands from root directory :<br/>
    `mvn package`<br/>
    `cd bookeshop-web`<br/>
    `mvn tomcat:run`<br/>
3.  Browse the following URL : `localhost:8080/index.do`
4.  You can register a new account or login using the following credentials : guest@server.com / guest

## License
Book eShop is released under the [MIT License][].

[MIT License]: http://opensource.org/licenses/mit-license.php/

## Screenshots

![Index page](https://github.com/benas/bookeshop/raw/master/src/site/screenshots/bookeshop-index.png)

![Checkout page](https://github.com/benas/bookeshop/raw/master/src/site/screenshots/bookeshop-checkout.png)
