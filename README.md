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

## SPECIFYING A PREFIX

In order to handle automatic cache invalidation, it is recommended to include a versioning system in your CDN URLs.

You can use the _prefix_ config parameters to include your app name and version.

```
// To deploy all the apps at the bucket root in a apps directory
grails.resources.mappers.cdn.prefix = "apps/${appName}-${appVersion}/"
```


## SPECIFYING A PLUGIN PREFIX TO SHARE PLUGINS RESOURCES BETWEEN APPS

By default, plugins resources will be looked up with the following pattern "{url}${prefix}plugins/{pluginName}-{pluginVersion}/".

If you want to share plugins resources between different apps, you can define _pluginsPrefix_ in your config in order to override app _prefix_ and look up resources with the following pattern "{url}${pluginPefix}{pluginName}-{pluginVersion}/".

```
// To deploy all plugins at the bucket root in a plugins directory
grails.resources.mappers.cdn.pluginsPrefix = 'plugins/'
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
	
## SETTING UP AMAZON CLOUDFRONT CDN

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