## Instructions to run the application

1) Use your PostgreSQL database server to create database=pizza_order_system, user=pizza_user with password=123456 or you can make custom database settings by editing /src/main/resources/jdbc.properties file

2) Run sql scripts ( location=/script/pizza_order_system_db.sql ) on your PostgreSQL database server to create all required tables for application

3) go to the root folder with the application's source code and run maven command - mvn tomcat7:run

4) Open your web browser and enter the target url http://localhost:8080/PizzaOrderSystem

## Pizza Order System application details

The application provides a web interface to create pizzas' orders. For unregistered users top menu provides links to the login page and to the registration page. Also any time you can switch current language of application from English into Russian and vice versa.

For demonstrational purpose you can register users with any types of user roles. You can register a user with ROLE_ADMIN credentials to test all permissions of administrator access. For an ordinary user's registration you can use ROLE_USER credentials. Any registered user has direct access to the list of orders made by current registration profile. Also you can view current user registration details.

There is a button "Create Order" on orders' list page. You can change the state of each order in your list. There are some rules, when you can do it.

If the order is just created and has a NEW status, you can cancel it, if you've changed your mind about this order. Also administrator can cancel it in a NEW status. If the status of order is IN_PROGRESS, you can't cancel it, but administrator is still able to do this.

After administrator sets the status of order DONE, you'll see the updated release date of your order. Nobody can change the status of all DONE orders. Administrator's account has direct access to all users' registration data, pizza types and all pizzas with the privilege to modify anything he wants.

By default registered users don't have any discount card. You can assign discount card in user details profile page. Every pizza order is saved in user's discount card. For the next order of the user he will have 10% of accumulated card sum discount, but it can't be more than 30% of order price. Also if the order consists of more than 4 pizzas, you'll have a 30% discount for the most expensive pizza in the order. 

The application has a user friendly interface, so you'll understand how to use Pizza Order System very quickly!
