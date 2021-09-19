package org.homework.hibernate.repository.interfaces;

import org.homework.hibernate.model.Project;

import java.util.List;

public interface ProjectCrudRepository extends CrudRepository<Project,Long>{

    List<String> getListProjectsWithDate();
}
