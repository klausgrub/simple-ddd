package org.samsun.domain;

import org.samsun.util.ThreadLocalContextUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * abstract business entity, should be checked that some properties about creator, updator...
 * , sub class can rename attributes at head of class body, for example: @AttributeOverrides{...}
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年10月20日
 */
@MappedSuperclass
public abstract class AbstractBizEntity<ID> extends AbstractEntity<ID> implements BizEntity<ID> {

    private static final long serialVersionUID = -1282213993854387535L;

    @Column(name = "create_by")
    private String creator;

    @Column(name="update_by")
    private String updator;

    @Column(name="create_date")
    private Date createTime;

    @Column(name="update_date")
    private Date updateTime;

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdater() {
        return updator;
    }

    @Override
    public void setUpdater(String updator) {
        this.updator = updator;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    protected void save() {
        String user = ThreadLocalContextUtils.getUser();
        if(isNew()) {

            setCreateTime(new Date());
            if(getCreator() == null)
                setCreator(user);
        }
        setUpdateTime(new Date());
        if(getUpdater() == null)
            setUpdater(user);
        super.save();
    }
}
