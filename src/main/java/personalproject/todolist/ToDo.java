package personalproject.todolist;

import java.util.Date;

public class ToDo {
    private int id;
    private String title;
    private String description;
    private Date dateCreated;
    private Date dateDue;

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
     * Title field getter.
     * @return ToDo's Title.
     */
    public String getTitle() {return title;}

    /**
     * Title field setter.
     * @param title New Title.
     */
    public void setString(String title) {this.title = title;}

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
}
