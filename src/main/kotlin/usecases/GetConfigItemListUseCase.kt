package usecases

import ConfigItem
import mapper.ConfigListItemMapper

class GetConfigItemListUseCase (private val configListMapper: ConfigListItemMapper) {
    fun invoke(listOfConfigItemStrings: List<String>): List<ConfigItem> {
        val configItems = mutableListOf<ConfigItem>()
        listOfConfigItemStrings.forEach { configItemString ->
            configItems.add(configListMapper.map(configItemString))
        }
        return configItems
    }
}