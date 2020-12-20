package com.Diachenko.WebFloorHelper.database.model;

import com.Diachenko.WebFloorHelper.database.entities.Layout;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public Model(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean add(Layout layout) throws SQLException {
        String sql = "INSERT INTO layouts (name, coordinates) VALUES (?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, layout.getName());
        statement.setString(2, layout.getCoordinates());

        boolean rowInserted = statement.executeUpdate() > 0;

        statement.close();
        disconnect();

        return rowInserted;
    }
    public List<Layout> list() throws SQLException {
        List<Layout> layouts = new ArrayList<Layout>();
        String sql = "SELECT * FROM layouts";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String coordinates = resultSet.getString("coordinates");

            Layout layout = new Layout(name, coordinates);
            layout.setId(id);
            layouts.add(layout);
        }

        resultSet.close();
        statement.close();
        disconnect();
        return layouts;
    }

    public boolean delete(int i) throws SQLException {
        String sql = "DELETE FROM layouts where id = ? ";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, i);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }
    public boolean edit(int id, String name, String coordinates) throws SQLException {
        String sql = "UPDATE layouts SET name = ?, coordinates = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, coordinates);
        statement.setInt(3, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }
    public List<Layout> searchById() throws SQLException {
        List<Layout> layouts = new ArrayList<>();
        String sql = "SELECT * FROM layouts";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String coordinates = resultSet.getString("coordinates");

            Layout layout = new Layout(name, coordinates);
            layout.setId(id);
            layouts.add(layout);
        }


        resultSet.close();
        statement.close();
        disconnect();
        return layouts;
    }
}
