package org.example.jdbc.controller.developer;

import org.example.jdbc.config.ScannerConsole;
import org.example.jdbc.utils.Validator;
import org.example.jdbc.controller.interfaces.Controller;
import org.example.jdbc.service.CompanyServiceImpl;
import org.example.jdbc.service.DeveloperServiceImpl;
import java.util.Scanner;

public class CreateDeveloperCommand implements Controller {

  private final DeveloperServiceImpl developerService = new DeveloperServiceImpl();
  private final Scanner scanner = ScannerConsole.getInstance();

  public void create() {
    String name = enterName();
    String age = enterAge();
    String companyId = enterCompanyId();
    String gender = enterGender();
    String email = enterEmail();
    String numberPhone = enterNumberPhone();
    String salary = enterSalary();
    developerService.createNewDeveloper(
        name,
        Long.valueOf(age),
        gender,
        email,
        Long.valueOf(numberPhone),
        Long.valueOf(companyId),
        Long.valueOf(salary));
    System.out.println(" ✅ You create \uD83D\uDC49   new Developer  \n");
  }

  private String enterName() {
    System.out.print(" ENTER NAME \n\uD83D\uDC49 ");
    String name = scanner.next();
    if (!Validator.validString(name)) {
      System.out.println("Try again");
      return enterName();
    }
    return name;
  }

  private String enterAge() {
    System.out.print(" ENTER AGE \n\uD83D\uDC49 ");
    String age = scanner.next();
    if (!Validator.validNumber(age)) {
      System.out.println("Try again");
      return enterAge();
    }
    return age;
  }

  private String enterCompanyId() {
    System.out.print(" ENTER COMPANY-ID \n\uD83D\uDC49 ");
    String companyId = scanner.next();
    try {
      if (!Validator.validNumber(companyId)
          | new CompanyServiceImpl().getById(Long.valueOf(companyId)).get().getId() == null) {
        System.out.println("Try again");
        return enterCompanyId();
      }
    } catch (NumberFormatException r) {
      System.out.println("Try again");
      return enterCompanyId();
    }
    return companyId;
  }

  private String enterSalary() {
    System.out.print(" ENTER SALARY \n\uD83D\uDC49 ");
    String salary = scanner.next();
    if (!Validator.validNumber(salary)) {
      System.out.println("Try again");
      return enterSalary();
    }
    return salary;
  }

  private String enterGender() {
    System.out.print(" ENTER GENDER \n\uD83D\uDC49 ");
    System.out.print(" Examples  \uD83D\uDC49  Male, Female\n");
    String gender = scanner.next();
    if (!Validator.validString(gender)
        | !gender.equalsIgnoreCase("male") & !gender.equalsIgnoreCase("female")) {
      System.out.println("Try again");
      return enterGender();
    }
    return gender;
  }

  private String enterEmail() {
    System.out.print(" ENTER EMAIL \n\uD83D\uDC49 ");
    String email = scanner.next();
    if (!email.contains("@")) {
      System.out.println("Try again");
      return enterEmail();
    }
    return email;
  }

  private String enterNumberPhone() {
    System.out.print(" ENTER NUMBER PHONE \n\uD83D\uDC49 ");
    String numberPhone = scanner.next();
    if (!Validator.validNumber(numberPhone)) {
      System.out.println("Try again");
      return enterNumberPhone();
    }
    return numberPhone;
  }

  @Override
  public void start() {
    create();
  }

  @Override
  public void close() {
    System.exit(0);
    scanner.close();
  }
}
