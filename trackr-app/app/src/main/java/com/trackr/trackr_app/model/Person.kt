package com.trackr.trackr_app.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.util.UUID

/**
 * A Person who is the target for an Event.
 * The Person class is responsible for storing the first and last name of the person,
 * along with a description and tags associated with this person.
 */
@Entity(tableName = "person-table",
        // Establish foreign key relation
        foreignKeys = [
                ForeignKey(entity = User::class,
                        parentColumns = ["id"],
                        childColumns = ["user_id"],
                        onDelete = CASCADE)])
class Person(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: UUID,

        @Relation(
                parentColumn = "id",
                entityColumn = "user_id"
        )
        @ColumnInfo(name = "user_id")
        val user_id: UUID,

        @ColumnInfo(name = "first_name")
        var firstName: String,

        @ColumnInfo(name = "last_name")
        var lastName: String = "",

//        private val tags: HashSet<String> = HashSet()
) {
    /**
     * Return the lastName of this Person
     * @return the lastName of this Person
     */
    /**
     * Return a description of this person
     * @return the description of this person
     */
    /**
     * Return the tags that this Person has
     * @return the tags associated with this Person
     */
//    fun getTags(): Set<String> {
//        return tags
//    }

    /**
     * Return whether the person has the given tag
     * @return a boolean representing whether the person has the given tag or not
     */
//    fun hasTag(tag: String): Boolean {
//        return tags.contains(tag)
//    }

    /**
     * Add a tag to this person.
     * Return true if the tag was successfully added and false if this person already has this tag.
     * @return false if the person already has this tag, true otherwise
     */
//    fun addTag(tag: String): Boolean {
//        return tags.add(tag)
//    }

    /**
     * Remove the tag from this person.
     * Return true if successfully removed, return false if this person doesn't have that tag
     * @return false if the Person does not have the tag
     * otherwise return remove the tag and return true
     */
//    fun removeTag(tag: String): Boolean {
//        return tags.remove(tag)
//    }
}