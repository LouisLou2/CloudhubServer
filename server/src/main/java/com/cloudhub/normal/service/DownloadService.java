package com.cloudhub.normal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface DownloadService {
    ResponseEntity<String> getDownloadInfo(@RequestParam(value="id",required = true)String id);
}
