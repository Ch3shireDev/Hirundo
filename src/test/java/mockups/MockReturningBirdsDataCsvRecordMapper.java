package mockups;

import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.services.CsvReturningBirdsData;
import com.hirundo.libs.services.IReturningBirdsDataCsvRecordMapper;

import java.util.List;

public class MockReturningBirdsDataCsvRecordMapper implements IReturningBirdsDataCsvRecordMapper {
    public Boolean isMapperCalled = false;
    public List<ReturningBirdsData> inputData;
    public List<CsvReturningBirdsData> outputData;

    @Override
    public List<CsvReturningBirdsData> getCsvReturningBirdsData(List<ReturningBirdsData> returningBirds) {
        isMapperCalled = true;
        inputData = returningBirds;
        return outputData;
    }
}
