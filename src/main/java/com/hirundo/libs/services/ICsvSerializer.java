package com.hirundo.libs.services;

import java.util.List;

public interface ICsvSerializer<T> {
    String serializeToCsv(List<T> birdDataList) throws Exception;
}
