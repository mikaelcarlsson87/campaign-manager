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
        println("Scenario 1")
        val path = getFilePath("sample_input_1.txt")
        val mostBeneficialCampaigns = withTimeStats {
            campaignManagerService.calculateMostBeneficialCampaigns(path)
        }

        assertEquals(32000000, mostBeneficialCampaigns.totalImpressions)
        assertEquals(3620, mostBeneficialCampaigns.totalRevenue)
    }

    //@Test //Needs increased stack-size.
    fun `scenario 2`() {
        println("Scenario 2")
        val path = getFilePath("sample_input_2.txt")
        val mostBeneficialCampaigns = withTimeStats {
            campaignManagerService.calculateMostBeneficialCampaigns(path)
        }

        assertEquals(50000000, mostBeneficialCampaigns.totalImpressions)
        assertEquals(51014000, mostBeneficialCampaigns.totalRevenue)
    }

    @Test
    fun `scenario 3`() {
        println("Scenario 3")
        val path = getFilePath("sample_input_3.txt")
        val mostBeneficialCampaigns = withTimeStats {
            campaignManagerService.calculateMostBeneficialCampaigns(path)
        }

        assertEquals(2000000000, mostBeneficialCampaigns.totalImpressions)
        assertEquals(13330000, mostBeneficialCampaigns.totalRevenue)
    }

    /* Helpers */

    private fun getFilePath(fileName: String) = javaClass.classLoader.getResource(fileName)?.path

    private fun <T> withTimeStats(function: () -> T): T {
        println("Starting")
        val start = System.currentTimeMillis()
        val result = function.invoke()
        val totalms = System.currentTimeMillis() - start
        println("Finished in ${totalms}ms")
        return result
    }
}