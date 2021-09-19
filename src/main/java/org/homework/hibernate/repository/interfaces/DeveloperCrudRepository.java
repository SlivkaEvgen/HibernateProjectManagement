package org.homework.hibernate.repository.interfaces;

import org.homework.hibernate.model.Developer;

import java.util.List;

public interface DeveloperCrudRepository extends CrudRepository<Developer,Long>{

    Long getSumSalariesDevelopersOfOneProject(Long projectId);

    List<Developer> getDevelopersFromOneProject(Long projectId);

    List<Developer> getDevelopersByActivity(String nameActivity);

    List<Developer> getDevelopersByLevel(String nameLevel);
}
