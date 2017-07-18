package com.blog.pojo;

import java.io.Serializable;
import java.util.List;

public class PostsWithBLOBs extends Posts implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String postContent;

    private String postTitle;

    private String postExcerpt;
    
    private List<Terms> term;
    

    public List<Terms> getTerm() {
		return term;
	}

	public void setTerm(List<Terms> term) {
		this.term = term;
	}

	public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt == null ? null : postExcerpt.trim();
    }
    
}