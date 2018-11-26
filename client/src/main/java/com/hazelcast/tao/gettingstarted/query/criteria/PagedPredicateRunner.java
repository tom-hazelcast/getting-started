package com.hazelcast.tao.gettingstarted.query.criteria;

/*
 * import static com.hazelcast.query.Predicates.and; import static com.hazelcast.query.Predicates.equal;
 * //import static com.hazelcast.query.Predicates.not; //import static com.hazelcast.query.Predicates.or;
 * 
 * import java.util.Collection; import java.util.Comparator; import java.util.Map; import java.util.Map.Entry;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import org.springframework.boot.CommandLineRunner;
 * import org.springframework.context.ApplicationContext; import
 * org.springframework.context.ApplicationContextAware; import org.springframework.stereotype.Component;
 * 
 * import com.hazelcast.core.HazelcastInstance; import com.hazelcast.core.IMap; import
 * com.hazelcast.tao.gettingstarted.common.model.Employee; import com.hazelcast.query.PagingPredicate; import
 * com.hazelcast.query.Predicate;
 * 
 * @Component("sqlQueryRunner") public class PagedPredicateRunner implements CommandLineRunner,
 * ApplicationContextAware { private final static Logger l =
 * LoggerFactory.getLogger(PagedPredicateRunner.class); private ApplicationContext applicationContext;
 * 
 * @SuppressWarnings( { "unchecked", "rawtypes" })
 * 
 * @Override public void run(String... args) throws Exception { HazelcastInstance client = (HazelcastInstance)
 * applicationContext.getBean("clientInstance"); // Employee foo;
 * 
 * IMap<Long, Employee> employees = client.getMap("employees"); Employee a = new Employee(Long.valueOf(1),
 * "Jane", "Doe"); Employee b = new Employee(Long.valueOf(2), "John", "Smith"); Employee c = new
 * Employee(Long.valueOf(3), "Tom", "Notmyrealname"); Employee d = new Employee(Long.valueOf(4), "Tom",
 * "FakeName"); Employee e = new Employee(Long.valueOf(4), "Tom", "ActualName"); employees.set(a.getEmpId(),
 * a); employees.set(b.getEmpId(), b); employees.set(c.getEmpId(), c); employees.set(d.getEmpId(), d);
 * employees.set(e.getEmpId(), e);
 * 
 * / * For the paged example, put more entries in the map. / for(int i=0;i<1000;i++){Employee emp=new
 * Employee(Long.valueOf(i),"fname-"+i,"lname-"+i);employees.set(emp.getEmpId(),emp);}
 * 
 * // Create a comparator which helps to sort in descending order // of the id field - note that this wouldn't
 * always be what you // want. Comparator<Map.Entry>descendingComparator=new Comparator<Map.Entry>(){@Override
 * public int compare(Map.Entry e1,Map.Entry e2){Employee emp1=(Employee)e1.getValue();Employee
 * emp2=(Employee)e2.getValue();return emp2.getId()-emp1.getId();}};
 * 
 * Predicate<Long,Employee>evenIdPredicate=new Predicate<Long,Employee>(){
 * 
 * private static final long serialVersionUID=1L;
 * 
 * @Override public boolean apply(Entry<Long,Employee>entry){return entry.getValue().getEmpId()%2==0l;}
 * 
 * };
 * 
 * Predicate<String,String>fnamePredicate=equal("firstName","Tom");Collection<Employee>employeesNamedTom=
 * employees.values(fnamePredicate);
 * 
 * l.info("found {} employees named 'Tom'",employeesNamedTom.size());
 * 
 * Predicate<String,String>lnamePredicate=equal("lastName","ActualName");Predicate
 * actNamePredicate=and(fnamePredicate,lnamePredicate);
 * 
 * // a predicate which filters out non ClassA students, sort them descending order and fetches 4 // students
 * for each page PagingPredicate pagingPredicate=new PagingPredicate(evenIdPredicate,descendingComparator,4);
 * 
 * l.info("{} emploees match first and last name",employees.values(actNamePredicate).size());int
 * pageSz=0;while(pageSz>0){
 * 
 * }
 * 
 * // }
 * 
 * / ***@return the applicationContext
 * 
 * 
 * }
 */