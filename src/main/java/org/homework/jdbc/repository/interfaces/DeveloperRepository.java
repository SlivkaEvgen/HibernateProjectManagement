package org.homework.jdbc.repository.interfaces;

import org.homework.jdbc.model.Developer;

import java.util.List;

public interface DeveloperRepository extends CrudRepository<Developer, Long> {

    String getSumSalariesDevelopersOfOneProject(Long projectId);

    List<Developer> getDevelopersFromOneProject(Long projectId);

    List<Developer> getDevelopersByActivity(String nameActivity);

    List<Developer> getDevelopersByLevel(String nameLevel);
}
