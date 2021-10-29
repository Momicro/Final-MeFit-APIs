package com.experis.de.MeFit.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@DynamicUpdate
@Table(name = "Login")
@Schema(description = "User Model")
public @Data class User {

    //autogenerated ID which never has to be defined in future functions.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Here the static components of the model
    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    //goal - users - relation
    @OneToMany(mappedBy = "user")
    private Set<Goal> goals = new HashSet<>();

    @JsonGetter("activeGoal")
    public Goal goalGetter() {
        if(goals != null){
            var goals = this.goals.stream().filter(g -> g.isArchived() == false).collect(Collectors.toList());
            if ((goals != null) && (goals.size() > 0))
                return goals.get(0);
        }
        return null;
    }

    /*
    @Column(name="password",
            nullable = false,
            columnDefinition = "TEXT")
    private String password;

    //related data
    //user - role - relation
    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Role> roles = new HashSet<>();

    // restricts the output to a List of IDs of the movies
    @JsonGetter("roles")
    public List<String> roles() {
        if(roles != null) {
            return roles.stream()
                    .map(role -> {
                        return "/api/v1/roles/" + role.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }
    */

}