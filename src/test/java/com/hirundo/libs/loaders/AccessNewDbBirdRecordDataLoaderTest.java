package com.hirundo.libs.loaders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AccessNewDbBirdRecordDataLoaderTest {

    AccessNewDbBirdRecordDataLoader dataLoader;

    @BeforeEach
    void setUp() {
        var fileName = "src/test/resources/access_example_new_db.mdb";
        var tableName = "example table";
        dataLoader = new AccessNewDbBirdRecordDataLoader(fileName, tableName);
    }

    @Test
    void loadData() {
        var data = dataLoader.loadData();

        Assertions.assertEquals(3, data.size());

        var record1 = data.get(0);
        Assertions.assertEquals(1924534, record1.IDR_Podab);
        Assertions.assertEquals(15471, record1.IdBaza);
        Assertions.assertEquals(1, record1.IdSezon);
        Assertions.assertEquals(1, record1.IdZesz);
        Assertions.assertEquals("L", record1.Ztyp);
        Assertions.assertEquals(1, record1.Znr);
        Assertions.assertEquals(1, record1.Zpage);
        Assertions.assertEquals(1, record1.Zrow);
        Assertions.assertEquals("PL.BK.08", record1.CoStaSit);
        Assertions.assertEquals(2017, record1.Year);
        Assertions.assertEquals("S", record1.Seas);
        Assertions.assertEquals("26.03", record1.Date);
        Assertions.assertEquals("", record1.Pole13);
        Assertions.assertEquals("2017-03-26", record1.Date2);
        Assertions.assertEquals(326, record1.MD);
        Assertions.assertEquals(6, record1.Hour);
        Assertions.assertEquals("", record1.Pole17);
        Assertions.assertEquals("LJ", record1.RingType);
        Assertions.assertEquals("985", record1.RingSer);
        Assertions.assertEquals("45", record1.RingNo);
        Assertions.assertEquals("LJ98545", record1.Ring);
        Assertions.assertEquals("", record1.Pole22);
        Assertions.assertEquals("O", record1.Stat);
        Assertions.assertEquals("M", record1.Net);
        Assertions.assertEquals("REG.REG", record1.SpeciesCode);
        Assertions.assertEquals("GOLDCREST", record1.EnglishName);
        Assertions.assertEquals("REGULUS REGULUS", record1.LatinName);
        Assertions.assertEquals("", record1.AddInfo);
        Assertions.assertEquals("M", record1.Sex);
        Assertions.assertEquals("", record1.Pole30);
        Assertions.assertEquals("", record1.QuestionMark);
        Assertions.assertEquals("2", record1.Euring);
        Assertions.assertEquals("I", record1.Age);
        Assertions.assertEquals("", record1.Pole34);
        Assertions.assertEquals("", record1.Pole35);
        Assertions.assertEquals("2", record1.WIEK);
        Assertions.assertEquals("", record1.SZATA);
        Assertions.assertEquals(0, record1.Fat);
        Assertions.assertEquals("", record1.Pole39);
        Assertions.assertEquals("45", record1.Tip);
        Assertions.assertEquals("", record1.Pole41);
        Assertions.assertEquals("1", record1.M1);
        Assertions.assertEquals("", record1.Pole43);
        Assertions.assertEquals("1", record1.M2);
        Assertions.assertEquals("", record1.Pole45);
        Assertions.assertEquals("5", record1.M3);
        Assertions.assertEquals("", record1.Pole47);
        Assertions.assertEquals("06", record1.M4);
        Assertions.assertEquals("", record1.Pole49);
        Assertions.assertEquals("7", record1.M5);
        Assertions.assertEquals("", record1.Pole51);
        Assertions.assertEquals("", record1.M6);
        Assertions.assertEquals("", record1.Pole53);
        Assertions.assertEquals(6, record1.D2);
        Assertions.assertEquals("", record1.Pole55);
        Assertions.assertEquals(1, record1.D3);
        Assertions.assertEquals("", record1.Pole57);
        Assertions.assertEquals(0, record1.D4);
        Assertions.assertEquals("", record1.Pole59);
        Assertions.assertEquals(0, record1.D5);
        Assertions.assertEquals("", record1.Pole61);
        Assertions.assertEquals(1, record1.D6);
        Assertions.assertEquals("", record1.Pole63);
        Assertions.assertEquals(5, record1.D7);
        Assertions.assertEquals("", record1.Pole65);
        Assertions.assertEquals(7, record1.D8);
        Assertions.assertEquals("", record1.Pole67);
        Assertions.assertEquals("", record1.Extra1);
        Assertions.assertEquals("", record1.Pole69);
        Assertions.assertEquals("", record1.Extra2);
        Assertions.assertEquals("", record1.Pole71);
        Assertions.assertEquals(53, record1.Wing);
        Assertions.assertEquals("", record1.Pole73);
        Assertions.assertEquals(42, record1.Tail);
        Assertions.assertEquals("", record1.Pole75);
        Assertions.assertEquals(4.9, record1.Weight);
        Assertions.assertEquals("", record1.Pole77);
        Assertions.assertEquals("0.1", record1.DW);
        Assertions.assertEquals("", record1.NotchDziob);
        Assertions.assertEquals("", record1.Pole80);
        Assertions.assertEquals("", record1.LocN_Pazur_Kipp);
        Assertions.assertEquals("", record1.Pole82);
        Assertions.assertEquals("21", record1.P1P2);
        Assertions.assertEquals("", record1.Pole84);
        Assertions.assertEquals("+7", record1.P1PC);
        Assertions.assertEquals("", record1.Pole86);
        Assertions.assertNull(record1.Special5);
        Assertions.assertNull(record1.Pole88);
        Assertions.assertNull(record1.Special6);
        Assertions.assertNull(record1.Pole90);
        Assertions.assertNull(record1.Special7);
        Assertions.assertNull(record1.Pole92);
        Assertions.assertNull(record1.Special8);
        Assertions.assertNull(record1.Pole94);
        Assertions.assertEquals("", record1.Comments);
        Assertions.assertNull(record1.Notes_T);
        Assertions.assertEquals("KRS", record1.CodeR);
        Assertions.assertEquals("ŻYWOB", record1.KOND);
        Assertions.assertEquals("25", record1.OKOL);
        Assertions.assertEquals("SIEĆ", record1.CHWYT);
        Assertions.assertEquals("BEZ", record1.WABIK);
        Assertions.assertEquals("-", record1.MANIP);
        Assertions.assertEquals("-", record1.PRZEM);
        Assertions.assertEquals("STĘPNIEWSKI,KRZYSZTOF", record1.NAZWOB2);
        Assertions.assertEquals("", record1.BLAD);
        Assertions.assertEquals(LocalDateTime.of(1899, 12, 30, 6, 0, 0), record1.Godz);
        Assertions.assertEquals(LocalDateTime.of(2017, 3, 26, 6, 0, 0), record1.DT);
        Assertions.assertNull(record1.OB2);
        Assertions.assertNull(record1.TYP2);
        Assertions.assertNull(record1.CTR2);
        Assertions.assertNull(record1.KRZ2);
        Assertions.assertNull(record1.NROB2);
        Assertions.assertEquals("KRZYSZTOF STĘPNIEWSKI", record1.Name);
        Assertions.assertEquals("O", record1.OB1);
        Assertions.assertEquals("MET", record1.TYPE1);
        Assertions.assertEquals("PLG", record1.CTR1);
        Assertions.assertEquals("0", record1.DD);
        Assertions.assertEquals("?", record1.STATUS);
        Assertions.assertNull(record1.Index_l_prim);
        Assertions.assertNull(record1.Index_e_prim);
        Assertions.assertNull(record1.IndexL);
        Assertions.assertNull(record1.IndexE);
        Assertions.assertNull(record1.IndexH);
        Assertions.assertNull(record1.FotoB);
        Assertions.assertNull(record1.FotoW);
        Assertions.assertNull(record1.D9);
        Assertions.assertNull(record1.D10);

        var record2 = data.get(1);

        Assertions.assertEquals("F", record2.Sex);

        var record3 = data.get(2);

        Assertions.assertEquals("", record3.Sex);
    }
}