package com.blog.pojo;

import java.io.Serializable;

public class Terms implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long termId;

    private String name;

    private String slug;
    
    private boolean checked;

    
    
    
    public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }
}