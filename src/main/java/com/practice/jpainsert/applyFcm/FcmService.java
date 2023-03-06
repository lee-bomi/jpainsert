package com.practice.jpainsert.applyFcm;

import com.google.api.client.util.Value;
import com.google.api.gax.rpc.ApiException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FcmService {

//	@Value("${spring.fcm.certification}")
//	private String credential;

	@PostConstruct
	public void initialize() throws IOException {

		ClassPathResource resource = new ClassPathResource("tossme-22f19-firebase-adminsdk-rcm2y-472b4affb6.json");

		try (InputStream stream = resource.getInputStream()) {
			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(stream))
				.build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
				log.info("============== FilebaseApp initialization complete ===============");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("================ 에러났어 =========================");

		}

		System.out.println("option에 firebaseoption build됨");
	}


}
