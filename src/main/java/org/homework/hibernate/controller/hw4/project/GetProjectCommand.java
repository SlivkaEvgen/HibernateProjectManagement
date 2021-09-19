package org.homework.hibernate.controller.hw4.project;

import org.homework.hibernate.config.ScannerConsole;
import org.homework.hibernate.service.hw4.ProjectServiceImpl;
import org.homework.hibernate.utils.Validator;

import java.io.Closeable;
import java.util.Scanner;

public class GetProjectCommand implements Closeable {

    private final ProjectServiceImpl PROJECT_SERVICE = new ProjectServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();

    public void all() {
        System.out.println(PROJECT_SERVICE.getAll());
    }

    public void byId() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        getById(scanner.next());
    }

    private void getById(String next) {
        if (Validator.validNumber(next)) {
            if (PROJECT_SERVICE.getById(Long.valueOf(next)).get().getId() != null) {
                System.out.println(PROJECT_SERVICE.getById(Long.valueOf(next)));
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
        System.out.println(PROJECT_SERVICE.getListProjectsWithDate() + "\n");
    }

    @Override
    public void close() {
        System.exit(0);
        scanner.close();
    }
}
