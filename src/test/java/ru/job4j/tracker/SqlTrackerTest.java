package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
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

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenReplaceItemThenItemIsUpdated() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        Item newItem = new Item("new_item");
        tracker.replace(item.getId(), newItem);
        Item replacedItem = tracker.findById(item.getId());
        assertThat(replacedItem.getName()).isEqualTo(newItem.getName());
        assertThat(replacedItem.getCreated()).isEqualTo(newItem.getCreated());
    }

    @Test
    public void whenDeleteItemThenItemIsDeleted() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenFindAllThenReturnAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findAll()).containsExactlyInAnyOrder(item1, item2);
    }

    @Test
    public void whenFindByNameThenReturnMatchingItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item");
        Item item2 = new Item("item");
        Item item3 = new Item("another_item");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findByName("item")).containsExactlyInAnyOrder(item1, item2);
    }

    @Test
    public void whenFindByIdThenReturnCorrectItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    public void whenItemWasReplacedSuccessfully() {
        Output output = new StubOutput();
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Replace replaceAction = new Replace(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(item.getId());
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        replaceAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Редактирование заявки ===" + ln
                        + "Заявка изменена успешно." + ln
        );
    }

    @Test
    public void whenItemWasCreatedSuccessfully() {
        Output output = new StubOutput();
        SqlTracker tracker = new SqlTracker(connection);
        String itemName = "New item";
        Create createAction = new Create(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(itemName);

        createAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Создание новой заявки ===" + ln
                        + "Добавленная заявка: " + tracker.findAll().get(0) + ln
        );
    }

    @Test
    public void whenItemWasDeletedSuccessfully() {
        Output output = new StubOutput();
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("Item to delete"));
        Delete deleteAction = new Delete(output);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());

        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Удаление заявки ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }

    @Test
    public void whenItemWasFindByIdSuccessfully() {
        Output output = new StubOutput();
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("New item"));
        FindById findByIdAction = new FindById(output);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        when(input.askStr(any(String.class))).thenReturn(item.getName());

        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Поиск заявки по ID ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasFindByNameSuccessfully() {
        Output output = new StubOutput();
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("New item"));
        FindByName findByNameAction = new FindByName(output);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        when(input.askStr(any(String.class))).thenReturn(item.getName());

        findByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Поиск заявок по имени ===" + ln
                        + item + ln
        );
    }
}