package com.cloudhub.normal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface DeleteService {
    ResponseEntity<String> deleteFile(@RequestParam(value="id") String id);
    ResponseEntity<String> deleteFolder(@RequestParam(value="folderId") String folderId);
    ResponseEntity<String> batchDelete(@RequestParam(value = "pairList") String pairList);
}
