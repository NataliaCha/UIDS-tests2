package BackTest; /**
 * Created by natalia.chaplygina on 14.01.2019.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//public class ApiSuiteTest {


@RunWith(Suite.class)
@SuiteClasses({CategoryControllerTest.class, LookupEntryControllerTest.class, RegionControllerTest.class, SynonymControllerTest.class, TaxonomyControllerTest.class,
APILookupTest.class,/*APIUserControllerTest.class,*/DqPoliciesTest.class,EntitiesTest.class,NotificationsTest.class} )
public class ApiSuiteTest {

}




