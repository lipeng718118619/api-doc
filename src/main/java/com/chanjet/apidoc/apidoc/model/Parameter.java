package com.chanjet.apidoc.apidoc.model;

import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: api-doc
 * @description:
 * @author: lipeng
 * @create: 2018-05-24 14:51
 **/
public class Parameter
{
    private String in;

    private String name;

    private String desc;

    private List<Scheme> schemes;

    public Parameter()
    {
        super();
    }

    public String getIn()
    {
        return in;
    }

    public void setIn(String in)
    {
        this.in = in;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public List<Scheme> getSchemes()
    {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes)
    {
        this.schemes = schemes;
    }

    public String schemes2String()
    {
        if(CollectionUtils.isEmpty(schemes))
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for(Scheme scheme : schemes)
        {
            sb.append(scheme.toString());
        }

        return sb.toString();
    }
}
