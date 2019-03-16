/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User extends EntityWithTimestamps {
    public static final String[] FIELD_NAMES = {
            "Név",
            "Szem. ig. szám",
            "Felhasználónév",
            "Hűségpontok",
            "Bejelentkezve",
            "Rögzítés ideje",
            "Utoljára módosítva"
    };

    @Getter @Setter private String name;
    @Getter @Setter private String idNumber;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private Integer points;
    @Setter private Boolean present;

    @Getter @Setter private Address address;
    @Getter @Setter private Session activeSession;
    @Getter @Setter private Payment pendingPayment;

    public Boolean isPresent() {
        return present;
    }
}
