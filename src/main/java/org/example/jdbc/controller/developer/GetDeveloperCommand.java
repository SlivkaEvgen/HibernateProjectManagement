package org.example.jdbc.controller.developer;

import org.example.jdbc.config.ScannerConsole;
import org.example.jdbc.utils.Validator;
import org.example.jdbc.service.DeveloperServiceImpl;
import java.io.Closeable;
import java.util.Scanner;

public class GetDeveloperCommand implements Closeable {

  private final DeveloperServiceImpl developerService = new DeveloperServiceImpl();
  private final Scanner scanner = ScannerConsole.getInstance();

  public void all() {
    System.out.println(developerService.getAll());
  }

  public void byId() {
    System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
    String next = scanner.next();
    getById(next);
  }

  private void getById(String next) {
    if (Validator.validNumber(next)) {
      if (developerService.getById(Long.valueOf(next)).get().getId() != null) {
        System.out.println(developerService.getById(Long.valueOf(next)));
      } else {
        System.out.print("\nNot found, try again ... ");
        byId();
      }
    } else {
      System.out.print("\nNot found, try again ... ");
      byId();
    }
  }

  public void getByProjectID() {
    System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
    String next = scanner.next();
    if (Validator.validNumber(next)) {
      if (!developerService.getDevelopersFromOneProject(Long.valueOf(next)).isEmpty()) {
        System.out.println(developerService.getDevelopersFromOneProject(Long.valueOf(next)) + "\n");
      } else {
        System.out.println("not found, try again ");
        getByProjectID();
      }
    } else {
      System.out.println("not found, try again ");
      getByProjectID();
    }
  }

  public void getByActivity() {
    System.out.print(" ENTER ACTIVITY \n ✅examples : Java, JS, C++, C# \n\uD83D\uDC49 ");
    String activity = scanner.next();
    if (Validator.validString(activity)) {
      if (!activity.equalsIgnoreCase("js")) {
        if (!activity.equalsIgnoreCase("java")) {
          if (!activity.equalsIgnoreCase("C++")) {
            if (!activity.equalsIgnoreCase("C#")) {
              System.out.println("not found, try again ");
              getByActivity();
            } else {
              System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
            }
          } else {
            System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
          }
        } else {
          System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
        }
      } else {
        System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
      }
    } else {
      System.out.println("not found, try again ");
      getByActivity();
    }
  }

  public void getByLevel() {
    System.out.print(" ENTER LEVEL \n ✅examples : Junior, Middle, Senior \n\uD83D\uDC49 ");
    String level = scanner.next();
    if (Validator.validString(level)) {
      if (!level.equalsIgnoreCase("middle")) {
        if (!level.equalsIgnoreCase("senior")) {
          if (!level.equalsIgnoreCase("junior")) {
            System.out.println("not found, try again ");
            getByLevel();
          } else {
            System.out.println(developerService.getDevelopersByLevel(level) + "\n");
          }
        } else {
          System.out.println(developerService.getDevelopersByLevel(level) + "\n");
        }
      } else {
        System.out.println(developerService.getDevelopersByLevel(level) + "\n");
      }
    } else {
      System.out.print("\n not found, try again \n");
      getByLevel();
    }
  }

  public void getSumSalaries() {
    System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
    String next = scanner.next();
    if (Validator.validNumber(next)) {
      try {
        if (!next.isEmpty()) {
          long nextLong = Long.parseLong(next);
          if (nextLong != 0) {
            System.out.print(
                developerService.getSumSalariesDevelopersOfOneProject(nextLong) + "\n");
          } else {
            System.out.print("\nnot found, try again \n");
            getSumSalaries();
          }
        } else {
          System.out.print("\nnot found, try again \n");
          getSumSalaries();
        }
      } catch (IndexOutOfBoundsException t) {
        System.out.print("\nnot found, try again \n");
        getSumSalaries();
      }
    } else {
      System.out.print("\nnot found, try again \n");
      getSumSalaries();
    }
  }

  @Override
  public void close() {
    System.exit(0);
    scanner.close();
  }
}
