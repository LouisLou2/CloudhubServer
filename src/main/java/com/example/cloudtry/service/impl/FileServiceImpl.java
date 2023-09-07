package com.example.cloudtry.service.impl;

import com.example.cloudtry.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public Integer checkBlockExist(FileUploader uploader) {
        return null;
    }

    @Override
    public Integer blockUpload(FileUploader uploader) {
        return null;
    }

    @Override
    public FileMetadataView fileMerge(String identifier, User user) throws IOException {
        return null;
    }

    @Override
    public void initialUserRoot(String userId) {

    }

    @Override
    public FileMetadataView mkdir(String pid, String name, String userId) {
        return null;
    }

    @Override
    public void batchDeleteFile(List<String> ids, User user) {

    }

    @Override
    public void batchCopy(List<String> sourcesIds, String targetId, User user) {

    }

    @Override
    public void batchMove(List<String> sourcesIds, String targetId, String userId) {

    }

    @Override
    public void rollbackTrash(List<String> idsList, User user) {

    }

    @Override
    public void rename(String id, String name) {

    }

    @Override
    public FileMetadataView queryById(String id, String userId) {
        return null;
    }

    @Override
    public List<BreadsView> queryBreads(String id, String userId) {
        return null;
    }

    @Override
    public PagerView<FileMetadataView> queryDeletedFiles(TrashQuery query, String userId) {
        return null;
    }

    @Override
    public PagerView<FileMetadataView> queryFiles(FileSearcher searcher, String userId) {
        return null;
    }

    @Override
    public List<FileMetadataView> queryDirs(DirLooker looker, String userId) {
        return null;
    }

    @Override
    public Integer calculateSuffixNumber(String pid, String name, Integer fileType) {
        return null;
    }

    @Override
    public String calculateName(String pid, String name, Integer fileType) {
        return null;
    }

    @Override
    public Boolean validDuplicatedName(String id, String pid, String name, Integer fileType) {
        return null;
    }

    @Override
    public void download(String id, HttpServletResponse response) {

    }
}
