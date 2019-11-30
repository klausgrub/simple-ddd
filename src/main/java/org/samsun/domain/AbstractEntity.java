package org.samsun.domain;

import org.samsun.repository.RepositoryTemplateFactory;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * abstract entity object which properties like creator, updator is not needed
 * @param <ID>
 */
@MappedSuperclass
public abstract class AbstractEntity<ID> implements Entity<ID>{

    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


    @Override
    public boolean isNew() {
        return null==getId();
    }

    /**
     * save method, if it's new object and call save method of repository else call merge method
     */
    protected void save() {
        if(isNew()) {
            RepositoryTemplateFactory.getRepositoryTemplate().save(this);
        }
        else
            RepositoryTemplateFactory.getRepositoryTemplate().merge(this);
    }

    protected void delete() {

        if(this instanceof VirtualDelete){
            ((VirtualDelete) this).setEntityState(EntityState.DELETED);
            save();
        }
        else
            RepositoryTemplateFactory.getRepositoryTemplate().delete(this);
    }
}
