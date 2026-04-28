package org.synbiohub.controller;

import org.synbiohub.api.StatusApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController implements StatusApi {

	@Override
	public ResponseEntity<String> statusGet() {
		return ResponseEntity.ok("The plugin is up and running");
	}
}
