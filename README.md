Cdn-resources -- Content Delivery Network support for grails resources plugin
=============================================================================

<a href="http://flattr.com/thing/304127/CND-Resources-plugin-for-grails" target="_blank"><img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" /></a>

## DESCRIPTION

This plugin allows you to use a content delivery network ( like Amazon CloudFront ) to deliver resources served by the grails resources plugin. It is loaded after the zipped and cached resources plugins so that any modifications made by these plugins will also apply.

## INSTALLATION

Define dependency in your _BuildConfig.groovy_:

```
compile ':cdn-resources:0.2.1'
```

Or, in your application directory, call

```bash
grails install-plugin cdn-resources
```

You would need to add the following parameters to your _Config.groovy_ .

```
grails.resources.mappers.cdn.enabled = true
// Default CDN URL
grails.resources.mappers.cdn.url = "http://static.mydomain.com/"
```

## SPECIFYING URLS PER MODULES

You can also define a separate CDN location per module ( for files being hosted by Google, for example ).

```
// Specific module URLs
grails.resources.cdn.moduleUrls = [ 'google' : 'http://www.google.com/apis', 'core' : 'http://subdomain.mysite.com' ]
```

## SPECIFYING A SHARED URL FOR PLUGINS

By default, plugins resources will be looked up with default CDN url and the following pattern "{grails.resources.cdn.url}/plugins/{pluginName}-{pluginVersion}/").

If you want to share plugins resources between different apps, you can define _pluginsUrl_ in your config  :

```
// Default CDN URL based on app name and app version
grails.resources.mappers.cdn.url = "http://static.mydomain.com/apps/${appName}-${appVersion}"
// Share plugins CDN URL
grails.resources.mappers.cdn.pluginsUrl = "http://static.mydomain.com/
```

## INCLUDING/EXCLUDING RESOURCES PER URIS

You can include/exclude specific resources URIs.

For example, to only include images except PNGs:

```
grails.resources.mappers.cdn.includes = [ '/images/**' ]
grails.resources.mappers.cdn.excludes = [ '**/*.png' ]
```

## INCLUDING/EXCLUDING RESOURCES PER IDS

You can also exclude specific resources IDs.

For example, to exclude bundled resources:

```
// Regex or Pattern list of IDs to exclude
grails.resources.mappers.cdn.excludeIds = [ 'bundle-*' ]
```

For example, to only include Twitter Bootstrap resources:

```
// Regex or Pattern list of IDs to include
grails.resources.mappers.cdn.includeIds = [ 'bootstrap-*' ] // Regex or Pattern list
```
	
## SETTING UP AMAZON CLOUDFRONT CDN SUPPORT

Before you can use this plugin, you need to set up a content delivery network to dispatch your resources. 

The following section describes how to do so on Amazon CloudFront. You would need an account for this, which you can sign up for at http://aws.amazon.com/cloudfront/

1 Login to your Amazon AWS Console - https://console.aws.amazon.com/cloudfront/home?

2 Click on Create Distribution

3 Select custom Origin and enter the URL of your site. 

![Set custom origin](https://github.com/tomaslin/grails-cdn-resources/raw/master/docs/origin.png "specifying an origin")

4 If you wish to map the URL to a CNAME ( ie, point to cdn.mydomain.com instead of 5kfd933kkdd.cloudfront.net ), you can specify this in the next screen.

5 Review the details and click OK.

![Review Details](https://github.com/tomaslin/grails-cdn-resources/raw/master/docs/details.png "Review Details")

6 You should now see your Cloudfront distribution. 

![Distribution](https://github.com/tomaslin/grails-cdn-resources/raw/master/docs/dist.png "Distribution details")

## A NOTE ON URLS

You will see an url of the form jlsadf423kl24hlf.cloudfront.net. This will be the value that you enter in the grails.resources.cdn.url value in Config.groovy.

```
grails.resources.mappers.cdn.url = "http://jlsadf423kl24hlf.cloudfront.net"
```