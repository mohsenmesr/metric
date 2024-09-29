package com.example.metric.repository;

import com.example.metric.model.entity.Impression;
import com.example.metric.model.response.AdvRes;
import com.example.metric.model.response.ImpRes;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImpressionRepo extends JpaRepository<Impression, String> {

  @Query(value = "SELECT new com.example.metric.model.response.ImpRes"
      + " (i.appId, i.cntCode,count(i),count(distinct c.id),sum(c.revenue)) "
      + " FROM Impression i left join Click c on c.impId = i.uid "
      + " group by i.appId, i.cntCode")
  Page<ImpRes> findImpressions(Pageable pageable);

  @Query(nativeQuery = true, value = "SELECT  "
      + "    app_Id as appId, "
      + "    cnt_Code as cntCode, "
      + "    GROUP_CONCAT(adv_Id ORDER BY total_revenue DESC) AS advIds "
      + ",  SUM(total_revenue) AS total_revenue "
      + "FROM ( "
      + "    SELECT  "
      + "        i.app_Id, "
      + "        i.cnt_Code, "
      + "        i.adv_Id, "
      + "        SUM(c.revenue) AS total_revenue, "
      + "        ROW_NUMBER() OVER (PARTITION BY i.app_Id, i.cnt_Code ORDER BY SUM(c.revenue) DESC) AS rank "
      + "    FROM  "
      + "        impression i "
      + "    JOIN  "
      + "        click c ON i.uId = c.imp_Id "
      + "    GROUP BY  "
      + "        i.app_Id, i.cnt_Code, i.adv_Id "
      + ") AS ranked_adv "
      + "WHERE  "
      + "    rank <= 5 "
      + "GROUP BY  "
      + "    app_Id, cnt_Code "
      + "ORDER BY total_revenue DESC "
      , countQuery = "SELECT COUNT(DISTINCT CONCAT(i.app_id, '-', i.cnt_code)) "
      + " FROM impression i "
      + " LEFT JOIN click c ON c.imp_id = i.uid")
  Page<AdvRes> findRecommendedAdvs(Pageable pageable);
}
