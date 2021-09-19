package org.homework.hibernate.controller.hw4.developer;

import org.homework.hibernate.config.ScannerConsole;
import org.homework.hibernate.controller.hw4.interfaces.Controller;
import org.homework.hibernate.service.hw4.CompanyServiceImpl;
import org.homework.hibernate.service.hw4.DeveloperServiceImpl;
import org.homework.hibernate.utils.Validator;

import java.util.Scanner;

public class UpdateDeveloperCommand implements Controller {

    private final DeveloperServiceImpl DEVELOPER_SERVICE = new DeveloperServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();

    private void update() {
        final String id = enterId();
        final String name = enterName();
        final String age = enterAge();
//        final String companyId = enterCompanyId();//Long.valueOf(companyId)
        final String gender = enterGender();
        final String email = enterEmail();
//        final String numberPhone = enterNumberPhone();
        final String salary = enterSalary();
        DEVELOPER_SERVICE.update(Long.valueOf(id), name, Long.valueOf(age), gender, email, Long.valueOf(salary));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + DEVELOPER_SERVICE.getById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (!Validator.validNumber(id) || DEVELOPER_SERVICE.getById(Long.valueOf(id)).get().getId() == null) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
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
            if (!Validator.validNumber(companyId) | new CompanyServiceImpl().getById(Long.valueOf(companyId)).get().getId() == null) {
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
        if (!Validator.validString(gender) | !gender.equalsIgnoreCase("male") & !gender.equalsIgnoreCase("female")) {
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
        update();
    }

    @Override
    public void close() {
        System.exit(0);
        scanner.close();
    }
}
