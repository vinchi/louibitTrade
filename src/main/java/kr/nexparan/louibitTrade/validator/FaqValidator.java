package kr.nexparan.louibitTrade.validator;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.model.Faq;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class FaqValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Faq.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Faq b = (Faq) obj;
        if(StringUtils.isEmpty(b.getContent())) {
            errors.rejectValue("content", "key", "The content is empty.");
        }
    }
}
