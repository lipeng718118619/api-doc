package com.chanjet.apidoc.apidoc.model;

import java.util.List;
import java.util.Map;

/**
 * @program: api-doc
 * @description:
 * @author: lipeng
 * @create: 2018-05-24 14:48
 **/
public class DocumentBody
{
    private List<String> tags;

    private String summary;

    private List<String> consumes;

    private List<String> produces;

    private List<Parameter> parameters;

    private Map<String, Object> responses;

    public DocumentBody()
    {
        super();
    }

    public List<String> getTags()
    {
        return tags;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public List<String> getConsumes()
    {
        return consumes;
    }

    public void setConsumes(List<String> consumes)
    {
        this.consumes = consumes;
    }

    public List<String> getProduces()
    {
        return produces;
    }

    public void setProduces(List<String> produces)
    {
        this.produces = produces;
    }

    public List<Parameter> getParameters()
    {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters)
    {
        this.parameters = parameters;
    }

    public Map<String, Object> getResponses()
    {
        return responses;
    }

    public void setResponses(Map<String, Object> responses)
    {
        this.responses = responses;
    }

}
