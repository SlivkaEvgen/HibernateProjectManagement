package org.homework.hibernate.controller.hw4.customer;

import org.homework.hibernate.config.ScannerConsole;
import org.homework.hibernate.model.Customer;
import org.homework.hibernate.service.hw4.CustomerServiceImpl;
import org.homework.hibernate.utils.Validator;

import java.util.Optional;
import java.util.Scanner;

public class DeleteCustomerCommand extends CustomerCommandImpl {

    private final Scanner scanner = ScannerConsole.getInstance();

    public void delete(String id) {
        final CustomerServiceImpl customerService = new CustomerServiceImpl();
        if (Validator.validNumber(id)) {
            final Optional<Customer> serviceById = customerService.getById(Long.valueOf(id));
            if (serviceById.get().getId() != null) {
                System.out.println("\nAre you sure ?  \n" + serviceById);
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    customerService.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Customer number \uD83D\uDC49 " + id + " \n");
                }
            } else {
                System.out.print("\nNot found, try again ... ");
                deleteCommand();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            deleteCommand();
        }
    }
}
