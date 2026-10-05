package com.book_catalog_web.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Column(name = "is_deleted", columnDefinition = "boolean default false not null")
    private Boolean isDeleted;

    @PrePersist
    public void prePersist(){
        this.isDeleted = false;
    }
}
