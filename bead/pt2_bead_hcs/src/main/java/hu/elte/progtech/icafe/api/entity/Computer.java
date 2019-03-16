/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Computer extends EntityWithTimestamps {
    public static final String[] FIELD_NAMES = {
            "Név",
            "CPU",
            "Alaplap",
            "RAM",
            "VGA",
            "HDD",
            "OS",
            "Foglalt",
            "Rögzítés ideje",
            "Utoljára módosítva"
    };

    @Getter @Setter private String name;
    @Getter @Setter private String cpu;
    @Getter @Setter private String motherboard;
    @Getter @Setter private String memory;
    @Getter @Setter private String vga;
    @Getter @Setter private String massStorage;
    @Getter @Setter private String os;
    @Setter private Boolean busy;

    @Getter @Setter private Session activeSession;

    public Boolean isBusy() {
        return busy;
    }
}
