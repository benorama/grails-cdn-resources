class CloudResourcesGrailsPlugin {
  // the plugin version
  def version = "0.1"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "1.3.5 > *"

  // the other plugins this plugin depends on
  def dependsOn = [resources:'1.0-alpha11 > *']
  def loadAfter = ['resources']

  // resources that are excluded from plugin packaging
  def pluginExcludes = [
          "grails-app/views/error.gsp",
          "web-app/css/**/*.*",
          "web-app/js/**/*.*"
  ]

  // TODO Fill in these fields
  def author = "Tomas Lin"
  def authorEmail = "tomaslin@gmail.com"
  def title = "Cloud Resources Plugin"
  def description = '''\\
Loads static resources using Content Delivery Networks and off-site cloud storage using the Resources plugin framework
'''
  // URL to the plugin's documentation
  def documentation = "http://grails.org/plugin/cloud-resources"

  def doWithWebDescriptor = { xml ->
  }

  def doWithSpring = {
  }

  def doWithDynamicMethods = { ctx ->
  }

  def doWithApplicationContext = { applicationContext ->
  }

  def onChange = { event ->
  }

  def onConfigChange = { event ->
  }
}