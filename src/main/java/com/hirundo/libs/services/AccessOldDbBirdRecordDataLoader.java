package com.hirundo.libs.services;

import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AccessOldDbBirdRecordDataLoader implements IOldDbBirdRecordDataLoader {

    String tableName;
    String filename;

    public AccessOldDbBirdRecordDataLoader() {
    }

    public void setFileName(final String filename) {
        this.filename = filename;
    }

    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<OldDbBirdRecord> loadData() {
        var records = new LinkedList<OldDbBirdRecord>();

        Table table = null;
        try {
            table = DatabaseBuilder.open(new File(filename)).getTable(tableName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Row row : table) {
            var record = new OldDbBirdRecord();
            record.IDR_Podab = row.getInt("IDR_Podab");
            record.IdrZesz = row.getInt("IdrZesz");
            record.IdBase = row.getShort("IdBase");
            record.IdInp = row.getInt("IdInp");
            record.Sta = row.getString("Sta");
            record.Row = row.getInt("Row");
            record.ST = row.getString("ST");
            record.CoStaSit = row.getString("CoStaSit");
            record.Year = row.getShort("Year");
            record.Seas = row.getString("Seas");
            record.Date = row.getLocalDateTime("Date");
            record.Hour = row.getShort("Hour");
            record.Ring = row.getString("Ring");
            record.Status = row.getString("Status");
            record.Spec = row.getString("Spec");
            record.Sex = row.getString("Sex");
            record.Age = row.getString("Age");
            record.Ringer = row.getString("Ringer");
            record.Mass = row.getFloat("Mass");
            record.Fat = row.getFloat("Fat");
            record.Wing = row.getFloat("Wing");
            record.Tail = row.getFloat("Tail");
            record.D2 = row.getFloat("D2");
            record.D3 = row.getFloat("D3");
            record.D4 = row.getFloat("D4");
            record.D5 = row.getFloat("D5");
            record.D6 = row.getFloat("D6");
            record.D7 = row.getFloat("D7");
            record.D8 = row.getFloat("D8");
            record.PP = row.getString("PP");
            record.P1 = row.getString("P1");
            record.P2 = row.getString("P2");
            record.P3 = row.getString("P3");
            record.P4 = row.getString("P4");
            record.P5 = row.getString("P5");
            record.P6 = row.getString("P6");
            record.Notes = row.getString("Notes");
            record.OK = row.getByte("OK");
            record.TS_Import = row.getLocalDateTime("TS_Import");
            record.TS_Zesz = row.getLocalDateTime("TS_Zesz");
            record.Sel = row.getBoolean("Sel");
            record.MD = row.getShort("MD");
            record.RNG = row.getString("RNG");
            record.AgeT = row.getString("AgeT");
            record.DT = row.getLocalDateTime("DT");
            record.Ztyp = row.getString("Ztyp");
            record.Znr = row.getInt("Znr");
            record.P1PC = row.getFloat("P1PC");
            record.P2PC = row.getFloat("P2PC");
            record.P1P2 = row.getFloat("P1P2");
            record.Kipp = row.getFloat("Kipp");
            record.Tarsus = row.getString("Tarsus");
            record.THL = row.getFloat("THL");
            record.Bill1 = row.getString("Bill1");
            record.Bill2 = row.getFloat("Bill2");
            record.Claw = row.getFloat("Claw");
            record.Emarg = row.getFloat("Emarg");
            record.Notch_mm = row.getString("Notch_mm");
            record.Notch_tip = row.getFloat("Notch_tip");
            record.Moult_short = row.getString("Moult_short");
            record.Parasite = row.getString("Parasite");
            record.N_unmoult_cov = row.getInt("N_unmoult_cov");
            record.X1 = row.getFloat("X1");
            record.X2 = row.getFloat("X2");
            record.X3 = row.getFloat("X3");
            record.X4 = row.getFloat("X4");
            record.X5 = row.getFloat("X5");
            record.X6 = row.getFloat("X6");
            record.Net = row.getString("Net");

        records.add(record);
        }

        return records;
    }
}
