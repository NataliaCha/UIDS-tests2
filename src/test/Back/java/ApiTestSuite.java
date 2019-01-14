/**
 * Created by natalia.chaplygina on 14.01.2019.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
//public class ApiTestSuite {


    @RunWith(Suite.class)
    @Suite.SuiteClasses({CategoryControllerTest.class, LookupEntryControllerTest.class,RegionControllerTest.class, SynonymControllerTest.class,TaxonomyControllerTest.class} )
    public class ApiTestSuite {

    }




