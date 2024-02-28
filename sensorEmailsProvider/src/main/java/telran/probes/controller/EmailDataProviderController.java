package telran.probes.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.service.EmailProviderService;


@RestController
@RequestMapping("/api/v1/emaildataprovidercontroller")
@RequiredArgsConstructor
@Slf4j
public class EmailDataProviderController {
final EmailProviderService providerService;
@GetMapping("/{id}")
String[] getEmails(@PathVariable(name="id") long id) {
	String[] emails =  providerService.getSensorEmails(id);
	log.debug("emails received are {}", Arrays.deepToString(emails));
	return emails;
}
}
