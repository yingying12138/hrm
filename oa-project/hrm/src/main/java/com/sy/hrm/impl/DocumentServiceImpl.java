package com.sy.hrm.impl;

import com.sy.hrm.bean.Document;
import com.sy.hrm.dao.DocumentMapper;
import com.sy.hrm.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public void saveDocument(Document document) {

        documentMapper.save(document);
    }

    @Override
    public List<Document> findAllDocument() {
        List<Document> documents = documentMapper.findAll(null);
        return documents;
    }

    @Override
    public Document getDocumentById(int id) {
        return documentMapper.get(id);
    }

    @Override
    public void deleteDocument(Integer id) {
        documentMapper.delete(id);
    }

}
