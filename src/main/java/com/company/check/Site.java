package com.company.check;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class Site {
    public static String get(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);

        Scanner scanner = new Scanner(response.getEntity().getContent(), "CP1251");
//        Scanner scanner = new Scanner(response.getEntity().getContent(), "UTF-8");
        StringBuffer buffer = new StringBuffer();
        while (scanner.hasNextLine()){
            buffer.append(scanner.nextLine()).append('\n');
        }
        return buffer.toString();
    }
}
