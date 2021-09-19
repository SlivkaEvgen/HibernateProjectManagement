package org.homework.hibernate.repository;

import org.hibernate.Session;
import org.homework.hibernate.model.Project;
import org.homework.hibernate.repository.interfaces.ProjectCrudRepository;
import org.homework.hibernate.utils.HibernateSessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class ProjectCrudRepositoryImpl extends CrudRepositoryHibernateImpl<Project,Long> implements ProjectCrudRepository {

    public ProjectCrudRepositoryImpl(Class<Project> modelClass) {
        super(modelClass);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        int size=0;
        String projectName="";
        String firstDate="";
        List<Project> projectList = loadAllData(Project.class, session);
        List<String> projects = new ArrayList<>();
        for (int i = 0; i <projectList.size() ; i++) {
            Project project = projectList.get(i);
             size = project.getDevelopers().size();
             projectName = project.getName();
             firstDate = project.getFirstDate();
            projects.add(projectName+" - "+size+" developers; "+firstDate);
        }
        session.getTransaction().commit();
        session.close();
        return projects;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
