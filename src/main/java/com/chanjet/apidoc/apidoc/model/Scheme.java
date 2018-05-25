package com.chanjet.apidoc.apidoc.model;

/**
 * @program: api-doc
 * @description:
 * @author: lipeng
 * @create: 2018-05-24 14:52
 **/
public class Scheme
{
    private String name;

    private String type;

    private String required;

    private String desc;

    public Scheme()
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getRequired()
    {
        return required;
    }

    public void setRequired(String required)
    {
        this.required = required;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    @Override
    public String toString()
    {
        return name+"|"+type+"|"+required+"|"+desc+"\n";
    }
}
