package cn.promptness.blog.controller;

import cn.promptness.blog.pojo.Terms;
import cn.promptness.blog.support.service.TermsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
     * 上移导航项
     */
    @GetMapping(value = "/admin/up/{termId:.+}")
    public String upTerms(@PathVariable("termId") Integer termId) {
        termsService.upTerms(termId);
        return "redirect:/admin/navigation";
    }

    /**
     * 下移导航项
     */
    @GetMapping(value = "/admin/down/{termId:.+}")
    public String downTerms(@PathVariable("termId") Integer termId) {
        termsService.downTerms(termId);
        return "redirect:/admin/navigation";
    }

}
