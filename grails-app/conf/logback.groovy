import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

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

appender('JSON', ConsoleAppender) {
    encoder("net.logstash.logback.encoder.LogstashEncoder") {
        def appName = System.getProperty('demo.app.name', 'DEFAULT_APP_NAME')
        def appEnv = System.getProperty('demo.app.environment', 'DEFAULT_APP_ENVIRONMENT')
        def appClient = System.getProperty('demo.app.environment', 'DEFAULT_CLIENT_NAME')
		customFields = "{\"appName\":\"$appName\", \"appEnv\":\"$appEnv\", \"appClient\":\"$appClient\"}"
		includeCallerData = "true"
    }
}

appender('METRICS', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        def appName = System.getProperty('demo.app.name', 'DEFAULT_APP_NAME')
        def appEnv = System.getProperty('demo.app.environment', 'DEFAULT_APP_ENVIRONMENT')
        def appClient = System.getProperty('demo.app.environment', 'DEFAULT_CLIENT_NAME')
        pattern = "[$appName] [$appEnv] [$appClient] %level %logger - %msg%n"
    }
}

root(INFO, ["JSON"])

logger 'grails.plugin.dropwizard.DropwizardMetricsGrailsPlugin',
        INFO, ['JSON', 'METRICS'], false