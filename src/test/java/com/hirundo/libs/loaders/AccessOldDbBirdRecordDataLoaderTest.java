package com.hirundo.libs.loaders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        assertEquals(1, result.IDR_Podab);
        assertEquals(128, result.IdrZesz);
        assertEquals(1, result.IDR_Podab);
        assertEquals(128, result.IdrZesz);
        assertEquals((short) 2007, result.IdBase);
        assertEquals(5, result.IdInp);
        assertEquals("BU", result.Sta);
        assertEquals(129, result.Row);
        assertEquals("BK", result.ST);
        assertEquals("PL.BK.01", result.CoStaSit);
        assertEquals((short) 1967, result.Year);
        assertEquals("A", result.Seas);
        assertEquals(LocalDateTime.of(1967, 8, 16, 0, 0, 0), result.Date);
        assertEquals((short) 18, result.Hour);
        assertEquals("J634038", result.Ring);
        assertEquals("O", result.Status);
        assertEquals("PHY.LUS", result.Spec);
        assertEquals("M", result.Sex);
        assertEquals("I", result.Age);
        assertNull(result.Ringer);
        assertEquals(9, result.Mass);
        assertEquals(0, result.Fat);
        assertEquals(61, result.Wing);
        assertEquals(45, result.Tail);
        assertEquals(5, result.D2);
        assertEquals(0, result.D3);
        assertEquals(0, result.D4);
        assertEquals(2, result.D5);
        assertEquals(6, result.D6);
        assertEquals(8, result.D7);
        assertEquals(10, result.D8);
        assertEquals("1", result.PP);
        assertEquals("2", result.P1);
        assertEquals("3", result.P2);
        assertEquals("4", result.P3);
        assertEquals("5", result.P4);
        assertEquals("+4", result.P5);
        assertEquals("6", result.P6);
        assertEquals("7", result.Notes);
        assertEquals((byte) 1, result.OK);
        assertNull(result.TS_Import);
        assertNull(result.TS_Zesz);
        assertEquals(true, result.Sel);
        assertEquals((short) 816, result.MD);
        assertNull(result.RNG);
        assertEquals("1", result.AgeT);
        assertEquals(LocalDateTime.of(1967, 8, 16, 18, 0, 0), result.DT);
        assertEquals("L", result.Ztyp);
        assertEquals(1, result.Znr);
        assertEquals(1, result.P1PC);
        assertEquals(2, result.P2PC);
        assertEquals(3, result.P1P2);
        assertEquals(4, result.Kipp);
        assertEquals("5", result.Tarsus);
        assertEquals(6, result.THL);
        assertEquals("7", result.Bill1);
        assertEquals(8, result.Bill2);
        assertEquals(9, result.Claw);
        assertEquals(10, result.Emarg);
        assertEquals("11", result.Notch_mm);
        assertEquals(12, result.Notch_tip);
        assertEquals("13", result.Moult_short);
        assertEquals("14", result.Parasite);
        assertEquals(15, result.N_unmoult_cov);
        assertEquals(16, result.X1);
        assertEquals(17, result.X2);
        assertEquals(18, result.X3);
        assertEquals(19, result.X4);
        assertEquals(20, result.X5);
        assertEquals(21, result.X6);
        assertEquals("22", result.Net);

        var result2 = data.get(1);

        assertEquals(2, result2.IDR_Podab);
        assertEquals(129, result2.IdrZesz);
        assertEquals("F", result2.Sex);
    }
}