package mapper

import ConfigItem

class ConfigListItemMapper : Mapper<String, ConfigItem> {
    override fun map(input: String): ConfigItem {
        val configTime = input.substringBefore("/").trim()
        val configCommand = "/"+input.substringAfter(" /")

        return ConfigItem(configTime, configCommand)
    }
}
