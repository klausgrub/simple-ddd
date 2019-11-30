package org.samsun.domain;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 *
 */
public interface Entity<ID> extends Persistable ,Serializable{

    void setId(ID id);
}
