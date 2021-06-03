package large.media.company.campaign.manager.valueobject

data class CustomerResult(
    val name: String,
    val nrOfCampaigns: Int,
    val totalImpressions: Int,
    val totalRevenue: Int
) {
}