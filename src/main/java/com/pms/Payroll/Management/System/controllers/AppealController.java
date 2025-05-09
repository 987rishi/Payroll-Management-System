package com.pms.Payroll.Management.System.controllers;

import com.pms.Payroll.Management.System.dto.AppealDto;
import com.pms.Payroll.Management.System.service.AppealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/appeals-management")
public class AppealController {

  @Autowired
  private AppealService appealService;


  @PostMapping(
          path = "/appeals",
          consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  public ResponseEntity<?> fileAppeals(@RequestPart("data")
                                       @Valid
                                       AppealDto appealDto,
                                       @RequestPart("file")
                                       MultipartFile attachment) throws IOException {

    appealDto.setAttachment(attachment.getBytes());
    return appealService.createAppeal(appealDto);
  }

  @GetMapping("appeals/{appealId}")
  public ResponseEntity<?> getAppealByAppealId(@PathVariable
                                                 Integer appealId) {
    return appealService.getAppealById(appealId);
  }

  @GetMapping("appeals")
  public ResponseEntity<?> getAllAppeals() {
    return appealService.getAllAppeals();
  }

}
