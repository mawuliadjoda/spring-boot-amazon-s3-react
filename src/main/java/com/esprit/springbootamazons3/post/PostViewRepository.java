package com.esprit.springbootamazons3.post;


import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

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
            double userDistanceZero) {


        StringBuilder sql = new StringBuilder(" CREATE OR REPLACE VIEW ").append(buildViewName(userTel));
        sql.append(" AS SELECT p.*, ")
                .append(" abs(p.distance_zero - " + userDistanceZero +") as distance_from ")
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

    // https://java-online-training.de/?p=576
    public List<Pair<String, Double>> findNearByPost(String userTel, double userDistanceZero, Integer limit) {

        String sql = """
                SELECT post_id, distance_from FROM
                	(
                	   SELECT p.*,
                       abs(p.distance_zero - :userDistanceZero) as distance_from
                	   FROM  POST p
                		
                	) customPost  order by distance_from asc limit(:limit)
                """;

        RowMapper<Pair<String, Double>> mapper = (ResultSet rs, int rowNum) ->
                new Pair<String, Double>(
                        rs.getString("post_id"),
                        rs.getDouble("distance_from")
                );

        List<Pair<String, Double>> pairs = jdbcClient.sql(sql)
                .param("userDistanceZero", userDistanceZero, Types.DOUBLE)
                .param("limit", limit, Types.INTEGER)
                .query(mapper).list();

        return pairs;
    }
}
