package com.codve.mybatis.controller;

import com.codve.mybatis.convert.ArticleConvert;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.business.object.ArticleBO;
import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.query.ArticleCreateQuery;
import com.codve.mybatis.model.query.ArticleQuery;
import com.codve.mybatis.model.query.ArticleUpdateQuery;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.model.vo.ArticleVO;
import com.codve.mybatis.service.ArticleService;
import com.codve.mybatis.util.CommonResult;
import com.codve.mybatis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.codve.mybatis.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/12/3 16:07
 */
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/save")
    public CommonResult save(@RequestBody @Validated ArticleCreateQuery query) {

        articleService.save(ArticleConvert.convert(query));
        return CommonResult.success();
    }

    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable @Valid @Min(value = 1) Long id) {
        articleService.deleteById(id);
        return CommonResult.success();
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody @Validated ArticleUpdateQuery query) {
        articleService.update(ArticleConvert.convert(query));
        return CommonResult.success();
    }

    @GetMapping("/{id}")
    public CommonResult<ArticleVO> findById(@PathVariable @Valid @Min(value = 1) Long id) {
        ArticleDO articleDO = articleService.findById(id);
        return CommonResult.success(ArticleConvert.convert(articleDO));
    }

    @GetMapping("/find")
    public CommonResult<PageResult<ArticleVO>> find(@RequestBody @Validated ArticleQuery query
    ) {
        List<ArticleDO> articleDoList = articleService.find(query);
        if (articleDoList.size() == 0) {
            exception(EX.E_1104);
        }
        PageResult<ArticleVO> pageResult = ArticleConvert.convert(articleDoList);
        return CommonResult.success(pageResult);
    }

    @GetMapping("/union/find")
    public CommonResult<PageResult<ArticleBO>> unionFind(@RequestBody(required = false) @Validated UserQuery userQuery) {
        PageResult<ArticleBO> result = articleService.unionFind(userQuery);
        return CommonResult.success(result);
    }

}
