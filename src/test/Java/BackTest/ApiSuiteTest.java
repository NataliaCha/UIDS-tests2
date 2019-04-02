package BackTest; /**
 * Created by natalia.chaplygina on 14.01.2019.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//public class ApiSuiteTest {


@RunWith(Suite.class)
@SuiteClasses({APILookupTest.class,CatalogTest.class, CategoryControllerTest.class,DIfTest.class,DqPoliciesTest.class,EntitiesTest.class, LookupEntryControllerTest.class,NotificationsTest.class, RegionControllerTest.class, SynonymControllerTest.class, TaxonomyControllerTest.class,
UserInfoTest.class} )
public class ApiSuiteTest {

}




