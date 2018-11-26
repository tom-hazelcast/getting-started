package com.hazelcast.tao.gettingstarted.query.criteria;
/*
 * import static com.hazelcast.query.Predicates.and; import static com.hazelcast.query.Predicates.equal;
 * //import static com.hazelcast.query.Predicates.not; //import static com.hazelcast.query.Predicates.or;
 * 
 * import java.util.Collection;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import org.springframework.boot.CommandLineRunner;
 * import org.springframework.context.ApplicationContext; import
 * org.springframework.context.ApplicationContextAware; import org.springframework.stereotype.Component;
 * 
 * import com.hazelcast.core.HazelcastInstance; import com.hazelcast.core.IMap; import
 * com.hazelcast.tao.gettingstarted.common.model.Employee; import com.hazelcast.query.Predicate; import
 * com.hazelcast.query.SqlPredicate;
 * 
 * @Component("criteriaQueryRunner") public class ClientCriteriaQueryRunner implements CommandLineRunner,
 * ApplicationContextAware { private final static Logger l =
 * LoggerFactory.getLogger(ClientCriteriaQueryRunner.class); private ApplicationContext applicationContext;
 * 
 * @SuppressWarnings( { "unchecked", "rawtypes" })
 * 
 * @Override public void run(String... args) throws Exception { HazelcastInstance client = (HazelcastInstance)
 * applicationContext.getBean("clientInstance");
 * 
 * IMap<Long, Employee> employees = client.getMap("employees"); Employee a = new Employee(Long.valueOf(1),
 * "Jane", "Doe"); Employee b = new Employee(Long.valueOf(2), "John", "Smith"); Employee c = new
 * Employee(Long.valueOf(3), "Tom", "Notmyrealname"); Employee d = new Employee(Long.valueOf(4), "Tom",
 * "FakeName"); Employee e = new Employee(Long.valueOf(4), "Tom", "ActualName"); employees.set(a.getEmpId(),
 * a); employees.set(b.getEmpId(), b); employees.set(c.getEmpId(), c); employees.set(d.getEmpId(), d);
 * employees.set(e.getEmpId(), e);
 * 
 * SqlPredicate sqlPredicate = new SqlPredicate("where firstName = 'Tom'"); employees.entrySet(sqlPredicate);
 * 
 * Predicate<String, String> fnamePredicate = equal("firstName", "Tom"); Collection<Employee>
 * employeesNamedTom = employees.values(fnamePredicate);
 * 
 * l.info("found {} employees named 'Tom'", employeesNamedTom.size());
 * 
 * Predicate<String, String> lnamePredicate = equal("lastName", "ActualName"); Predicate actNamePredicate =
 * and(fnamePredicate, lnamePredicate);
 * 
 * l.info("{} emploees match first and last name", employees.values(actNamePredicate).size());
 * 
 * // }
 * 
 * / **
 * 
 * @return the applicationContext / public ApplicationContext getApplicationContext() { return
 * applicationContext; }
 * 
 * / **
 * 
 * @param applicationContext the applicationContext to set /
 * 
 * @Override public void setApplicationContext(ApplicationContext applicationContext) {
 * this.applicationContext = applicationContext; }
 * 
 * }
 */