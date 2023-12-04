package com.khk11.isotope.controller;

import com.khk11.isotope.dto.IsotopeDto;
import com.khk11.isotope.eunms.Category;
import com.khk11.isotope.service.IsotopeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/isotope")
@Slf4j
@RequiredArgsConstructor
public class IsotopeController {
    private final IsotopeService isotopeService;

    private Category category[] = {Category.SKETCH,Category.PAINT,Category.PHOTO};
    private List<Category> categoryList = Arrays.asList(category);
    @Value("${file.path}")
    private String uploadFolder;

    @GetMapping({"/","index","/main",""})    // GetMapping({배열로 할수 있음})
    public String index(Model model, IsotopeDto isotopeDto){
        List<IsotopeDto> isotopeList = isotopeService.getAllList();
        model.addAttribute("isotopeList",isotopeList);

        /*List<Category> categoryList = new ArrayList<>();
        categoryList.add(Category.PAINT);
        categoryList.add(Category.SKETCH);
        categoryList.add(Category.PHOTO);*/
        /*=========위와 아래는 같습니다.*/
        List<Category> categoryList = Arrays.asList(category);
        model.addAttribute("categoryList",categoryList);
        return "/index";
    }

    @GetMapping("/insert")
    public String insert(Model model){
        IsotopeDto isotopeDto = new IsotopeDto();
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(Category.PAINT);
        categoryList.add(Category.SKETCH);
        categoryList.add(Category.PHOTO);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("isotopeDto",isotopeDto);
        return "/insert";
    }

    @PostMapping("/insert")
    public String insertProcess(@Valid @ModelAttribute IsotopeDto isotopeDto,
                                BindingResult bindingResult,  //BindingResult는 반드시 dto 다음으로 나와야함
                                Model model){
        /*if(isotopeDto.getFile().isEmpty()){  //멀티파트파일 유효성 검사부분
            model.addAttribute("categoryList",categoryList);
            model.addAttribute("isotopeDto",isotopeDto);
            bindingResult.addError(new FieldError("fileError","file",
                    "이미지를 입력해주세요"));
            return "/insert";
        }*/
        if(bindingResult.hasErrors()){
            log.info("title 오류==={}");
            model.addAttribute("categoryList",categoryList);
            model.addAttribute("isotopeDto",isotopeDto);
            return "/insert";
        }
        int result = isotopeService.insertIsotope(isotopeDto);
        if(result>0){
            return "redirect:/";
        }
        return "/index";
    }



}
