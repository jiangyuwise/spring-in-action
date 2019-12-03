package com.codve.mybatis.convert;

import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.query.ArticleCreateQuery;
import com.codve.mybatis.model.query.ArticleUpdateQuery;
import com.codve.mybatis.model.vo.ArticleVO;
import com.codve.mybatis.util.BeanUtil;
import com.codve.mybatis.util.PageResult;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/3 16:11
 */
public class ArticleConvert {

    public static ArticleDO convert(ArticleCreateQuery query) {
        ArticleDO articleDO = new ArticleDO();
        BeanUtils.copyProperties(query, articleDO);
        return articleDO;
    }

    public static ArticleDO convert(ArticleUpdateQuery query) {
        ArticleDO articleDO = new ArticleDO();
        BeanUtils.copyProperties(query, articleDO);
        return articleDO;
    }

    public static ArticleVO convert(ArticleDO articleDO) {
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(articleDO, articleVO);
        return articleVO;
    }

    public static PageResult<ArticleVO> convert(List<ArticleDO> articleDoList) {
        PageResult<ArticleDO> doPageResult = new PageResult<>(articleDoList);
        PageResult<ArticleVO> voPageResult = new PageResult<>();
        voPageResult.setList(BeanUtil.copyList(doPageResult.getList(), ArticleVO.class));
        voPageResult.setTotal(doPageResult.getTotal());
        return voPageResult;
    }
}
