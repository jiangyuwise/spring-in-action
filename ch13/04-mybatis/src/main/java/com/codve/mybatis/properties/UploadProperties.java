package com.codve.mybatis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author admin
 * @date 2019/11/25 16:45
 */
@ConfigurationProperties(prefix = "file.upload")
public class UploadProperties {

    private String location = "./upload";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
