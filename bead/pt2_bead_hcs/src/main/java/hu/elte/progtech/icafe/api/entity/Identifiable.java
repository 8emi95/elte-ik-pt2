package hu.elte.progtech.icafe.api.entity;

/**
 * Created by Csaba_Hete on 2017.03.17..
 */
public interface Identifiable<T extends Number> {
    T getId();

    void setId(T id);
}
