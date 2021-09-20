package org.homework.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"projects", "skills","company"})
@ToString(exclude = {"projects","skills","company"})
@Entity
@Table(name = "developers")
public class Developer implements BaseModel<Long> {

    private static final long serialVersionUID = 1111974651988374656L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "age", length = 3)
    private Long age;

    @Column(name = "gender", length = 30)
    private String gender;

    @Column(name = "email", length = 30, unique = true)
    private String email;

    @Column(name = "salary", length = 10)
    private Long salary;

    @ManyToOne
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    private Company company;

//    @ManyToOne
//    @JoinColumn(name = "skill_id",insertable = false,updatable = false)
//    private Skill skill;
//
//    @ManyToOne
//    @JoinColumn(name = "project_id",insertable = false,updatable = false)
//    private Project project;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private Set<Skill> skills;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_projects",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projects;
}
