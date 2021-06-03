package large.media.company.campaign.manager.valueobject

data class CampaignInfo(
    val maxImpressions: Int,
    val customers: List<CustomerInfo>
)