class CdnResourcesGrailsPlugin {
  // the plugin version
  def version = "0.2.2.RC4"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "1.3.1 > *"

  // the other plugins this plugin depends on
  def dependsOn = [resources:'1.0 > *']
  def loadAfter = ['resources']

  // resources that are excluded from plugin packaging
  def pluginExcludes = [
          "grails-app/views/error.gsp",
          "web-app/css/**/*.*",
          "web-app/js/**/*.*",
          "docs/*.*"
  ]

  def author = "Tomas Lin"
  def authorEmail = "tomaslin@gmail.com"
  def title = "Cdn Resources Plugin"
  def description = '''
Loads static resources using Content Delivery Networks using the Resources plugin framework
'''
  def license ='APACHE'
  def documentation = "https://github.com/tomaslin/grails-cdn-resources"
  def issueManagement = [ system: "github", url: "https://github.com/tomaslin/grails-cdn-resources/issues" ]
  def scm = [  url: "https://github.com/tomaslin/grails-cdn-resources" ]
}
