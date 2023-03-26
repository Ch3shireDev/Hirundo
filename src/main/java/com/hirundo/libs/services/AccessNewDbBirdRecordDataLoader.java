package com.hirundo.libs.services;

import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.hirundo.libs.data_structures.NewDbBirdRecord;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AccessNewDbBirdRecordDataLoader implements INewDbBirdRecordDataLoader {
    private String filename;
    private String tableName;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    @Override
    public List<NewDbBirdRecord> loadData() {

        var records = new LinkedList<NewDbBirdRecord>();

        Table table = null;
        try {
            table = DatabaseBuilder.open(new File(filename)).getTable(tableName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Row row : table) {
            var record = new NewDbBirdRecord();

            record.IDR_Podab = row.getDouble("IDR_Podab");
            record.IdBaza = row.getDouble("IdBaza");
            record.IdSezon = row.getDouble("IdSezon");
            record.IdZesz = row.getDouble("IdZesz");
            record.Ztyp = row.getString("Ztyp");
            record.Znr = row.getDouble("Znr");
            record.Zpage = row.getDouble("Zpage");
            record.Zrow = row.getDouble("Zrow");
            record.CoStaSit = row.getString("CoStaSit");
            record.Year = row.getDouble("Year");
            record.Seas = row.getString("Seas");
            record.Date = row.getString("Date");
            record.Pole13 = row.getString("Pole13");
            record.Date2 = row.getString("Date2");
            record.MD = row.getDouble("MD");
            record.Hour = row.getDouble("Hour");
            record.Pole17 = row.getString("Pole17");
            record.RingType = row.getString("Ring Type");
            record.RingSer = row.getString("Ring Ser");
            record.RingNo = row.getString("Ring No");
            record.Ring = row.getString("Ring");
            record.Pole22 = row.getString("Pole22");
            record.Stat = row.getString("Stat");
            record.Net = row.getString("Net");
            record.SpeciesCode = row.getString("Species Code");
            record.EnglishName = row.getString("English Name");
            record.LatinName = row.getString("Latin Name");
            record.AddInfo = row.getString("AddInfo");
            record.Sex = row.getString("Sex");
            record.Pole30 = row.getString("Pole30");
            record.QuestionMark = row.getString("'?'");
            record.Euring = row.getString("Euring");
            record.Age = row.getString("Age");
            record.Pole34 = row.getString("Pole34");
            record.Pole35 = row.getString("Pole35");
            record.WIEK = row.getString("WIEK");
            record.SZATA = row.getString("SZATA");
            record.Fat = row.getDouble("Fat");
            record.Pole39 = row.getString("Pole39");
            record.Tip = row.getString("Tip");
            record.Pole41 = row.getString("Pole41");
            record.M1 = row.getString("M1");
            record.Pole43 = row.getString("Pole43");
            record.M2 = row.getString("M2");
            record.Pole45 = row.getString("Pole45");
            record.M3 = row.getString("M3");
            record.Pole47 = row.getString("Pole47");
            record.M4 = row.getString("M4");
            record.Pole49 = row.getString("Pole49");
            record.M5 = row.getString("M5");
            record.Pole51 = row.getString("Pole51");
            record.M6 = row.getString("M6");
            record.Pole53 = row.getString("Pole53");
            record.D2 = row.getDouble("D2");
            record.Pole55 = row.getString("Pole55");
            record.D3 = row.getDouble("D3");
            record.Pole57 = row.getString("Pole57");
            record.D4 = row.getDouble("D4");
            record.Pole59 = row.getString("Pole59");
            record.D5 = row.getDouble("D5");
            record.Pole61 = row.getString("Pole61");
            record.D6 = row.getDouble("D6");
            record.Pole63 = row.getString("Pole63");
            record.D7 = row.getDouble("D7");
            record.Pole65 = row.getString("Pole65");
            record.D8 = row.getDouble("D8");
            record.Pole67 = row.getString("Pole67");
            record.Extra1 = row.getString("Extra 1");
            record.Pole69 = row.getString("Pole69");
            record.Extra2 = row.getString("Extra 2");
            record.Pole71 = row.getString("Pole71");
            record.Wing = row.getDouble("Wing");
            record.Pole73 = row.getString("Pole73");
            record.Tail = row.getDouble("Tail");
            record.Pole75 = row.getString("Pole75");
            record.Weight = row.getDouble("Weight");
            record.Pole77 = row.getString("Pole77");
            record.DW = row.getString("DW");
            record.NotchDziób = row.getString("Notch/Dziób");
            record.Pole80 = row.getString("Pole80");
            record.LocN_Pazur_Kipp = row.getString("LocN/Pazur/Kipp");
            record.Pole82 = row.getString("Pole82");
            record.P1P2 = row.getString("P1-P2");
            record.Pole84 = row.getString("Pole84");
            record.P1PC = row.getString("P1-PC");
            record.Pole86 = row.getString("Pole86");
            record.Special5 = row.getString("Special 5");
            record.Pole88 = row.getString("Pole88");
            record.Special6 = row.getString("Special 6");
            record.Pole90 = row.getString("Pole90");
            record.Special7 = row.getString("Special 7");
            record.Pole92 = row.getString("Pole92");
            record.Special8 = row.getString("Special 8");
            record.Pole94 = row.getString("Pole94");
            record.Comments = row.getString("Comments");
            record.Notes_T = row.getString("Notes_T");
            record.CodeR = row.getString("CodeR");
            record.KOND = row.getString("KOND");
            record.OKOL = row.getString("OKOL");
            record.CHWYT = row.getString("CHWYT");
            record.WABIK = row.getString("WABIK");
            record.MANIP = row.getString("MANIP");
            record.PRZEM = row.getString("PRZEM");
            record.NAZWOB2 = row.getString("NAZWOB2");
            record.BŁĄD = row.getString("BŁĄD");
            record.Godz = row.getLocalDateTime("Godz");
            record.DT = row.getLocalDateTime("DT");
            record.OB2 = row.getString("OB2");
            record.TYP2 = row.getString("TYP2");
            record.CTR2 = row.getString("CTR2");
            record.KRZ2 = row.getString("KRZ2");
            record.NROB2 = row.getString("NROB2");
            record.Name = row.getString("Name");
            record.OB1 = row.getString("OB1");
            record.TYPE1 = row.getString("TYPE1");
            record.CTR1 = row.getString("CTR1");
            record.DD = row.getString("DD");
            record.STATUS = row.getString("STATUS");
            record.Index_l_prim = row.getInt("Index l'");
            record.Index_e_prim = row.getInt("Index e'");
            record.IndexL = row.getInt("Index L");
            record.IndexE = row.getInt("Index E");
            record.IndexH = row.getInt("Index H");
            record.FotoB = row.getString("FotoB");
            record.FotoW = row.getString("FotoW");
            record.D9 = row.getInt("D9");
            record.D10 = row.getInt("D10");

            records.add(record);
        }

        return records;
    }

}
