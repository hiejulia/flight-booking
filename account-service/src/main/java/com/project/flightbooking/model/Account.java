package src.main.java.com.project.flightbooking.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * This is the entity model for account.
 *
 * @author Hien Nguyen
 * @since 1.0
 * @version 1.0
 * Account - id, email, name,
 */
@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
public class Account implements Comparable<Account>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false,
            name = "id")
    @TableGenerator(initialValue = 1,
            name = "account_id_generator",
            pkColumnName = "table_name",
            pkColumnValue = "account",
            table = "id_generator",
            valueColumnName = "available_id")
    @GeneratedValue(generator = "account_id_generator",
            strategy = GenerationType.TABLE)
    protected long id;

    @Column(name = "email",
            nullable = false,
            unique = true)
    @NotNull
    protected String email;

    @Column(name = "first_name")
    @NotNull
    protected String firstName;

    @Column(name = "middle_name")
    protected String middleName;

    @Column(name = "last_name")
    @NotNull
    protected String lastName;

    @ElementCollection(fetch = FetchType.LAZY,
            targetClass = Address.class)
    @CollectionTable(foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            joinColumns = @JoinColumn(name = "account_id",
                    nullable = false,
                    referencedColumnName = "id"),
            name = "account_address")
    @MapKeyColumn(name = "type",
            nullable = false)
    protected Map<String, Address> addresses;

    @ElementCollection(fetch = FetchType.LAZY,
            targetClass = String.class)
    @CollectionTable(foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            joinColumns = @JoinColumn(name = "account_id",
                    nullable = false,
                    referencedColumnName = "id"),
            name = "account_contact")
    @MapKeyColumn(name = "type",
            nullable = false)
    @Column(name = "contact",
            nullable = false)
    protected Map<String, String> contacts;

    @CreatedBy
    @Column(name = "creator",
            nullable = false)
    @NotNull
    protected String creator;

    @CreatedDate
    @Column(name = "creation_date",
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    protected Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date",
            nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    protected Date lastModifiedDate;

    @Override
    public int compareTo(Account otherAccount) {
        return email.compareTo(otherAccount.email);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        return Objects.equals(this.email, other.email);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<String, Address> addresses) {
        this.addresses = addresses;
    }

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", addresses=" + addresses + ", contacts=" + contacts + ", creator=" + creator + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + '}';
    }

}