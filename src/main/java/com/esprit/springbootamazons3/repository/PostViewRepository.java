package com.esprit.springbootamazons3.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.sql.Types;

@Component
public class PostViewRepository {

    private JdbcClient jdbcClient;

    @Autowired
    public PostViewRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public void dropView() {
        String sql = "DROP VIEW PharmacyView";
        jdbcClient.sql(sql).update();
    }

    public void createPostView(
            String userTel,
            double userDistanceZero,
            double lat,
            double lng) {


        StringBuilder sql = new StringBuilder(" CREATE OR REPLACE VIEW ").append(buildViewName(userTel));
        sql.append(" AS SELECT p.*, ")
                .append(" (p.distance_zero - " + userDistanceZero +") as distance_from ")
                .append(" FROM  POST p");

        jdbcClient.sql(sql.toString()).update();
    }

    public void deletePostView(String userTel) {
        String sql = new StringBuilder(" DROP VIEW ").append(buildViewName(userTel)).toString();
        jdbcClient.sql(sql).update();
    }

    private String buildViewName(String userTel) {
        return new StringBuilder(" POST_VIEW").append(userTel).toString();
    }
}
