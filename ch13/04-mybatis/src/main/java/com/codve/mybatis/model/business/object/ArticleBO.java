package com.codve.mybatis.model.business.object;

import com.codve.mybatis.model.vo.ArticleVO;
import com.codve.mybatis.model.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/3 17:00
 */
@Data
public class ArticleBO {

    @JsonProperty("user")
    private UserVO userVO;

    @JsonProperty("articleList")
    private List<ArticleVO> articleVoList;
}
