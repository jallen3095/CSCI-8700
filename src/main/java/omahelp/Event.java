package omahelp;

public class Event {

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("area")
  private String area = null;

  @JsonProperty("tags")
  @Valid
  private List<String> tags = null;

  @JsonProperty("date")
  private OffsetDateTime date = null;

  @JsonProperty("attendees")
  @Valid
  private List<User> attendees = null;

  @JsonProperty("organizers")
  @Valid
  private List<User> organizers = null;

  public Event id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Event name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Event description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Event location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Event area(String area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   * @return area
  **/
  @ApiModelProperty(value = "")

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public Event tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public Event addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<String>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
  **/
  @ApiModelProperty(value = "")

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Event date(OffsetDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getDate() {
    return date;
  }

  public void setDate(OffsetDateTime date) {
    this.date = date;
  }

  public Event attendees(List<User> attendees) {
    this.attendees = attendees;
    return this;
  }

  public Event addAttendeesItem(User attendeesItem) {
    if (this.attendees == null) {
      this.attendees = new ArrayList<User>();
    }
    this.attendees.add(attendeesItem);
    return this;
  }

  /**
   * Get attendees
   * @return attendees
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<User> getAttendees() {
    return attendees;
  }

  public void setAttendees(List<User> attendees) {
    this.attendees = attendees;
  }

  public Event organizers(List<User> organizers) {
    this.organizers = organizers;
    return this;
  }

  public Event addOrganizersItem(User organizersItem) {
    if (this.organizers == null) {
      this.organizers = new ArrayList<User>();
    }
    this.organizers.add(organizersItem);
    return this;
  }

  /**
   * Get organizers
   * @return organizers
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<User> getOrganizers() {
    return organizers;
  }

  public void setOrganizers(List<User> organizers) {
    this.organizers = organizers;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.id, event.id) &&
        Objects.equals(this.name, event.name) &&
        Objects.equals(this.description, event.description) &&
        Objects.equals(this.location, event.location) &&
        Objects.equals(this.area, event.area) &&
        Objects.equals(this.tags, event.tags) &&
        Objects.equals(this.date, event.date) &&
        Objects.equals(this.attendees, event.attendees) &&
        Objects.equals(this.organizers, event.organizers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, location, area, tags, date, attendees, organizers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    attendees: ").append(toIndentedString(attendees)).append("\n");
    sb.append("    organizers: ").append(toIndentedString(organizers)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}