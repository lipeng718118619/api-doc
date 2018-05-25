package com.chanjet.apidoc.apidoc.model;

/**
 * @program: api-doc
 * @description:
 * @author: lipeng
 * @create: 2018-05-24 14:32
 **/
public class Tag
{
    private String name;

    private String description;

    public Tag()
    {
        super();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
