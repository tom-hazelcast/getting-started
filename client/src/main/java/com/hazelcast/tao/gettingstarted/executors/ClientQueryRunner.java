package com.hazelcast.tao.gettingstarted.executors;

import static com.hazelcast.query.Predicates.and;
import static com.hazelcast.query.Predicates.equal;
import static com.hazelcast.query.Predicates.greaterThan;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.SqlPredicate;
import com.hazelcast.tao.gettingstarted.Employee;

@Component("queryRunner")
public class ClientQueryRunner implements CommandLineRunner, ApplicationContextAware
	{
		private final static Logger l = LoggerFactory.getLogger(ClientQueryRunner.class);
		private ApplicationContext  applicationContext;

		@SuppressWarnings(
			{
			    "unchecked",
			    "rawtypes"
			})

		@Override
		public void run(String... args) throws Exception
			{
				HazelcastInstance         client            = applicationContext.getBean(HazelcastInstance.class);

				IMap<Long, Employee>      employees         = client.getMap("employees");

				Predicate<String, String> fnamePredicate    = equal("firstName", "Tom");
				Collection<Employee>      employeesNamedTom = employees.values(fnamePredicate);

				l.info("found {} employees named 'Tom'", employeesNamedTom.size());

				Predicate<String, String> lnamePredicate   = equal("lastName", "ActualName");
				Predicate                 actNamePredicate = and(fnamePredicate, lnamePredicate);

				l.info("{} emploees match first and last name", employees.values(actNamePredicate).size());

				SqlPredicate         predicate = new SqlPredicate("lastName LIKE 'A%' AND age between 30 AND 40");
				Collection<Employee> matching  = employees.values(predicate);
				l.info("found {} matching employees", matching.size());

			}

		@SuppressWarnings("unchecked")
		public void sqlApiDemo(final IMap<Long, Employee> employees)
			{
				populateEmployees(employees);

				Predicate<String, String> fnamePredicate    = equal("firstName", "Tom");
				Collection<Employee>      employeesNamedTom = employees.values(fnamePredicate);

				l.info("found {} employees named 'Tom'", employeesNamedTom.size());

				Predicate<Long, Employee> lnamePredicate   = equal("lastName", "ActualName");
				Predicate<Long, Employee> actNamePredicate = and(fnamePredicate, lnamePredicate);

				l.info("{} emploees match first and last name", employees.values(actNamePredicate).size());

				SqlPredicate         predicate = new SqlPredicate("lastName LIKE 'A%' AND age between 30 AND 40");
				Collection<Employee> matching  = employees.values(predicate);
				l.info("found {} matching employees", matching.size());
			}

		public void populateEmployees(final IMap<Long, Employee> employees)
			{
				int      departmentOne = 1;
				int      departmentTwo = 2;

				Employee a             = new Employee(Long.valueOf(1), "Jane", "Doe", 31, departmentOne);
				Employee b             = new Employee(Long.valueOf(2), "John", "Smith", 45, departmentOne);
				Employee c             = new Employee(Long.valueOf(3), "Tom", "Notmyrealname", 22, departmentTwo);
				Employee d             = new Employee(Long.valueOf(4), "Tom", "AFakeName", 32, departmentTwo);
				Employee e             = new Employee(Long.valueOf(5), "Tom", "ActualName", 42, departmentTwo);

				employees.set(a.getEmpId(), a);
				employees.set(b.getEmpId(), b);
				employees.set(c.getEmpId(), c);
				employees.set(d.getEmpId(), d);
				employees.set(e.getEmpId(), e);

			}

		@SuppressWarnings("unchecked")
		public void criteriaApiDemo(IMap<Long, Employee> employees)
			{
				// put sample data in the map
				populateEmployees(employees);
				Predicate<Long, Employee> lastNameP = equal("lastName", "Doe");
				Predicate<Long, Employee> ageP      = greaterThan("age", Integer.valueOf(21));
				Predicate<Long, Employee> deptP     = equal("deptId", 1);

				Predicate<Long, Employee> andP      = and(lastNameP, ageP, deptP);

				// find all the employees that match the compound condition
				Collection<Employee>      matching  = employees.values(andP);

				l.info("found {} matching employees", matching.size());
			}

		public void pagedResultDemo(IMap<Long, Employee> employees, Predicate<Long, Employee> predicate)
			{
				PagingPredicate<Long, Employee> pagedResults = new PagingPredicate<>(predicate, 3);
				while (true)
					{
						Collection<Employee> empPage = employees.values(pagedResults);
						// use results
						// ...
						if (empPage.size() == 0)
							{
								break;
							}
						pagedResults.nextPage();
					}
			}

		@SuppressWarnings("unchecked")
		public void simpleAggregationDemo(IMap<Long, Employee> employees)
			{
				Predicate<Long, Employee> deptP  = equal("deptId", 1);

				// find the average age for the department named in the predicate;
				double                    avgAge = employees
				  .aggregate(Aggregators.<Map.Entry<Long, Employee>> integerAvg("age"), deptP);
				l.info("Overall average age: " + avgAge);

			}

		/**
		 * @return the applicationContext
		 */
		public ApplicationContext getApplicationContext()
			{
				return applicationContext;
			}

		/**
		 * @param applicationContext
		 *          the applicationContext to set
		 */
		@Override
		public void setApplicationContext(ApplicationContext applicationContext)
			{
				this.applicationContext = applicationContext;
			}
	}