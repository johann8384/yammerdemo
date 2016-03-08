package demo

import com.codahale.metrics.MetricRegistry
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
@Stepwise
class DemoControllerSpec extends Specification {

    static doWithSpring = {
        dropwizardMetricsRegistry MetricRegistry
    }

    void 'test the @Metered annotation'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someMeteredAction()

        then:
        response.text == 'this was metered'
        registry.meter('some meter').count == 1
    }

    void 'test the @Metered annotation again'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someMeteredAction()

        then:
        response.text == 'this was metered'
        registry.meter('some meter').count == 2
    }

    void 'test the @Timed annotation'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someTimedAction()

        then:
        response.text == 'this was timed'
        registry.timer('some timer').count == 1
    }

    void 'test the @Timed annotation again'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someTimedAction()

        then:
        response.text == 'this was timed'
        registry.timer('some timer').count == 2
    }

    void 'test the @Metered annotation with useClassPrefix set to true'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someOtherMeteredAction()

        then:
        response.text == 'this was metered'
        registry.meter('demo.DemoController.some other meter').count == 1
    }

    void 'test the @Metered annotation again with useClassPrefix set to true'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someOtherMeteredAction()

        then:
        response.text == 'this was metered'
        registry.meter('demo.DemoController.some other meter').count == 2
    }

    void 'test the @Timed annotation with useClassPrefix set to true'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someOtherTimedAction()

        then:
        response.text == 'this was timed'
        registry.timer('demo.DemoController.some other timer').count == 1
    }

    void 'test the @Timed annotation again with useClassPrefix set to true'() {
        setup:
        def registry = applicationContext.dropwizardMetricsRegistry

        when:
        controller.someOtherTimedAction()

        then:
        response.text == 'this was timed'
        registry.timer('demo.DemoController.some other timer').count == 2
    }
}
