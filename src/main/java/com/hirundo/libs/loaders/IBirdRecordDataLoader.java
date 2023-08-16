package com.hirundo.libs.loaders;

import java.util.List;

public interface IBirdRecordDataLoader<DataRecordType> {
    List<DataRecordType> loadData() throws Exception;
}

