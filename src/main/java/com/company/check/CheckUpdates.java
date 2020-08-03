package com.company.check;

import java.util.Scanner;

public class CheckUpdates {
    public static void main(String[] args) {
        while(args.length==0){
            Scanner scanner = new Scanner(System.in);
            args = scanner.nextLine().split(" ");
        }
        Manager manager = new Manager();
        if (args[0].equals("/a") || args[0].equals("/add")) {
            if (args.length >= 3) {
                String name = args[1];
                String url = args[2];
                int result = manager.add(name, url);
                switch (result) {
                    case 0:
                        System.out.println("Всё ОК");
                        break;
                    case 1:
                        System.out.println("Уже существует");
                        break;
                    case 2:
                        System.out.println("Что-то пошло не так...");
                        break;
                }
            }
        } else if (args[0].equals("/c") || args[0].equals("/check")) {
            if (args.length >= 2) {
                String name = args[1];
                int result = manager.check(name);
                switch (result){
                    case 0:
                        System.out.println("Изменения нет");
                        break;
                    case 1:
                        System.out.println("Страница изменилась");
                        break;
                    case 2:
                        System.out.println("Всё сломалось...");
                        break;
                }
            }
        } else if (args[0].equals("/d") || args[0].equals("/delete")) {
            if (args.length >= 2) {
                String name = args[1];
                int result = manager.delete(name);
                switch (result){
                    case 0:
                        System.out.println("Всё ок");
                        break;
                }
            }
        }
    }
}
