/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
public class Session extends Entity {
    @Getter @Setter private Long userId;
    @Getter @Setter private Long computerId;
    @Getter @Setter private Timestamp startTime;
    @Getter @Setter private Timestamp endTime;

    @Getter @Setter private User user;
    @Getter @Setter private Computer computer;
}
