package large.media.company.campaign.manager.valueobject

class CampaignsResult(
    val customers: List<CustomerInfo>,
    val campaigns: List<Int>,
    val totalImpressions: Int,
    val totalRevenue: Int,
) {

    fun getCampaignResultPerCustomer(): MutableList<CustomerResult> {
        val totalCampaignsPerCustomer = mutableListOf<CustomerResult>()
        for(ndx in customers.indices) {
            val customerInfo = customers[ndx]
            val numberOfCampaigns = campaigns[ndx]

            val totalImpressionsForCustomer = numberOfCampaigns * customerInfo.impressions
            val totalRevenueForCustomer = numberOfCampaigns * customerInfo.price

            totalCampaignsPerCustomer.add(CustomerResult(customerInfo.name, numberOfCampaigns, totalImpressionsForCustomer, totalRevenueForCustomer))
        }

        return totalCampaignsPerCustomer
    }
}