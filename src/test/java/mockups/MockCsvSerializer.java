package mockups;

import com.hirundo.libs.services.ICsvSerializer;

import java.util.List;

public class MockCsvSerializer<T> implements ICsvSerializer<T> {

    public boolean isSerializeCalled;
    public List<T> inputData;
    public String outputData;

    @Override
    public String serializeToCsv(List<T> birdDataList) {
        isSerializeCalled = true;
        inputData = birdDataList;
        return outputData;
    }
}
