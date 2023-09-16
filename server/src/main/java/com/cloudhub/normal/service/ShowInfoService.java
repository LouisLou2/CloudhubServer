package com.cloudhub.normal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface ShowInfoService {
    ResponseEntity<String> showInfo(@RequestParam(value="folderId") String folderId);
    ResponseEntity<String> childFolderInfo(@RequestParam(value="folderId") String folderId);
}
