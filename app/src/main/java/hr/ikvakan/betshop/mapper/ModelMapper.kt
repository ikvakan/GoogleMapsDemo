package hr.ikvakan.betshop.mapper

interface ModelMapper<EntityItem,DomainModel> {
    fun mapFromEntityItemToDomainModel(entityItem: EntityItem) : DomainModel
}