package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable{
	
	
	
	private int id;
	private String email;
	private String name;
	private String contact;
	private String address;
	private int age;
	private String gender;
	private String password;
	
	public List<Integer> numofTickets;
//	public List<Flight> flightRegistered;
	
//	public static List<Customer> customers = UserRunner.getCustomers();
	
	public Customer()
	{
		
	}
	
	public Customer(int id, String email, String name, String contact, 
			String address, int age, String gender,
			String password) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.password = password;
		this.numofTickets = new ArrayList<>();
//		this.flightRegistered = new ArrayList<>();
	}
	
	
	
	
	// ToStrings
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getNumofTickets() {
		return numofTickets;
	}

	public void setNumofTickets(List<Integer> numofTickets) {
		this.numofTickets = numofTickets;
	}

	

	/**
	 * Add new customer
	 * @param : NA
	 * @return : NA
	 */
	public void addNewCustomer()
	{
		// Take Input from user
		// name , address, email, password, age, address
		
		ArrayList<Customer> customers = Customer.View();
		
        if (customers.isEmpty()) {
            this.id = 1;
        } else {
            this.id = customers.get((customers.size() - 1)).id + 1; 
        }
        customers.add(this);
        File file = new File("Customer.ser");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (int i = 0; i < customers.size(); i++) {
                outputStream.writeObject(customers.get(i));
            }
        } 
        
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex);
        } catch (IOException ex) 
        {
            System.out.println(ex);
        } 
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
	}
	
	
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", name=" + name + ", contact=" + contact + ", address="
				+ address + ", age=" + age + ", gender=" + gender + ", password=" + password + ", numofTickets="
				+ numofTickets + "]";
	}

	/**
	 * Search the customer with the given id and prints the data
	 * @param id
	 * return : nothing
	 */
	public void searchUser(int id)
	{
		boolean isFound = false;
//        Customer customerWithTheID = customerCollection.get(0);
		
		ArrayList<Customer> customerCollection = Customer.View();
		Customer customerWithTheID=null;
		
        for (Customer c : customerCollection) {
            if (id == c.getId()) {
                System.out.printf("Customer Found...!!!Here is the Full Record...");
         
                isFound = true;
                customerWithTheID = c;
                break;
            }
        }
        if (isFound) {
            System.out.println(customerWithTheID.toString());
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", id);
        }
	}
	
	
	/**
	 * return true if email id is already registered
	 * @param id
	 * return : nothing
	 */
	public boolean emailUnique(String emailId)
	{
		ArrayList<Customer> customerCollection = Customer.View();
		boolean isUnique = false;
        for (Customer c : customerCollection) {
            if (emailId.equals(c.getEmail())) {
                isUnique = true;
                break;
            }
        }
        return isUnique;
	}
	
	
	
	
	/**
	 * Delete customer
	 * @param : id
	 * @return : NA
	 */
	public void deleteCustomer(int id)
	{
		ArrayList<Customer> customerCollection = Customer.View();
		
        boolean isFound = false;
        for (int i = 0; i < customerCollection.size(); i++) {
            if (customerCollection.get(i).id == id) {
            	customerCollection.remove(i);
            	isFound = true;
            }
        }
        
        if (isFound) {
        	
            System.out.printf("\n%-50sPrinting all  Customer's Data after deleting Customer with the ID %s.....!!!!\n", "", id);
        } else {
            System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", id);
        }
	}
	
	
	
	
	
	
	/**
	 * Display customer
	 * @param : id
	 * @return : NA
	 */
	public void printCustomer()
	{
		ArrayList<Customer> customerCollection = Customer.View();
		for(Customer c : customerCollection)
		{
			System.out.println(c);
		}
	}
	
	
	public static ArrayList<Customer> View() {
        ArrayList<Customer> CustomerList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
        	// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Customer.ser"));
            boolean EOF = false;
            	
            // Keep reading file until file ends
            while (!EOF) {
                try {
                    Customer myObj = (Customer) inputStream.readObject();
                    CustomerList.add(myObj);
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        } 
        
        catch (IOException e) {
            System.out.println(e);
        } 
        
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return CustomerList;
    }

	
	
}
