package com.hirundo.libs.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccessOldDbBirdRecordDataLoaderTest {

    AccessOldDbBirdRecordDataLoader dataLoader;

    @BeforeEach
    void setUp() {
    var filename = "src/test/resources/access_example_old_db.mdb";
    var tableName = "Tab_Ring_Podab";
        dataLoader = new AccessOldDbBirdRecordDataLoader(filename, tableName);
    }

    @Test
    void loadData() {
        var data = dataLoader.loadData();

        assertEquals(2, data.size());
        var result = data.get(0);

        Assertions.assertEquals(1, result.IDR_Podab);
        Assertions.assertEquals(128, result.IdrZesz);
        Assertions.assertEquals(1, result.IDR_Podab);
        Assertions.assertEquals(128, result.IdrZesz);
        Assertions.assertEquals((short) 2007, result.IdBase);
        Assertions.assertEquals(5, result.IdInp);
        Assertions.assertEquals("BU", result.Sta);
        Assertions.assertEquals(129, result.Row);
        Assertions.assertEquals("BK", result.ST);
        Assertions.assertEquals("PL.BK.01", result.CoStaSit);
        Assertions.assertEquals((short) 1967, result.Year);
        Assertions.assertEquals("A", result.Seas);
        Assertions.assertEquals(LocalDateTime.of(1967, 8, 16, 0, 0, 0), result.Date);
        Assertions.assertEquals((short) 18, result.Hour);
        Assertions.assertEquals("J634038", result.Ring);
        Assertions.assertEquals("O", result.Status);
        Assertions.assertEquals("PHY.LUS", result.Spec);
        Assertions.assertEquals("M", result.Sex);
        Assertions.assertEquals("I", result.Age);
        Assertions.assertEquals(null, result.Ringer);
        Assertions.assertEquals(9, result.Mass);
        Assertions.assertEquals(0, result.Fat);
        Assertions.assertEquals(61, result.Wing);
        Assertions.assertEquals(45, result.Tail);
        Assertions.assertEquals(5, result.D2);
        Assertions.assertEquals(0, result.D3);
        Assertions.assertEquals(0, result.D4);
        Assertions.assertEquals(2, result.D5);
        Assertions.assertEquals(6, result.D6);
        Assertions.assertEquals(8, result.D7);
        Assertions.assertEquals(10, result.D8);
        Assertions.assertEquals("1", result.PP);
        Assertions.assertEquals("2", result.P1);
        Assertions.assertEquals("3", result.P2);
        Assertions.assertEquals("4", result.P3);
        Assertions.assertEquals("5", result.P4);
        Assertions.assertEquals("+4", result.P5);
        Assertions.assertEquals("6", result.P6);
        Assertions.assertEquals("7", result.Notes);
        Assertions.assertEquals((byte) 1, result.OK);
        Assertions.assertEquals(null, result.TS_Import);
        Assertions.assertEquals(null, result.TS_Zesz);
        Assertions.assertEquals(true, result.Sel);
        Assertions.assertEquals((short) 816, result.MD);
        Assertions.assertEquals(null, result.RNG);
        Assertions.assertEquals("1", result.AgeT);
        Assertions.assertEquals(LocalDateTime.of(1967, 8, 16, 18, 0, 0), result.DT);
        Assertions.assertEquals("L", result.Ztyp);
        Assertions.assertEquals(1, result.Znr);
        Assertions.assertEquals(1, result.P1PC);
        Assertions.assertEquals(2, result.P2PC);
        Assertions.assertEquals(3, result.P1P2);
        Assertions.assertEquals(4, result.Kipp);
        Assertions.assertEquals("5", result.Tarsus);
        Assertions.assertEquals(6, result.THL);
        Assertions.assertEquals("7", result.Bill1);
        Assertions.assertEquals(8, result.Bill2);
        Assertions.assertEquals(9, result.Claw);
        Assertions.assertEquals(10, result.Emarg);
        Assertions.assertEquals("11", result.Notch_mm);
        Assertions.assertEquals(12, result.Notch_tip);
        Assertions.assertEquals("13", result.Moult_short);
        Assertions.assertEquals("14", result.Parasite);
        Assertions.assertEquals(15, result.N_unmoult_cov);
        Assertions.assertEquals(16, result.X1);
        Assertions.assertEquals(17, result.X2);
        Assertions.assertEquals(18, result.X3);
        Assertions.assertEquals(19, result.X4);
        Assertions.assertEquals(20, result.X5);
        Assertions.assertEquals(21, result.X6);
        Assertions.assertEquals("22", result.Net);

        var result2 = data.get(1);

        Assertions.assertEquals(2, result2.IDR_Podab);
        Assertions.assertEquals(129, result2.IdrZesz);
        Assertions.assertEquals("F", result2.Sex);
    }
}