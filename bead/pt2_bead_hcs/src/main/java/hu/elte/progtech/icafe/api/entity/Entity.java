/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe.api.entity;

import lombok.ToString;

import java.io.Serializable;

@ToString
public abstract class Entity implements Identifiable<Long>, Serializable {
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (null == object || !(object.getClass().equals(this.getClass()))) {
            return false;
        }

        final Identifiable other = (Identifiable) object;
        return this.getId() != null && this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return null == getId() ? 0 : getId().hashCode();
    }
}
