package com.blog.mapper;

import com.blog.pojo.Terms;
import com.blog.pojo.TermsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TermsMapper {
    int countByExample(TermsExample example);

    int deleteByExample(TermsExample example);

    int deleteByPrimaryKey(Long termId);

    int insert(Terms record);

    int insertSelective(Terms record);

    List<Terms> selectByExample(TermsExample example);

    Terms selectByPrimaryKey(Long termId);

    int updateByExampleSelective(@Param("record") Terms record, @Param("example") TermsExample example);

    int updateByExample(@Param("record") Terms record, @Param("example") TermsExample example);

    int updateByPrimaryKeySelective(Terms record);

    int updateByPrimaryKey(Terms record);

	void bathSaveTerms(@Param("termsId")List<Integer> termsId, @Param("postsId")Long postsId);

	void deleteRelationship(@Param("postId")Long id);

	void updateTermsOrderByTermsId(@Param("termId")Long termId,@Param("termOrder") Long termOrder);

	long selectTermsOrderByTermsId(@Param("termsId")long termsId);

	long selectUpTargetTermsId(@Param("termsOrder")long termsOrder);

	long selectDownTargetTermsId(@Param("termsOrder")long termsOrder);

	String geNameBySlug(@Param("slug")String slug);
}