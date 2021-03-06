package org.homework.hibernate.service.hw4;

import org.hibernate.Session;
import org.homework.hibernate.model.Company;
import org.homework.hibernate.model.Developer;
import org.homework.hibernate.model.Project;
import org.homework.hibernate.model.Skill;
import org.homework.hibernate.repository.DeveloperCrudRepositoryImpl;
import org.homework.hibernate.service.hw4.interfaces.CompanyService;
import org.homework.hibernate.service.hw4.interfaces.DeveloperService;
import org.homework.hibernate.utils.SessionsOpenClose;

import java.util.List;
import java.util.Optional;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperCrudRepositoryImpl CRUD_REPOSITORY = new DeveloperCrudRepositoryImpl(Developer.class);
    private final SessionsOpenClose sessionsOpenClose = SessionsOpenClose.getInstance();
SkillServiceImpl skillService = new SkillServiceImpl();
CompanyServiceImpl companyService = new CompanyServiceImpl();
ProjectServiceImpl projectService = new ProjectServiceImpl();

    @Override
    public Optional<Developer> getById(Long id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<Developer> getAll() {
        return CRUD_REPOSITORY.findAll();
    }

    @Override
    public Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary,Long companyId,Long projectId,Long skillId) {
        Company company = companyService.getById(companyId).get();
        Project project = projectService.getById(projectId).get();
        Skill skill = skillService.getById(skillId).get();
        Developer developer = Developer.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .email(email)
                .salary(salary)
//                .skill(skill)
                .company(company)
//                .project(project)
                .build();
        return CRUD_REPOSITORY.save(developer);
    }

    @Override
    public void update(Long id, String name, Long age, String gender, String email, Long salary) {
        Session session = sessionsOpenClose.createSession();
        session.getTransaction();
        Developer developer = session.get(Developer.class, id);
        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setEmail(email);
        developer.setSalary(salary);
//        developer.setCompany_id(companyId);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY.deleteById(id);
    }

    @Override
    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
        return CRUD_REPOSITORY.getSumSalariesDevelopersOfOneProject(projectId);
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        return CRUD_REPOSITORY.getDevelopersFromOneProject(projectId);
    }

    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        return CRUD_REPOSITORY.getDevelopersByActivity(nameActivity);
    }

    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        return CRUD_REPOSITORY.getDevelopersByLevel(nameLevel);
    }
}
