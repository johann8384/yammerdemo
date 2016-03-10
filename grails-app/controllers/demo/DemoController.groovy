package demo

import grails.plugin.dropwizard.metrics.timers.Timed
import grails.plugin.dropwizard.metrics.meters.Metered

class DemoController {

    @Timed('some timer')
    def someTimedAction() {
        flash.message = 'someTimedAction was invoked'
        redirect uri: '/'
    }

    @Metered('some meter')
    def someMeteredAction() {
        flash.message = 'someMeteredAction was invoked'
        redirect uri: '/'
    }

    @Timed(value='some other timer', useClassPrefix=true)
    def someOtherTimedAction() {
        flash.message = 'someOtherTimedAction was invoked'
        redirect uri: '/'
    }

    @Metered(value='some other meter', useClassPrefix=true)
    def someOtherMeteredAction() {
        flash.message = 'someOtherMeteredAction was invoked'
        redirect uri: '/'
    }
}
