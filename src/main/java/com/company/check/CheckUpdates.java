package com.company.check;

import java.util.Scanner;

public class CheckUpdates {
    public static void main(String[] args) {
        if(args.length>0){
            menu(args);
        } else {
            Scanner scanner = new Scanner(System.in);
            String line;
            for(;;){
                line = scanner.nextLine();
                if(line.equals("/e") || line.equals("/exit")){
                    break;
                }
                menu(line.split(" "));
            }
        }
    }

    private static void menu(String[] args){
        Manager manager = new Manager();
        if (args[0].equals("/a") || args[0].equals("/add")) {
            if (args.length >= 3) {
                String name = args[1];
                String url = args[2];
                int result = manager.add(name, url);
                switch (result) {
                    case 0:
                        System.out.println("Запись добавлена");
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
                int result = 2;
                try {
                    int number = Integer.parseInt(args[1]);
                    result = manager.check(number);
                }catch (NumberFormatException e){
                    String name = args[1];
                    result = manager.check(name);
                }

                switch (result) {
                    case 0:
                        System.out.println("Изменений нет");
                        break;
                    case 1:
                        System.out.println("Страница изменилась");
                        break;
                    case 2:
                        System.out.println("Всё сломалось...");
                        break;
                    case 3:
                        System.out.println("Отсутствует запись с таким названием");
                        break;
                }
            }
        } else if (args[0].equals("/d") || args[0].equals("/delete")) {
            if (args.length >= 2) {
                String name = args[1];
                int result = manager.delete(name);
                switch (result) {
                    case 0:
                        System.out.println("Удаление успешно");
                        break;
                }
            }
        } else if (args[0].equals("/s") || args[0].equals("/status")) {
            System.out.println(manager.getListText());
        }
        System.out.println();
    }
}
