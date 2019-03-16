/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe.api.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public abstract class EntityWithTimestamps extends Entity {
    @Getter @Setter protected Timestamp createdAt;
    @Getter @Setter protected Timestamp updatedAt;
}
