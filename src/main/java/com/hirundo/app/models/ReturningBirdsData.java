package com.hirundo.app.models;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.Season;

import java.time.LocalDateTime;
import java.util.List;

public class ReturningBirdsData {
    public List<DbBirdRecord> Records;
    public String RingNumber;
    public String Species;
    public LocalDateTime FirstDateSeen;
    public LocalDateTime LastDateSeen;
    public Season FirstSeasonSeen;
    public Season LastSeasonSeen;
}
