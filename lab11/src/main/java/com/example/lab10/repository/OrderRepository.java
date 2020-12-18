package com.example.lab10.repository;

import com.example.lab10.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long save(Order order)   {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "INSERT INTO orders VALUES(NULL, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {"id"});
            ps.setDouble(1, order.getTotalPrice());
            ps.setString(2, order.getStatus().toString());
            return ps;
        }, keyHolder);

        return (long) keyHolder.getKey().longValue();
    }
}
