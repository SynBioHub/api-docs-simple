package org.synbiohub.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.synbiohub.api.EvaluateApi;
import org.synbiohub.model.EvaluateRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EvaluateController implements EvaluateApi {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ResponseEntity<String> evaluatePost(EvaluateRequest evaluateRequest) {
		if (evaluateRequest == null) {
			return ResponseEntity.internalServerError().body("Evaluate request payload is missing");
		}

		try {
			Map<String, Object> response = new LinkedHashMap<>();
			List<Map<String, Object>> evaluatedFiles = new ArrayList<>();

			Map<String, Object> root = evaluateRequest.getAdditionalProperties();
			Map<String, Object> manifest = asMap(root == null ? null : root.get("manifest"));
			for (Map<String, Object> file : asListOfMaps(manifest == null ? null : manifest.get("files"))) {
				String fileName = stringValue(file.get("filename"), "unknown");

				Map<String, Object> fileResult = new LinkedHashMap<>();
				fileResult.put("filename", fileName);
				fileResult.put("requirement", 2);
				evaluatedFiles.add(fileResult);
			}

			response.put("manifest", evaluatedFiles);
			return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(objectMapper.writeValueAsString(response));
		} catch (JsonProcessingException ex) {
			return ResponseEntity.internalServerError().body("Failed to build evaluate response: " + ex.getMessage());
		}
	}

	private static Map<String, Object> asMap(Object value) {
		if (value instanceof Map<?, ?> mapValue) {
			Map<String, Object> casted = new LinkedHashMap<>();
			for (Map.Entry<?, ?> entry : mapValue.entrySet()) {
				casted.put(String.valueOf(entry.getKey()), entry.getValue());
			}
			return casted;
		}
		return null;
	}

	private static List<Map<String, Object>> asListOfMaps(Object value) {
		if (!(value instanceof List<?> listValue)) {
			return Collections.emptyList();
		}

		List<Map<String, Object>> result = new ArrayList<>();
		for (Object item : listValue) {
			Map<String, Object> map = asMap(item);
			if (map != null) {
				result.add(map);
			}
		}
		return result;
	}

	private static String stringValue(Object value, String fallback) {
		return value == null ? fallback : String.valueOf(value);
	}
}
