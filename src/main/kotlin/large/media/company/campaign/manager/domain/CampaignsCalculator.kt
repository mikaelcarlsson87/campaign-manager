package large.media.company.campaign.manager.domain

import large.media.company.campaign.manager.valueobject.CampaignInfo
import large.media.company.campaign.manager.valueobject.CampaignsResult
import large.media.company.campaign.manager.valueobject.CustomerInfo
import org.springframework.stereotype.Component
import kotlin.properties.Delegates

@Component
class CampaignsCalculator {

    lateinit var highestRevenueTriple: Triple<List<Int>, Int, Int>

    var maxImpressions by Delegates.notNull<Int>()

    fun calculateBestCombination(campaignInfo: CampaignInfo): CampaignsResult {
        highestRevenueTriple = Triple(emptyList(), 0, 0)
        maxImpressions = campaignInfo.maxImpressions
        val customerCounters = arrayListOf(0, 0, 0, 0, 0, 0, 0)

        calculateBestCombination(campaignInfo.customers, customerCounters)

        return CampaignsResult(campaignInfo.customers, highestRevenueTriple.first, highestRevenueTriple.second, highestRevenueTriple.third)
    }

    private fun calculateBestCombination(
        customers: List<CustomerInfo>,
        customerCounters: ArrayList<Int>,
        customerNr: Int = 0,
        totalImpressions: Int = 0,
        totalRevenue: Int = 0) {

        if(customerNr >= customers.size)
            return

        //Go down to the bottom of the stack
        calculateBestCombination(customers, customerCounters, customerNr+1, totalImpressions, totalRevenue)
        val currentCustomer = customers[customerNr]

        if (wouldExceedThreshold(totalImpressions, currentCustomer.impressions)) {
            replaceHighestTripleIfNeeded(Triple(customerCounters.toMutableList(), totalImpressions, totalRevenue))
            customerCounters[customerNr] = 0
            return
        }

        customerCounters[customerNr]++
        calculateBestCombination(customers, customerCounters, customerNr,totalImpressions + currentCustomer.impressions, totalRevenue + currentCustomer.price)
    }

    private fun replaceHighestTripleIfNeeded(latestTriple: Triple<List<Int>, Int, Int>) {
        if(latestTriple.third > highestRevenueTriple.third)
            highestRevenueTriple = latestTriple
    }

    private fun wouldExceedThreshold(
        totalImpressions: Int,
        impressions: Int
    ) = totalImpressions + impressions > maxImpressions
}
