package com.justice.justiceforall.entity.casesentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Table(name = "cases")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@With
public class CaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "case_id")
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "lawyer_id")
  private Long lawyerId;

  @Column(nullable = false, length = 30)
  private String title;

  @Column(nullable = false, length = 100)
  private String category;

  @Column(nullable = false, length = 500)
  private String description;

  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING)
  private Alegation alegation;
  
  @Column(name = "evidences_pdf", length = 120)
  private String evidencesPdf;

  @Column(name = "evidence_image", length = 120)
  private String evidenceImage;

  @Column(name = "case_identifier", nullable = false, length = 120)
  private String caseIdentifier;

  @Column(nullable = false)
  private boolean open;

}
