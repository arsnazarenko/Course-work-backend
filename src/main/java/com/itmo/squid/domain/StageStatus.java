package com.itmo.squid.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@NoArgsConstructor
@Entity
@Table(name = "stage_status")
@Data
@EqualsAndHashCode(of = {"id"})
public class StageStatus {

    @Id
    @GeneratedValue(generator = "stage_status_id_seq")
    Long id;

    @Column
    String status;
}
