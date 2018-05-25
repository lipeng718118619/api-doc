package com.chanjet.apidoc.apidoc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chanjet.apidoc.apidoc.model.DocumentBody;
import com.chanjet.apidoc.apidoc.model.Scheme;
import com.chanjet.apidoc.apidoc.model.YamlDoc;
import com.chanjet.apidoc.apidoc.service.DocTransitionCtrlService;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

/**
 * @program: api-doc
 * @description:
 * @author: lipeng
 * @create: 2018-05-24 13:50
 **/
@Service
public class DocTransitionCtrlServiceImpl implements DocTransitionCtrlService
{
    private static final Yaml YAML = new Yaml();

    @Override
    public String yaml2MarkdownDoc(String yamlString)
    {
        YamlDoc yamlDoc = YAML.loadAs(yamlSyy, YamlDoc.class);
        Map<String, Map<String, Object>> data = yamlDoc.getPaths();

        String url;

        String method;//post get ...

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Map<String, Object>> entry : data.entrySet())
        {
            url = entry.getKey(); // ## 二级标题  2
            Map<String,Object> dataMap = entry.getValue();
            for(Map.Entry<String,Object> entryObj : dataMap.entrySet())
            {
                method = entryObj.getKey(); //

                DocumentBody documentBody = JSONObject.parseObject(JSONObject.toJSONString(entryObj.getValue()),DocumentBody.class);
                String apiName = documentBody.getSummary(); // # 一级标题  1

                sb.append("# "+apiName+"\n");//接口名称
                sb.append("## url "+url+"\n");//接口url
                String schemeString = yamlDoc.schemes2String();
                if(!StringUtils.isEmpty(schemeString))
                {
                   sb.append("## schemes \n");  //## schemes
                   sb.append(schemeString);     // -https
                }
                sb.append("## 请求方式 "+"\n");//请求方式
                sb.append("#### "+method.toUpperCase()+"\n");
                sb.append("## 请求参数 \n");//请求参数
                sb.append("参数| 类型 | 是否必填| 说明\n---|---|---|---\n");
                sb.append(documentBody.getParameters().get(0).schemes2String());

                //响应参数处理
                Map<String, Object> responses = documentBody.getResponses();

                if(!responses.isEmpty())
                {
                    sb.append("## 响应参数 \n");//响应参数
                    List<Scheme> responseSchemes = JSONArray.parseObject(JSONArray.toJSONString(responses.get("schemes")),
                            new TypeReference< List<Scheme>>(){});

                    if(!CollectionUtils.isEmpty(responseSchemes))
                    {
                        sb.append("参数| 类型 | 是否必填| 说明\n---|---|---|---\n");

                        for(Scheme scheme: responseSchemes)
                        {
                            sb.append(scheme.toString());
                        }
                    }
                }

            }

        }

        return sb.toString();
    }

    public static void main(String[] args)
    {
        DocTransitionCtrlServiceImpl ii = new DocTransitionCtrlServiceImpl();

        ii.yaml2MarkdownDoc(yamlSyy);
    }

    private static String yamlSyy = "apidoc: \"1.0\"\n" +
            "tag:\n" +
            "   - name: \"user\"\n" +
            "     description: \"用户接口\"\n" +
            "   - name: \"business\"\n" +
            "     description: \"业务接口\"\n" +
            "   - name: \"other\"\n" +
            "     description: \"其他\"\n" +
            "schemes:\n" +
            "  - \"http\"\n" +
            "  - \"https\"\n" +
            "paths:\n" +
            "    /changjet/user:\n" +
            "     post:\n" +
            "        tags:\n" +
            "          - \"user\"\n" +
            "        summary: \"添加畅捷通用户\"\n" +
            "        consumes:\n" +
            "          - \"application/json\"\n" +
            "          - \"application/xml\"\n" +
            "        produces:\n" +
            "          - \"application/xml\"\n" +
            "          - \"application/json\"\n" +
            "        parameters:\n" +
            "          - in : \"body\"\n" +
            "            name: \"post body 体\"\n" +
            "            desc: \"post 请求体\"\n" +
            "            schemes:\n" +
            "              - name: \"userId\"\n" +
            "                type: \"String\"\n" +
            "                required: true\n" +
            "                desc: \"用户ID\"\n" +
            "              - name : \"age\"\n" +
            "                type: \"int\"\n" +
            "                required: false\n" +
            "                desc: \"用户年龄\"\n" +
            "        responses:\n" +
            "           schemes:\n" +
            "            - name: \"userId\"\n" +
            "              type: \"String\"\n" +
            "              required: true\n" +
            "              desc: \"用户ID\"\n" +
            "            - name : \"age\"\n" +
            "              type: \"int\"\n" +
            "              required: false\n" +
            "              desc: \"用户年龄\"\n" +
            "           \"200\":\n" +
            "             description: \"successful operation\"\n" +
            "           \"400\":\n" +
            "             description: \"Invalid ID supplied\"\n" +
            "           \"404\":\n" +
            "             description: \"Pet not found\"\n" +
            "     get:\n" +
            "         tags:\n" +
            "           - \"user\"\n" +
            "         summary: \"查询畅捷通用户\"\n" +
            "         consumes:\n" +
            "           - \"application/json\"\n" +
            "           - \"application/xml\"\n" +
            "         produces:\n" +
            "           - \"application/xml\"\n" +
            "           - \"application/json\"\n" +
            "         parameters:\n" +
            "           - in : \"query\"\n" +
            "             name: \"查询参数\"\n" +
            "             desc: \"查询参数\"\n" +
            "             schemes:\n" +
            "               - name: \"userId\"\n" +
            "                 type: \"String\"\n" +
            "                 required: true\n" +
            "                 desc: \"用户ID\"\n" +
            "               - name : \"age\"\n" +
            "                 type: \"int\"\n" +
            "                 required: false\n" +
            "                 desc: \"用户年龄\"\n" +
            "         responses:\n" +
            "            schemes:\n" +
            "              - name: \"userId\"\n" +
            "                type: \"String\"\n" +
            "                required: true\n" +
            "                desc: \"用户ID\"\n" +
            "              - name : \"age\"\n" +
            "                type: \"int\"\n" +
            "                required: false\n" +
            "                desc: \"用户年龄\"\n" +
            "            \"200\":\n" +
            "              description: \"successful operation\"\n" +
            "            \"400\":\n" +
            "              description: \"Invalid ID supplied\"\n" +
            "            \"404\":\n" +
            "              description: \"Pet not found\"\n" +
            "\n" +
            "\n";
}
