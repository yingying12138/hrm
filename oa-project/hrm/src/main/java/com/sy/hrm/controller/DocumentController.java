package com.sy.hrm.controller;

import com.sy.hrm.bean.Document;
import com.sy.hrm.bean.User;
import com.sy.hrm.constant.WebConstant;
import com.sy.hrm.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    //去添加的跳转路径
    @RequestMapping("/document/toAddDocument")
    public String toAddDocument(){

        return "document/addDocument";
    }

    //查询文档列表
    @RequestMapping("/document/documentList")
    public String documentList(Model model){
        List<Document> documents = documentService.findAllDocument();
        model.addAttribute("documents",documents);
        return "document/documentList";
    }

    //添加文档的方法    /document/addDocument
    @RequestMapping("/document/addDocument")
    public void addDocument(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Document document) throws IOException {
        //文件上传就是把本地选择的文件copy到指定的文件下面 sql.txt copy电脑上选择的资源到/images/document/
        //不能让文件重名 UUID                                             images.document
        String realPath = request.getServletContext().getRealPath("/images/document/");
        System.out.println("realpath"+realPath);

        //生成一个新的文件名
        String newFileName = UUID.randomUUID().toString()+file.getOriginalFilename();

        //把需要上传的文件copy到哪里
        //C:\Users\Administrator\AppData\Local\Temp\tomcat-docbase.3637649574249375759.8082\images.document
        file.transferTo(new File(realPath + newFileName));

        document.setUrl(realPath + newFileName);
        document.setCreateDate(new Date());

        //获取user
        User user = (User) request.getSession().getAttribute(WebConstant.SESSION_USER);
        document.setUser(user);

        //把记录保存到hrm_document表中
        documentService.saveDocument(document);
        response.sendRedirect("/document/documentList");
    }

    //文档下载
    @RequestMapping("/document/downDocument")
    public void dwonLoadDocument(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Document document = documentService.getDocumentById(Integer.parseInt(id));
        String title = document.getTitle();

        //下载就是把指定的资源通过IO流响应到本地
        //C:\Users\Administrator\AppData\Local\Temp\tomcat-docbase.3637649574249375759.8082\images.document638428a3-f8a1-4059-91c3-414f6a2878be新建文本文档.txt

        String filename = document.getUrl();
        System.out.println(filename);

        //告诉浏览器本次请求响应给浏览器的是文档附件资源,不是普通的一碗面或者其他
        response.setHeader("content-type","application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename");

        //从服务器上响应给浏览器的文件
        //把读取到的文件响应给浏览器
        //定义缓冲数组
        byte[] buff = new byte[1024];

        //定义输入流,用来读取服务器上的资源
        BufferedInputStream bis = null;

        //输出流把输入流中的数据写出去
        OutputStream os = null;
        os = response.getOutputStream();

        //把服务器上的文件读取到IO流中
        bis = new BufferedInputStream(new FileInputStream(new File(filename)));
        int len = 0;

        //循环读取服务器上的资源
        while ((len = bis.read(buff)) != -1){
            os.write(buff,0,buff.length);
            os.flush();
            len=bis.read(buff);
        }

        //关闭流
        os.close();
        bis.close();
    }

    //更新文档 1 先把需要更新的内容查询出来放到页面上  2 修改需要更新的内容
    @RequestMapping("/document/toUpdateDocument")
    public  String toUpdateDocument(String id ,Model model){
        Document document = documentService.getDocumentById(Integer.parseInt(id));
        model.addAttribute("document",document);
        return "document/updateDocument";

    }

    @RequestMapping("/document/updateDocument")
    public String updateDocument(){
        documentService.findAllDocument();
        return "/document/documentList";
    }

    //删除文档
    @RequestMapping("/document/deleteDocument")
    public void deleteDept(Integer id, HttpServletResponse response) throws Exception {
        documentService.deleteDocument(id);
        response.sendRedirect("/document/documentList");
    }
}
