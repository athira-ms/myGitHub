package testautomationproject.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

	public List<HashMap<String, String>> getJsonDatatoMap() throws IOException {

		// Read json file and convert to String
		String jsonCntent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\testautomationproject\\data\\purchaseData.json"),
				StandardCharsets.UTF_8);

		// String to Hashmap using jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonCntent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}
}
