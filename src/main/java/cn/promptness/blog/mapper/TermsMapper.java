package cn.promptness.blog.mapper;

import cn.promptness.blog.pojo.Terms;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermsMapper {

    int deleteByPrimaryKey(Long termId);

    int insert(Terms record);

    int insertSelective(Terms record);


    Terms selectByPrimaryKey(Long termId);


    int updateByPrimaryKeySelective(Terms record);

    int updateByPrimaryKey(Terms record);

    int bathSaveTerms(@Param("termsId") List<Integer> termsId, @Param("postsId") Long postsId);

    int deleteRelationship(@Param("postId") Long id);

    int updateTermsOrderByTermsId(@Param("termId") Long termId, @Param("termOrder") Long termOrder);

    long selectTermsOrderByTermsId(@Param("termsId") long termsId);

    long selectUpTargetTermsId(@Param("termsOrder") long termsOrder);

    long selectDownTargetTermsId(@Param("termsOrder") long termsOrder);

    String geNameBySlug(@Param("slug") String slug);

    List<Terms> getTeamsByPostsId(long postsId);

    List<Terms> listTerms();

    int deleteRelationshipByTermId(long termId);
}
