package org.javadominicano.workshop.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode(of = "userId", callSuper = false)
@Table(name = "userr", indexes = {@Index(columnList = "username"), @Index(columnList = "name"), @Index(columnList = "email"), @Index(columnList = "role")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(length = 30, unique = true, updatable = false)
    @Length(message = "This field can not be blank.", min = 1, max = 30)
    private String username;

    @NotNull
    @JsonIgnore
    @Column(length = 250)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    @Column(length = 50)
    @Length(message = "This field can not be blank.", min = 1, max = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;

    private boolean locked;
    private boolean oneLogPwd;

    private Instant lastLoginTs;

}
