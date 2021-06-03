package large.media.company.campaign.manager

import large.media.company.campaign.manager.service.CampaignManagerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CampaignManagerServiceTest(
    @Autowired
    val campaignManagerService: CampaignManagerService,
) {

    @Test
    fun `scenario 1`() {

        val path = getFilePath("sample_input_1.txt")
        val mostBeneficialCampaigns = withTimeStats {
            campaignManagerService.calculateMostBeneficialCampaigns(path)
        }

        assertEquals(mostBeneficialCampaigns.totalImpressions, 32000000)
        assertEquals(mostBeneficialCampaigns.totalRevenue, 3620)

    }

    //@Test //Needs increased stack-size. -Xss1G for instance
    fun `scenario 2`() {

        val path = getFilePath("sample_input_2.txt")
        val mostBeneficialCampaigns = withTimeStats {
            campaignManagerService.calculateMostBeneficialCampaigns(path)
        }

        assertEquals(mostBeneficialCampaigns.totalImpressions, 50000000)
        assertEquals(mostBeneficialCampaigns.totalRevenue, 51014000)
    }

    @Test
    fun `scenario 3`() {

        val path = getFilePath("sample_input_3.txt")
        val mostBeneficialCampaigns = withTimeStats {
            campaignManagerService.calculateMostBeneficialCampaigns(path)
        }

        assertEquals(mostBeneficialCampaigns.totalImpressions, 2000000000)
        assertEquals(mostBeneficialCampaigns.totalRevenue, 13330000)
    }

    /* Helpers */

    private fun getFilePath(fileName: String) = javaClass.classLoader.getResource(fileName)?.path

    private fun <T> withTimeStats(function: () -> T): T {
        println("Starting")
        val start = System.currentTimeMillis()
        val triple = function.invoke()
        val totalms = System.currentTimeMillis() - start
        println("Finished in ${totalms}ms")
        return triple
    }
}