package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Alexey", "Alekseev", "user1@gmail.com");
      User user2 = new User("Ivan", "Ivanov", "user2@gmail.com");
      User user3 = new User("Petr", "Petrov", "user3@gmail.com");
      User user4 = new User("Oleg", "Olegov", "user4@gmail.com");


      Car car1 = new Car("Vaz", 2106);
      Car car2 = new Car("Mercedes", 600);
      Car car3 = new Car("Tesla", 3);
      Car car4 = new Car("Gaz", 22);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user5 = userService.getUser("Gaz", 22);
      System.out.println(user5);

      context.close();
   }
}
