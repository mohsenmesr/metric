package com.example.metric.service;

import com.example.metric.model.entity.Click;
import com.example.metric.model.entity.Impression;
import com.example.metric.model.request.ClickReq;
import com.example.metric.model.request.ImpReq;
import com.example.metric.model.response.ImpRes;
import com.example.metric.model.response.RecommendRes;
import com.example.metric.repository.ImpressionRepo;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ImpService {

  @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
  private int batchSize;

  private final ImpressionRepo impressionRepo;
  private final EntityManager entityManager;

  public void storeImpAll(final Collection<ImpReq> req) {
    List<Impression> entities = req.stream()
        .map(Impression::fromRequest)
        .collect(Collectors.toList());
    persisInBatch(entities);
  }

  public void storeClickAll(final Collection<ClickReq> req) {
    List<Click> entities = req.stream()
        .map(Click::fromRequest)
        .collect(Collectors.toList());
    persisInBatch(entities);
  }

  private void persisInBatch(final List<?> entities) {
    for (int i = 0; i < entities.size(); i++) {
      entityManager.persist(entities.get(i));

      if (i > 0 && i % batchSize == 0) {
        entityManager.flush();
        entityManager.clear();
      }
    }
  }

  public Page<ImpRes> getImpressions(final int page, final int size) {
    return impressionRepo.findImpressions(PageRequest.of(page, size));
  }

  public Page<RecommendRes> getClicks(int page, int size) {
    return impressionRepo.findRecommendedAdvs(PageRequest.of(page, size))
        .map(RecommendRes::fromRequest);
  }
}
