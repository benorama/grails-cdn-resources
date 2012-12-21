package grails.plugin.cdnresources

import org.grails.plugin.resource.mapper.MapperPhase

class CdnResourceMapper {

    static priority = 15000 // after resources have been zipped, cached + properly beaten
    static phase = MapperPhase.DISTRIBUTION

    def grailsApplication

    def map(resource, config) {

        if (grailsApplication.config.grails?.resources?.cdn) {
            config += config + grailsApplication.config.grails.resources.cdn // For old plugin config compatibility
        }

        if (config.enabled && !resource.linkUrl.startsWith('http')){ // Do not include resources that have already been overridden (starts with http)

            if (config.excludeIds) {
                if (config.excludeIds instanceof Collection) {
                    if (config.excludeIds.any { resource.id?.find(it) }) return null
                } else {
                    log.warn "CDN excludeIds config ignored, it must be a collection of pattern"
                }
            }

            if (config.includeIds) {
                if (config.includeIds instanceof Collection) {
                    if (config.excludeIds.any { !resource.id?.find(it) }) return null
                } else {
                    log.warn "CDN includeIds config ignored, it must be a collection of pattern"
                }
            }

            def url
            if (resource.module?.name && config.moduleUrls && config.moduleUrls[resource.module.name]){
                url = config.moduleUrls[ resource.module.name ]
            }
            if (!url){
                url = config.url
                if (!url.endsWith('/')) url = "$url/"
                if (resource.linkUrl.startsWith('/plugins') && config.pluginsPrefix) {
                    String pluginsPrefix = config.pluginsPrefix.startsWith('/') ? config.pluginsPrefix.replaceFirst('/', '') : config.pluginsPrefix
                    pluginsPrefix = pluginsPrefix.replaceFirst('plugins', '').replaceFirst('//', '/')
                    url = "$url$pluginsPrefix"
                } else if (config.prefix) {
                    String prefix = config.prefix.startsWith('/') ? config.prefix.replaceFirst('/', '') : config.prefix
                    url = "$url$prefix"
                }
            }

            if (url) {
                if (url.endsWith('/')) {
                    url = url[0..-2]
                }
                resource.linkOverride = url + resource.linkUrl
                if (log.debugEnabled) {
                    log.debug "CDN link override ${resource.linkOverride}"
                }
            }
        }
    }

}