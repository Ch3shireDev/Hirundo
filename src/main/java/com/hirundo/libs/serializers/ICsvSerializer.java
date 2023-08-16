package com.hirundo.libs.serializers;

import java.util.List;

public interface ICsvSerializer<T> {
    String serializeToCsv(List<T> birdDataList) throws Exception;
}
