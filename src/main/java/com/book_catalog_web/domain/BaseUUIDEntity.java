package com.book_catalog_web.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseUUIDEntity extends BaseEntity{
    @Column(name = "secure_id", nullable = false, unique = true)
    private UUID secureId;

    @Override
    public void prePersist(){
        setIsDeleted(false);
        if (this.secureId == null) secureId = UUID.randomUUID();
    }
}
