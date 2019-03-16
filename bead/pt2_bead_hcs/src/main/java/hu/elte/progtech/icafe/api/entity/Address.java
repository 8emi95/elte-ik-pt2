/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Address extends EntityWithTimestamps {
    @Getter @Setter private Long userId;
    @Getter @Setter private String country;
    @Getter @Setter private String city;
    @Getter @Setter private String zip;
    @Getter @Setter private String streetName;
    @Getter @Setter private String streetSuffix;
    @Getter @Setter private String houseNumber;

    @Getter @Setter private User user;
}
