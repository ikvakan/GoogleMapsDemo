package hr.ikvakan.betshop.mapper

import hr.ikvakan.betshop.model.BetshopLocationModel
import hr.ikvakan.betshop.retrofit.BetshopLocationItem
import javax.inject.Inject

class ModelMapperImpl
@Inject constructor() : ModelMapper<BetshopLocationItem, BetshopLocationModel> {
    override fun mapFromEntityItemToDomainModel(entityItem: BetshopLocationItem): BetshopLocationModel {
        return BetshopLocationModel(
            count = entityItem.count,
            betshops =  entityItem.betshops.map {
                BetshopLocationModel.BetshopsModel(
                    name = it.name,
                    BetshopLocationModel.BetshopsModel.LocationModel(
                        lng = it.location.lng,
                        lat = it.location.lat
                    ),
                    id = it.id,
                    county = it.county,
                    city_id = it.city_id,
                    city = it.city,
                    address = it.address
                )
            }
        )
    }
}