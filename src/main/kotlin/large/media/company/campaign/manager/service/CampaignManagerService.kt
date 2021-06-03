package large.media.company.campaign.manager.service

import large.media.company.campaign.manager.domain.CampaignsCalculator
import large.media.company.campaign.manager.util.FileHandler
import large.media.company.campaign.manager.valueobject.CampaignInfo
import large.media.company.campaign.manager.valueobject.CampaignsResult
import large.media.company.campaign.manager.valueobject.CustomerInfo
import org.springframework.stereotype.Component
import kotlin.streams.toList

@Component
class CampaignManagerService(
    val fileHandler: FileHandler,
    val campaignsCalculator: CampaignsCalculator
) {

    fun calculateMostBeneficialCampaigns(path: String?): CampaignsResult {
        if(path == null) {
            throw IllegalArgumentException("No path given!")
        }

        val allLines = fileHandler.readFile(path)
        val campaignInfo = toCampaignInfo(allLines)

        val campaignsResult = campaignsCalculator.calculateBestCombination(campaignInfo)
        campaignsResult.prettyPrint()
        return campaignsResult
    }

    private fun toCampaignInfo(lines: List<String>): CampaignInfo {
        val maxImpressions = lines[0].toInt()

        val customerInfoList = lines
            .stream()
            .skip(1)
            .map { it.split(",") }
            .map { CustomerInfo(it[0], it[1].toInt(), it[2].toInt()) }
            .toList()

        return CampaignInfo(maxImpressions, customerInfoList)
    }

    private fun CampaignsResult.prettyPrint() {
        getCampaignResultPerCustomer()
            .forEach {
                println("${it.name},${it.nrOfCampaigns},${it.totalImpressions},${it.totalRevenue}")
            }
        println("$totalImpressions,$totalRevenue")
    }
}
