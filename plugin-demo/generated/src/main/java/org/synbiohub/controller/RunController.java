package org.synbiohub.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.synbiohub.api.RunApi;
import org.synbiohub.model.RunRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RunController implements RunApi {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ResponseEntity<String> runPost(RunRequest runRequest) {
		if (runRequest == null) {
			return ResponseEntity.badRequest().body("Run request payload is missing");
		}

		try {
			String template = loadTemplate();
			String runManifestText = objectMapper.writeValueAsString(runRequest.getAdditionalProperties());

			Map<String, Object> root = runRequest.getAdditionalProperties();
			Map<String, Object> manifest = asMap(root == null ? null : root.get("manifest"));
			List<Map<String, Object>> files = asListOfMaps(manifest == null ? null : manifest.get("files"));

			Map<String, byte[]> generatedFiles = new LinkedHashMap<>();
			List<Map<String, Object>> results = new ArrayList<>();

			for (Map<String, Object> file : files) {
				String fileName = stringValue(file.get("filename"), "unknown");
				String fileType = stringValue(file.get("type"), "application/octet-stream");
				String fileUrl = stringValue(file.get("url"), "");
				String fileData = objectMapper.writeValueAsString(file);

				String convertedFileName = sanitizeFilename(fileName) + ".converted";
				String converted = template
					.replace("TEST_FILE", fileName)
					.replace("REPLACE_DISPLAYID", sanitizeDisplayId(fileName))
					.replace("REPLACE_FILENAME", fileName)
					.replace("REPLACE_FILETYPE", fileType)
					.replace("REPLACE_FILEURL", fileUrl)
					.replace("FILE_DATA_REPLACE", fileData)
					.replace("DATA_REPLACE", runManifestText);

				generatedFiles.put(convertedFileName, converted.getBytes(StandardCharsets.UTF_8));

				Map<String, Object> resultEntry = new LinkedHashMap<>();
				resultEntry.put("filename", convertedFileName);
				resultEntry.put("sources", List.of(fileName));
				results.add(resultEntry);
			}

			Map<String, Object> runResponseManifest = new LinkedHashMap<>();
			runResponseManifest.put("results", results);

			byte[] zipBytes = buildZip(generatedFiles, objectMapper.writeValueAsBytes(runResponseManifest));

			return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"converted.zip\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new String(zipBytes, StandardCharsets.ISO_8859_1));
		} catch (Exception ex) {
			String message = "Run failed: " + ex.getMessage();
			return ResponseEntity.internalServerError().body(message);
		}
	}

	private String loadTemplate() throws IOException {
		ClassPathResource resource = new ClassPathResource("Test.xml");
		if (!resource.exists()) {
			return "<converted><file>REPLACE_FILENAME</file><type>REPLACE_FILETYPE</type><url>REPLACE_FILEURL</url><data>DATA_REPLACE</data></converted>";
		}

		try (InputStream inputStream = resource.getInputStream()) {
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		}
	}

	private byte[] buildZip(Map<String, byte[]> generatedFiles, byte[] manifestContent) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 ZipOutputStream zos = new ZipOutputStream(baos)) {

			for (Map.Entry<String, byte[]> entry : generatedFiles.entrySet()) {
				ZipEntry zipEntry = new ZipEntry(entry.getKey());
				zos.putNextEntry(zipEntry);
				zos.write(entry.getValue());
				zos.closeEntry();
			}

			ZipEntry manifestEntry = new ZipEntry("manifest.json");
			zos.putNextEntry(manifestEntry);
			zos.write(manifestContent);
			zos.closeEntry();

			zos.finish();
			return baos.toByteArray();
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

	private static String sanitizeFilename(String filename) {
		return filename.replaceAll("[^a-zA-Z0-9._-]", "_");
	}

	private static String sanitizeDisplayId(String filename) {
		return filename.replaceAll("[^a-zA-Z0-9_]", "_");
	}
}
