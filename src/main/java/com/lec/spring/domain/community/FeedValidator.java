package com.lec.spring.domain.community;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FeedValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports(" + clazz.getName() + ")");
        boolean result = FeedDTO.class.isAssignableFrom(clazz);
        System.out.println(result);
        return result;
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validatre() 호출");
        FeedDTO feed = (FeedDTO) target;

        if(feed.getFeedState().equals("comp")) { // 완료 글일 때 검사
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feedTitle", "피드 제목은 필수입니다.");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feedContent", "피드 내용은 필수입니다.");
        } else {  // 임시 저장 글일 때 검사
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feedTitle", "피드 제목은 필수입니다.");
        }
    }
}
