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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Lada", 5)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Mercedes", 8)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Honda", 7)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Gas", 10)));

      List<User> users1 = userService.listUsers();
      for (User user : users1) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Model = "+user.getCar().getModel());
         System.out.println("Series = "+user.getCar().getSeries());
         System.out.println();
      }
      try {
         System.out.println(userService.getUserByCarModelAndSeries("Honda", 7));
      } catch (Exception e) {
         System.err.println("User not found");
      }


      context.close();
   }
}
