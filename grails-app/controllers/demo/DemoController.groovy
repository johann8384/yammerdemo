package demo

import grails.plugin.dropwizard.metrics.timers.Timed
import grails.plugin.dropwizard.metrics.meters.Metered

class DemoController {

    @Timed('some timer')
    def someTimedAction() {
        render 'this was timed'
    }

    @Metered('some meter')
    def someMeteredAction() {
        render 'this was metered'
    }

    @Timed(value='some other timer', useClassPrefix=true)
    def someOtherTimedAction() {
        render 'this was timed'
    }

    @Metered(value='some other meter', useClassPrefix=true)
    def someOtherMeteredAction() {
        render 'this was metered'
    }
}
