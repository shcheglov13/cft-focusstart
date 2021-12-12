package ru.cft.focusstart.shcheglov.task3.db;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;
import ru.cft.focusstart.shcheglov.task3.utils.Constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class Database {
    private static final String BEST_RECORD_QUERY =
            "SELECT * FROM records WHERE difficult_id = ? ORDER BY play_time LIMIT 1";
    private static final String INSERT_RECORD_QUERY =
            "INSERT INTO RECORDS(name, play_time, difficult_id) VALUES(?,?,?)";

    private Database() {
    }

    public static void insertRecordIntoDatabase(String name, Time time, int difficultId) {
        try (Connection connection = getDatabaseConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_RECORD_QUERY, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setTime(2, time);
            ps.setInt(3, difficultId);
            ps.executeUpdate();

            log.info("В базу данных внесена запись: name - {}, time - {}, difficultId - {}", name, time, difficultId);
        } catch (SQLException e) {
            log.error("Произошла ошибка базы данных: " + e.getLocalizedMessage());
        } catch (IOException e) {
            log.error("Произошла ошибка чтения property файла: " + e.getLocalizedMessage());
        }
    }

    public static Optional<String[]> getBestRecord(int difficultId) {
        try (Connection connection = getDatabaseConnection()) {
            PreparedStatement ps = connection.prepareStatement(BEST_RECORD_QUERY);
            ps.setInt(1, difficultId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                log.info("Запрос информации из базы данных: difficultId - {}", difficultId);
                return Optional.of(new String[]{rs.getString("name"), rs.getTime("play_time").toString()});
            }
        } catch (SQLException e) {
            log.error("Произошла ошибка базы данных: " + e.getLocalizedMessage());
        } catch (IOException e) {
            log.error("Произошла ошибка чтения property файла: " + e.getLocalizedMessage());
        }

        log.info("Информация не найдена в базе данных для: difficultId - {}", difficultId);
        return Optional.empty();
    }


    private static Connection getDatabaseConnection() throws SQLException, IOException {
        log.info("Подключение к базе данных");

        Properties properties = new Properties();

        if (Constants.PROPERTY_FILE_URL != null) {
            properties.load(new FileReader(Constants.PROPERTY_FILE_URL.getFile(), StandardCharsets.UTF_8));
        } else {
            throw new FileNotFoundException("Файл с конфигурацией базы данных не найден");
        }

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(properties.getProperty("datasource.url"));
        dataSource.setUser(properties.getProperty("datasource.username"));
        dataSource.setPassword(properties.getProperty("datasource.password"));

        return dataSource.getConnection();
    }
}
