package org.example.jdbc.repository;

import lombok.SneakyThrows;
import org.example.jdbc.config.DatabaseConnection;
import org.example.jdbc.model.Skill;
import org.example.jdbc.repository.interfaces.CrudRepository;
import org.example.jdbc.utils.PropertiesLoader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepositoryImpl implements CrudRepository<Skill, Long> {

    private final Connection CONNECTION = DatabaseConnection.getInstance().getConnection();
    private final String SCHEMA_NAME = PropertiesLoader.getProperties("db.schemaName");

    @SneakyThrows
    @Override
    public List<Skill> findAll() {
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM skills");
        final List<Skill> skillArrayList = new ArrayList<>();
        while (resultSet.next()) {final Skill skill = Skill.builder()
                    .id(resultSet.getLong("id"))
                    .activities(resultSet.getString("activities"))
                    .level(resultSet.getString("level"))
                    .build();
            skillArrayList.add(skill);
        }
        return skillArrayList;
    }

    @SneakyThrows
    @Override
    public Optional<Skill> findById(Long id) {
        Skill skill = new Skill();
        ResultSet resultSet = CONNECTION.createStatement().executeQuery("SELECT * FROM skills WHERE id=" + id + ";");
        while (resultSet.next()) {skill = Skill.builder()
                    .id(resultSet.getLong("id"))
                    .activities(resultSet.getString("activities"))
                    .level(resultSet.getString("level"))
                    .build();
        }
        return Optional.ofNullable(skill);
    }

    @SneakyThrows
    @Override
    public Skill create(Skill skill) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO " + SCHEMA_NAME + ".skills (activities,level) VALUES (?,?)");
        preparedStatement.setString(1, skill.getActivities());
        preparedStatement.setString(2, skill.getLevel());
        preparedStatement.executeUpdate();
        return skill;
    }

    @SneakyThrows
    @Override
    public Skill update(Long id, Skill skill) {
        PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE " + SCHEMA_NAME + ".skills set activities=?,level=? WHERE id=" + id + ";");
        preparedStatement.setString(1, skill.getActivities());
        preparedStatement.setString(2, skill.getLevel());
        preparedStatement.execute();
        return skill;
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        CONNECTION.createStatement().execute("DELETE FROM " + SCHEMA_NAME + ".skills WHERE id=" + id);
    }
}
