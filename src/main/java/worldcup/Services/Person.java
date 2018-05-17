package worldcup.Services;

import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@Entry(objectClasses = { "person", "top" }, base="ou=someOu")
public class Person {
    @Id
    private Name dn;

    @Attribute(name="cn")
    @DnAttribute(value="cn", index=1)
    private String fullName;

    // No @Attribute annotation means this will be bound to the LDAP attribute
    // with the same value
    private String department;

    private String mail;

    @DnAttribute(value="ou", index=0)
    @Transient
    private String company;

    @Attribute(type = Attribute.Type.BINARY)
    private String thumbnailPhoto;

    @Transient
    private String someUnmappedField;


    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getThumbnailPhoto() {
        return thumbnailPhoto;
    }

    public void setThumbnailPhoto(String thumbnailPhoto) {
        this.thumbnailPhoto = thumbnailPhoto;
    }

    public String getSomeUnmappedField() {
        return someUnmappedField;
    }

    public void setSomeUnmappedField(String someUnmappedField) {
        this.someUnmappedField = someUnmappedField;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

