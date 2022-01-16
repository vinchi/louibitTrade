package kr.nexparan.louibitTrade.service;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.model.Faq;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import kr.nexparan.louibitTrade.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;


    public Faq save(Faq faq) {
        faq.setContent(faq.getContent().replace("\r\n", "<br />"));
        return faqRepository.save(faq);
    }
}
