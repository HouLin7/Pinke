
package com.gome.work.core.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 基础数据字典结构
 */
public class RegionItem implements Serializable {

    private static final long serialVersionUID = -6507871928142789829L;

    @Expose
    public String code;

    @Expose
    public String name;

    @Expose
    public String fullName;

    @Expose
    public String firstChar;

    @Expose
    public int grade;

    @Expose
    public RegionItem[] children;
}
