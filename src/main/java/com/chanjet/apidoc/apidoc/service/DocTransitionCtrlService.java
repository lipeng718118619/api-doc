package com.chanjet.apidoc.apidoc.service;


import javax.servlet.http.HttpServletResponse;

/**
 * @program: api-doc
 * @description:
 * @author: lipeng
 * @create: 2018-05-24 13:50
 **/
public interface DocTransitionCtrlService
{
    String yaml2MarkdownDoc(String yaml);
    void exportWord(String html,HttpServletResponse response);
}
