package com.github.sparsick.flyway.demo.webapp;

import com.github.sparsick.flyway.demo.webapp.HomePage;
import com.github.sparsick.flyway.demo.webapp.WicketApplication;
import java.util.Collections;
import java.util.List;

import com.github.sparsick.flyway.demo.webapp.domain.Person;
import com.github.sparsick.flyway.demo.webapp.repository.PersonRepository;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {

    private WicketTester tester;

    private ApplicationContextMock applicationContextMock;


    @Before
    public void setUp() {
        applicationContextMock = new ApplicationContextMock();
        WicketApplication wicketApp = new WicketApplication();
        wicketApp.getComponentInstantiationListeners()
        .add(new SpringComponentInjector(wicketApp,applicationContextMock));
        tester = new WicketTester(wicketApp);

        applicationContextMock.putBean(new PersonRepository(){
            @Override
            public List<Person> findAllPersons() {
              return Collections.emptyList();
            }
        });

    }


    @Test
    public void homepageRendersSuccessfully() {
        // start and render the test page
        tester.startPage(HomePage.class);

        // assert rendered page class
        tester.assertRenderedPage(HomePage.class);
    }
}
