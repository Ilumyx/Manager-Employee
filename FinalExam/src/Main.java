import Repository.UserConnectRepository;

import Object.Admin;
import Object.Employee;
import java.util.Scanner;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        UserConnectRepository userConnectRepository = new UserConnectRepository();
        question4(userConnectRepository);

    }

    public static void question2(UserConnectRepository userConnectRepository){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cau 2");
        System.out.println("Nhap projectID: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Data is processing");
        userConnectRepository.getAllDatabyProjectID(projectId);
    }

    public static void question3(UserConnectRepository userConnectRepository){
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        System.out.println("Cau 3");
        System.out.print("Nhap Email: ");
        admin.setEmail(scanner.next());
        if(!validateEmail(admin.getEmail())){
            System.out.println("Your email is invalid");
            return;
        }
        System.out.print("Nhap Password: ");
        admin.setPassword(scanner.next());
        if(!validatePassword(admin.getPassword())){
            System.out.println("Your password is invalid");
            return;
        }
        System.out.println("Login is processing");
        userConnectRepository.login(admin);
    }
    public static void question4(UserConnectRepository userRepository) {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();
        System.out.println("Cau 4");
        System.out.println("Nhap Email: ");
        employee.setEmail(scanner.nextLine());
        if (!validateEmail(employee.getEmail())) {
            System.out.println("Your email is invalid ");
            return;
        }

        System.out.println("Nhap full name: ");
        String fullName = scanner.nextLine();
        employee.setFullName(fullName);
        System.out.println("Data is processing");
        userRepository.createEmployee(employee);
    }

    public static boolean validateEmail(String email) {

        String regexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if(email.matches(regexPattern)) {
            return true;
        }
        return false;
    }

    public static boolean validatePassword(String password) {
        if(password.length() >= 6 ) {
            return true;
        }
        return false;
    }
}

