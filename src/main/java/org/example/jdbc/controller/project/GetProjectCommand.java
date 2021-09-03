package org.example.jdbc.controller.project;

import org.example.jdbc.config.ScannerConsole;
import org.example.jdbc.service.ProjectServiceImpl;
import org.example.jdbc.utils.Validator;
import java.io.Closeable;
import java.util.Scanner;

public class GetProjectCommand implements Closeable {

  private final ProjectServiceImpl projectService = new ProjectServiceImpl();
  private final Scanner scanner = ScannerConsole.getInstance();

  public void all() {
    System.out.println(projectService.getAll());
  }

  public void byId() {
    System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
    String next = scanner.next();
    getById(next);
  }

  private void getById(String next) {
    if (Validator.validNumber(next)) {
      if (projectService.getById(Long.valueOf(next)).get().getId() != null) {
        System.out.println(projectService.getById(Long.valueOf(next)));
      } else {
        System.out.print("\nNot found, try again ... ");
        byId();
      }
    } else {
      System.out.print("\nNot found, try again ... ");
      byId();
    }
  }

  public void getListProjectsWithDate() {
    System.out.println(projectService.getListProjectsWithDate() + "\n");
  }

  @Override
  public void close() {
    System.exit(0);
    scanner.close();
  }
}
