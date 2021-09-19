package org.homework.hibernate.repository;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.homework.hibernate.model.Developer;
import org.homework.hibernate.model.Project;
import org.homework.hibernate.model.Skill;
import org.homework.hibernate.repository.interfaces.DeveloperCrudRepository;
import org.homework.hibernate.utils.HibernateSessionFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeveloperCrudRepositoryImpl extends CrudRepositoryHibernateImpl<Developer, Long> implements DeveloperCrudRepository {

    public DeveloperCrudRepositoryImpl(Class<Developer> modelClass) {
        super(modelClass);
    }

    @SneakyThrows
    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Project project = session.get(Project.class, projectId);
        Set<Developer> developers = project.getDevelopers();
        System.out.println(project);
        List<Developer> developerList = new ArrayList<>(developers);
        System.out.println(developerList);
        long totalSalaries = 0L;
        for (Developer developer : developerList) {
            Long salary = developer.getSalary();
            totalSalaries = totalSalaries + salary;
        }
        session.getTransaction().commit();
        session.close();
        return totalSalaries;
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Project project = session.get(Project.class, projectId);
        Set<Developer> developers = project.getDevelopers();
        List<Developer> developerList = new ArrayList<>(developers);
//        List<Developer> developerList = developers.stream().collect(Collectors.toList());
        session.getTransaction().commit();
        session.close();
        return developerList;
    }

    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Developer> developerList = loadAllData(Developer.class, session);
        List<Developer> developers = new ArrayList<>();
        for (Developer developer : developerList) {
            List<Skill> skillsList = new ArrayList<>(developer.getSkills());
            for (Skill skill : skillsList) {
                String activities = skill.getActivities();
                if (activities.equalsIgnoreCase(nameActivity)) {
                    developers.add(developer);
                }
            }
        }
        session.getTransaction().commit();
        session.close();
        return developers;
    }

    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Developer> developerList = loadAllData(Developer.class, session);
        List<Developer> developers = new ArrayList<>();
        for (Developer developer : developerList) {
            List<Skill> skillsList = new ArrayList<>(developer.getSkills());
            for (Skill skill : skillsList) {
                String level = skill.getLevel();
                if (level.equalsIgnoreCase(nameLevel)) {
                    developers.add(developer);
                }
            }
        }
        session.getTransaction().commit();
        session.close();
        return developers;
    }

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return session.createQuery(criteria).getResultList();
    }
}
