package com.hazelcast.tao.gettingstarted;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

public class Employee implements IdentifiedDataSerializable
	{
		@SuppressWarnings("unused")
		private static final long serialVersionUID = 1L;

		private Long              empId;
		private String            firstName;
		private String            lastName;
		private Integer           age;
		private Integer           deptId;

		public Employee()
			{
				this(null, null, null);
			}

		public Employee(Long empId, String firstName, String lastName)
			{
				this(empId, firstName, lastName, -1, -1);
			}

		public Employee(Long empId, String firstName, String lastName, Integer age, Integer deptId)
			{
				super();
				this.empId     = empId;
				this.firstName = firstName;
				this.lastName  = lastName;
				this.age       = age;
				this.deptId    = deptId;
			}

		@Override
		public void writeData(ObjectDataOutput out) throws IOException
			{
				out.writeLong(this.empId);
				out.writeUTF(this.firstName);
				out.writeUTF(this.lastName);
			}

		@Override
		public void readData(ObjectDataInput in) throws IOException
			{
				this.empId     = in.readLong();
				this.firstName = in.readUTF();
				this.lastName  = in.readUTF();
			}

		@Override
		/**
		 * returns '1' as the factory Id.
		 */
		public int getFactoryId()
			{
				return 1;
			}

		@Override
		/**
		 * returns '1' as the class Id.
		 */
		public int getId()
			{
				return 1;
			}

		/**
		 * @return the empId
		 */
		public Long getEmpId()
			{
				return empId;
			}

		/**
		 * @param empId
		 *          the empId to set
		 */
		public void setEmpId(Long empId)
			{
				this.empId = empId;
			}

		/**
		 * @return the firstName
		 */
		public String getFirstName()
			{
				return firstName;
			}

		/**
		 * @param firstName
		 *          the firstName to set
		 */
		public void setFirstName(String firstName)
			{
				this.firstName = firstName;
			}

		/**
		 * @return the lastName
		 */
		public String getLastName()
			{
				return lastName;
			}

		/**
		 * @param lastName
		 *          the lastName to set
		 */
		public void setLastName(String lastName)
			{
				this.lastName = lastName;
			}

		/**
		 * @return the deptId
		 */
		public Integer getDeptId()
			{
				return deptId;
			}

		/**
		 * @param deptId
		 *          the deptId to set
		 */
		public void setDeptId(Integer deptId)
			{
				this.deptId = deptId;
			}

		/**
		 * @return the age
		 */
		public Integer getAge()
			{
				return age;
			}

		/**
		 * @param age
		 *          the age to set
		 */
		public void setAge(Integer age)
			{
				this.age = age;
			}

	}
