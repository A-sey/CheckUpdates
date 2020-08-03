package com.company.check;

import java.time.LocalDateTime;

public class Rercord {
    private String name;
    private String url;
    private LocalDateTime time;

    public Rercord(String name, String url, LocalDateTime time) {
        this.name = name;
        this.url = url;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
