grails.project.work.dir = 'target'
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    inherits 'global'
    log 'warn'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        compile('org.codehaus.groovy.modules.http-builder:http-builder:0.6') {
            excludes "commons-logging", "xml-apis", "groovy"
        }
        test 'org.spockframework:spock-grails-support:0.7-groovy-2.0'
    }
    plugins {
        build(':release:2.2.1', ':rest-client-builder:1.0.3') {
            export = false
        }
        runtime ':resources:1.2.RC2'
        test(':spock:0.7') {
            exclude 'spock-grails-support'
            export = false
        }
    }
}