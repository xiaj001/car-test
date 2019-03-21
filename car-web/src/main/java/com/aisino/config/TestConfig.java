package com.aisino.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiajun003
 * @Date: 2019/1/3 17:57
 * @Description:
 */
@Data
@ConfigurationProperties("com.test")
@Configuration
public class TestConfig {
    private String name;

    private List<Job> jobs   = new ArrayList<>();

    @Data
    public static class Job{
        private String jobId;
        private String jobName;
        private List<Part> parts  = new ArrayList<>();
    }

    @Data
    public static class Part{
        private String partId;
        private Map<String,String> partMap   = new LinkedHashMap<>();;
    }
}
