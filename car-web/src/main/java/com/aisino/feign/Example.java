package com.aisino.feign;


import feign.*;

public final class Example {

    static class Contributor {
        String login;
        int contributions;
    }

    public interface GitHub {
        // RequestLine注解声明请求方法和请求地址,可以允许有查询参数
        /*@RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);*/


        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        String contributors(@Param("owner") String owner, @Param("repo") String repo);
    }

    public static void main(String... args) {
        GitHub github = Feign.builder()
                //.decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        String contributors = github.contributors(null, "feign");
        System.err.println(contributors);
        /*for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }*/
    }
}