package com.hyun6ik.batchstudy.core.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
@Table(name = "result_text")
@NoArgsConstructor
public class ResultText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @Builder
    public ResultText(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public static ResultText of(Integer id, String text) {
        return new ResultText(id, text);
    }
}
