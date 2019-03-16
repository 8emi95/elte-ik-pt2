/**
 * Created by Csaba_Hete on 2017.03.18..
 */
package hu.elte.progtech.icafe.backend.controller;

import lombok.Getter;

public enum Currency {
    HUF("Forint", "Ft"), EUR("Euró", "€"), USD("Dollár", "$");

    @Getter
    private String label;
    @Getter
    private String symbol;

    Currency(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }
}
