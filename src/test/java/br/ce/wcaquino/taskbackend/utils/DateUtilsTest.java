package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDatasFuturas() {
        LocalDate dataFutura = LocalDate.of(2030, Month.DECEMBER, 30);
        assertTrue(DateUtils.isEqualOrFutureDate(dataFutura));
    }

    @Test
    public void deveRetornarTrueParaDataPresente() {
        LocalDate dataPresente = LocalDate.now();
        assertTrue(DateUtils.isEqualOrFutureDate(dataPresente));
    }

    @Test
    public void deveRetornarFalseParaDatasPassada() {
        LocalDate dataPassada = LocalDate.of(2010, Month.DECEMBER, 30);
        assertFalse(DateUtils.isEqualOrFutureDate(dataPassada));
    }
}