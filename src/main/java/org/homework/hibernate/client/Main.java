package org.homework.hibernate.client;

import org.hibernate.Session;
import org.homework.hibernate.model.Developer;
import org.homework.hibernate.model.Skill;
import org.homework.hibernate.service.hw4.DeveloperServiceImpl;
import org.homework.hibernate.service.hw4.SkillServiceImpl;
import org.homework.hibernate.utils.HibernateSessionFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        AddData addData = new AddData();
//        addData.addData();
        //      new CommandImpl().start();
//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//        session.getTransaction();
//
//        DeveloperServiceImpl developerService = new DeveloperServiceImpl();
//        SkillServiceImpl skillService = new SkillServiceImpl();
//        Set<Skill> skillSet = new HashSet<>();
//        Skill skill = skillService.getById(1L).get();
//        skillSet.add(skill);
//        Developer developer = Developer.builder()
//                .name("Fase")
//                .gender("Male")
//                .email("deee@ujlk")
//                .age(34L)
//                .salary(3500L)
//                .skills(skillSet)
//                .build();
//        session.save(developer);
//      //  session.getTransaction().commit();
//        session.close();

        Session session1 = HibernateSessionFactory.getSessionFactory().openSession();
        session1.getTransaction();

        DeveloperServiceImpl developerService1 = new DeveloperServiceImpl();
        Developer developer1 = developerService1.getById(3L).get();
        System.out.println(developer1);
//        Skill skill1 = developer1.getSkill();
        Set<Skill> skills = developer1.getSkills();
//        System.out.println(skill1);
        System.out.println(skills);


        session1.close();
    }
}