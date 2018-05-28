package com.chanjet.apidoc.apidoc.controller;

import com.chanjet.apidoc.apidoc.service.DocTransitionCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Map;

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

    @PostMapping(value = "/api/v1/converter/yaml")
    public String yaml2MarkdownDoc(@RequestBody String yaml)
    {
        try
        {
            String[] yamlData = null;
            if(!StringUtils.isEmpty(yaml) && (yamlData = yaml.split("=")).length == 2 && !StringUtils.isEmpty(yamlData[1]))
            {
                //解码
                return docTransitionCtrlService.yaml2MarkdownDoc(URLDecoder.decode(yamlData[1],"utf-8"));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }

    @PostMapping(value = "/api/v1/converter/word")
    public String exportWord(@RequestBody String html,HttpServletResponse response)
    {
        try
        {
            String[] htmlData = null;
            if(!StringUtils.isEmpty(html) && (htmlData = html.split("=")).length == 2 && !StringUtils.isEmpty(htmlData[1]))
            {
                //解码
                 docTransitionCtrlService.exportWord(URLDecoder.decode(htmlData[1],"utf-8"),response);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }
}
