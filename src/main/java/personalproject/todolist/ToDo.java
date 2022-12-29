package personalproject.todolist;

import java.util.Date;
import java.util.List;

public class ToDo {
    private int id;
    private String group;
    private String title;
    private String description;
    private Date dateCreated;
    private Date dateDue;
    private List<ToDo> relatedToDos;
    private boolean isComplete;

    /**
     * ID field getter.
     * @return ToDo's ID.
     */
    public int getId() {return id;}

    /**
     * ID field setter.
     * @param id New ID.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Group field getter.
     * @return ToDo's Group.
     */
    public String getGroup() {return group;}

    /**
     * Group field setter.
     * @param group New Group.
     */
    public void setGroup(String group) {
        if (null == group)
            throw new IllegalArgumentException("Null group is not accepted.");
        this.group = group;
    }

    /**
     * Title field getter.
     * @return ToDo's Title.
     */
    public String getTitle() {return title;}

    /**
     * Title field setter.
     * @param title New Title.
     */
    public void setTitle(String title) {
        if (null == title)
            throw new IllegalArgumentException("Null title is not accepted.");
        this.title = title;
    }

    /**
     * Description field getter.
     * @return ToDo's Description.
     */
     public String getDescription() {return description;}

    /**
     * Description field setter.
     * @param description New Description.
     */
    public void setDescription(String description) {
        if (null == description)
            throw new IllegalArgumentException("Null description is not accepted.");
        this.description = description;
    }

    /**
     * DateCreated field getter.
     * @return ToDo's DataCreated
     */
    public Date getDateCreated() {return dateCreated;}

    /**
     * DateCreated field setter.
     * @param dateCreated New DateCreated.
     */
    public void setDateCreated(Date dateCreated) {
        if (null == dateCreated)
            throw new IllegalArgumentException("Null date created is not accepted.");
        this.dateCreated = dateCreated;
    }

    /**
     * DateDue field getter.
     * @return ToDo's DateCreated.
     */
    public Date getDateDue() {return dateDue;}

    /**
     * DateDue field setter.
     * @param dateDue New DateDue.
     */
    public void setDateDue(Date dateDue) {
        if (null == dateDue)
            throw new IllegalArgumentException("Null date due is not accepted.");
        this.dateDue = dateDue;
    }

    /**
     * RelatedToDos field getter.
     * @return List of related ToDos.
     */
    public List<ToDo> getRelatedToDos() {return relatedToDos;}

    /**
     * RelatedToDos field setter.
     * @param relatedToDos List of related ToDos.
     */
    public void setRelatedToDos(List<ToDo> relatedToDos) {
        if (null == relatedToDos)
            throw new IllegalArgumentException("Null ToDo list is not accepted.");
        if (relatedToDos.contains(null))
            throw new IllegalArgumentException("Null ToDo in the list is not accepted");
        this.relatedToDos = relatedToDos;
    }

    /**
     * Adds a ToDo to the related ToDos list.
     * @param todo ToDo to be added.
     */
    public void addRelatedToDo(ToDo todo) {
        if (null == todo)
            throw new IllegalArgumentException("Null ToDo is not accepted.");
        this.relatedToDos.add(todo);
    }

    /**
     * IsComplete field getter.
     * @return Boolean indicating status of IsComplete.
     */
    public boolean getIsComplete() {return isComplete;}

    /**
     * IsComplete field setter.
     * @param isComplete Boolean indicating new status of IsComplete.
     */
    public void setIsComplete(boolean isComplete) {this.isComplete = isComplete;}

    /**
     * Toggles IsComplete status from true to false and vice versa.
     */
    public void toggleIsComplete() {isComplete = !isComplete;}
}
