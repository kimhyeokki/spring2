package com.khk11.outstargram.service;

import com.khk11.outstargram.dto.SubscribeDto;
import com.khk11.outstargram.repository.SubscribeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    private final EntityManager em;

    @Transactional
    public int subscribeCount(int memberId) {
        return subscribeRepository.subscribeCount(memberId);   //count
    }


    @Transactional
    public void subscribe(int customerDetailsId, int urlId) {
        subscribeRepository.subscribe(customerDetailsId, urlId);
    }

    @Transactional
    public void unSubscribe(int customerDetailsId, int urlId) {
        subscribeRepository.unSubscribe(customerDetailsId, urlId);
    }

    // 구독 리스트 만들기..

    @Transactional
    public List<SubscribeDto> subscribeList(int customerDetailsId, int urlId) {

        String queryString =
                "SELECT tm.MEMBER_ID AS id , tm.PROFILEIMAGEURL , tm.NAME, " +
                        "NVL2((SELECT 1 FROM SUBSCRIBE WHERE FROMMEMBERID = ? AND TOMEMBERID = tm.MEMBER_ID),TO_CHAR(1),TO_CHAR(0)) " +
                        "AS subscribeState, " +
                        "NVL2((SELECT 1 FROM dual WHERE ? = tm.MEMBER_ID),TO_CHAR(1),TO_CHAR(0)) AS equalState " +
                        "FROM TABLE_MEMBER tm INNER JOIN SUBSCRIBE s " +
                        "ON tm.MEMBER_ID = s.TOMEMBERID " +
                        "WHERE s.FROMMEMBERID = ?";
        Query query = em.createNativeQuery(queryString)
                .setParameter(1, customerDetailsId)
                .setParameter(2, customerDetailsId)
                .setParameter(3, urlId);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<SubscribeDto> subscribeDtoList = jpaResultMapper.list(query, SubscribeDto.class);
        return subscribeDtoList;
    }
}




