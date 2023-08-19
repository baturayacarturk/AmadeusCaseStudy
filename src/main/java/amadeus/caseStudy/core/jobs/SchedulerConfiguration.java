package amadeus.caseStudy.core.jobs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import amadeus.caseStudy.business.abstracts.FlightService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfiguration {
	
	private final FlightService flightService;
	private final String Url = "http://localhost:8080/api/mock/todaysManifest"
			;
	
	@Bean
	public TaskScheduler scheduleTask() {
		final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(2);
		return scheduler;
	}
	
	@Scheduled(fixedRate=24*60*60*1000)
	public void sendApiRequest() {
		flightService.sendApiRequest(Url);
	}
	
}
