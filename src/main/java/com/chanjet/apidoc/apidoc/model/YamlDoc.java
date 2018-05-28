package com.chanjet.apidoc.apidoc.model;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @program: api-doc
 * @description:
 * @author: lipeng
 * @create: 2018-05-24 14:29
 **/
public class YamlDoc
{
    private String apidoc;

    private List<Tag> tag;

    private List<String> schemes;

    private Map<String, Map<String, Object>> paths;

    public YamlDoc()
    {
        super();
    }

    public List<String> getSchemes()
    {
        return schemes;
    }

    public void setSchemes(List<String> schemes)
    {
        this.schemes = schemes;
    }

    public String getApidoc()
    {
        return apidoc;
    }

    public void setApidoc(String apidoc)
    {
        this.apidoc = apidoc;
    }

    public List<Tag> getTag()
    {
        return tag;
    }

    public void setTag(List<Tag> tag)
    {
        this.tag = tag;
    }

    public Map<String, Map<String, Object>> getPaths()
    {
        return paths;
    }

    public void setPaths(Map<String, Map<String, Object>> paths)
    {
        this.paths = paths;
    }

    public String schemes2String()
    {
        if(CollectionUtils.isEmpty(schemes))
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String temp : schemes)
        {
            sb.append("- " + temp + "\n");
        }
        return sb.toString();
    }
}
