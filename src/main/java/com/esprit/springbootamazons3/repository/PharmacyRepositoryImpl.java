package com.esprit.springbootamazons3.repository;

import com.esprit.springbootamazons3.record.PharmacyRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.sql.Types;

@Component
public class PharmacyRepositoryImpl {
    @Autowired
    private JdbcClient jdbcClient;


    public void createNew(PharmacyRecord pharmacy) {
        String sql = "INSERT INTO Pharmacy (id, name, address)"
                + "VALUES (:id, :name, :address)";
        jdbcClient.sql(sql)
                .param("id", pharmacy.id(), Types.BIGINT)
                .param("name", pharmacy.name(), Types.VARCHAR)
                .param("address", pharmacy.address(), Types.VARCHAR)
                .update();
    }

    public void createView() {
        // String sql = "CREATE OR REPLACE VIEW PharmacyView AS SELECT * FROM Pharmacy";
        dropView();
        String sql = "CREATE OR REPLACE VIEW PharmacyView AS SELECT id, lat, lng, (lat -10) as custom FROM Pharmacy";
        jdbcClient.sql(sql).update();
    }

    public void dropView() {
        String sql = "DROP VIEW PharmacyView";
        jdbcClient.sql(sql).update();
    }
}
