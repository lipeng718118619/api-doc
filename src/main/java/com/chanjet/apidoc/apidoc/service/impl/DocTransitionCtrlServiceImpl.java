package com.chanjet.apidoc.apidoc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chanjet.apidoc.apidoc.model.DocumentBody;
import com.chanjet.apidoc.apidoc.model.Scheme;
import com.chanjet.apidoc.apidoc.model.YamlDoc;
import com.chanjet.apidoc.apidoc.service.DocTransitionCtrlService;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public String yaml2MarkdownDoc(String yamlData)
    {
        YamlDoc yamlDoc = YAML.loadAs(yamlData.replace('\t',' '), YamlDoc.class);
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
                sb.append("## 请求参数 \n\n");//请求参数
                sb.append("|参数| 类型 | 是否必填| 说明\n |---|---|----|---\n");
                sb.append(documentBody.getParameters().get(0).schemes2String());

                //响应参数处理
                Map<String, Object> responses = documentBody.getResponses();

                if(!responses.isEmpty())
                {
                    sb.append("## 响应参数 \n\n");//响应参数
                    List<Scheme> responseSchemes = JSONArray.parseObject(JSONArray.toJSONString(responses.get("schemes")),
                            new TypeReference< List<Scheme>>(){});

                    if(!CollectionUtils.isEmpty(responseSchemes))
                    {
                        sb.append("|参数| 类型 | 是否必填| 说明\n |---|---|----|---\n");

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

    @Override
    public void exportWord(String html,HttpServletResponse response)
        {
        try {
            //word内容
            byte b[] = html.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
            /*
             * 关键地方
             * 生成word格式 */
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            directory.createDocument("WordDocument", bais);
            String url = ResourceUtils.getURL("classpath:static/data-word/").getPath();
            String id = UUID.randomUUID().toString();
            FileOutputStream outputStream = new FileOutputStream(url+id+".doc");
            poifs.writeFilesystem(outputStream);
            outputStream.flush();
            outputStream.close();
            bais.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
