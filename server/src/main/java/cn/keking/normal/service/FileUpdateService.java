package cn.keking.normal.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FileUpdateService {
    @GetMapping("/createfolder")
    public ResponseEntity<String> createFolder(@RequestParam(value="name")String name, @RequestParam(value="pid")String pid);

    @GetMapping("/rename")
    ResponseEntity<String> rename(@RequestParam(value="baseType")String baseType,@RequestParam(value="id") String id, @RequestParam(value="id")String newName);
}
