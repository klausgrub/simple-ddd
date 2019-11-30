package org.samsun.domain;

import java.util.Date;

/**
 * interface of business entity has creator, updater information
 * @author sunmin
 * @param <ID>
 */
public interface BizEntity<ID> extends Entity<ID> {

    String getCreator();

    void setCreator(String creator);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    String getUpdater();

    void setUpdater(String updator);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);
}
