package com.justice.justiceforall.entity.casesentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cases")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class CaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "case_id")
  private Long id;

  @Column(name = "case_title", nullable = false, length = 30)
  private String title;

  @Column(name = "case_category", nullable = false, length = 100)
  private String category;

  @Column(nullable = false, length = 500)
  private String description;

  @Column(name = "alegation", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Alegation alegation;
  
  @Column(nullable = false, length = 120)
  private String evidencesPdf;

  @Column(nullable = false, length = 120)
  private String evidenceImage;

}
