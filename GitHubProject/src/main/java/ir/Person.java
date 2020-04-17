
package ir;

public class Person {
 
    private String personId;
    private String name;
 
    //standard getters and setters
 
    @Override
    public String toString() {
        return String.format("Person{personId='%s', name='%s'}", 
            personId, name);
    }

    public void setPersonId(String id){
        this.personId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonId(){
        return personId;
    }

    public String getName() {
        return name;
    }
}
