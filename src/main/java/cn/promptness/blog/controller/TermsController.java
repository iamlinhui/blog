package cn.promptness.blog.controller;

import cn.promptness.blog.pojo.Terms;
import cn.promptness.blog.support.service.TermsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author Lynn
 */
@Controller
public class TermsController {

    @Resource
    private TermsService termsService;

    /**
     * 到导航栏设置页面
     */
    @GetMapping(value = "/admin/navigation")
    public String toAdminNavigation() {
        return "admin/navigation";
    }

    /**
     * 保存或者修改导航栏
     */
    @PostMapping(value = "/admin/navigation")
    public String addOrUpdateTerms(Terms terms) {

        if (Objects.isNull(terms.getTermId())) {
            termsService.saveTerms(terms);
        } else {
            termsService.updateTerms(terms);
        }
        return "redirect:/admin/navigation";
    }

    /**
     * 删除导航项
     */
    @GetMapping(value = "/admin/delete")
    public String deleteTerms(@RequestParam("termId") Integer termId) {
        termsService.deleteTermsById(termId);
        return "redirect:/admin/navigation";
    }

    /**
     * 拖拽排序导航项
     */
    @PostMapping(value = "/admin/navigation/reorder")
    @ResponseBody
    public ResponseEntity<Void> reorderTerms(@RequestBody List<Integer> termIds) {
        termsService.reorderTerms(termIds);
        return ResponseEntity.ok().build();
    }

}
