package com.example.metric.controller;

import com.example.metric.model.request.ClickReq;
import com.example.metric.model.request.ImpReq;
import com.example.metric.model.response.AdvRes;
import com.example.metric.model.response.ImpRes;
import com.example.metric.model.response.RecommendRes;
import com.example.metric.service.ImpService;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BaseController {

  private final ImpService impService;

  @PostMapping("impression")
  void uploadImpression(@RequestBody Collection<ImpReq> req) {
    impService.storeImpAll(req);
  }

  @PostMapping("click")
  void uploadClick(@RequestBody List<ClickReq> req) {
    impService.storeClickAll(req);
  }

  @GetMapping("impression")
  Page<ImpRes> getImpression(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return impService.getImpressions(page, size);
  }

  @GetMapping("click")
  Page<RecommendRes> getClicks(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return impService.getClicks(page, size);
  }
}