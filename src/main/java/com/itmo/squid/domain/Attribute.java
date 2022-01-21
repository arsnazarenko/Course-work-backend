package com.itmo.squid.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "attribute")
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Attribute {
    @Id
    private Long id;
    @Column
    private String name;

    @Column
    @ManyToMany(mappedBy = "attributes", fetch = FetchType.LAZY)
    private List<Stage> stages;
}
