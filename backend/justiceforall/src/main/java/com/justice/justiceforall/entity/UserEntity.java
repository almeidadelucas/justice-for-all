package com.justice.justiceforall.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private long id;

  @Column(name = "first_name", nullable = false, length = 30)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @Column(nullable = false)
  private String password;

  @Column(name = "user_type", nullable = false)
  private int userType;

  @Column(length = 11)
  private String cpf;

  @Column(length = 8)
  private String oab;
}