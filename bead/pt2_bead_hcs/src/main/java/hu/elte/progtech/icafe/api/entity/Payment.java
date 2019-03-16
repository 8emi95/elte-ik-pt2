/**
 * Created by Csaba_Hete on 2017.03.17..
 */
package hu.elte.progtech.icafe.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Payment extends EntityWithTimestamps {
    public static final String[] FIELD_NAMES = {
            "Bruttó összeg",
            "Deviza",
            "Befizetés teljesítve",
            "Rögzítés ideje",
            "Utoljára módosítva"
    };

    @Getter @Setter private Long userId;
    @Getter @Setter private Long sessionId;
    @Getter @Setter private Long quantity;
    @Getter @Setter private String unit;
    @Getter @Setter private Double netPrice;
    @Getter @Setter private Integer vatRate;
    @Getter @Setter private Integer discount;
    @Getter @Setter private Double grossAmount;
    @Getter @Setter private String currency;
    @Setter private Boolean fulfilled;

    @Getter @Setter private User user;
    @Getter @Setter private Session session;

    public Boolean isFulfilled() {
        return fulfilled;
    }
}
