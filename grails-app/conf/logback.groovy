import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}

appender('METRICS', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        def appName = System.getProperty('demo.app.name', 'DEFAULT_APP_NAME')
        def appEnv = System.getProperty('demo.app.environment', 'DEFAULT_APP_ENVIRONMENT')
        def appClient = System.getProperty('demo.app.environment', 'DEFAULT_CLIENT_NAME')
        pattern = "[$appName] [$appEnv] [$appClient] %level %logger - %msg%n"
    }
}

logger 'grails.plugin.dropwizard.DropwizardMetricsGrailsPlugin',
        INFO, ['METRICS'], false