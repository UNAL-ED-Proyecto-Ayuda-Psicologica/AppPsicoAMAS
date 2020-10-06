package PsicObj;

import java.util.Date;

public class PsicoDate {
    Psico psicologist;
    NoPsico pacient;
    Date date;

    public PsicoDate(Psico psicologist, NoPsico pacient, Date date) {
        this.psicologist = psicologist;
        this.pacient = pacient;
        this.date = date;
    }
}
