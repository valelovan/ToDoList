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
    public void setGroup(String group) {this.group = group;}

    /**
     * Title field getter.
     * @return ToDo's Title.
     */
    public String getTitle() {return title;}

    /**
     * Title field setter.
     * @param title New Title.
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * Description field getter.
     * @return ToDo's Description.
     */
     public String getDescription() {return description;}

    /**
     * Description field setter.
     * @param description New Description.
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * DateCreated field getter.
     * @return ToDo's DataCreated
     */
    public Date getDateCreated() {return dateCreated;}

    /**
     * DateCreated field setter.
     * @param dateCreated New DateCreated.
     */
    public void setDateCreated(Date dateCreated) {this.dateCreated = dateCreated;}

    /**
     * DateDue field getter.
     * @return ToDo's DateCreated.
     */
    public Date getDateDue() {return dateDue;}

    /**
     * DateDue field setter.
     * @param dateDue New DateDue.
     */
    public void setDateDue(Date dateDue) {this.dateDue = dateDue;}

    /**
     * RelatedToDos field getter.
     * @return List of related ToDos.
     */
    public List<ToDo> getRelatedToDos() {return relatedToDos;}

    /**
     * RelatedToDos field setter.
     * @param relatedToDos List of related ToDos.
     */
    public void setRelatedToDos(List<ToDo> relatedToDos) {this.relatedToDos = relatedToDos;}

}
