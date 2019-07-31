package com.sy.hrm.service;

import com.sy.hrm.bean.Document;

import java.util.List;


public interface DocumentService{

    void saveDocument(Document document);

    List<Document> findAllDocument();

    Document getDocumentById(int parseInt);

    /**
     * 删除学生
     * @param id
     */
    void deleteDocument(Integer id);
}
