package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(input);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
              config.getProperty("url"),
              config.getProperty("username"),
              config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        String sql = "INSERT INTO items(name, created) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        String sql = "UPDATE items SET name = ?, created = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return findById(id) != null;
    }

    @Override
    public void delete(int id) {
        if (findById(id) == null) {
            throw new IllegalArgumentException("Item with id = " + id + "not found");
        }
        String sql = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        if (findById(id) != null) {
            throw new IllegalArgumentException("Item with id = " + id + " was not deleted successfully");
        }
    }

    @Override
    public List<Item> findAll() {
        String sql = "SELECT * FROM items";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Item> items = new ArrayList<>();
            while (resultSet.next()) {
                items.add(createItem(resultSet));
            }
            return items;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Item> findByName(String key) {
        String sql = "SELECT * FROM items WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Item> items = new ArrayList<>();
                while (resultSet.next()) {
                    items.add(createItem(resultSet));
                }
                return items;
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item findById(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createItem(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    private Item createItem(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Timestamp timestamp = resultSet.getTimestamp("created");
        LocalDateTime created = timestamp.toLocalDateTime();
        return new Item(id, name, created);
    }

}
