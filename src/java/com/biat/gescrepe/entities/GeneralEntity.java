package com.biat.gescrepe.entities;

import java.io.Serializable;
import javax.persistence.Version;

/**
 *
 * @author MENSAH Y.O.D.
 */

public abstract class GeneralEntity implements Serializable {

    @Version
    protected Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}