package com.chanjet.apidoc.apidoc.controller;

import com.chanjet.apidoc.apidoc.service.DocTransitionCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: api-doc
 * @description: 文档转换
 * @author: lipeng
 * @create: 2018-05-24 13:35
 **/
@RestController
public class DocTransitionCtrl
{
    @Autowired
    private DocTransitionCtrlService docTransitionCtrlService;

    @PostMapping("/api/v1/converter/yaml")
    public String yaml2MarkdownDoc(@RequestBody String yaml)
    {
        return docTransitionCtrlService.yaml2MarkdownDoc(yaml);
    }
}
