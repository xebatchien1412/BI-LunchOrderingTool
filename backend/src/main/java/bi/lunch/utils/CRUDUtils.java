package bi.lunch.utils;

import java.util.Map;

public class CRUDUtils {
	public static void insertMessageToClient(Map<Object, Object> result, Object[] message) {
		result.put("response_code", message[0]);
		result.put("message", message[1]);
		result.put("status", message[2]);
	}

	public static boolean validateRequireFields(String[] requireFields, Map<Object, Object> data) {
		for (String require : requireFields) {
			if (!data.containsKey(require)) {
				return false;
			}
		}
		return true;
	}
}
