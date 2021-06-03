package large.media.company.campaign.manager

import large.media.company.campaign.manager.service.CampaignManagerService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CampaignManagerApplication(
	val campaignManagerService: CampaignManagerService
): ApplicationRunner {

	override fun run(args: ApplicationArguments?) {
		if(args?.nonOptionArgs?.size!! > 0) {
			campaignManagerService.calculateMostBeneficialCampaigns(args.nonOptionArgs[0])
		}
		else
			println("No args given.")
	}

}

fun main(args: Array<String>) {
	runApplication<CampaignManagerApplication>(*args)
}
