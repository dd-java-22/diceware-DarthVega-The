package edu.cnm.deepdive.diceware.controller;

import edu.cnm.deepdive.diceware.service.PassphraseService;
import jakarta.validation.constraints.Max;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passphrase")
@Validated
public class PassphraseController {

  private final PassphraseService service;

  @Autowired
  public PassphraseController(PassphraseService service) {
    this.service = service;
  }

  @PostMapping(path = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<String> post(
      @Max(20)
      @RequestParam(defaultValue = "5")
      int length
  ) {
    return service.generate(length);
  }

  @PostMapping(path = "/generate", produces = MediaType.TEXT_PLAIN_VALUE)
  public String post(
      @Max(20)
      @RequestParam(defaultValue = "5")
      int length,
      @Length(max = 5)
      @RequestParam(defaultValue = " ")
          String delimiter
  ) {
    return String.join(delimiter, service
        .generate(length));
  }
}
