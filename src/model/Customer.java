package model;

import java.util.Objects;
import java.util.regex.Pattern;
//creating Customer class for maintaining all details of customer
public class Customer {
    private String firstName;
    private String lastName;
    private String email;

//    Using regular expression for avoiding the customer by entering invalid email
    private final String emailRegex = (".+@.+\\..+") ;
    private final Pattern pattern = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);

    public Customer(String firstName, String lastName, String email){
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Oops! you have entered invalid email");
        }
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;

    }

//    Adding setters and getters for email, first name and last name
    public String getFirstName(){
        return firstName;
    }
    public String getEmail(){
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    using toString method for getting result in proper format
    @Override
    public String toString(){
        return "First name: " + this.firstName + "  Last name: " + this.lastName + "  Email: " + email;
    }

//    Reference : https://www.baeldung.com/java-equals-hashcode-contracts
    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Customer)){
            return false;
        }

        Customer other = (Customer) obj;
        boolean firstNameEquals = (this.firstName == null && other.firstName == null)||
                (this.firstName != null && this.firstName.equals(other.firstName));
        boolean lastNameEquals = (this.lastName== null && other.lastName == null)||
                (this.lastName != null && this.lastName.equals(other.lastName));
        boolean emailEquals = (this.email == null && other.email == null)||
                (this.email != null && this.email.equals(other.email));

        return firstNameEquals && lastNameEquals && emailEquals;
    }

    @Override

    public int hashCode(){
        return Objects.hash(firstName, lastName, email);
    }

}
