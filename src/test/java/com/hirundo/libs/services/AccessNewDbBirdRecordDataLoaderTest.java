package com.hirundo.libs.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AccessNewDbBirdRecordDataLoaderTest {

    AccessNewDbBirdRecordDataLoader dataLoader;

    @BeforeEach
    void setUp() {
        dataLoader = new AccessNewDbBirdRecordDataLoader();
    }

    @Test
    void loadData() {
        dataLoader.setFileName("src/test/resources/access_example_new_db.mdb");
        var data = dataLoader.loadData("example table");

        Assertions.assertEquals(3, data.size());

        var birb = data.get(0);
        Assertions.assertEquals(1924534, birb.IDR_Podab);
        Assertions.assertEquals(15471, birb.IdBaza);
        Assertions.assertEquals(1, birb.IdSezon);
        Assertions.assertEquals(1, birb.IdZesz);
        Assertions.assertEquals("L", birb.Ztyp);
        Assertions.assertEquals(1, birb.Znr);
        Assertions.assertEquals(1, birb.Zpage);
        Assertions.assertEquals(1, birb.Zrow);
        Assertions.assertEquals("PL.BK.08", birb.CoStaSit);
        Assertions.assertEquals(2017, birb.Year);
        Assertions.assertEquals("S", birb.Seas);
        Assertions.assertEquals("26.03", birb.Date);
        Assertions.assertEquals("", birb.Pole13);
        Assertions.assertEquals("2017-03-26", birb.Date2);
        Assertions.assertEquals(326, birb.MD);
        Assertions.assertEquals(6, birb.Hour);
        Assertions.assertEquals("", birb.Pole17);
        Assertions.assertEquals("LJ", birb.RingType);
        Assertions.assertEquals("985", birb.RingSer);
        Assertions.assertEquals("45", birb.RingNo);
        Assertions.assertEquals("LJ98545", birb.Ring);
        Assertions.assertEquals("", birb.Pole22);
        Assertions.assertEquals("O", birb.Stat);
        Assertions.assertEquals("M", birb.Net);
        Assertions.assertEquals("REG.REG", birb.SpeciesCode);
        Assertions.assertEquals("GOLDCREST", birb.EnglishName);
        Assertions.assertEquals("REGULUS REGULUS", birb.LatinName);
        Assertions.assertEquals("", birb.AddInfo);
        Assertions.assertEquals("M", birb.Sex);
        Assertions.assertEquals("", birb.Pole30);
        Assertions.assertEquals("", birb.QuestionMark);
        Assertions.assertEquals("2", birb.Euring);
        Assertions.assertEquals("I", birb.Age);
        Assertions.assertEquals("", birb.Pole34);
        Assertions.assertEquals("", birb.Pole35);
        Assertions.assertEquals("2", birb.WIEK);
        Assertions.assertEquals("", birb.SZATA);
        Assertions.assertEquals(0, birb.Fat);
        Assertions.assertEquals("", birb.Pole39);
        Assertions.assertEquals("45", birb.Tip);
        Assertions.assertEquals("", birb.Pole41);
        Assertions.assertEquals("1", birb.M1);
        Assertions.assertEquals("", birb.Pole43);
        Assertions.assertEquals("1", birb.M2);
        Assertions.assertEquals("", birb.Pole45);
        Assertions.assertEquals("5", birb.M3);
        Assertions.assertEquals("", birb.Pole47);
        Assertions.assertEquals("06", birb.M4);
        Assertions.assertEquals("", birb.Pole49);
        Assertions.assertEquals("7", birb.M5);
        Assertions.assertEquals("", birb.Pole51);
        Assertions.assertEquals("", birb.M6);
        Assertions.assertEquals("", birb.Pole53);
        Assertions.assertEquals(6, birb.D2);
        Assertions.assertEquals("", birb.Pole55);
        Assertions.assertEquals(1, birb.D3);
        Assertions.assertEquals("", birb.Pole57);
        Assertions.assertEquals(0, birb.D4);
        Assertions.assertEquals("", birb.Pole59);
        Assertions.assertEquals(0, birb.D5);
        Assertions.assertEquals("", birb.Pole61);
        Assertions.assertEquals(1, birb.D6);
        Assertions.assertEquals("", birb.Pole63);
        Assertions.assertEquals(5, birb.D7);
        Assertions.assertEquals("", birb.Pole65);
        Assertions.assertEquals(7, birb.D8);
        Assertions.assertEquals("", birb.Pole67);
        Assertions.assertEquals("", birb.Extra1);
        Assertions.assertEquals("", birb.Pole69);
        Assertions.assertEquals("", birb.Extra2);
        Assertions.assertEquals("", birb.Pole71);
        Assertions.assertEquals(53, birb.Wing);
        Assertions.assertEquals("", birb.Pole73);
        Assertions.assertEquals(42, birb.Tail);
        Assertions.assertEquals("", birb.Pole75);
        Assertions.assertEquals(4.9, birb.Weight);
        Assertions.assertEquals("", birb.Pole77);
        Assertions.assertEquals("0.1", birb.DW);
        Assertions.assertEquals("", birb.NotchDziób);
        Assertions.assertEquals("", birb.Pole80);
        Assertions.assertEquals("", birb.LocN_Pazur_Kipp);
        Assertions.assertEquals("", birb.Pole82);
        Assertions.assertEquals("21", birb.P1P2);
        Assertions.assertEquals("", birb.Pole84);
        Assertions.assertEquals("+7", birb.P1PC);
        Assertions.assertEquals("", birb.Pole86);
        Assertions.assertNull(birb.Special5);
        Assertions.assertNull(birb.Pole88);
        Assertions.assertNull(birb.Special6);
        Assertions.assertNull(birb.Pole90);
        Assertions.assertNull(birb.Special7);
        Assertions.assertNull(birb.Pole92);
        Assertions.assertNull(birb.Special8);
        Assertions.assertNull(birb.Pole94);
        Assertions.assertEquals("", birb.Comments);
        Assertions.assertNull(birb.Notes_T);
        Assertions.assertEquals("KRS", birb.CodeR);
        Assertions.assertEquals("ŻYWOB", birb.KOND);
        Assertions.assertEquals("25", birb.OKOL);
        Assertions.assertEquals("SIEĆ", birb.CHWYT);
        Assertions.assertEquals("BEZ", birb.WABIK);
        Assertions.assertEquals("-", birb.MANIP);
        Assertions.assertEquals("-", birb.PRZEM);
        Assertions.assertEquals("STĘPNIEWSKI,KRZYSZTOF", birb.NAZWOB2);
        Assertions.assertEquals("", birb.BŁĄD);
        Assertions.assertEquals(LocalDateTime.of(1899, 12, 30, 6, 0, 0), birb.Godz);
        Assertions.assertEquals(LocalDateTime.of(2017, 3, 26, 6, 0, 0), birb.DT);
        Assertions.assertNull(birb.OB2);
        Assertions.assertNull(birb.TYP2);
        Assertions.assertNull(birb.CTR2);
        Assertions.assertNull(birb.KRZ2);
        Assertions.assertNull(birb.NROB2);
        Assertions.assertEquals("KRZYSZTOF STĘPNIEWSKI", birb.Name);
        Assertions.assertEquals("O", birb.OB1);
        Assertions.assertEquals("MET", birb.TYPE1);
        Assertions.assertEquals("PLG", birb.CTR1);
        Assertions.assertEquals("0", birb.DD);
        Assertions.assertEquals("?", birb.STATUS);
        Assertions.assertNull(birb.Index_l_prim);
        Assertions.assertNull(birb.Index_e_prim);
        Assertions.assertNull(birb.IndexL);
        Assertions.assertNull(birb.IndexE);
        Assertions.assertNull(birb.IndexH);
        Assertions.assertNull(birb.FotoB);
        Assertions.assertNull(birb.FotoW);
        Assertions.assertNull(birb.D9);
        Assertions.assertNull(birb.D10);
    }
}