package tdd.ytetdd.lastword.entity;

import lombok.*;
import tdd.ytetdd.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "note", callSuper = false)
//@Entity
//@Table(name = "NOTE")
public class Note extends AbstractEntity {

//    @Column(name = "NOTE")
    private String note;

}
