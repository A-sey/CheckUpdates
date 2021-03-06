package com.company.check;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, управляющий файлами
 */
public class Storage {
    private static String path = "files/";
    private String fileName = "records.csv";
    private List<Rercord> list;

    public Storage() {
        list = new LinkedList<>();
        new File(path).mkdir();
    }

    /**
     * Чтение списка сохранённых страниц
     * @return Успешность чтения списка из файла
     */
    public boolean readList(){
        try {

            FileReader reader = new FileReader(path+fileName);
            Scanner scanner = new Scanner(reader);
            list.clear();
            while (scanner.hasNextLine()) {
                String[] s = scanner.nextLine().split(";");
                Rercord record = new Rercord(s[0], s[1], LocalDateTime.parse(s[2]));
                list.add(record);
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Запись списка страниц в файл
     * @return Успешность записи
     */
    public boolean writeList(){
        try {
            FileWriter writer = new FileWriter(path+fileName);
            for(Rercord r: list){
                StringBuffer line = new StringBuffer();
                line.append(r.getName()).append(";");
                line.append(r.getUrl()).append(";");
                line.append(r.getTime()).append("\n");
                writer.write(line.toString());
            }
            writer.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Добавление нового сайта в список
     * @param name Короткое уникальное название
     * @param url Адрес страницы
     */
    public void addRecord(String name, String url){
        Rercord record = new Rercord(name, url, LocalDateTime.now());
        list.add(record);
    }

    public void updateTime(String name){
        for(Rercord r: list){
            if(r.getName().equals(name)){
                r.setTime(LocalDateTime.now());
                break;
            }
        }
    }

    /**
     * Удаление записи из списка
     * @param name
     */
    public void dropRecord(String name){
        for(Rercord r: list){
            if(r.getName().equals(name)){
                list.remove(r);
                File file = new File(name);
                file.delete();
            }
        }
    }

    /**
     * Преобразование кодировки текста
     * @param text Преобразуемый текст
     * @return Результат преобразования
     */
    public static String distortText(String text){
        String name = "temp";
        writeFile(name, text);
        String result = readFile(name);
        File file = new File(name);
        file.delete();
        return result;
    }

    /**
     * Получение адреса страницы по короткому имени
     * @param name Короткое название
     * @return Строка с адресом или null
     */
    public String getUrlByName(String name){
        for (Rercord r: list){
            if(r.getName().equals(name)){
                return r.getUrl();
            }
        }
        return null;
    }

    /**
     * Чтение файла по названию
     * @param name Название файла
     * @return Строка с содержимым файла
     */
    public static String readFile(String name){
        try {
            FileReader reader = new FileReader(path+name);
            Scanner scanner = new Scanner(reader);
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextLine()) {
                text.append(scanner.nextLine()).append('\n');
            }
            reader.close();
            return text.toString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Запись содержимого страницы в файл
     * @param name Название страницы
     * @param text Текст страницы
     * @return Факт успеха записи
     */
    public static boolean writeFile(String name, String text){
        try {
            FileWriter writer = new FileWriter(path+name);
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public String getListText() {
        StringBuilder text = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        int c=0;
        for(Rercord r: list){
            text.append(++c).append('\t')
                    .append(r.getName()).append('\t')
                    .append(r.getUrl()).append('\t')
                    .append(formatter.format(r.getTime())).append('\n');
        }
        return text.toString().trim();
    }

    public String getNameByNumber(int number){
        try {
            return list.get(number-1).getName();
        }catch (IndexOutOfBoundsException e){
            return null;
        }
    }
}
